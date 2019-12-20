package com.surhoo.sh.search;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.home.bean.HomePageBean;
import com.surhoo.sh.home.vlayout.DesignerLayoutAdapter;
import com.surhoo.sh.home.vlayout.GoodsLayoutAdapter;
import com.surhoo.sh.home.vlayout.MaterialLayoutAdapter;
import com.surhoo.sh.home.vlayout.ScenarioLayoutAdapter;
import com.surhoo.sh.home.vlayout.SearchTitleLayoutAdapter;
import com.surhoo.sh.search.presenter.ISearchPresent;
import com.surhoo.sh.search.presenter.SearchPresentImpl;
import com.surhoo.sh.search.view.SearchView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements SearchView {

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
    ISearchPresent searchPresent;
    @BindView(R.id.activity_search_recyclerView)
    RecyclerView activitySearchRecyclerView;

    private DelegateAdapter delegateAdapter;
    private VirtualLayoutManager virtualLayoutManager;


    @Override
    public int getContentView() {
        return R.layout.activity_search;
    }

    @Override
    public boolean isFirstInLoadData() {
        return false;
    }


    @Override
    public void initView() {
        searchLayoutContent.setHint("搜索");
        searchLayoutContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    if(StringUtils.isEmpty(searchLayoutContent.getText().toString())){
                        ToastUtils.showShort("搜索内容不能为空");
                        return true;
                    }
                    searchPresent.requestData(searchLayoutContent.getText().toString());
                    return true;
                }
                return false;
            }
        });

        searchLayoutContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==0){
                    activitySearchRecyclerView.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public void initData() {

        searchPresent = new SearchPresentImpl();

        searchPresent.bindView(this, this);

        virtualLayoutManager = new VirtualLayoutManager(this);
        activitySearchRecyclerView.setLayoutManager(virtualLayoutManager);
//        delegateAdapter = new DelegateAdapter(virtualLayoutManager);
//        activitySearchRecyclerView.setAdapter(delegateAdapter);

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

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }


    @Override
    public void showData(HomePageBean homePageBean) {

        delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        activitySearchRecyclerView.setAdapter(delegateAdapter);

        activitySearchRecyclerView.setVisibility(View.VISIBLE);

        if (!ObjectUtils.isEmpty(homePageBean.getGOODS())) {

            SearchTitleLayoutAdapter adapter = new SearchTitleLayoutAdapter(this,"商品",searchLayoutContent.getText().toString());
            delegateAdapter.addAdapter(adapter);

            GoodsLayoutAdapter goodsLayoutAdapter = new GoodsLayoutAdapter(this, homePageBean.getGOODS());
            delegateAdapter.addAdapter(goodsLayoutAdapter);

        }

        if (!ObjectUtils.isEmpty(homePageBean.getDESIGNER())) {
            SearchTitleLayoutAdapter adapter = new SearchTitleLayoutAdapter(this,"设计师",searchLayoutContent.getText().toString());
            delegateAdapter.addAdapter(adapter);

            DesignerLayoutAdapter designerLayoutAdapter = new DesignerLayoutAdapter(this, homePageBean.getDESIGNER());
            delegateAdapter.addAdapter(designerLayoutAdapter);
        }


        if (!ObjectUtils.isEmpty(homePageBean.getSCENE())) {

            SearchTitleLayoutAdapter adapter = new SearchTitleLayoutAdapter(this,"场景",searchLayoutContent.getText().toString());
            delegateAdapter.addAdapter(adapter);

            ScenarioLayoutAdapter scenarioLayoutAdapter = new ScenarioLayoutAdapter(this, homePageBean.getSCENE());
            delegateAdapter.addAdapter(scenarioLayoutAdapter);
        }

        if (!ObjectUtils.isEmpty(homePageBean.getMATERIAL())) {

            SearchTitleLayoutAdapter adapter = new SearchTitleLayoutAdapter(this,"素材",searchLayoutContent.getText().toString());
            delegateAdapter.addAdapter(adapter);

            MaterialLayoutAdapter materialLayoutAdapter = new MaterialLayoutAdapter(this, homePageBean.getMATERIAL());
            delegateAdapter.addAdapter(materialLayoutAdapter);
        }

    }
}
