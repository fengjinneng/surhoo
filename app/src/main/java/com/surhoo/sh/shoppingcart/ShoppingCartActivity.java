package com.surhoo.sh.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.goods.bean.GoodsBean;
import com.surhoo.sh.goods.view.impl.GoodsDetailActivity;
import com.surhoo.sh.order.OrderConfirmationActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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


    private boolean isDeleteStatu;

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

        toolbarLayoutTitle.setText("购物车");
        activityShoppingCartCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ObjectUtils.isEmpty(adapter) || ObjectUtils.isEmpty(adapter.getData())) {
                    return;
                }
                List<ShoppingCartBean.CarGoodsListBean> data = adapter.getData();

                if(is){
                    is = false;
                }else {
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
    public void initData() {

    }

    @Override
    public void requestData() {
        shoppingCartPresent.requestData();
    }



    private boolean is;

    @Override
    public void showList(List<ShoppingCartBean> list) {

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
                goodsListBean.setFlag(i);
                data.add(goodsListBean);

            }

            ShoppingCartBean.CarGoodsListBean carGoodsListBean3 = new ShoppingCartBean.CarGoodsListBean(3, "");
            carGoodsListBean3.setFlag(i);
            carGoodsListBean3.setGoodsPrice("0");
            carGoodsListBean3.setItemType(3);
            data.add(carGoodsListBean3);
        }

        adapter = new ShoppingCartAdapter(data);



        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (adapter.getItemViewType(position)==2) {
                    Intent i = new Intent(ShoppingCartActivity.this, GoodsDetailActivity.class);
                    i.putExtra("id", ((ShoppingCartBean.CarGoodsListBean) adapter.getData().get(position)).getGoodsId());
                    ActivityUtils.startActivity(i);
                }
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {

                    case R.id.item_shopping_cart2_addNum:
                        TextView addNum = (TextView) adapter.getViewByPosition(activityShoppingCartRecyclerview, position, R.id.item_shopping_cart2_num);
                        ShoppingCartBean.CarGoodsListBean addNumBean = (ShoppingCartBean.CarGoodsListBean) adapter.getData().get(position);

                        shoppingCartPresent.changeShoppingCarNum(true,
                                addNumBean.getId(), Integer.parseInt(addNum.getText().toString()) + 1, addNumBean, addNum);
                        break;

                    case R.id.item_shopping_cart2_reduceNum:

                        ShoppingCartBean.CarGoodsListBean reduceNumBean = (ShoppingCartBean.CarGoodsListBean) adapter.getData().get(position);

                        TextView reduceNum = (TextView) adapter.getViewByPosition(activityShoppingCartRecyclerview, position, R.id.item_shopping_cart2_num);

                        if (Integer.parseInt(reduceNum.getText().toString()) - 1 > 0) {

                            shoppingCartPresent.changeShoppingCarNum(false,
                                    reduceNumBean.getId(), Integer.parseInt(reduceNum.getText().toString()) - 1,
                                    reduceNumBean, reduceNum);

                        } else {
                            ToastUtils.showShort("最低数量为1");
                            return;
                        }

                        break;

                    case R.id.item_shopping_cart1_checkbox:
                        ShoppingCartBean.CarGoodsListBean carGoodsListBean = (ShoppingCartBean.CarGoodsListBean) adapter.getData().get(position);

                        List<ShoppingCartBean.CarGoodsListBean> adapterData = adapter.getData();

                        if (carGoodsListBean.isChecked()) {
                            int flag = carGoodsListBean.getFlag();
                            for (int i = 0; i < adapterData.size(); i++) {

                                if (flag == adapterData.get(i).getFlag()) {
                                    adapterData.get(i).setChecked(false);
                                }

                            }

                            is = true;
                            activityShoppingCartCheckAll.setChecked(false);

                        } else {
                            int flag = carGoodsListBean.getFlag();
                            for (int i = 0; i < adapterData.size(); i++) {

                                if (flag == adapterData.get(i).getFlag()) {
                                    adapterData.get(i).setChecked(true);
                                }
                            }
                        }

                        totalPrice(adapterData);

                        adapter.notifyDataSetChanged();

                        break;

                    case R.id.item_shopping_cart2_checkbox:
                        ShoppingCartBean.CarGoodsListBean carGoodsListBean1 = (ShoppingCartBean.CarGoodsListBean) adapter.getData().get(position);
                        if (carGoodsListBean1.isChecked()) {
                            carGoodsListBean1.setChecked(false);

                            for (int i = 0; i < adapter.getData().size(); i++) {
                                if (carGoodsListBean1.getFlag()== ((ShoppingCartBean.CarGoodsListBean) adapter.getData().get(i)).getFlag()) {
                                    ((ShoppingCartBean.CarGoodsListBean) adapter.getData().get(i)).setChecked(false);
                                    adapter.notifyItemChanged(i);
                                    break;
                                }
                            }
                            is = true;
                            activityShoppingCartCheckAll.setChecked(false);

                        } else {
                            carGoodsListBean1.setChecked(true);
                        }

                        boolean shopAllCheck = true;

                        for (int i = 0; i < adapter.getData().size(); i++) {
                            ShoppingCartBean.CarGoodsListBean bean = (ShoppingCartBean.CarGoodsListBean) adapter.getData().get(i);

                            if (carGoodsListBean1.getFlag() == bean.getFlag() && bean.getItemType() == 2) {
                                if (!bean.isChecked()) {
                                    shopAllCheck = false;
                                    break;
                                }
                            }
                        }
                        if (shopAllCheck) {
                            for (int i = 0; i < adapter.getData().size(); i++) {
                                ShoppingCartBean.CarGoodsListBean carGoodsListBean2 = (ShoppingCartBean.CarGoodsListBean) adapter.getData().get(i);
                                if (carGoodsListBean1.getFlag() == carGoodsListBean2.getFlag()) {
                                    carGoodsListBean2.setChecked(true);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }

                        List<ShoppingCartBean.CarGoodsListBean> adapterData2 = adapter.getData();
                        totalPrice(adapterData2);
                        break;


                }
            }
        });

        activityShoppingCartRecyclerview.setLayoutManager(new

                LinearLayoutManager(this));
        activityShoppingCartRecyclerview.setAdapter(adapter);
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
        activityShoppingCartTotalPrice.setText("¥ "+String.valueOf(total));

    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }


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
                deleteGoods.clear();

                for (int i = 0; i < adapter.getData().size(); i++) {

                    if (adapter.getData().get(i).getItemType() == 2 && adapter.getData().get(i).isChecked()) {
                        deleteGoods.add(adapter.getData().get(i));
                    }

                }

                StringBuffer idList = new StringBuffer();
                for (int i = 0; i < deleteGoods.size(); i++) {
                    if (i == deleteGoods.size() - 1) {
                        idList.append(deleteGoods.get(i).getId());
                    } else {
                        idList.append(deleteGoods.get(i).getId() + ",");
                    }
                }
                shoppingCartPresent.deleteShoppingCart(idList.toString());

                break;
            case R.id.activity_shopping_cart_settlement:
                buyGoods.clear();
                List<ShoppingCartBean.CarGoodsListBean> data =adapter.getData();
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

                Intent intent = new Intent(this,OrderConfirmationActivity.class);
                intent.putExtra("data",buyGoods);
                intent.putExtra("shopName",buyGoods.get(0).getShopName());
                intent.putExtra("goodsTotalPrice",activityShoppingCartTotalPrice.getText().toString());

                ActivityUtils.startActivity(intent);

                break;
        }
    }

    //勾选的需要购买的商品列表
    ArrayList<ShoppingCartBean.CarGoodsListBean> buyGoods = new ArrayList<>();
    ArrayList<ShoppingCartBean.CarGoodsListBean> deleteGoods = new ArrayList<>();

    @Override
    public void changShoppingCarNum(boolean isAdd, int id, int goodsNum, ShoppingCartBean.CarGoodsListBean bean, TextView num) {

        if (isAdd) {
            bean.setGoodsNum(Integer.parseInt(num.getText().toString()) + 1);
            num.setText(String.valueOf(Integer.parseInt(num.getText().toString()) + 1));
        } else {
            bean.setGoodsNum(Integer.parseInt(num.getText().toString()) - 1);
            num.setText(String.valueOf(Integer.parseInt(num.getText().toString()) - 1));
        }

        totalPrice(adapter.getData());


    }

    @Override
    public void deleteShoppingCart() {

        List<ShoppingCartBean.CarGoodsListBean> data = adapter.getData();

        data.removeAll(deleteGoods);

        adapter.notifyDataSetChanged();
    }
}
