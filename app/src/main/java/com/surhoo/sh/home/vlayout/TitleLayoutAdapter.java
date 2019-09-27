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
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.bumptech.glide.Glide;
import com.surhoo.sh.R;
import com.surhoo.sh.home.bean.HomePageBean;

import java.util.List;

public class TitleLayoutAdapter extends DelegateAdapter.Adapter<TitleLayoutAdapter.TitleLayoutViewHolder> {


    Context context;

    String title;

    public TitleLayoutAdapter(Context context,String title) {
        this.context = context;
        this.title = title;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new SingleLayoutHelper();
    }

    @NonNull
    @Override
    public TitleLayoutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TitleLayoutViewHolder(LayoutInflater.from(context).inflate(R.layout.home_title, viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TitleLayoutViewHolder titleLayoutViewHolder, int i) {

        titleLayoutViewHolder.title.setText(title);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    static class TitleLayoutViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        public TitleLayoutViewHolder(View root) {
            super(root);
            title = root.findViewById(R.id.home_title_name);
        }
    }
}
