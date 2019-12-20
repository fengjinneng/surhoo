package com.surhoo.sh.home.vlayout;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.goods.view.impl.GoodsDetailActivity;
import com.surhoo.sh.home.bean.HomePageBean;

import java.util.List;

public class GoodsLayoutAdapter extends DelegateAdapter.Adapter<GoodsLayoutAdapter.GoodsLayoutViewHolder> {


    Context context;
    List<HomePageBean.GOODSBean> datas;

    public GoodsLayoutAdapter(Context context, List<HomePageBean.GOODSBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        GridLayoutHelper gridHelper = new GridLayoutHelper(2);
//        gridHelper.setGap(ConvertUtils.dp2px(12));

        //设置垂直方向条目的间隔

        gridHelper.setGap(ConvertUtils.dp2px(12));

        gridHelper.setMarginLeft(ConvertUtils.dp2px(16));
        gridHelper.setMarginRight(ConvertUtils.dp2px(16));

        gridHelper.setAutoExpand(false);
        return gridHelper;
    }

    @NonNull
    @Override
    public GoodsLayoutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new GoodsLayoutViewHolder(LayoutInflater.from(context).inflate(R.layout.item_goods_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsLayoutViewHolder scenarioLayoutViewHolder, int i) {

        HomePageBean.GOODSBean item = datas.get(i);

        scenarioLayoutViewHolder.goodsName.setText(item.getGoodsName());
        scenarioLayoutViewHolder.price.setText( item.getGoodsPrice());
        scenarioLayoutViewHolder.sales.setText(String.valueOf(item.getSaleCount()));


        scenarioLayoutViewHolder.goodsImg.setLayoutParams(new ConstraintLayout.LayoutParams((int) (ScreenUtils.getScreenWidth() * 0.44),(int) (ScreenUtils.getScreenWidth() * 0.44)));

        scenarioLayoutViewHolder.goodsImg.setPadding(ConvertUtils.dp2px(1),0,ConvertUtils.dp2px(1),0);

        GlideUtil.loadDefaultImg(context,item.getLogo() ,scenarioLayoutViewHolder.goodsImg);

        scenarioLayoutViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,GoodsDetailActivity.class);
                i.putExtra("id",item.getGoodsId());
                ActivityUtils.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class GoodsLayoutViewHolder extends RecyclerView.ViewHolder {

        TextView goodsName, price, sales;
        RoundedImageView goodsImg;

        public GoodsLayoutViewHolder(View root) {
            super(root);
            goodsName = root.findViewById(R.id.item_goods_list_goodsname);
            price = root.findViewById(R.id.item_goods_list_price);
            sales = root.findViewById(R.id.item_goods_list_sales);
            goodsImg = root.findViewById(R.id.item_goods_list_img);
        }
    }
}
