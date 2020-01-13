package com.surhoo.sh.home.vlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.ClickUtil;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ScenarioExpandLayoutAdapter extends DelegateAdapter.Adapter<ScenarioExpandLayoutAdapter.ScenarioExpandLayoutViewHolder> {

    Context context;

    private OnScenarioExpandClickListener onScenarioExpandClickListener;

    public ScenarioExpandLayoutAdapter(Context context) {
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {

        return new SingleLayoutHelper();
    }

    public void setOnScenarioExpandClickListener(OnScenarioExpandClickListener onScenarioExpandClickListener) {
        this.onScenarioExpandClickListener = onScenarioExpandClickListener;
    }

    public OnScenarioExpandClickListener getOnScenarioExpandClickListener() {
        return onScenarioExpandClickListener;
    }

    @NonNull
    @Override
    public ScenarioExpandLayoutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ScenarioExpandLayoutViewHolder(LayoutInflater.from(context).inflate(R.layout.item_scenario_expand, viewGroup, false));
    }

    int rotation = 0;

    @Override
    public void onBindViewHolder(@NonNull ScenarioExpandLayoutViewHolder viewHolder, int i) {


        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ClickUtil.isFastClick()) {

                    if (rotation == 0) {
                        rotation = 180;
                        viewHolder.img.setRotation(rotation);
                    } else {
                        rotation = 0;
                        viewHolder.img.setRotation(rotation);
                    }

                    onScenarioExpandClickListener.onScenarioExpandClick();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    static class ScenarioExpandLayoutViewHolder extends RecyclerView.ViewHolder {

        ImageView img;

        public ScenarioExpandLayoutViewHolder(View root) {
            super(root);
            img = root.findViewById(R.id.item_scenario_expand_icon);
        }
    }


    public interface OnScenarioExpandClickListener {
        void onScenarioExpandClick();

    }


}
