package com.surhoo.sh.shoppingcart;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;

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
    @BindView(R.id.activity_shopping_checkAll)
    CheckBox activityShoppingCheckAll;
    @BindView(R.id.activity_shopping_cart_edit_layout)
    ConstraintLayout activityShoppingCartEditLayout;


    private ShoppingCartPresent shoppingCartPresent;
    private ShoppingCartAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        ButterKnife.bind(this);


        initView();

        shoppingCartPresent = new ShoppingCartPresentImpl();
        shoppingCartPresent.bindView(this, this);
        requestData();

    }

    @Override
    public void initView() {
        toolbarLayoutTitle.setText("购物车");
        activityShoppingCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ObjectUtils.isEmpty(adapter) || ObjectUtils.isEmpty(adapter.getData())) {
                    return;
                }
                List<ShoppingCartBean> data = adapter.getData();
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).setChecked(isChecked);
                }
                adapter.notifyDataSetChanged();
                totalPrice(data);
            }
        });

    }

    @Override
    public void requestData() {
        shoppingCartPresent.requestData();
    }

    @OnClick(R.id.toolbar_layout_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showList(List<SBBBB> list) {

        activityShoppingCartEditLayout.setVisibility(View.VISIBLE);

        List<ShoppingCartBean> data = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            data.add(new ShoppingCartBean(1, i, list.get(i).getShopName(), null));


            for (int j = 0; j < list.get(i).getCarGoodsList().size(); j++) {

                ShoppingCartBean.CarGoodsListBean goodsListBean = new ShoppingCartBean.CarGoodsListBean();
                goodsListBean.setGoodsName(list.get(i).getCarGoodsList().get(j).getGoodsName());
                goodsListBean.setGoodsPrice(list.get(i).getCarGoodsList().get(j).getGoodsPrice());
                goodsListBean.setGoodsImg(list.get(i).getCarGoodsList().get(j).getGoodsImg());
                data.add(new ShoppingCartBean(2, i, list.get(i).getCarGoodsList().get(j).getGoodsName(), goodsListBean));

            }
            data.add(new ShoppingCartBean(3, i, "DSAAS", null));
        }


        adapter = new ShoppingCartAdapter(data);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewType = adapter.getItemViewType(position);
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {

                    case R.id.item_shopping_cart1_shopname:

                        break;

                    case R.id.item_shopping_cart2_shopname:
                        break;

                    case R.id.item_shopping_cart1_checkbox:
                        ShoppingCartBean shoppingCartBean1 = (ShoppingCartBean) adapter.getData().get(position);
                        List<ShoppingCartBean> adapterData = adapter.getData();

                        if (shoppingCartBean1.isChecked()) {
                            for (int i = 0; i < adapterData.size(); i++) {
                                if (shoppingCartBean1.getShopId() == adapterData.get(i).getShopId()) {
                                    adapterData.get(i).setChecked(false);
                                }
                            }
                        } else {
                            for (int i = 0; i < adapterData.size(); i++) {
                                if (shoppingCartBean1.getShopId() == adapterData.get(i).getShopId()) {
                                    adapterData.get(i).setChecked(true);
                                }
                            }
                        }

                        totalPrice(adapterData);


                        adapter.notifyDataSetChanged();

                        break;

                    case R.id.item_shopping_cart2_checkbox:
                        ShoppingCartBean shoppingCartBean2 = (ShoppingCartBean) adapter.getData().get(position);
                        if (shoppingCartBean2.isChecked()) {
                            shoppingCartBean2.setChecked(false);
                        } else {
                            shoppingCartBean2.setChecked(true);
                        }

                        List<ShoppingCartBean> adapterData2 = adapter.getData();
                        totalPrice(adapterData2);
                        break;


                }
            }
        });


        activityShoppingCartRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        activityShoppingCartRecyclerview.setAdapter(adapter);
    }

    private void totalPrice(List<ShoppingCartBean> adapterData) {

        double total = 0;
        for (int i = 0; i < adapterData.size(); i++) {
            if (ObjectUtils.isEmpty(adapterData.get(i).getCarGoodsList())) {

            } else {
                if (adapterData.get(i).isChecked()) {
                    total += Double.parseDouble(adapterData.get(i).getCarGoodsList().getGoodsPrice());
                }
            }
        }

        activityShoppingCartTotalPrice.setText(String.valueOf(total));

    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }
}
