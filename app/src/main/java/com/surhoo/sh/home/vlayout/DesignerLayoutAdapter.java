package com.surhoo.sh.home.vlayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
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
import com.alibaba.android.vlayout.layout.RangeGridLayoutHelper;
import com.blankj.utilcode.util.ActivityUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.R;
import com.surhoo.sh.designer.view.DesignerActivity;
import com.surhoo.sh.home.bean.HomePageBean;

import java.util.List;

public class DesignerLayoutAdapter extends DelegateAdapter.Adapter<DesignerLayoutAdapter.DesignerLayoutViewHolder> {


    Context context;
    List<HomePageBean.DESIGNERBean> datas;

    public DesignerLayoutAdapter(Context context, List<HomePageBean.DESIGNERBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @NonNull
    @Override
    public DesignerLayoutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DesignerLayoutViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home_desinger_recycler, viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DesignerLayoutViewHolder scenarioLayoutViewHolder, int i) {


        scenarioLayoutViewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));


        HomeDesignerAdapter homeDesignerAdapter = new HomeDesignerAdapter(R.layout.item_home_desinger, datas);
        homeDesignerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ActivityUtils.startActivity(DesignerActivity.class);
            }
        });

        scenarioLayoutViewHolder.recyclerView.setAdapter(homeDesignerAdapter);

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    static class DesignerLayoutViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;

        public DesignerLayoutViewHolder(View root) {
            super(root);
            recyclerView = root.findViewById(R.id.item_home_designer_recyclerview);
        }
    }
}
