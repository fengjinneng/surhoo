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
import com.blankj.utilcode.util.ActivityUtils;
import com.bumptech.glide.Glide;
import com.surhoo.sh.R;
import com.surhoo.sh.home.bean.HomePageBean;
import com.surhoo.sh.scenario.view.ScenarioActivity;
import com.surhoo.sh.search.SearchCategoryActivity;

import java.util.List;

public class LevelOneScenarioLayoutAdapter extends DelegateAdapter.Adapter<LevelOneScenarioLayoutAdapter.LevelOneGoodsLayoutViewHolder> {


    Context context;
    List<HomePageBean.FIRSTSCENEBean> datas;

    private OnLevelOneScenarioClickListener onLevelOneScenarioClickListener;

    public LevelOneScenarioLayoutAdapter(Context context, List<HomePageBean.FIRSTSCENEBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(4);
        gridLayoutHelper.setAutoExpand(false);
        return gridLayoutHelper;
    }


    public void setOnLevelOneScenarioClickListener(OnLevelOneScenarioClickListener onLevelOneScenarioClickListener) {
        this.onLevelOneScenarioClickListener = onLevelOneScenarioClickListener;
    }

    @NonNull
    @Override
    public LevelOneGoodsLayoutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LevelOneGoodsLayoutViewHolder(LayoutInflater.from(context).inflate(R.layout.item_scenario_level, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LevelOneGoodsLayoutViewHolder scenarioLayoutViewHolder, int i) {

        HomePageBean.FIRSTSCENEBean item = datas.get(i);

        scenarioLayoutViewHolder.name.setText(item.getName());

        Glide.with(context).load(item.getIcon()).into(scenarioLayoutViewHolder.img);



        scenarioLayoutViewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,ScenarioActivity.class);
                intent.putExtra("id",item.getSceneId());
                ActivityUtils.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class LevelOneGoodsLayoutViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView img;

        public LevelOneGoodsLayoutViewHolder(View root) {
            super(root);
            name = root.findViewById(R.id.item_scenario_level_name);
            img = root.findViewById(R.id.item_scenario_level_icon);
        }
    }

    public interface OnLevelOneScenarioClickListener {
        void onClick();

    }
}
