package com.surhoo.sh.home.vlayout;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.surhoo.sh.R;
import com.surhoo.sh.home.bean.HomePageBean;

import java.util.List;

public class CutPriceLayoutAdapter extends DelegateAdapter.Adapter<CutPriceLayoutAdapter.CutPriceLayoutViewHolder> {


    Context context;
    List<HomePageBean.BARGAINGOODSBean> datas;

    public CutPriceLayoutAdapter(Context context, List<HomePageBean.BARGAINGOODSBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @NonNull
    @Override
    public CutPriceLayoutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CutPriceLayoutViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home_cut_price, viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CutPriceLayoutViewHolder cutPriceLayoutViewHolder, int i) {

        HomePageBean.BARGAINGOODSBean item = datas.get(i);

        cutPriceLayoutViewHolder.name.setText(item.getGoodsName());
        cutPriceLayoutViewHolder.bottomPrice.setText("¥"+item.getBottomPrice());
        cutPriceLayoutViewHolder.marketPrice.setText("¥"+item.getMarketPrice());
        cutPriceLayoutViewHolder.saleCount.setText(item.getSaleCount()+"");

        Glide.with(context).load(item.getLogo()).into(cutPriceLayoutViewHolder.img);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class CutPriceLayoutViewHolder extends RecyclerView.ViewHolder {

        ImageView img;


        TextView name,bottomPrice,marketPrice,saleCount;

        public CutPriceLayoutViewHolder(View root) {
            super(root);
            img = root.findViewById(R.id.item_home_cut_price_img);

            name = root.findViewById(R.id.item_home_cut_price_name);
            bottomPrice = root.findViewById(R.id.item_home_cut_price_bottomPrice);
            marketPrice = root.findViewById(R.id.item_home_cut_price_marketPrice);
            saleCount = root.findViewById(R.id.item_home_cut_price_saleCount);
        }
    }
}
