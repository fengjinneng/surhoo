package com.surhoo.sh.address;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
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
import com.surhoo.sh.common.dialog.MyDialogFragment;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;
import com.surhoo.sh.common.util.ClickUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddressActivity extends BaseActivity implements AddressView {

    private static final String DELETEADDRESS = "deleteAddress";
    private static final String GETADDRESSLIST = "getAddressList";

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

    private View loadingView;
    private View loadingEmptyView;
    private View loadingErrorView;
    //当前要删除的位置
    private int deletePosition = -1;
    private AddressAdapter addressAdapter;

    @Override
    public void onRecevieMessage(EventBusMessageBean bean) {
        switch (bean.getCode()) {
            case EventBusMessageBean.Address.addAddressSuccess:
            case EventBusMessageBean.Address.updateAddressSuccess:
                requestData();
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
        addressPresenter = new AddressPresenterImpl();
        addressPresenter.bindView(this, this);
        toolbarLayoutTitle.setText("我的收货地址");
        from = getIntent().getStringExtra("from");
        initLoadingView();
    }

    @Override
    public void initData() {

        initRecyclerviewData();

    }


    @Override
    public void requestData() {
        addressPresenter.requestAddressList(GETADDRESSLIST);
    }

    @Override
    public void setNoPageEmptyView() {
        addressAdapter.setNewData(null);

        addressAdapter.setEmptyView(loadingEmptyView);
    }

    @Override
    public void setNoPageErrorView() {
        addressAdapter.setNewData(null);

        addressAdapter.setEmptyView(loadingErrorView);
    }

    @Override
    public void showNoPageList(String requestTag, List<AddressBean> list) {
        addressAdapter.setNewData(list);
    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }


    @Override
    public void showStringData(String requestTag,String s) {
        if (deletePosition != -1) {
            addressAdapter.remove(deletePosition);
        }
        if(addressAdapter.getData().size()==0){
            addressAdapter.setEmptyView(loadingEmptyView);
        }
    }


    @OnClick({R.id.toolbar_layout_back, R.id.activity_address_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_layout_back:
                finish();

                break;
            case R.id.activity_address_add:
                ActivityUtils.startActivity(EditAddresActivity.class);
                break;
        }
    }

    private void initLoadingView() {
        loadingView = LayoutInflater.from(this).inflate(R.layout.load_data_loading_view, null);
        loadingEmptyView = LayoutInflater.from(this).inflate(R.layout.load_data_empty_view, null);
        ImageView emptyImg = (ImageView) loadingEmptyView.findViewById(R.id.load_data_empty_view_img);
        emptyImg.setImageDrawable(getResources().getDrawable(R.mipmap.address_empty_img));
        TextView emptyText = (TextView) loadingEmptyView.findViewById(R.id.load_data_empty_view_content);
        emptyText.setText("还没有地址,快去添加吧~");

        loadingErrorView = LayoutInflater.from(this).inflate(R.layout.load_data_error_view, null);

        TextView errorText = (TextView) loadingErrorView.findViewById(R.id.load_data_error_view_retry);
        errorText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) {
                    addressAdapter.setEmptyView(loadingView);
                    requestData();
                }
            }
        });
    }

    private void initRecyclerviewData() {
        addressAdapter = new AddressAdapter(R.layout.item_address_list, null);

        activityAddressRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        activityAddressRecyclerview.setAdapter(addressAdapter);

        addressAdapter.setEmptyView(loadingView);

        addressAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                AddressBean addressBean = (AddressBean) adapter.getData().get(position);

                switch (view.getId()) {
                    case R.id.item_address_list_delete:

                        MyDialogFragment myDialogFragment = MyDialogFragment.newInstance("确定要删除这个地址吗?");
                        myDialogFragment.setOnConfirmClickListener(new MyDialogFragment.onConfirmClickListener() {
                            @Override
                            public void onConfirmClick() {
                                myDialogFragment.dismiss();
                                deletePosition = position;
                                addressPresenter.deleteAddress(DELETEADDRESS, addressBean.getId());
                            }
                        });
                        myDialogFragment.setOnCancelClickListener(new MyDialogFragment.onCancelClickListener() {
                            @Override
                            public void onCancelClick() {
                                myDialogFragment.dismiss();
                            }
                        });

                        myDialogFragment.show(getSupportFragmentManager(), "dialog");

                        break;

                    case R.id.item_address_list_edit:
                        Intent intent = new Intent(AddressActivity.this, EditAddresActivity.class);
                        intent.putExtra("addressBean", addressBean);
                        ActivityUtils.startActivity(intent);

                        break;
                }
            }
        });

        addressAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (StringUtils.equals("order", from)) {

                    AddressBean addressBean = (AddressBean) adapter.getData().get(position);
                    EventBus.getDefault().post(new EventBusMessageBean(
                            EventBusMessageBean.Address.choiceAddress, addressBean));
                    finish();
                }
            }
        });
    }
}
