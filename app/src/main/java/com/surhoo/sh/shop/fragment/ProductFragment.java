package com.surhoo.sh.shop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseFragment;
import com.surhoo.sh.base.NoPageListBaseView;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.BaseViewpageAdapter;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.shop.bean.ArtistProductBean;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends BaseFragment implements NoPageListBaseView<ArtistProductBean> {
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.fragment_product_tab)
    SlidingTabLayout fragmentProductTab;
    @BindView(R.id.fragment_product_viewPager)
    ViewPager viewPager;

    // TODO: Rename and change types of parameters
    private int shopId;


    public ProductFragment() {
        // Required empty public constructor
    }


    public static ProductFragment newInstance(int shopId) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, shopId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            shopId = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View getView(ViewGroup container) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void init() {

    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void requestData() {

        HttpParams httpParams = new HttpParams();

        httpParams.put("shopId",shopId);

        NetworkReturnUtil.requestNoPageList("",this,getActivity(), Api.shopOriginalClassifys,httpParams, ArtistProductBean.class);

    }


    @Override
    public void setNoPageEmptyView() {

    }

    @Override
    public void setNoPageErrorView() {

    }

    @Override
    public void showNoPageList(String requestTag, List<ArtistProductBean> list) {

        List<Fragment> fragments = new ArrayList<>();

        String[] strs = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {

            fragments.add(ArtistProductTypeFragment.newInstance(list.get(i).getId()));
            strs[i] = list.get(i).getName();
        }

        viewPager.setAdapter(new BaseViewpageAdapter(getFragmentManager(), fragments));
        fragmentProductTab.setViewPager(viewPager, strs);

    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }
}
