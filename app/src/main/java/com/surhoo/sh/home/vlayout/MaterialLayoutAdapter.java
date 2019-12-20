package com.surhoo.sh.home.vlayout;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.makeramen.roundedimageview.RoundedImageView;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.goods.view.impl.GoodsDetailActivity;
import com.surhoo.sh.home.bean.HomePageBean;
import com.surhoo.sh.material.MaterialDetailActivity;
import com.surhoo.sh.material.bean.MaterialBean;
import com.surhoo.sh.search.SearchCategoryActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MaterialLayoutAdapter extends DelegateAdapter.Adapter<MaterialLayoutAdapter.MaterialLayoutViewHolder> {


    Context context;
    List<HomePageBean.MATERIALBean> datas;

    public MaterialLayoutAdapter(Context context, List<HomePageBean.MATERIALBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @NonNull
    @Override
    public MaterialLayoutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MaterialLayoutViewHolder(LayoutInflater.from(context).inflate(R.layout.item_material_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialLayoutViewHolder viewHolder, int i) {

        HomePageBean.MATERIALBean item = datas.get(i);

        viewHolder.name.setText(item.getName());
        viewHolder.price.setText("¥" + item.getPrice());
        viewHolder.content.setText(item.getDetail());


        GlideUtil.loadDefaultImg(context,item.getLogo() ,viewHolder.img);

        List<String> strings = new ArrayList<>();

        if (!ObjectUtils.isEmpty(item.getLabelInfoList())) {

            viewHolder.flowLayout.setVisibility(View.VISIBLE);

            for (int j = 0; j < item.getLabelInfoList().size(); j++) {
                strings.add(item.getLabelInfoList().get(j).getName());
            }

            TagAdapter<String> tagAdapter = new TagAdapter<String>(strings) {
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.item_designer_tag, viewHolder.flowLayout, false);
                    tv.setText(s);
                    return tv;
                }
            };

            viewHolder.flowLayout.setAdapter(tagAdapter);

        } else {

            viewHolder.flowLayout.setVisibility(View.GONE);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent materialIntent = new Intent(context, MaterialDetailActivity.class);
                materialIntent.putExtra("id", item.getMaterialId());
                ActivityUtils.startActivity(materialIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class MaterialLayoutViewHolder extends RecyclerView.ViewHolder {

        TextView name, price,content;
        TagFlowLayout flowLayout;
        ImageView img;

        public MaterialLayoutViewHolder(View root) {
            super(root);
            name = root.findViewById(R.id.item_material_name);
            price = root.findViewById(R.id.item_material_price);
            content = root.findViewById(R.id.item_material_content);
            img = root.findViewById(R.id.item_material_img);

            flowLayout = root.findViewById(R.id.item_material_tag);
        }
    }
}
