package com.surhoo.sh.shop.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseFragment;
import com.surhoo.sh.base.OneResultBaseView;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.shop.bean.ArtistIntroduceBean;

import androidx.fragment.app.Fragment;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArtistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArtistFragment extends BaseFragment implements OneResultBaseView<ArtistIntroduceBean> {
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.fragment_artist_web)
    WebView fragmentArtistWeb;

    private int synopsisId;

    public ArtistFragment() {
    }

    public static ArtistFragment newInstance(int synopsisId) {
        ArtistFragment fragment = new ArtistFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, synopsisId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            synopsisId = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View getView(ViewGroup container) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_artist, container, false);
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
        httpParams.put("synopsisId", synopsisId);
        NetworkReturnUtil.requestBeanResultUseGet(this, getActivity(), Api.shopSynopsis, httpParams, ArtistIntroduceBean.class);

    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void showBeanData(ArtistIntroduceBean artistIntroduceBean) {
        fragmentArtistWeb.loadData(artistIntroduceBean.getContent(), "text/html;charset=UTF-8", null);
    }
}
