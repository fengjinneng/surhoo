package com.surhoo.sh.invoice.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.invoice.bean.InvoiceBean;

import java.util.List;

public class InvoiceListAdapter extends BaseQuickAdapter<InvoiceBean, BaseViewHolder> {


    public InvoiceListAdapter(int layoutResId, @Nullable List<InvoiceBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, InvoiceBean item) {


        if (!ObjectUtils.isEmpty(item.getInvoiceType())) {
            if (item.getInvoiceType() == 1) {
                helper.setText(R.id.item_invoice_type, "普通发票");
            }
            if (item.getInvoiceType() == 2) {
                helper.setText(R.id.item_invoice_type, "增值税专用发票");
            }
        }

        helper.setText(R.id.item_invoice_title,item.getTitle());

        helper.setText(R.id.item_invoice_content,item.getContent());

        helper.addOnClickListener(R.id.item_invoice_delete,R.id.item_invoice_edit);


        if(item.getDefaultStatus()){
            helper.getView(R.id.item_invoice_default).setVisibility(View.VISIBLE);
        }else {
            helper.getView(R.id.item_invoice_default).setVisibility(View.GONE);
        }
    }
}
