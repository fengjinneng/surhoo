package com.surhoo.sh.invoice;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
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
import com.surhoo.sh.common.dialog.MyDialogFragment;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;
import com.surhoo.sh.common.util.ClickUtil;
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


    private static final String GETINVOCIELIST = "getInvoiceList";
    private static final String DELETEINVOCIE = "deleteInvocie";


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

    private View loadingView;
    private View loadingEmptyView;
    private View loadingErrorView;

    private int deletePosition = -1;
    private InvoiceListAdapter adapter;

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

        initLoadingView();

    }


    @Override
    public void onRecevieMessage(EventBusMessageBean bean) {

        switch (bean.getCode()) {
            case EventBusMessageBean.Invoice.addInvoiceSuccess:
                requestData();
                break;
        }
    }

    @Override
    public void initData() {


        adapter = new InvoiceListAdapter(R.layout.item_invoice_list, null);

        activityInvoiceRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        activityInvoiceRecyclerview.setAdapter(adapter);

        adapter.setEmptyView(loadingView);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (ClickUtil.isFastClick()) {
                    InvoiceBean invoiceBean = (InvoiceBean) adapter.getData().get(position);

                    if (StringUtils.equals("order", from)) {
                        EventBus.getDefault().post(new EventBusMessageBean(
                                EventBusMessageBean.Invoice.choiceInvoice, invoiceBean.getId()
                                , invoiceBean.getTitle()));
                        finish();
                    }
                }
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (ClickUtil.isFastClick()) {

                    InvoiceBean invoiceBean = (InvoiceBean) adapter.getData().get(position);

                    switch (view.getId()) {
                        case R.id.item_invoice_edit:
                            Intent intent = new Intent(InvoiceListActivity.this, EditInvoiceActivity.class);
                            intent.putExtra("invoiceBean", invoiceBean);
                            ActivityUtils.startActivity(intent);
                            break;
                        case R.id.item_invoice_delete:

                            MyDialogFragment myDialogFragment = MyDialogFragment.newInstance("确定要删除这个发票信息吗?");
                            myDialogFragment.setOnConfirmClickListener(new MyDialogFragment.onConfirmClickListener() {
                                @Override
                                public void onConfirmClick() {
                                    myDialogFragment.dismiss();
                                    deletePosition = position;
                                    presenter.deleteInvoice(DELETEINVOCIE, invoiceBean.getId());
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
                    }
                }
            }
        });
    }

    private void initLoadingView() {
        loadingView = LayoutInflater.from(this).inflate(R.layout.load_data_loading_view, null);
        loadingEmptyView = LayoutInflater.from(this).inflate(R.layout.load_data_empty_view, null);
        ImageView emptyImg = (ImageView) loadingEmptyView.findViewById(R.id.load_data_empty_view_img);
        emptyImg.setImageDrawable(getResources().getDrawable(R.mipmap.invoice_empty_img));
        TextView emptyText = (TextView) loadingEmptyView.findViewById(R.id.load_data_empty_view_content);
        emptyText.setText("暂无发票信息!");

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
    public void requestData() {
        presenter.requestInvoiceList(GETINVOCIELIST);
    }

    @OnClick({R.id.toolbar_layout_back, R.id.activity_invoice_add})
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case R.id.toolbar_layout_back:
                finish();

                break;
            case R.id.activity_invoice_add:
                break;
        }
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
    public void showNoPageList(String requestTag, List<InvoiceBean> list) {

        adapter.setNewData(list);
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
    public void showStringData(String requestTag, String s) {

        if (deletePosition != -1) {
            adapter.remove(deletePosition);
        }

        if (adapter.getData().size() == 0) {
            adapter.setEmptyView(loadingEmptyView);
        }
    }
}
