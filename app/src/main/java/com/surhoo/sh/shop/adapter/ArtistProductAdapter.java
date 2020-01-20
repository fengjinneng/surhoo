package com.surhoo.sh.shop.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.shop.bean.ArtistProductListBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ArtistProductAdapter extends BaseQuickAdapter<ArtistProductListBean, BaseViewHolder> {


    public ArtistProductAdapter(int layoutResId, @Nullable List<ArtistProductListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ArtistProductListBean item) {

        helper.setText(R.id.item_artist_name,item.getOriginalName());
        helper.setText(R.id.item_artist_spec,item.getSpecs());
        helper.setText(R.id.item_artist_price,item.getPriceShow());
        helper.setText(R.id.item_artist_time,item.getGmtCreate());

        ImageView view = (ImageView) helper.getView(R.id.item_artist_img);
        GlideUtil.loadBannerImage(mContext,item.getOriginalUrl(),view);

    }
}
