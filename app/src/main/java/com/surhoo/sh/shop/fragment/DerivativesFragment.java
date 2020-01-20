package com.surhoo.sh.shop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.blankj.utilcode.util.ObjectUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseFragment;
import com.surhoo.sh.common.util.BaseViewpageAdapter;
import com.surhoo.sh.shop.bean.ClassifyListBean;
import java.util.ArrayList;
import java.util.List;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;


public class DerivativesFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.fragment_derivatives_tab)
    SlidingTabLayout fragmentDerivativesTab;
    @BindView(R.id.fragment_derivatives_viewPager)
    ViewPager viewPager;

    // TODO: Rename and change types of parameters
    private ArrayList<ClassifyListBean> classifyListBeans;


    public DerivativesFragment() {
        // Required empty public constructor
    }


    public static DerivativesFragment newInstance(ArrayList<ClassifyListBean> classifyListBeans) {
        DerivativesFragment fragment = new DerivativesFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, classifyListBeans);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            classifyListBeans = getArguments().getParcelableArrayList(ARG_PARAM1);
        }
    }

    @Override
    public View getView(ViewGroup container) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_derivatives, container, false);
    }

    @Override
    public void init() {

        if (ObjectUtils.isEmpty(classifyListBeans)) {
            return;
        }

        String[] arr = new String[classifyListBeans.size()];

        for (int i = 0; i < classifyListBeans.size(); i++) {
            arr[i] = classifyListBeans.get(i).getName();
        }

        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < classifyListBeans.size(); i++) {
            fragments.add(ShopFragment.newInstance(classifyListBeans.get(i).getClassifyId()));
        }
        viewPager.setAdapter(new BaseViewpageAdapter(getFragmentManager(), fragments));
        fragmentDerivativesTab.setViewPager(viewPager, arr);

    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void requestData() {

    }

}
