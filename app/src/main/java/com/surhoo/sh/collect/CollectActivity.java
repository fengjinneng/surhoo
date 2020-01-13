package com.surhoo.sh.collect;

import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.flyco.tablayout.SlidingTabLayout;
import com.goyourfly.multi_picture.ImageLoader;
import com.goyourfly.multi_picture.MultiPictureView;
import com.surhoo.sh.R;
import com.surhoo.sh.TestActivityActivity;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.common.util.BaseViewpageAdapter;
import com.surhoo.sh.designer.fragment.DesignerListFragment;
import com.surhoo.sh.designer.fragment.MaterialFragment;
import com.surhoo.sh.goods.fragment.GoodsListFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CollectActivity extends BaseActivity implements CollectView{

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_collect_tab)
    SlidingTabLayout activityCollectTab;
    @BindView(R.id.activity_collect_viewpager)
    ViewPager activityCollectViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_collect;
    }

    @Override
    public boolean isFirstInLoadData() {
        return false;
    }

    @Override
    public void initView() {
        toolbarLayoutTitle.setText("我的收藏");

        List<Fragment> datas = new ArrayList<>();
        datas.add(GoodsListFragment.newInstance("collect",0 , 0));
        datas.add(MaterialFragment.newInstance("collect",0));
        datas.add(DesignerListFragment.newInstance("",""));
//        datas.add(GoodsListFragment.newInstance(4,0 , 0));

        String[] arr = new String[3];
        arr[0] = "商品";
        arr[1] = "素材";
        arr[2] = "设计师";
//        arr[3] = " 店铺";
        activityCollectViewpager.setAdapter(new BaseViewpageAdapter(getSupportFragmentManager(), datas));
        activityCollectTab.setViewPager(activityCollectViewpager, arr);

        MultiPictureView.setImageLoader(new ImageLoader() {
            @Override
            public void loadImage(@NotNull ImageView imageView, @NotNull Uri uri) {
                Glide.with(CollectActivity.this).load(uri).into(imageView);
            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public void requestData() {

    }

    @OnClick(R.id.toolbar_layout_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }
}
