package com.surhoo.sh.invoice;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.ObjectUtils;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.bean.order.response.OrderDetailReturnBean;
import com.surhoo.sh.invoice.adapter.InvoiceDetailAdapter;
import com.surhoo.sh.invoice.bean.InvoiceDetailItem;
import java.util.ArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class InvoiceDetailActivity extends BaseActivity {
    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_invoice_detail_recyclerView)
    RecyclerView activityInvoiceDetailRecyclerView;

    private OrderDetailReturnBean orderDetailReturnBean;

    private InvoiceDetailAdapter adapter;

    @Override
    public int getContentView() {
        return R.layout.activity_invoice_detail;
    }

    @Override
    public boolean isFirstInLoadData() {
        return false;
    }

    @Override
    public void initView() {

        toolbarLayoutTitle.setText("发票详情");
        orderDetailReturnBean = getIntent().getParcelableExtra("orderDetailReturnBean");

    }

    @Override
    public void initData() {

        if (ObjectUtils.isEmpty(orderDetailReturnBean)) {
            return;
        }

        ArrayList<InvoiceDetailItem> datas = new ArrayList<>();

        InvoiceDetailItem item1 = new InvoiceDetailItem(false);
        item1.setKey("发票类型");

        if (orderDetailReturnBean.getInvoiceType() == 1) {
            item1.setValue("普通发票(电子)");
        } else if (orderDetailReturnBean.getInvoiceType() == 2) {
            item1.setValue("增值税专用发票");
        }


        InvoiceDetailItem item2 = new InvoiceDetailItem(false);
        item2.setKey("发票性质");
        if (orderDetailReturnBean.getNormalType() == 1) {
            item2.setValue("个人");
        } else if (orderDetailReturnBean.getNormalType() == 2) {
            item2.setValue("企业");
        }

        InvoiceDetailItem item3 = new InvoiceDetailItem(false);
        item3.setKey("发票抬头");
        item3.setValue(orderDetailReturnBean.getInvoiceTitle());

        InvoiceDetailItem item4 = new InvoiceDetailItem(false);
        item4.setKey("发票内容");
        item4.setValue(orderDetailReturnBean.getInvoiceContent());

        datas.add(item1);
        datas.add(item2);
        datas.add(item3);
        datas.add(item4);

        if(orderDetailReturnBean.getInvoiceType()==2){
            InvoiceDetailItem item5 = new InvoiceDetailItem(true);
            item5.setKey("单位名称");
            item5.setValue(orderDetailReturnBean.getInvoiceEnterpriseName());
            item5.setTitle("公司信息");

            InvoiceDetailItem item6 = new InvoiceDetailItem(false);
            item6.setKey("纳税人识别码");
            item6.setValue(orderDetailReturnBean.getInvoiceTaxCode());

            InvoiceDetailItem item7 = new InvoiceDetailItem(false);
            item7.setKey("注册地址");
            item7.setValue(orderDetailReturnBean.getInvoiceAddress());

            InvoiceDetailItem item8 = new InvoiceDetailItem(false);
            item8.setKey("注册电话");
            item8.setValue(orderDetailReturnBean.getInvoiceMobile());

            InvoiceDetailItem item9 = new InvoiceDetailItem(false);
            item9.setKey("开户银行");
            item9.setValue(orderDetailReturnBean.getInvoiceBankName());

            InvoiceDetailItem item10 = new InvoiceDetailItem(false);
            item10.setKey("银行账户");
            item10.setValue(orderDetailReturnBean.getInvoiceAccount());

            InvoiceDetailItem item11 = new InvoiceDetailItem(true);
            item11.setKey("昵称");
            item11.setValue(orderDetailReturnBean.getInvoiceNickname());
            item11.setTitle("收票人信息");

            InvoiceDetailItem item12 = new InvoiceDetailItem(false);
            item12.setKey("地址");
            item12.setValue(orderDetailReturnBean.getInvoiceAddress());


            datas.add(item5);
            datas.add(item6);
            datas.add(item7);
            datas.add(item8);
            datas.add(item9);
            datas.add(item10);
            datas.add(item11);
            datas.add(item12);

        }
        adapter = new InvoiceDetailAdapter(R.layout.item_invoice_detail, datas);
        activityInvoiceDetailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        activityInvoiceDetailRecyclerView.setAdapter(adapter);

    }

    @Override
    public void requestData() {

    }

    @OnClick(R.id.toolbar_layout_back)
    public void onViewClicked() {
        finish();
    }


}
