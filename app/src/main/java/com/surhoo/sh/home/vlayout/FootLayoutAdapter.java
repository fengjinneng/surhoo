package com.surhoo.sh.home.vlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.surhoo.sh.R;
import com.surhoo.sh.common.custom.MyLoadMoreView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FootLayoutAdapter extends DelegateAdapter.Adapter<FootLayoutAdapter.TitleLayoutViewHolder> {


    Context context;


    public FootLayoutAdapter(Context context) {
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new SingleLayoutHelper();
    }

    @NonNull
    @Override
    public TitleLayoutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TitleLayoutViewHolder(LayoutInflater.from(context).inflate(R.layout.home_foot_view, viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TitleLayoutViewHolder titleLayoutViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    static class TitleLayoutViewHolder extends RecyclerView.ViewHolder {


        public TitleLayoutViewHolder(View root) {
            super(root);
        }
    }
}
