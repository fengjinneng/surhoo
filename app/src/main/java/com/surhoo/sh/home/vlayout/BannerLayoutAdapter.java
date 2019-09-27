package com.surhoo.sh.home.vlayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.blankj.utilcode.util.ToastUtils;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.CommonUtil;
import com.surhoo.sh.home.bean.HomePageBean;

import java.util.ArrayList;
import java.util.List;

public class BannerLayoutAdapter extends DelegateAdapter.Adapter<BannerLayoutAdapter.BannerLayoutViewHolder> {


    Context context;
    List<HomePageBean.BANNERBean> datas;

    public BannerLayoutAdapter(Context context, List<HomePageBean.BANNERBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {

        return new SingleLayoutHelper();
    }

    @NonNull
    @Override
    public BannerLayoutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new BannerLayoutViewHolder(LayoutInflater.from(context).inflate(R.layout.home_banner_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BannerLayoutViewHolder scenarioLayoutViewHolder, int i) {

        ArrayList<String> bannerDatas = new ArrayList<>();
        for (int j = 0; j < datas.size(); j++) {
            bannerDatas.add(datas.get(i).getBanner());
        }


        CommonUtil.setBannerInfo(scenarioLayoutViewHolder.convenientBanner,bannerDatas);

        scenarioLayoutViewHolder.convenientBanner.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                ToastUtils.showShort("banner click");
            }
        });
        
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    static class BannerLayoutViewHolder extends RecyclerView.ViewHolder {


        ConvenientBanner convenientBanner;

        public BannerLayoutViewHolder(View root) {
            super(root);
            convenientBanner = root.findViewById(R.id.hame_banner_layout_banner);
        }
    }
}
