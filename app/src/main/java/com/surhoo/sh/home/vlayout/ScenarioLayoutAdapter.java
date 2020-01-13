package com.surhoo.sh.home.vlayout;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.blankj.utilcode.util.ActivityUtils;
import com.bumptech.glide.Glide;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.ClickUtil;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.home.bean.HomePageBean;
import com.surhoo.sh.scenario.bean.ScenarioBean;
import com.surhoo.sh.scenario.view.ScenarioActivity;
import com.surhoo.sh.search.SearchCategoryActivity;

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

        GlideUtil.loadBannerImage(context,item.getLogo(),scenarioLayoutViewHolder.imageView);

        scenarioLayoutViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ClickUtil.isFastClick()){
                    Intent intent = new Intent(context, ScenarioActivity.class);
                    intent.putExtra("id", item.getSceneId());
                    intent.putExtra("title", item.getName());
                    ActivityUtils.startActivity(intent);
                }
            }
        });
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
