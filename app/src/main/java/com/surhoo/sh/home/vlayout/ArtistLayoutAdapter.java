package com.surhoo.sh.home.vlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.blankj.utilcode.util.ConvertUtils;
import com.surhoo.sh.R;
import com.surhoo.sh.common.view.CardItem;
import com.surhoo.sh.common.view.CardPagerAdapter;
import com.surhoo.sh.common.view.ShadowTransformer;
import com.surhoo.sh.home.bean.HomePageBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class ArtistLayoutAdapter  extends DelegateAdapter.Adapter<ArtistLayoutAdapter.ArtistLayoutViewHolder>  {


    Context context;
    private List<HomePageBean.ArtistShopListBean> artistShopListBeans;

    private CardPagerAdapter mCardAdapter;

    private ShadowTransformer mCardShadowTransformer;

    public ArtistLayoutAdapter(Context context,List<HomePageBean.ArtistShopListBean> artistShopListBeans) {
        this.context = context;
        this.artistShopListBeans =artistShopListBeans;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new SingleLayoutHelper();
    }

    @NonNull
    @Override
    public ArtistLayoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArtistLayoutViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home_artist, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistLayoutViewHolder holder, int position) {

        mCardAdapter = new CardPagerAdapter();

        for (int i = 0; i < artistShopListBeans.size(); i++) {
            mCardAdapter.addCardItem(new CardItem(artistShopListBeans.get(i).getLogo()));
        }

        mCardShadowTransformer = new ShadowTransformer(holder.viewPager, mCardAdapter);

        holder.viewPager.setPageMargin(ConvertUtils.dp2px(-20));

        holder.viewPager.setAdapter(mCardAdapter);

        holder.viewPager.setPageTransformer(false, mCardShadowTransformer);

        holder.viewPager.setOffscreenPageLimit(3);

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    static class ArtistLayoutViewHolder extends RecyclerView.ViewHolder {

        ViewPager viewPager;

        public ArtistLayoutViewHolder(View root) {
            super(root);
            viewPager = root.findViewById(R.id.item_home_artist_viewPager);
        }
    }

}
