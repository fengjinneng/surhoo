package com.surhoo.sh.home.vlayout;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.blankj.utilcode.util.ActivityUtils;
import com.bumptech.glide.Glide;
import com.surhoo.sh.R;
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
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
        gridLayoutHelper.setAutoExpand(false);
        return gridLayoutHelper;
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

        Glide.with(context).load(item.getLogo()).into(scenarioLayoutViewHolder.goodsImg);

        scenarioLayoutViewHolder.goodsImg.setOnClickListener(new View.OnClickListener() {
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
        ImageView goodsImg;

        public GoodsLayoutViewHolder(View root) {
            super(root);
            goodsName = root.findViewById(R.id.item_goods_list_goodsname);
            price = root.findViewById(R.id.item_goods_list_price);
            sales = root.findViewById(R.id.item_goods_list_sales);
            goodsImg = root.findViewById(R.id.item_goods_list_img);
        }
    }
}
