package com.surhoo.sh.shop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseFragment;
import com.surhoo.sh.base.NoPageListBaseView;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.shop.adapter.ArtistProductAdapter;
import com.surhoo.sh.shop.bean.ArtistProductListBean;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class ArtistProductTypeFragment extends BaseFragment implements NoPageListBaseView<ArtistProductListBean> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "classifysId";
    @BindView(R.id.fragment_artist_product_type_recyclerView)
    RecyclerView recyclerView;

    // TODO: Rename and change types of parameters
    private int classifysId;

    private ArtistProductAdapter adapter;


    public ArtistProductTypeFragment() {
        // Required empty public constructor
    }

    public static ArtistProductTypeFragment newInstance(int classifysId) {
        ArtistProductTypeFragment fragment = new ArtistProductTypeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, classifysId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            classifysId = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View getView(ViewGroup container) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_artist_product_type, container, false);
    }

    @Override
    public void init() {
        adapter = new ArtistProductAdapter(R.layout.item_artist_product, null);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void requestData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("classifysId", classifysId);
        NetworkReturnUtil.requestNoPageList("", this, getActivity(), Api.shopArtistOriginal, httpParams, ArtistProductListBean.class);

    }

    @Override
    public void setNoPageEmptyView() {

    }

    @Override
    public void setNoPageErrorView() {

    }

    @Override
    public void showNoPageList(String requestTag, List<ArtistProductListBean> list) {

        adapter.setNewData(list);

    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }
}
