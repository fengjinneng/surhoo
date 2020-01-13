package com.surhoo.sh.shoppingcart.shoppingcart2;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.bean.shoppingcar.request.ChangeCarNumberBean;
import com.surhoo.sh.common.util.ClickUtil;
import com.surhoo.sh.common.util.NumberUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class ShoppingCart2Activity extends BaseActivity implements ShoppingCart2View {

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

    private ShoppingCartPresent2 shoppingCartPresent;
    private ShoppingCart2Adapter adapter;

    private View loadingView;
    private View loadingEmptyView;
    private View loadingErrorView;

    //避免点击一项后全选的状态改变，走了setOnCheckedChangeListener
    private boolean isItemClick ;

    @Override
    public int getContentView() {
        return R.layout.activity_shopping_cart2;
    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void initView() {

        toolbarLayoutTitle.setText("购物车");
        shoppingCartPresent = new ShoppingCartPresentImpl2();
        shoppingCartPresent.bindView(this, this);

        initLoadingView();

        activityShoppingCartCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isItemClick){
                    isItemClick = false;
                    if(isChecked){

                    }else {
                        return;
                    }
                }

                List<ShoppingCartBean2.CarGoodsListBean> adapterData = adapter.getData();
                for (int i = 0; i < adapter.getData().size(); i++) {
                    adapterData.get(i).setCheck(isChecked);
                    adapterData.get(i).setHeadCheck(isChecked);
                }
                adapter.notifyDataSetChanged();
                totalPrice(adapter.getData());
            }
        });

    }

    private void initLoadingView() {
        loadingView = LayoutInflater.from(this).inflate(R.layout.load_data_loading_view, null);
        loadingEmptyView = LayoutInflater.from(this).inflate(R.layout.load_data_empty_view, null);
        ImageView emptyImg = (ImageView) loadingEmptyView.findViewById(R.id.load_data_empty_view_img);
        emptyImg.setImageDrawable(getResources().getDrawable(R.mipmap.search_empty_img));
        TextView emptyText = (TextView) loadingEmptyView.findViewById(R.id.load_data_empty_view_content);
        emptyText.setText("抱歉,没有找到相关内容");

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


    private static final String ADDCARNUMBER = "addNumber";
    private static final String REDUCECARNUMBER = "reduceNumber";
    private static final String DELETECART = "deleteCart";

    @Override
    public void initData() {

        adapter = new ShoppingCart2Adapter(R.layout.item_shopping_cart, null);
        activityShoppingCartRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        activityShoppingCartRecyclerview.setAdapter(adapter);
        adapter.setEmptyView(loadingView);


        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                ShoppingCartBean2.CarGoodsListBean bean = (ShoppingCartBean2.CarGoodsListBean) adapter.getData().get(position);
                checkPosition = position;
                switch (view.getId()) {
                    case R.id.item_shopping_cart_addNum:
                        ChangeCarNumberBean changeCarNumberBean = new ChangeCarNumberBean();
                        changeCarNumberBean.setGoodsNum(bean.getGoodsNum() + 1);
                        changeCarNumberBean.setId(bean.getId());
                        shoppingCartPresent.changeShoppingCarNum(ADDCARNUMBER, changeCarNumberBean);

                        break;
                    case R.id.item_shopping_cart_reduceNum:
                        ChangeCarNumberBean changeCarNumberBean2 = new ChangeCarNumberBean();
                        changeCarNumberBean2.setGoodsNum(bean.getGoodsNum() - 1);
                        changeCarNumberBean2.setId(bean.getId());
                        shoppingCartPresent.changeShoppingCarNum(REDUCECARNUMBER, changeCarNumberBean2);
                        break;

                    case R.id.item_shopping_cart_checkBox:

                        isItemClick = true;

                        CheckBox checkBox = (CheckBox) adapter.getViewByPosition(activityShoppingCartRecyclerview, position, R.id.item_shopping_cart_checkBox);
                        bean.setCheck(checkBox.isChecked());

                        boolean checkAll = true;
                        for (int i = 0; i < adapter.getData().size(); i++) {
                            ShoppingCartBean2.CarGoodsListBean carGoodsListBean = (ShoppingCartBean2.CarGoodsListBean) adapter.getData().get(i);
                            if (bean.getTag() == carGoodsListBean.getTag()) {
                                if (carGoodsListBean.isCheck()) {

                                } else {
                                    checkAll = false;

                                }
                            }
                        }
                        if (checkAll) {
                            //设置店铺全选
                            for (int i = 0; i < adapter.getData().size(); i++) {
                                ShoppingCartBean2.CarGoodsListBean carGoodsListBean = (ShoppingCartBean2.CarGoodsListBean) adapter.getData().get(i);
                                if (carGoodsListBean.isHead() && carGoodsListBean.getTag() == bean.getTag()) {
                                    carGoodsListBean.setHeadCheck(true);
                                }
                            }
                        } else {
                            //设置店铺没有选择
                            for (int i = 0; i < adapter.getData().size(); i++) {
                                ShoppingCartBean2.CarGoodsListBean carGoodsListBean = (ShoppingCartBean2.CarGoodsListBean) adapter.getData().get(i);
                                if (carGoodsListBean.isHead() && carGoodsListBean.getTag() == bean.getTag()) {
                                    carGoodsListBean.setHeadCheck(false);
                                }
                            }
                        }

                        if (isChoiceAll()) {
                            activityShoppingCartCheckAll.setChecked(true);
                        }else {
                            activityShoppingCartCheckAll.setChecked(false);
                        }

                        adapter.notifyDataSetChanged();
                        totalPrice(adapter.getData());

                        break;

                    case R.id.item_shopping_cart_shop_checkBox:

                        isItemClick = true;

                        CheckBox shopCheckBox = (CheckBox) adapter.getViewByPosition(activityShoppingCartRecyclerview, position, R.id.item_shopping_cart_shop_checkBox);

                        for (int i = 0; i < adapter.getData().size(); i++) {
                            ShoppingCartBean2.CarGoodsListBean carGoodsListBean = (ShoppingCartBean2.CarGoodsListBean) adapter.getData().get(i);


                            if (bean.getTag() == carGoodsListBean.getTag()) {
                                carGoodsListBean.setHeadCheck(shopCheckBox.isChecked());
                                carGoodsListBean.setCheck(shopCheckBox.isChecked());
                            }
                        }

                        if (isChoiceAll()) {
                            activityShoppingCartCheckAll.setChecked(true);
                        }else {
                            activityShoppingCartCheckAll.setChecked(false);
                        }

                        adapter.notifyDataSetChanged();
                        totalPrice(adapter.getData());

                        break;
                }
            }
        });
    }

    private boolean isChoiceAll() {
        List<ShoppingCartBean2.CarGoodsListBean> adapterData = adapter.getData();
        for (int i = 0; i < adapter.getData().size(); i++) {
            if (adapterData.get(i).isCheck()) {

            } else {
                return false;
            }
        }
        return true;
    }

    private boolean isDeleteStatu;

    List<ShoppingCartBean2.CarGoodsListBean> deleteGoods;

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

                deleteGoods = new ArrayList<>();

                for (int i = 0; i < adapter.getData().size(); i++) {

                    if (adapter.getData().get(i).isCheck()) {
                        deleteGoods.add(adapter.getData().get(i));
                    }
                }


                int[] a = new int[deleteGoods.size()];

                for (int i = 0; i < deleteGoods.size(); i++) {

                    a[i] = deleteGoods.get(i).getId();
                }

                shoppingCartPresent.deleteShoppingCart(DELETECART, "{\"idList\":" + Arrays.toString(a) + "}");

                break;
            case R.id.activity_shopping_cart_settlement:
//                buyGoods.clear();
//                List<ShoppingCartBean.CarGoodsListBean> data = adapter.getData();
//                //只能同时提交一个flag的数据。不能跨店铺提交
//                for (int i = 0; i < data.size(); i++) {
//                    if (data.get(i).getItemType() == 2 && data.get(i).isChecked() == true) {
//                        buyGoods.add(data.get(i));
//                    }
//                }
//
//                if (buyGoods.size() == 0) {
//                    ToastUtils.showShort("请选择需要结算的商品。");
//                    return;
//                }
//                int tempFlag = buyGoods.get(0).getFlag();
//                for (int i = 0; i < buyGoods.size(); i++) {
//                    if (tempFlag != buyGoods.get(i).getFlag()) {
//                        ToastUtils.showShort("暂不支持跨店铺支付。");
//                        return;
//                    }
//                }
//
//                Intent intent = new Intent(this, OrderConfirmationActivity.class);
//                intent.putExtra("data", buyGoods);
//
//                ActivityUtils.startActivity(intent);

                break;
        }
    }


    private void totalPrice(List<ShoppingCartBean2.CarGoodsListBean> data) {

        double total = 0;
        for (int i = 0; i < data.size(); i++) {
            if (ObjectUtils.isEmpty(data)) {

            } else {
                if (data.get(i).isCheck()&&!data.get(i).isDelete()) {
                    total += Double.parseDouble(data.get(i).getGoodsPrice()) * data.get(i).getGoodsNum();
                }
            }
        }
        activityShoppingCartTotalPrice.setText("¥ " + NumberUtil.getTwoPointString(total));

    }

    private int checkPosition;

    @Override
    public void requestData() {

        shoppingCartPresent.requestData();

    }

    @Override
    public void setNoPageEmptyView() {
        adapter.setNewData(null);
        adapter.setEmptyView(loadingEmptyView);
    }

    @Override
    public void setNoPageErrorView() {
        adapter.setNewData(null);
        adapter.setEmptyView(loadingErrorView);
    }

    @Override
    public void showNoPageList(String requestTag, List<ShoppingCartBean2> list) {

        List<ShoppingCartBean2.CarGoodsListBean> temp = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            for (int j = 0; j < list.get(i).getCarGoodsList().size(); j++) {

                if (list.get(i).getCarGoodsList().size() == 1) {
                    list.get(i).getCarGoodsList().get(j).setHead(true);
                    list.get(i).getCarGoodsList().get(j).setFoot(true);
                    list.get(i).getCarGoodsList().get(j).setBody(true);
                } else {
                    if (j == 0) {
                        list.get(i).getCarGoodsList().get(j).setHead(true);
                    } else if (j == list.get(i).getCarGoodsList().size() - 1) {
                        list.get(i).getCarGoodsList().get(j).setFoot(true);
                    } else {
                        list.get(i).getCarGoodsList().get(j).setBody(true);
                    }
                }
                list.get(i).getCarGoodsList().get(j).setTag(i);
                temp.add(list.get(i).getCarGoodsList().get(j));
            }
        }

        adapter.setNewData(temp);

    }

    @Override
    public void showStringData(String requestTag, String s) {

        if (StringUtils.equals(ADDCARNUMBER, requestTag)) {
            adapter.getData().get(checkPosition).setGoodsNum(adapter.getData().get(checkPosition).getGoodsNum() + 1);
            adapter.notifyItemChanged(checkPosition);
        }

        if (StringUtils.equals(REDUCECARNUMBER, requestTag)) {
            adapter.getData().get(checkPosition).setGoodsNum(adapter.getData().get(checkPosition).getGoodsNum() - 1);
            adapter.notifyItemChanged(checkPosition);
        }

        if (StringUtils.equals(DELETECART, requestTag)) {

            for (int i = 0; i < deleteGoods.size(); i++) {

                adapter.remove(deleteGoods.get(i).getPosition());

            }

        }

    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }

}
