package com.surhoo.sh.invoice.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.invoice.bean.InvoiceDetailItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class InvoiceDetailAdapter extends BaseQuickAdapter<InvoiceDetailItem, BaseViewHolder> {


    public InvoiceDetailAdapter(int layoutResId, @Nullable List<InvoiceDetailItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, InvoiceDetailItem item) {


        helper.setText(R.id.item_invoice_detail_key,item.getKey());
        helper.setText(R.id.item_invoice_detail_value,item.getValue());

        TextView title = (TextView) helper.getView(R.id.item_invoice_detail_title);

        if(item.isNeedHead()){
            title.setVisibility(View.VISIBLE);
            title.setText(item.getTitle());
        }else {
            title.setVisibility(View.GONE);
        }

    }
}
