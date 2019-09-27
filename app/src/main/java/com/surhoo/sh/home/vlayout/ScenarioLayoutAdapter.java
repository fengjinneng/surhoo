package com.surhoo.sh.home.vlayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

public class ScenarioLayoutAdapter extends DelegateAdapter.Adapter<ScenarioLayoutAdapter.ScenarioLayoutViewHolder> {


    Context context;
    List<HomePageBean.SCENEBean> datas;

    public ScenarioLayoutAdapter(Context context, List<HomePageBean.SCENEBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @NonNull
    @Override
    public ScenarioLayoutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ScenarioLayoutViewHolder(LayoutInflater.from(context).inflate(R.layout.item_scenario, viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ScenarioLayoutViewHolder scenarioLayoutViewHolder, int i) {

        HomePageBean.SCENEBean item = datas.get(i);

        scenarioLayoutViewHolder.content.setText(item.getName());

        Glide.with(context).load(item.getLogo()).into(scenarioLayoutViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ScenarioLayoutViewHolder extends RecyclerView.ViewHolder {

        TextView content;
        ImageView imageView;

        public ScenarioLayoutViewHolder(View root) {
            super(root);
            content = root.findViewById(R.id.item_scenario_content);
            imageView = root.findViewById(R.id.item_scenario_img);
        }
    }
}
