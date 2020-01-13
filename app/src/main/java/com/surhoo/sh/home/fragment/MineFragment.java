package com.surhoo.sh.home.fragment;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.R;
import com.surhoo.sh.SettingActivity;
import com.surhoo.sh.address.AddressActivity;
import com.surhoo.sh.base.BaseFragment;
import com.surhoo.sh.collect.CollectActivity;
import com.surhoo.sh.common.UserUtil;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;
import com.surhoo.sh.common.recyclerview.MineItemDecoration;
import com.surhoo.sh.common.util.ClickUtil;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.home.adapter.MineItemAdapter;
import com.surhoo.sh.home.bean.MineItemBean;
import com.surhoo.sh.invoice.InvoiceListActivity;
import com.surhoo.sh.login.view.LoginActivity;
import com.surhoo.sh.order.MyOrderListActivity;
import com.surhoo.sh.shoppingcart.ShoppingCartActivity;
import com.surhoo.sh.user.MineInformationActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MineFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.fragment_mine_recyclerview)
    RecyclerView fragmentMineRecyclerview;
    @BindView(R.id.fragment_mine_img)
    ImageView fragmentMineImg;
    @BindView(R.id.fragment_mine_nickName)
    TextView fragmentMineNickName;
    @BindView(R.id.fragment_mine_setting)
    ImageView fragmentMineSetting;
    @BindView(R.id.fragment_mine_order)
    ConstraintLayout fragmentMineOrder;
    @BindView(R.id.fragment_mine_afterSale)
    ConstraintLayout fragmentMineAfterSale;
    @BindView(R.id.fragment_mine_groupOrder)
    ConstraintLayout fragmentMineGroupOrder;
    @BindView(R.id.fragment_mine_cutPriceOrder)
    ConstraintLayout fragmentMineCutPriceOrder;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MineFragment() {
        // Required empty public constructor
    }


    public static MineFragment newInstance(String param1, String param2) {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View getView(ViewGroup container) {
        return getLayoutInflater().inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void init() {
        if (UserUtil.isLogin()) {
            fragmentMineNickName.setText(SPUtils.getInstance().getString("nickName"));
            GlideUtil.loadCircleImage(getActivity(), SPUtils.getInstance().getString("headImg"), fragmentMineImg);
        }
    }

    @Override
    public boolean isFirstInLoadData() {
        return false;
    }

    @Override
    public void requestData() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<MineItemBean> list = new ArrayList();

        MineItemBean shoppingcar = new MineItemBean(1, "购物车", getResources().getDrawable(R.mipmap.mine_shopping_cart));
        MineItemBean address = new MineItemBean(1, "收货地址", getResources().getDrawable(R.mipmap.mine_address));
        MineItemBean invoce = new MineItemBean(1, "我的发票", getResources().getDrawable(R.mipmap.mine_invoice));
        MineItemBean collect = new MineItemBean(2, "我的收藏", getResources().getDrawable(R.mipmap.mine_collect));
        MineItemBean beDesigner = new MineItemBean(1, "成为设计师", getResources().getDrawable(R.mipmap.mine_be_desinger));
        MineItemBean beBusiness = new MineItemBean(2, "成为商家", getResources().getDrawable(R.mipmap.mine_be_businesses));
        MineItemBean customerService = new MineItemBean(2, "联系客服", getResources().getDrawable(R.mipmap.mine_customer_service));

        list.add(shoppingcar);
        list.add(address);
        list.add(invoce);
        list.add(collect);
//        list.add(beDesigner);
//        list.add(beBusiness);
//        list.add(customerService);

        MineItemAdapter adapter = new MineItemAdapter(R.layout.template_mine_item, list);

        fragmentMineRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        fragmentMineRecyclerview.setAdapter(adapter);

        fragmentMineRecyclerview.addItemDecoration(new MineItemDecoration(getContext(), list));


        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (ClickUtil.isFastClick()) {
                    MineItemBean mineItem = (MineItemBean) adapter.getData().get(position);
                    if (StringUtils.equals("购物车", mineItem.getName())) {
                        if (UserUtil.isLogin()) {
                            ActivityUtils.startActivity(ShoppingCartActivity.class);
                        } else {
                            ActivityUtils.startActivity(LoginActivity.class);
                        }
                    }
                    if (StringUtils.equals("收货地址", mineItem.getName())) {
                        if (UserUtil.isLogin()) {
                            ActivityUtils.startActivity(AddressActivity.class);
                        } else {
                            ActivityUtils.startActivity(LoginActivity.class);
                        }
                    }
                    if (StringUtils.equals("我的发票", mineItem.getName())) {
                        if (UserUtil.isLogin()) {
                            ActivityUtils.startActivity(InvoiceListActivity.class);
                        } else {
                            ActivityUtils.startActivity(LoginActivity.class);
                        }
                    }
                    if (StringUtils.equals("我的收藏", mineItem.getName())) {
                        if (UserUtil.isLogin()) {
                            ActivityUtils.startActivity(CollectActivity.class);
                        } else {
                            ActivityUtils.startActivity(LoginActivity.class);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onRecevieMessage(EventBusMessageBean bean) {
        super.onRecevieMessage(bean);

        switch (bean.getCode()) {
            case EventBusMessageBean.User.login:

                fragmentMineNickName.setText(SPUtils.getInstance().getString("nickName"));
                GlideUtil.loadCircleImage(getActivity(), SPUtils.getInstance().getString("headImg"), fragmentMineImg);

                break;

            case EventBusMessageBean.User.updateNickNameSuccess:
                fragmentMineNickName.setText(SPUtils.getInstance().getString("nickName"));
                break;
        }
    }

    @OnClick({R.id.fragment_mine_order, R.id.fragment_mine_afterSale,
            R.id.fragment_mine_groupOrder, R.id.fragment_mine_cutPriceOrder,
            R.id.fragment_mine_img, R.id.fragment_mine_setting, R.id.fragment_mine_nickName})
    public void onViewClicked(View view) {
        if (ClickUtil.isFastClick()) {
            switch (view.getId()) {
                case R.id.fragment_mine_order:
                    if (UserUtil.isLogin()) {
                        ActivityUtils.startActivity(MyOrderListActivity.class);
                    } else {
                        ActivityUtils.startActivity(LoginActivity.class);
                    }
                    break;
                case R.id.fragment_mine_afterSale:
                    ToastUtils.showShort("功能暂未上线");
                    break;
                case R.id.fragment_mine_groupOrder:
                    ToastUtils.showShort("功能暂未上线");
                    break;
                case R.id.fragment_mine_cutPriceOrder:
                    ToastUtils.showShort("功能暂未上线");
                    break;
                case R.id.fragment_mine_img:
                    if (UserUtil.isLogin()) {
                        ActivityUtils.startActivity(MineInformationActivity.class);
                    }else {
                        ActivityUtils.startActivity(LoginActivity.class);
                    }
                    break;
                case R.id.fragment_mine_setting:
                    ActivityUtils.startActivity(SettingActivity.class);
                    break;
                case R.id.fragment_mine_nickName:
                    if (!UserUtil.isLogin()) {
                        ActivityUtils.startActivity(LoginActivity.class);
                    }
                    break;
            }
        }
    }
}
