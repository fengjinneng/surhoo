package com.surhoo.sh.address;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;

import java.util.List;

public class AddressAdapter extends BaseQuickAdapter<AddressBean
        , BaseViewHolder> {



    public AddressAdapter(int layoutResId, @Nullable List<AddressBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, AddressBean item) {


        helper.setText(R.id.item_address_list_name,item.getName());

        helper.setText(R.id.item_address_list_phone,item.getPhone());

        helper.setText(R.id.item_address_list_address,item.getAddress());

        helper.addOnClickListener(R.id.item_address_list_delete).addOnClickListener(R.id.item_address_list_edit);

    }
}
