package com.surhoo.sh.user;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.model.HttpParams;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.base.OneResultBaseView;
import com.surhoo.sh.common.Api;
import com.surhoo.sh.common.eventBus.EventBusMessageBean;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.user.bean.UserInfoBean;

import org.greenrobot.eventbus.EventBus;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateNickNameActivity extends BaseActivity implements OneResultBaseView<UserInfoBean> {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_update_nick_name_nickName)
    EditText activityUpdateNickNameNickName;
    @BindView(R.id.activity_update_nick_name_save)
    Button activityUpdateNickNameSave;

    private String nickName;

    @Override
    public int getContentView() {
        return R.layout.activity_update_nick_name;
    }

    @Override
    public boolean isFirstInLoadData() {
        return false;
    }

    @Override
    public void initView() {
        toolbarLayoutTitle.setText("更改昵称");
    }

    @Override
    public void initData() {

        nickName = getIntent().getStringExtra("nickName");
        activityUpdateNickNameNickName.setText(nickName);
    }

    @Override
    public void requestData() {

    }


    @OnClick({R.id.toolbar_layout_back, R.id.activity_update_nick_name_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_layout_back:
                KeyboardUtils.hideSoftInput(this);
                finish();
                break;
            case R.id.activity_update_nick_name_save:
                if(StringUtils.isEmpty(activityUpdateNickNameNickName.getText().toString())){
                    ToastUtils.showShort("请填写昵称！");
                    return;
                }

                if(StringUtils.equals(nickName,activityUpdateNickNameNickName.getText().toString())){
                    ToastUtils.showShort("你还没有任何修改！");
                    return;
                }
                HttpParams httpParams = new HttpParams();
                httpParams.put("nickname",activityUpdateNickNameNickName.getText().toString());
                NetworkReturnUtil.requestBeanResultUsePostNoBody(this, this, Api.updateuserInfo,httpParams, UserInfoBean.class);
                break;
        }
    }

    @Override
    public void showBeanData(UserInfoBean userInfoBean) {
        SPUtils.getInstance().put("nickName", userInfoBean.getNickname());
        EventBus.getDefault().post(new EventBusMessageBean(EventBusMessageBean.User.updateNickNameSuccess));
        finish();
    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }
}
