package com.surhoo.sh.address;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.R;
import com.surhoo.sh.address.adapter.AddressAdapter;
import com.surhoo.sh.address.bean.AddressBean;
import com.surhoo.sh.address.present.AddressPresenter;
import com.surhoo.sh.address.present.AddressPresenterImpl;
import com.surhoo.sh.address.view.AddressView;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
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

    private String from;

    @Override
    public void onRecevieMessage(EventBusMessageBean bean) {
        switch (bean.getCode()) {
            case EventBusMessageBean.Address.addAddressSuccess:
            case EventBusMessageBean.Address.updateAddressSuccess:
                addressPresenter.requestData();
                break;
        }
    }

    @Override
    public int getContentView() {
        return R.layout.activity_address;
    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void initView() {

        getWindow().setBackgroundDrawableResource(R.color.bgColor);

        addressPresenter = new AddressPresenterImpl();
        addressPresenter.bindView(this, this);

        toolbarLayoutTitle.setText("选择收货地址");

        from = getIntent().getStringExtra("from");
    }

    @Override
    public void initData() {

    }

    @Override
    public void requestData() {
        addressPresenter.requestData();
    }

    @OnClick(R.id.toolbar_layout_back)
    public void onViewClicked() {
        finish();
    }

    AddressAdapter addressAdapter;

    @Override
    public void showList(List<AddressBean> list) {
        if (ObjectUtils.isEmpty(addressAdapter)) {
            addressAdapter = new AddressAdapter(R.layout.item_address_list, list);
            activityAddressRecyclerview.setLayoutManager(new LinearLayoutManager(this));

            activityAddressRecyclerview.setAdapter(addressAdapter);

            addressAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                    AddressBean addressBean = (AddressBean) adapter.getData().get(position);

                    switch (view.getId()) {
                        case R.id.item_address_list_delete:
                            addressPresenter.deleteAddress(addressBean.getId(),position);
                            break;

                        case R.id.item_address_list_edit:
                            Intent intent = new Intent(AddressActivity.this,EditAddresActivity.class);
                            intent.putExtra("addressBean",addressBean);
                            ActivityUtils.startActivity(intent);

                            break;
                    }
                }
            });


            addressAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    if(StringUtils.equals("order",from)){

                        AddressBean addressBean = (AddressBean) adapter.getData().get(position);
                        EventBus.getDefault().post(new EventBusMessageBean(
                                EventBusMessageBean.Address.choiceAddress,addressBean));
                        finish();
                    }
                }
            });
        } else {
            addressAdapter.setNewData(list);
        }
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
    public void getDeleteResult(int position) {
        addressAdapter.remove(position);
    }
}
