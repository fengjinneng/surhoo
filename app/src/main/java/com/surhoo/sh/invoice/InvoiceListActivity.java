package com.surhoo.sh.invoice;

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
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;
import com.surhoo.sh.invoice.adapter.InvoiceListAdapter;
import com.surhoo.sh.invoice.bean.InvoiceBean;
import com.surhoo.sh.invoice.present.InvoicePresenter;
import com.surhoo.sh.invoice.present.InvoicePresenterImpl;
import com.surhoo.sh.invoice.view.InvoiceView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class InvoiceListActivity extends BaseActivity implements InvoiceView {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_invoice_recyclerview)
    RecyclerView activityInvoiceRecyclerview;
    InvoicePresenter presenter;
    @BindView(R.id.activity_invoice_add)
    Button activityInvoiceAdd;


    private String from;

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
        from = getIntent().getStringExtra("from");

    }


    @Override
    public void onRecevieMessage(EventBusMessageBean bean) {

        switch (bean.getCode()) {
            case EventBusMessageBean.Invoice.addInvoiceSuccess:
                presenter.requestData();
                break;
        }
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


    private InvoiceListAdapter adapter;

    @Override
    public void showList(List<InvoiceBean> list) {

        if (ObjectUtils.isEmpty(adapter)) {
            adapter = new InvoiceListAdapter(R.layout.item_invoice_list, list);

            activityInvoiceRecyclerview.setLayoutManager(new LinearLayoutManager(this));

            activityInvoiceRecyclerview.setAdapter(adapter);

            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    InvoiceBean invoiceBean = (InvoiceBean) adapter.getData().get(position);

                    if (StringUtils.equals("order", from)) {
                        EventBus.getDefault().post(new EventBusMessageBean(
                                EventBusMessageBean.Invoice.choiceInvoice, invoiceBean.getId()
                                , invoiceBean.getTitle()));
                        finish();
                    }
                }
            });

            adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                    InvoiceBean invoiceBean = (InvoiceBean) adapter.getData().get(position);

                    switch (view.getId()){
                        case R.id.item_invoice_edit:
                            Intent intent = new Intent(InvoiceListActivity.this,EditInvoiceActivity.class);
                            intent.putExtra("invoiceBean",invoiceBean);
                            ActivityUtils.startActivity(intent);
                            break;
                        case R.id.item_invoice_delete:
                            presenter.deleteInvoice(invoiceBean.getId(),position);
                            break;
                    }
                }
            });

        }else {
            adapter.setNewData(list);
        }
    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }

    @OnClick(R.id.activity_invoice_add)
    public void onAddClicked() {
        ActivityUtils.startActivity(EditInvoiceActivity.class);
    }

    @Override
    public void getDeleteResult(int position) {
            adapter.remove(position);
    }
}
