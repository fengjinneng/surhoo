package com.surhoo.sh.invoice;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InvoiceActivity extends BaseActivity implements InvoiceView {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_invoice_recyclerview)
    RecyclerView activityInvoiceRecyclerview;

    InvoicePresenter presenter;
    @BindView(R.id.activity_invoice_add)
    Button activityInvoiceAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public int getContentView() {
        return R.layout.activity_invoice;
    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void initView() {

        presenter = new InvoicePresenterImpl();
        presenter.bindView(this, this);
        toolbarLayoutTitle.setText("我的发票");
    }

    @Override
    public void initData() {

    }

    @Override
    public void requestData() {
        presenter.requestData();
    }

    @OnClick(R.id.toolbar_layout_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showList(List<InvoiceBean> list) {
        InvoiceListAdapter adapter = new InvoiceListAdapter(R.layout.item_invoice_list, list);

        activityInvoiceRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        activityInvoiceRecyclerview.setAdapter(adapter);
    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }

    @OnClick(R.id.activity_invoice_add)
    public void onAddClicked() {
        ActivityUtils.startActivity(EditInvoiceActivity.class);
    }
}
