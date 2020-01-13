package com.surhoo.sh;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.surhoo.sh.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_web_back)
    ImageView activityWebBack;
    @BindView(R.id.activity_web_forward)
    ImageView activityWebForward;
    @BindView(R.id.activity_web_refresh)
    ImageView activityWebRefresh;
    @BindView(R.id.activity_web_webView)
    WebView mWebview;

    private String webUrl;


    @Override
    public int getContentView() {
        return R.layout.activity_web_view;
    }

    @Override
    public boolean isFirstInLoadData() {
        return false;
    }

    @Override
    public void initView() {

        webUrl = getIntent().getStringExtra("webUrl");

    }

    @Override
    public void initData() {

        mWebview.setWebChromeClient(new WebChromeClient());
        mWebview.setWebViewClient(new WebViewClient());
        mWebview.getSettings().setLoadWithOverviewMode(true);
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebview.loadUrl(webUrl);

        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                toolbarLayoutTitle.setText(title);
            }
        };

        mWebview.setWebChromeClient(wvcc);
    }

    @Override
    public void requestData() {

    }

    @OnClick(R.id.toolbar_layout_back)
    public void onViewClicked() {
    }

    @OnClick({R.id.toolbar_layout_back, R.id.activity_web_back, R.id.activity_web_forward, R.id.activity_web_refresh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_layout_back:
                if (mWebview.canGoBack()) {
                    mWebview.goBack(); //goBack()表示返回WebView的上一页面
                } else {
                    finish();
                }
                break;
            case R.id.activity_web_back:

                if (mWebview.canGoBack()) {
                    mWebview.goBack();
                }
                break;
            case R.id.activity_web_forward:
                if (mWebview.canGoForward()) {
                    mWebview.goForward();
                }
                break;
            case R.id.activity_web_refresh:
                mWebview.reload();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (mWebview.canGoBack()) {
                mWebview.goBack(); //goBack()表示返回WebView的上一页面
                return true;
            } else {
                finish();
                return true;
            }

        }
        return false;
    }
}
