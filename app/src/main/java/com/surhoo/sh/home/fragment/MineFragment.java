package com.surhoo.sh.home.fragment;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.R;
import com.surhoo.sh.address.AddressActivity;
import com.surhoo.sh.base.BaseFragment;
import com.surhoo.sh.collect.CollectActivity;
import com.surhoo.sh.common.recyclerview.MineItemDecoration;
import com.surhoo.sh.home.adapter.MineItemAdapter;
import com.surhoo.sh.home.bean.MineItemBean;
import com.surhoo.sh.invoice.InvoiceListActivity;
import com.surhoo.sh.order.MyOrderListActivity;
import com.surhoo.sh.shoppingcart.ShoppingCartActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    @BindView(R.id.fragment_mine_order_img)
    ImageView fragmentMineOrderImg;
    @BindView(R.id.fragment_mine_after_sale_img)
    ImageView fragmentMineAfterSaleImg;
    @BindView(R.id.fragment_mine_team_order_img)
    ImageView fragmentMineTeamOrderImg;
    @BindView(R.id.fragment_mine_bargain_order_img)
    ImageView fragmentMineBargainOrderImg;
    @BindView(R.id.fragment_mine_order_text)
    TextView fragmentMineOrderText;
    @BindView(R.id.fragment_mine_after_sale_text)
    TextView fragmentMineAfterSaleText;
    @BindView(R.id.fragment_mine_team_order_text)
    TextView fragmentMineTeamOrderText;
    @BindView(R.id.fragment_mine_bargain_order_text)
    TextView fragmentMineBargainOrderText;

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
        list.add(beDesigner);
        list.add(beBusiness);
        list.add(customerService);

        MineItemAdapter adapter = new MineItemAdapter(R.layout.template_mine_item, list);

        fragmentMineRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        fragmentMineRecyclerview.setAdapter(adapter);

        fragmentMineRecyclerview.addItemDecoration(new MineItemDecoration(getContext(), list));


        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MineItemBean mineItem = (MineItemBean) adapter.getData().get(position);
                if (StringUtils.equals("购物车", mineItem.getName())) {
                    ActivityUtils.startActivity(ShoppingCartActivity.class);
                }

                if (StringUtils.equals("收货地址", mineItem.getName())) {
                    ActivityUtils.startActivity(AddressActivity.class);
                }

                if (StringUtils.equals("我的发票", mineItem.getName())) {
                    ActivityUtils.startActivity(InvoiceListActivity.class);
                }

                if (StringUtils.equals("我的收藏", mineItem.getName())) {
                    ActivityUtils.startActivity(CollectActivity.class);

                }
            }
        });
    }


    @OnClick({R.id.fragment_mine_order_img, R.id.fragment_mine_after_sale_img,
            R.id.fragment_mine_team_order_img, R.id.fragment_mine_bargain_order_img,
            R.id.fragment_mine_order_text, R.id.fragment_mine_after_sale_text,
            R.id.fragment_mine_team_order_text, R.id.fragment_mine_bargain_order_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_mine_order_img:
            case R.id.fragment_mine_order_text:
                ActivityUtils.startActivity(MyOrderListActivity.class);
                break;
            case R.id.fragment_mine_after_sale_img:
            case R.id.fragment_mine_after_sale_text:

                break;
            case R.id.fragment_mine_team_order_img:
            case R.id.fragment_mine_team_order_text:

                break;
            case R.id.fragment_mine_bargain_order_img:
            case R.id.fragment_mine_bargain_order_text:

                break;


        }
    }
}
