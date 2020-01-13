package com.surhoo.sh.order;

import android.widget.ImageView;
import android.widget.TextView;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class PaySuccessActivity extends BaseActivity {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;

    @Override
    public int getContentView() {
        return R.layout.activity_pay_success;
    }

    @Override
    public boolean isFirstInLoadData() {
        return false;
    }

    @Override
    public void initView() {
        toolbarLayoutTitle.setText("支付成功");
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
}
