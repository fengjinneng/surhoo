package com.surhoo.sh.designer.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.GlideUtil;

import java.util.List;

public class DesingerListMaterialAdapter extends BaseQuickAdapter<String
        , BaseViewHolder> {


    public DesingerListMaterialAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {


        ImageView imageView = helper.getView(R.id.item_designer_list_material_item_img);

//        Glide.with(mContext).load(item).into(imageView);
        GlideUtil.loadCircleImage(mContext,item,imageView);

    }
}
