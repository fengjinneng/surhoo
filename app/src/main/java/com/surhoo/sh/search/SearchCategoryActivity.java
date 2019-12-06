package com.surhoo.sh.search;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.R;
import com.surhoo.sh.common.util.GridDivider;
import com.surhoo.sh.designer.adapter.DesignerLabelAdapter;
import com.surhoo.sh.designer.bean.DesignerLabelBean;
import com.surhoo.sh.designer.bean.DesignerListBean;
import com.surhoo.sh.designer.view.DesignerActivity;
import com.surhoo.sh.goods.bean.GoodsBean;
import com.surhoo.sh.goods.view.impl.GoodsDetailActivity;
import com.surhoo.sh.material.bean.MaterialBean;
import com.surhoo.sh.material.MaterialDetailActivity;
import com.surhoo.sh.scenario.bean.ScenarioBean;
import com.surhoo.sh.scenario.view.ScenarioActivity;
import com.surhoo.sh.search.adapter.SearchDesignerAdapter;
import com.surhoo.sh.search.adapter.SearchGoodsAdapter;
import com.surhoo.sh.search.adapter.SearchMaterialAdapter;
import com.surhoo.sh.search.adapter.SearchScenarioAdapter;
import com.surhoo.sh.search.adapter.SearchShopAdapter;
import com.surhoo.sh.search.presenter.SearchCategoryPresenter;
import com.surhoo.sh.search.presenter.SearchCategoryPresenterImpl;
import com.surhoo.sh.search.view.SearchCategoryView;
import com.surhoo.sh.shop.ShopListBean;
import com.zyyoona7.popup.EasyPopup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchCategoryActivity extends BaseActivity implements SearchCategoryView,
        BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.search_layout_content)
    EditText searchLayoutContent;
    @BindView(R.id.activity_search_category_recyclerview)
    RecyclerView activitySearchCategoryRecyclerview;
    SearchCategoryPresenter searchCategoryPresenter;
    SearchGoodsAdapter searchGoodsAdapter;
    SearchScenarioAdapter searchScenarioAdapter;
    SearchShopAdapter searchShopAdapter;
    SearchDesignerAdapter searchDesignerAdapter;
    SearchMaterialAdapter searchMaterialAdapter;
    @BindView(R.id.search_layout_back)
    ImageView searchLayoutBack;
    @BindView(R.id.search_layout_img)
    ImageView searchLayoutImg;

    private List datas;

    private int type;//1 商品 2 场景 3 店铺 4 设计师 5素材

    @Override
    public int getContentView() {
        return R.layout.activity_search_category;
    }

    @Override
    public boolean isFirstInLoadData() {
        return false;
    }

    @Override
    public void initView() {

        type = getIntent().getIntExtra("type", 0);

        setPageTitle();

        searchCategoryPresenter = new SearchCategoryPresenterImpl();
        searchCategoryPresenter.bindView(this, this);

        searchLayoutContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    KeyboardUtils.hideSoftInput(SearchCategoryActivity.this);
                    pageIndex = 1;
                    requestData();
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    public void initData() {

    }

    private void setPageTitle() {
        switch (type) {
            case 0:
                searchLayoutContent.setHint("出错了...");
                break;
            case 1:
                searchLayoutContent.setHint("搜索商品");
                activitySearchCategoryRecyclerview.setLayoutManager(new GridLayoutManager(this, 2));
                datas = new ArrayList<GoodsBean>();
                searchGoodsAdapter = new SearchGoodsAdapter(R.layout.item_goods_list, datas);
                activitySearchCategoryRecyclerview.setAdapter(searchGoodsAdapter);
                searchGoodsAdapter.setOnLoadMoreListener(this, activitySearchCategoryRecyclerview);
                searchGoodsAdapter.setOnItemClickListener(this);
                activitySearchCategoryRecyclerview.addItemDecoration(new GridDivider(20));


                break;
            case 2:
                searchLayoutContent.setHint("搜索场景");

                activitySearchCategoryRecyclerview.setLayoutManager(new LinearLayoutManager(this));
                datas = new ArrayList<ScenarioBean>();
                searchScenarioAdapter = new SearchScenarioAdapter(R.layout.item_scenario, datas);
                activitySearchCategoryRecyclerview.setAdapter(searchScenarioAdapter);
                searchScenarioAdapter.setOnLoadMoreListener(this, activitySearchCategoryRecyclerview);
                searchScenarioAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        ScenarioBean scenarioBean = (ScenarioBean) adapter.getData().get(position);
                        Intent intent = new Intent(SearchCategoryActivity.this, ScenarioActivity.class);
                        intent.putExtra("id", scenarioBean.getSceneId());
                        ActivityUtils.startActivity(intent);
                    }
                });
                break;
            case 3:
                searchLayoutContent.setHint("搜索店铺");

                activitySearchCategoryRecyclerview.setLayoutManager(new LinearLayoutManager(this));
                datas = new ArrayList<ShopListBean>();
                searchShopAdapter = new SearchShopAdapter(R.layout.item_shop_list, datas);
                activitySearchCategoryRecyclerview.setAdapter(searchShopAdapter);
                searchShopAdapter.setOnLoadMoreListener(this, activitySearchCategoryRecyclerview);
                break;
            case 4:
                searchLayoutContent.setHint("搜索设计师");
                searchLayoutImg.setVisibility(View.VISIBLE);
                activitySearchCategoryRecyclerview.setLayoutManager(new LinearLayoutManager(this));
                datas = new ArrayList<DesignerListBean>();
                searchDesignerAdapter = new SearchDesignerAdapter(R.layout.item_designer_list, datas);
                activitySearchCategoryRecyclerview.setAdapter(searchDesignerAdapter);
                searchDesignerAdapter.setOnItemClickListener(this);
                searchDesignerAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        DesignerListBean designerListBean = (DesignerListBean) adapter.getData().get(position);
                        switch (view.getId()) {
                            case R.id.item_designer_list_material_1:
                                ActivityUtils.startActivity(MaterialDetailActivity.class);
                                break;
                            case R.id.item_designer_list_material_2:
                                break;
                            case R.id.item_designer_list_material_3:
                                break;
                            case R.id.item_designer_list_material_4:
                                break;
                            case R.id.item_designer_list_material_5:
                                break;
                            case R.id.item_designer_list_material_6:
                                break;

                        }
                    }
                });
                searchDesignerAdapter.setOnLoadMoreListener(this, activitySearchCategoryRecyclerview);
                break;
            case 5:
                searchLayoutContent.setHint("搜索素材");

                activitySearchCategoryRecyclerview.setLayoutManager(new LinearLayoutManager(this));
                datas = new ArrayList<MaterialBean>();
                searchMaterialAdapter = new SearchMaterialAdapter(R.layout.item_material_list, datas);
                activitySearchCategoryRecyclerview.setAdapter(searchMaterialAdapter);
                searchMaterialAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        MaterialBean materialBean = (MaterialBean) adapter.getData().get(position);
                        Intent i = new Intent(SearchCategoryActivity.this, MaterialDetailActivity.class);
                        i.putExtra("id", materialBean.getMaterialId());
                        ActivityUtils.startActivity(i);
                    }
                });
                searchMaterialAdapter.setOnLoadMoreListener(this, activitySearchCategoryRecyclerview);
                break;
        }
    }

    private int pageIndex = 1;

    @Override
    public void requestData() {
        searchCategoryPresenter.requestData(type, searchLayoutContent.getText().toString().trim(),
                20, pageIndex, 1, "");
    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void firstInEmpty() {
//        datas.clear();
//        switch (type) {
//            case 1:
//                searchGoodsAdapter.notifyDataSetChanged();
//                break;
//            case 2:
//                searchScenarioAdapter.notifyDataSetChanged();
//                break;
//
//            case 3:
//                searchShopAdapter.notifyDataSetChanged();
//                break;
//
//            case 4:
//                searchDesignerAdapter.notifyDataSetChanged();
//                break;
//
//            case 5:
//                searchMaterialAdapter.notifyDataSetChanged();
//                break;
    }
//    }

    @Override
    public void loadEnd() {

        switch (type) {
            case 1:
                searchGoodsAdapter.loadMoreEnd();
                break;
            case 2:
                searchScenarioAdapter.loadMoreEnd();

                break;

            case 3:
                searchShopAdapter.loadMoreEnd();
                break;

            case 4:
                searchDesignerAdapter.loadMoreEnd();
                break;

            case 5:
                searchMaterialAdapter.loadMoreEnd();
                break;
        }
    }

    @Override
    public void refresh(List list) {

        //1 商品 2 场景 3 店铺 4 设计师 5素材
        switch (type) {
            case 1:
                searchGoodsAdapter.setNewData(list);
                searchGoodsAdapter.loadMoreComplete();
                break;

            case 2:
                searchScenarioAdapter.setNewData(list);
                searchScenarioAdapter.loadMoreComplete();

                break;

            case 3:
                searchShopAdapter.setNewData(list);
                searchShopAdapter.loadMoreComplete();
                break;

            case 4:
                searchDesignerAdapter.setNewData(list);
                searchDesignerAdapter.loadMoreComplete();
                break;

            case 5:
                searchMaterialAdapter.setNewData(list);
                searchMaterialAdapter.loadMoreComplete();
                break;
        }
    }

    @Override
    public void loadData(List list) {

        //1 商品 2 场景 3 店铺 4 设计师 5素材
        switch (type) {
            case 1:
                searchGoodsAdapter.addData(list);
                searchGoodsAdapter.loadMoreComplete();
                break;

            case 2:
                searchScenarioAdapter.addData(list);
                searchScenarioAdapter.loadMoreComplete();
                break;

            case 3:
                searchShopAdapter.addData(list);
                searchShopAdapter.loadMoreComplete();
                break;

            case 4:
                searchDesignerAdapter.addData(list);
                searchDesignerAdapter.loadMoreComplete();
                break;

            case 5:
                searchMaterialAdapter.addData(list);
                searchMaterialAdapter.loadMoreComplete();
                break;
        }
    }

    @Override
    public void onLoadMoreRequested() {
        pageIndex++;
        requestData();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (type) {
            case 1:
                GoodsBean goodsBean = (GoodsBean) adapter.getData().get(position);
                Intent i = new Intent(this, GoodsDetailActivity.class);
                i.putExtra("id", goodsBean.getGoodsId());
                ActivityUtils.startActivity(i);
                break;

            case 2:
                break;

            case 3:
                break;
            case 4:
                DesignerListBean designerBean = (DesignerListBean) adapter.getData().get(position);
                Intent designerIntent = new Intent(this, DesignerActivity.class);
                designerIntent.putExtra("id", designerBean.getDesignerId());
                ActivityUtils.startActivity(designerIntent);
                break;

            case 5:
                break;
        }
    }


    EasyPopup designerLabelPop;

    @OnClick({R.id.search_layout_back, R.id.search_layout_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_layout_back:
                KeyboardUtils.hideSoftInput(this);
                finish();
                break;
            case R.id.search_layout_img:

                switch (type) {
                    case 4:
                        if (ObjectUtils.isEmpty(designerLabelPop)) {
                            searchCategoryPresenter.requestDesignerLable(4);
                        } else {
                            designerLabelPop.showAtLocation(searchLayoutContent, Gravity.TOP, 0, 0);
                        }
                        break;
                }

                break;
        }
    }

    @Override
    public void showList(List list) {
        switch (type) {
            case 1:
                break;

            case 4:

                setDesignerPopInfo(list);

                break;
        }
    }

    //搜索设计师时的点击上一个标签
    private DesignerLabelBean lastDesignerLabelBean;
    private int lastDesignerLabelPosition;

    private void setDesignerPopInfo(List list) {
        if (ObjectUtils.isEmpty(designerLabelPop)) {
            designerLabelPop = EasyPopup.create()
                    .setContentView(this, R.layout.pop_designer_label)
//                .setAnimationStyle(R.style.RightPopAnim)
                    //是否允许点击PopupWindow之外的地方消失
                    .setFocusAndOutsideEnable(true)
                    .setBackgroundDimEnable(true)
                    //变暗的透明度(0-1)，0为完全透明
                    .setDimValue(0.4f)
//                变暗的背景颜色
                    .setDimColor(Color.BLACK)
                    //指定任意 ViewGroup 背景变暗
//                .setDimView(viewGroup)

                    .setWidth(ViewGroup.LayoutParams.MATCH_PARENT)

                    .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                    .apply();

            Button cancle = designerLabelPop.findViewById(R.id.pop_designer_label_cancle);
            Button confirm = designerLabelPop.findViewById(R.id.pop_designer_label_confirm);
            RecyclerView recyclerView = designerLabelPop.findViewById(R.id.pop_designer_label_recyclerview);

            recyclerView.setLayoutManager(new GridLayoutManager(this,3));
            DesignerLabelAdapter adapter = new DesignerLabelAdapter(R.layout.item_designer_label, list);

            recyclerView.setAdapter(adapter);

            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                    if(!ObjectUtils.isEmpty(lastDesignerLabelBean)){
                        if(lastDesignerLabelPosition==position){
                            return;
                        }
                    }

                    DesignerLabelBean designerLabelBean = (DesignerLabelBean) adapter.getData().get(position);
                    designerLabelBean.setChecked(true);

                    if (ObjectUtils.isEmpty(lastDesignerLabelBean)) {

                    } else {
                        lastDesignerLabelBean.setChecked(false);
                        adapter.notifyItemChanged(lastDesignerLabelPosition);

                    }
                    lastDesignerLabelBean = designerLabelBean;
                    lastDesignerLabelPosition = position;
//
                    adapter.notifyItemChanged(position);


                }
            });


            cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    designerLabelPop.dismiss();
                }
            });

            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    designerLabelPop.dismiss();
                }
            });
        }
        designerLabelPop.showAtLocation(searchLayoutContent, Gravity.TOP, 0, 0);
    }
}