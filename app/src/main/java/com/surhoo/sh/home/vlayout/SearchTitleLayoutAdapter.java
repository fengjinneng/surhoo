package com.surhoo.sh.home.vlayout;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.ClickUtil;
import com.surhoo.sh.goods.view.GoodsListActivity;
import com.surhoo.sh.search.SearchCategoryActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchTitleLayoutAdapter extends DelegateAdapter.Adapter<SearchTitleLayoutAdapter.TitleLayoutViewHolder> {


    Context context;

    String title;

    String searchName;

    public SearchTitleLayoutAdapter(Context context, String title,String searchName) {
        this.context = context;
        this.title = title;
        this.searchName = searchName;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new SingleLayoutHelper();
    }

    @NonNull
    @Override
    public TitleLayoutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TitleLayoutViewHolder(LayoutInflater.from(context).inflate(R.layout.search_all_title, viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TitleLayoutViewHolder viewHolder, int i) {

        viewHolder.title.setText(title);

        viewHolder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) {
                    if (StringUtils.equals("商品", title)) {
                        Intent intent = new Intent(context, GoodsListActivity.class);
                        intent.putExtra("from", 1);
                        intent.putExtra("searchName", searchName);
                        ActivityUtils.startActivity(intent);
                    }

                    if (StringUtils.equals("素材", title)) {
                        Intent intent = new Intent(context, SearchCategoryActivity.class);
                        intent.putExtra("type", 5);
                        intent.putExtra("searchName", searchName);
                        ActivityUtils.startActivity(intent);
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    static class TitleLayoutViewHolder extends RecyclerView.ViewHolder {

        TextView title,more;

        public TitleLayoutViewHolder(View root) {
            super(root);
            title = root.findViewById(R.id.search_all_title_name);
            more = root.findViewById(R.id.search_all_title_more);
        }
    }
}
