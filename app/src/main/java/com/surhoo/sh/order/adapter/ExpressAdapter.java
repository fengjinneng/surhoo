package com.surhoo.sh.order.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.bean.order.response.ExpressBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ExpressAdapter extends BaseQuickAdapter<ExpressBean.TracesBean, BaseViewHolder> {


    public ExpressAdapter(int layoutResId, @Nullable List<ExpressBean.TracesBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ExpressBean.TracesBean item) {


        helper.setText(R.id.item_express_content,item.getAcceptStation());
        helper.setText(R.id.item_express_time,item.getAcceptTime());


    }
}
