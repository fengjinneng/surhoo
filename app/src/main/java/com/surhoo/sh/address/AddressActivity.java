package com.surhoo.sh.address;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressActivity extends BaseActivity implements AddressView {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_address_recyclerview)
    RecyclerView activityAddressRecyclerview;
    @BindView(R.id.activity_address_add)
    Button activityAddressAdd;

    private AddressPresenter addressPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);

        initView();

        addressPresenter = new AddressPresenterImpl();
        addressPresenter.bindView(this, this);

        requestData();
    }

    @Override
    public void initView() {
        toolbarLayoutTitle.setText("选择收货地址");
    }

    @Override
    public void requestData() {
        addressPresenter.requestData();
    }

    @OnClick(R.id.toolbar_layout_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void showList(List<AddressBean> list) {
        AddressAdapter addressAdapter = new AddressAdapter(R.layout.item_address_list, list);
        activityAddressRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        activityAddressRecyclerview.setAdapter(addressAdapter);

        addressAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.item_address_list_delete:
                        AddressBean addressBean = (AddressBean) adapter.getData().get(position);
                        addressPresenter.deleteAddress(addressBean.getId());
                        break;

                    case R.id.item_address_list_edit:
                        break;
                }
            }
        });

    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }

    @OnClick(R.id.activity_address_add)
    public void onAddClicked() {

        ActivityUtils.startActivity(EditAddresActivity.class);
    }

    @Override
    public void showData(AddressBean addressBean) {

    }
}
