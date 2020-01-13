package com.surhoo.sh.shoppingcart;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.bean.shoppingcar.request.ChangeCarNumberBean;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;
import com.surhoo.sh.common.util.ClickUtil;
import com.surhoo.sh.common.util.NumberUtil;
import com.surhoo.sh.goods.view.impl.GoodsDetailActivity;
import com.surhoo.sh.order.OrderConfirmationActivity;
import com.surhoo.sh.shoppingcart.shoppingcart2.ShoppingCartBean2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShoppingCartActivity extends BaseActivity implements ShoppingCartView {

    @BindView(R.id.activity_shopping_cart_recyclerview)
    RecyclerView activityShoppingCartRecyclerview;
    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_shopping_cart_total_price)
    TextView activityShoppingCartTotalPrice;
    @BindView(R.id.activity_shopping_cart_checkAll)
    CheckBox activityShoppingCartCheckAll;
    @BindView(R.id.activity_shopping_cart_edit)
    TextView activityShoppingCartEdit;
    @BindView(R.id.activity_shopping_cart_delete)
    TextView activityShoppingCartDelete;
    @BindView(R.id.activity_shopping_cart_settlement)
    TextView activityShoppingCartSettlement;
    private ShoppingCartPresent shoppingCartPresent;
    private ShoppingCartAdapter adapter;
    private View loadingView;
    private View loadingEmptyView;
    private View loadingErrorView;
    private boolean isDeleteStatu;

    private double totalPrice;

    private boolean isAdd;
    private TextView checkedText;
    private ShoppingCartBean.CarGoodsListBean checkedBean;

    private static final String CHANGCARNUMBER = "changeCarNumber";
    private static final String DELETECART = "deleteCart";


    //勾选的需要购买的商品列表
    ArrayList<ShoppingCartBean.CarGoodsListBean> buyGoods = new ArrayList<>();
    ArrayList<ShoppingCartBean.CarGoodsListBean> deleteGoods = new ArrayList<>();


    //避免点击一项后全选的状态改变，走了setOnCheckedChangeListener
    private boolean isItemClick;

    @Override
    public int getContentView() {
        return R.layout.activity_shopping_cart;
    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void initView() {

        shoppingCartPresent = new ShoppingCartPresentImpl();
        shoppingCartPresent.bindView(this, this);

        initLoadingView();

        toolbarLayoutTitle.setText("购物车");
        activityShoppingCartCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ObjectUtils.isEmpty(adapter) || ObjectUtils.isEmpty(adapter.getData())) {
                    return;
                }
                List<ShoppingCartBean.CarGoodsListBean> data = adapter.getData();


                if (isItemClick) {
                    isItemClick = false;
                } else {
                    for (int i = 0; i < data.size(); i++) {
                        data.get(i).setChecked(isChecked);
                    }
                    adapter.notifyDataSetChanged();
                    totalPrice(data);
                }
            }
        });

    }


    @Override
    public void onRecevieMessage(EventBusMessageBean bean) {

        super.onRecevieMessage(bean);

        switch (bean.getCode()) {
            case EventBusMessageBean.Order.payOrderSuccess:
                totalPrice = 0;
                activityShoppingCartTotalPrice.setText("0.00");
                adapter.setEmptyView(loadingView);
                requestData();
                break;
        }
    }

    private void initLoadingView() {
        loadingView = LayoutInflater.from(this).inflate(R.layout.load_data_loading_view, null);
        loadingEmptyView = LayoutInflater.from(this).inflate(R.layout.load_data_empty_view, null);
        ImageView emptyImg = (ImageView) loadingEmptyView.findViewById(R.id.load_data_empty_view_img);
        emptyImg.setImageDrawable(getResources().getDrawable(R.mipmap.search_empty_img));
        TextView emptyText = (TextView) loadingEmptyView.findViewById(R.id.load_data_empty_view_content);
        emptyText.setText("您的购物车是空的哦！");

        loadingErrorView = LayoutInflater.from(this).inflate(R.layout.load_data_error_view, null);

        TextView errorText = (TextView) loadingErrorView.findViewById(R.id.load_data_error_view_retry);
        errorText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) {
                    adapter.setEmptyView(loadingView);
                    requestData();
                }
            }
        });
    }

    @Override
    public void initData() {
        adapter = new ShoppingCartAdapter(null);
        activityShoppingCartRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        activityShoppingCartRecyclerview.setAdapter(adapter);
        adapter.setEmptyView(loadingView);

    }
    @Override
    public void requestData() {
        shoppingCartPresent.requestData();
    }

    @Override
    public void setNoPageEmptyView() {
        activityShoppingCartCheckAll.setChecked(false);
        activityShoppingCartTotalPrice.setText("0.00");
        adapter.setNewData(null);
        adapter.setEmptyView(loadingEmptyView);
        activityShoppingCartRecyclerview.setBackgroundColor(getResources().getColor(R.color.white));
    }

    @Override
    public void setNoPageErrorView() {
        adapter.setNewData(null);
        adapter.setEmptyView(loadingErrorView);
    }


    private ChangeCarNumberBean changeCarNumberBean = new ChangeCarNumberBean();

    @Override
    public void showNoPageList(String requestTag, List<ShoppingCartBean> list) {

        List<ShoppingCartBean.CarGoodsListBean> data = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            ShoppingCartBean.CarGoodsListBean carGoodsListBean = new ShoppingCartBean.CarGoodsListBean(1, list.get(i).getShopName());
            carGoodsListBean.setFlag(i);
            carGoodsListBean.setGoodsPrice("0");
            data.add(carGoodsListBean);

            for (int j = 0; j < list.get(i).getCarGoodsList().size(); j++) {

                ShoppingCartBean.CarGoodsListBean goodsListBean = new ShoppingCartBean.CarGoodsListBean();
                goodsListBean.setItemType(2);
                goodsListBean.setGoodsName(list.get(i).getCarGoodsList().get(j).getGoodsName());
                goodsListBean.setGoodsPrice(list.get(i).getCarGoodsList().get(j).getGoodsPrice());
                goodsListBean.setGoodsImg(list.get(i).getCarGoodsList().get(j).getGoodsImg());
                goodsListBean.setSkuName(list.get(i).getCarGoodsList().get(j).getSkuName());
                goodsListBean.setGoodsNum(list.get(i).getCarGoodsList().get(j).getGoodsNum());
                goodsListBean.setId(list.get(i).getCarGoodsList().get(j).getId());
                goodsListBean.setGoodsId(list.get(i).getCarGoodsList().get(j).getGoodsId());
                goodsListBean.setShopName(list.get(i).getShopName());
                goodsListBean.setGoodsMarketPrice(list.get(i).getCarGoodsList().get(j).getGoodsMarketPrice());
                goodsListBean.setSkuId(list.get(i).getCarGoodsList().get(j).getSkuId());
                goodsListBean.setFlag(i);
                data.add(goodsListBean);

            }

            ShoppingCartBean.CarGoodsListBean carGoodsListBean3 = new ShoppingCartBean.CarGoodsListBean(3, "");
            carGoodsListBean3.setFlag(i);
            carGoodsListBean3.setGoodsPrice("0");
            carGoodsListBean3.setItemType(3);
            data.add(carGoodsListBean3);
        }

        adapter.setNewData(data);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (ClickUtil.isFastClick()) {
                    if (adapter.getItemViewType(position) == 2) {
                        Intent i = new Intent(ShoppingCartActivity.this, GoodsDetailActivity.class);
                        i.putExtra("id", ((ShoppingCartBean.CarGoodsListBean) adapter.getData().get(position)).getGoodsId());
                        ActivityUtils.startActivity(i);
                    }
                }
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                List<ShoppingCartBean.CarGoodsListBean> adapterData = adapter.getData();

                switch (view.getId()) {
                    case R.id.item_shopping_cart2_addNum:
                        TextView addNum = (TextView) adapter.getViewByPosition(activityShoppingCartRecyclerview, position, R.id.item_shopping_cart2_num);
                        checkedText = addNum;
                        ShoppingCartBean.CarGoodsListBean addNumBean = (ShoppingCartBean.CarGoodsListBean) adapter.getData().get(position);
                        checkedBean = addNumBean;
                        isAdd = true;
                        changeCarNumberBean.setId(addNumBean.getId());
                        changeCarNumberBean.setGoodsNum(addNumBean.getGoodsNum());

                        shoppingCartPresent.changeShoppingCarNum(CHANGCARNUMBER, changeCarNumberBean);
                        break;

                    case R.id.item_shopping_cart2_reduceNum:

                        ShoppingCartBean.CarGoodsListBean reduceNumBean = (ShoppingCartBean.CarGoodsListBean) adapter.getData().get(position);

                        TextView reduceNum = (TextView) adapter.getViewByPosition(activityShoppingCartRecyclerview, position, R.id.item_shopping_cart2_num);

                        checkedText = reduceNum;
                        checkedBean = reduceNumBean;

                        changeCarNumberBean.setId(reduceNumBean.getId());
                        changeCarNumberBean.setGoodsNum(reduceNumBean.getGoodsNum());

                        isAdd = false;
                        if (Integer.parseInt(reduceNum.getText().toString()) - 1 > 0) {

                            shoppingCartPresent.changeShoppingCarNum(CHANGCARNUMBER, changeCarNumberBean);

                        } else {
                            ToastUtils.showShort("最低数量为1");
                            return;
                        }

                        break;

                    case R.id.item_shopping_cart1_checkbox:
                        ShoppingCartBean.CarGoodsListBean carGoodsListBean = (ShoppingCartBean.CarGoodsListBean) adapter.getData().get(position);

                        CheckBox checkBox1 = (CheckBox) adapter.getViewByPosition(activityShoppingCartRecyclerview, position, R.id.item_shopping_cart1_checkbox);

                        carGoodsListBean.setChecked(checkBox1.isChecked());

                        //设置整个店铺的选择状态
                        for (int i = 0; i < adapterData.size(); i++) {
                            if (carGoodsListBean.getFlag() == adapterData.get(i).getFlag()) {
                                adapterData.get(i).setChecked(checkBox1.isChecked());
                            }
                        }

                        if (isCheckAll(adapterData)) {
                            activityShoppingCartCheckAll.setChecked(true);
                        } else {
                            activityShoppingCartCheckAll.setChecked(false);
                        }

                        totalPrice(adapterData);
                        adapter.notifyDataSetChanged();
                        break;

                    case R.id.item_shopping_cart2_checkbox:
                        ShoppingCartBean.CarGoodsListBean carGoodsListBean1 = (ShoppingCartBean.CarGoodsListBean) adapter.getData().get(position);
                        CheckBox checkBox2 = (CheckBox) adapter.getViewByPosition(activityShoppingCartRecyclerview, position, R.id.item_shopping_cart2_checkbox);
                        carGoodsListBean1.setChecked(checkBox2.isChecked());

                        boolean shopCheckAll = true;

                        for (int i = 0; i < adapterData.size(); i++) {
                            if (carGoodsListBean1.getFlag() == adapterData.get(i).getFlag() && adapterData.get(i).getItemType() == 2) {
                                if (adapterData.get(i).isChecked()) {

                                } else {
                                    shopCheckAll = false;
                                }
                            }
                        }

                        if (shopCheckAll) {
                            for (int i = 0; i < adapterData.size(); i++) {
                                if (carGoodsListBean1.getFlag() == adapterData.get(i).getFlag()) {
                                    adapterData.get(i).setChecked(true);
                                }
                            }

                        } else {
                            for (int i = 0; i < adapterData.size(); i++) {
                                if (carGoodsListBean1.getFlag() == adapterData.get(i).getFlag()) {
                                    adapterData.get(i).setChecked(false);
                                    break;
                                }
                            }
                        }

                        if (isCheckAll(adapterData)) {
                            activityShoppingCartCheckAll.setChecked(true);
                        } else {
                            activityShoppingCartCheckAll.setChecked(false);
                        }

                        adapter.notifyDataSetChanged();
                        totalPrice(adapterData);

                        break;
                }
            }
        });
    }

    private boolean isCheckAll(List<ShoppingCartBean.CarGoodsListBean> adapterData) {
        boolean checkAll = true;
        for (int i = 0; i < adapterData.size(); i++) {
            if (adapterData.get(i).isChecked()) {

            } else {
                checkAll = false;
            }
        }
        return checkAll;
    }


    private void totalPrice(List<ShoppingCartBean.CarGoodsListBean> data) {

        double total = 0;
        for (int i = 0; i < data.size(); i++) {
            if (ObjectUtils.isEmpty(data)) {

            } else {
                if (data.get(i).isChecked()) {
                    if (data.get(i).getItemType() == 2) {
                        total += Double.parseDouble(data.get(i).getGoodsPrice()) * data.get(i).getGoodsNum();
                    }
                }
            }
        }
        totalPrice = total;
        activityShoppingCartTotalPrice.setText("¥ " + NumberUtil.getTwoPointString(total));

    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }

    private ArrayList<ShoppingCartBean.CarGoodsListBean> needRemove = new ArrayList<>();


    @OnClick({R.id.toolbar_layout_back, R.id.activity_shopping_cart_edit, R.id.activity_shopping_cart_delete,
            R.id.activity_shopping_cart_settlement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_layout_back:
                finish();
                break;
            case R.id.activity_shopping_cart_edit:

                if (isDeleteStatu) {
                    activityShoppingCartDelete.setVisibility(View.GONE);
                    activityShoppingCartSettlement.setVisibility(View.VISIBLE);
                    isDeleteStatu = false;
                    activityShoppingCartEdit.setText("编辑");
                } else {
                    activityShoppingCartDelete.setVisibility(View.VISIBLE);
                    activityShoppingCartSettlement.setVisibility(View.GONE);
                    isDeleteStatu = true;
                    activityShoppingCartEdit.setText("完成");
                }

                break;
            case R.id.activity_shopping_cart_delete:
                isItemClick =true;
                deleteGoods.clear();

                for (int i = 0; i < adapter.getData().size(); i++) {

                    if (adapter.getData().get(i).getItemType() == 2 && adapter.getData().get(i).isChecked()) {
                        deleteGoods.add(adapter.getData().get(i));
                    }
                    if (adapter.getData().get(i).isChecked()) {
                        needRemove.add(adapter.getData().get(i));
                    }
                }

                int[] a = new int[deleteGoods.size()];

                for (int i = 0; i < deleteGoods.size(); i++) {

                    a[i] = deleteGoods.get(i).getId();

                }

                shoppingCartPresent.deleteShoppingCart(DELETECART, "{\"idList\":" + Arrays.toString(a) + "}");

                break;
            case R.id.activity_shopping_cart_settlement:
                isItemClick =true;
                buyGoods.clear();
                List<ShoppingCartBean.CarGoodsListBean> data = adapter.getData();
                //只能同时提交一个flag的数据。不能跨店铺提交
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getItemType() == 2 && data.get(i).isChecked() == true) {
                        buyGoods.add(data.get(i));
                    }
                }

                if (buyGoods.size() == 0) {
                    ToastUtils.showShort("请选择需要结算的商品。");
                    return;
                }
                int tempFlag = buyGoods.get(0).getFlag();
                for (int i = 0; i < buyGoods.size(); i++) {
                    if (tempFlag != buyGoods.get(i).getFlag()) {
                        ToastUtils.showShort("暂不支持跨店铺支付。");
                        return;
                    }
                }

                Intent intent = new Intent(this, OrderConfirmationActivity.class);
                intent.putExtra("data", buyGoods);

                ActivityUtils.startActivity(intent);

                break;
        }
    }


    @Override
    public void showStringData(String requestTag, String s) {
        if (StringUtils.equals(CHANGCARNUMBER, requestTag)) {
            if (isAdd) {
                checkedBean.setGoodsNum(Integer.parseInt(checkedText.getText().toString()) + 1);
                checkedText.setText(String.valueOf(Integer.parseInt(checkedText.getText().toString()) + 1));
            } else {
                checkedBean.setGoodsNum(Integer.parseInt(checkedText.getText().toString()) - 1);
                checkedText.setText(String.valueOf(Integer.parseInt(checkedText.getText().toString()) - 1));
            }

            totalPrice(adapter.getData());
        }

        if (StringUtils.equals(DELETECART, requestTag)) {
            requestData();
        }
    }
}
