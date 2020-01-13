package com.surhoo.sh.order;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.bean.order.response.ExpressBean;
import com.surhoo.sh.common.util.ClickUtil;
import com.surhoo.sh.order.adapter.ExpressAdapter;
import com.surhoo.sh.order.present.IExpressPresent;
import com.surhoo.sh.order.present.impl.ExpressPresentImpl;
import com.surhoo.sh.order.view.ExpressView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class ExpressActivity extends BaseActivity implements ExpressView {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_express_recyclerView)
    RecyclerView activityExpressRecyclerView;


    private IExpressPresent expressPresent;
    private ExpressAdapter adapter;

    @Override
    public int getContentView() {
        return R.layout.activity_express;
    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    private String expressCode;
    private String expressNumber;


    @Override
    public void initView() {

        initLoadingView();
        toolbarLayoutTitle.setText("查看物流");
        expressPresent = new ExpressPresentImpl();
        expressPresent.bindView(this, this);

    }

    @Override
    public void initData() {

        adapter = new ExpressAdapter(R.layout.item_express, null);
        activityExpressRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        activityExpressRecyclerView.setAdapter(adapter);

        expressCode = getIntent().getStringExtra("expressCode");
        expressNumber = getIntent().getStringExtra("expressNumber");

        adapter.setEmptyView(loadingView);

    }

    private View loadingView;
    private View loadingEmptyView;
    private View loadingErrorView;



    private void initLoadingView() {
        loadingView = LayoutInflater.from(this).inflate(R.layout.load_data_loading_view, null);
        loadingEmptyView = LayoutInflater.from(this).inflate(R.layout.load_data_empty_view, null);
        ImageView emptyImg = (ImageView) loadingEmptyView.findViewById(R.id.load_data_empty_view_img);
        emptyImg.setImageDrawable(getResources().getDrawable(R.mipmap.search_empty_img));
        TextView emptyText = (TextView) loadingEmptyView.findViewById(R.id.load_data_empty_view_content);
        emptyText.setText("没有物流信息哦！");

        loadingErrorView = LayoutInflater.from(this).inflate(R.layout.load_data_error_view, null);

        TextView errorText = (TextView) loadingErrorView.findViewById(R.id.load_data_error_view_retry);
        errorText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) {
                    adapter.setEmptyView(loadingView);
                    requestData();
                }
            }
        });
    }


    @Override
    public void requestData() {

        expressPresent.getExpressInfo(expressCode,expressNumber);

    }

    @OnClick(R.id.toolbar_layout_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showBeanData(ExpressBean expressBean) {

        if(ObjectUtils.isEmpty(expressBean.getTraces())){
            adapter.setEmptyView(loadingEmptyView);
            return;
        }

        adapter.setNewData(expressBean.getTraces());

    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }
}
