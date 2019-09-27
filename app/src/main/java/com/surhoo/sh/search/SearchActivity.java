package com.surhoo.sh.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {


    @BindView(R.id.search_layout_content)
    EditText searchLayoutContent;
    @BindView(R.id.activity_search_goods)
    TextView activitySearchGoods;
    @BindView(R.id.activity_search_scenario)
    TextView activitySearchScenario;
    @BindView(R.id.activity_search_shop)
    TextView activitySearchShop;
    @BindView(R.id.activity_search_designer)
    TextView activitySearchDesigner;
    @BindView(R.id.activity_search_material)
    TextView activitySearchMaterial;
    @BindView(R.id.search_layout_back)
    ImageView searchLayoutBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void initView() {
        searchLayoutContent.setHint("搜索");
        searchLayoutContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    requestData();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void requestData() {

    }


    //1 商品 2 场景 3 店铺 4 设计师 5素材
    @OnClick({R.id.activity_search_goods, R.id.activity_search_scenario, R.id.activity_search_shop, R.id.activity_search_designer, R.id.activity_search_material})
    public void onViewClicked(View view) {

        Intent intent = new Intent(SearchActivity.this, SearchCategoryActivity.class);

        switch (view.getId()) {

            case R.id.activity_search_goods:
                intent.putExtra("type", 1);
                break;
            case R.id.activity_search_scenario:
                intent.putExtra("type", 2);
                break;
            case R.id.activity_search_shop:
                intent.putExtra("type", 3);
                break;
            case R.id.activity_search_designer:
                intent.putExtra("type", 4);
                break;
            case R.id.activity_search_material:
                intent.putExtra("type", 5);
                break;

        }

        ActivityUtils.startActivity(intent);

    }

    @OnClick(R.id.search_layout_back)
    public void onViewClicked() {
        KeyboardUtils.hideSoftInput(this);
        finish();
    }
}
