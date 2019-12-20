package com.surhoo.sh.home.vlayout;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.blankj.utilcode.util.ActivityUtils;
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

                int designerId = datas.get(position).getDesignerId();
                Intent i = new Intent(context,DesignerActivity.class);
                i.putExtra("id",designerId);
                ActivityUtils.startActivity(i);
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
