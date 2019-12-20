package com.surhoo.sh.search;

import android.content.Intent;
import android.graphics.Color;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.R;
import com.surhoo.sh.common.recyclerview.GridDivider;
import com.surhoo.sh.designer.adapter.DesignerLabelAdapter;
import com.surhoo.sh.designer.bean.DesignerLabelBean;
import com.surhoo.sh.designer.bean.DesignerListBean;
import com.surhoo.sh.designer.view.DesignerActivity;
import com.surhoo.sh.goods.bean.GoodsBean;
import com.surhoo.sh.goods.view.impl.GoodsDetailActivity;
import com.surhoo.sh.home.vlayout.MaterialLayoutAdapter;
import com.surhoo.sh.material.adapter.MaterialLabelAdapter;
import com.surhoo.sh.material.bean.MaterialBean;
import com.surhoo.sh.material.MaterialDetailActivity;
import com.surhoo.sh.material.bean.MaterialLabelBean;
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

    private int type;//1 商品 2 场景 3 店铺 4 设计师 5素材

    private String searchName;

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

        searchName  = getIntent().getStringExtra("searchName");

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

        if(!StringUtils.isEmpty(searchName)){
            searchLayoutContent.setText(searchName);
            searchLayoutContent.setSelection(searchName.length());
            requestData();

        }
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
                break;
            case 2:
                searchLayoutContent.setHint("搜索场景");

                activitySearchCategoryRecyclerview.setLayoutManager(new LinearLayoutManager(this));
                searchScenarioAdapter = new SearchScenarioAdapter(R.layout.item_scenario, null);
                activitySearchCategoryRecyclerview.setAdapter(searchScenarioAdapter);
                searchScenarioAdapter.setOnLoadMoreListener(this, activitySearchCategoryRecyclerview);
                searchScenarioAdapter.setOnItemClickListener(this);
                break;
            case 3:
                searchLayoutContent.setHint("搜索店铺");

                activitySearchCategoryRecyclerview.setLayoutManager(new LinearLayoutManager(this));
                searchShopAdapter = new SearchShopAdapter(R.layout.item_shop_list, null);
                activitySearchCategoryRecyclerview.setAdapter(searchShopAdapter);
                searchShopAdapter.setOnLoadMoreListener(this, activitySearchCategoryRecyclerview);
                break;
            case 4:
                searchLayoutContent.setHint("搜索设计师");
                searchLayoutImg.setVisibility(View.VISIBLE);
                activitySearchCategoryRecyclerview.setLayoutManager(new LinearLayoutManager(this));
                searchDesignerAdapter = new SearchDesignerAdapter(R.layout.item_designer_list, null);
                activitySearchCategoryRecyclerview.setAdapter(searchDesignerAdapter);
                searchDesignerAdapter.setOnItemClickListener(this);
                searchDesignerAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        DesignerListBean designerListBean = (DesignerListBean) adapter.getData().get(position);
                        switch (view.getId()) {
                            case R.id.item_designer_list_material_1:
//                                ActivityUtils.startActivity(MaterialDetailActivity.class);
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
                searchLayoutImg.setVisibility(View.VISIBLE);
                activitySearchCategoryRecyclerview.setLayoutManager(new LinearLayoutManager(this));
                searchMaterialAdapter = new SearchMaterialAdapter(R.layout.item_material_list, null);
                activitySearchCategoryRecyclerview.setAdapter(searchMaterialAdapter);
                searchMaterialAdapter.setOnItemClickListener(this);
                searchMaterialAdapter.setOnLoadMoreListener(this, activitySearchCategoryRecyclerview);
                break;
        }
    }

    private int pageIndex = 1;

    //选择的标签.
    private ArrayList<Integer> idList;

    @Override
    public void requestData() {
        StringBuffer tempIdList = new StringBuffer();
        if (!ObjectUtils.isEmpty(idList)) {
            for (int i = 0; i < idList.size(); i++) {
                if (i == idList.size() - 1) {
                    tempIdList.append(idList.get(i));
                } else {
                    tempIdList.append(idList.get(i) + ",");
                }
            }
        }
        searchCategoryPresenter.requestData(type, searchLayoutContent.getText().toString().trim(),
                20, pageIndex, tempIdList.toString());
    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void firstInEmpty() {
        switch (type) {

            case 2:
                searchScenarioAdapter.setNewData(null);
                searchScenarioAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.search_empty_view,null));
                break;

            case 3:
                searchShopAdapter.setNewData(null);
                searchShopAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.search_empty_view,null));
                break;

            case 4:
                searchDesignerAdapter.setNewData(null);
                searchDesignerAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.search_empty_view,null));
                break;

            case 5:
                searchMaterialAdapter.setNewData(null);
                searchMaterialAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.search_empty_view,null));
                break;
        }
    }

    @Override
    public void loadEnd() {
        switch (type) {

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

            case 2:
                ScenarioBean scenarioBean = (ScenarioBean) adapter.getData().get(position);
                Intent intent = new Intent(SearchCategoryActivity.this, ScenarioActivity.class);
                intent.putExtra("id", scenarioBean.getSceneId());
                ActivityUtils.startActivity(intent);
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
                MaterialBean materialBean = (MaterialBean) adapter.getData().get(position);
                Intent materialIntent = new Intent(SearchCategoryActivity.this, MaterialDetailActivity.class);
                materialIntent.putExtra("id", materialBean.getMaterialId());
                ActivityUtils.startActivity(materialIntent);
                break;
        }
    }


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
                        if (ObjectUtils.isEmpty(labelPop)) {
                            searchCategoryPresenter.requestDesignerLabel();
                        } else {
                            labelPop.showAtLocation(searchLayoutContent, Gravity.TOP, 0, 0);
                        }
                        break;

                    case 5:
                        if (ObjectUtils.isEmpty(labelPop)) {
                            searchCategoryPresenter.requestMaterialLabel();
                        } else {
                            labelPop.showAtLocation(searchLayoutContent, Gravity.TOP, 0, 0);
                        }
                        break;
                }

                break;
        }
    }

    EasyPopup labelPop;


    private void setDesignerPopInfo(List list) {
        if (ObjectUtils.isEmpty(labelPop)) {
            creatLabelPop();

            setDesignLabel(list);
        }
        labelPop.showAtLocation(searchLayoutContent, Gravity.TOP, 0, 0);
    }

    private void setDesignLabel(List list) {
        Button cancle = labelPop.findViewById(R.id.pop_label_cancle);
        Button confirm = labelPop.findViewById(R.id.pop_label_confirm);
        RecyclerView recyclerView = labelPop.findViewById(R.id.pop_label_recyclerview);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        DesignerLabelAdapter adapter = new DesignerLabelAdapter(R.layout.item_label, list);

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TextView labelName = (TextView) adapter.getViewByPosition(recyclerView, position, R.id.item_label_name);

                DesignerLabelBean designerLabelBean = (DesignerLabelBean) adapter.getData().get(position);

                if (designerLabelBean.isChecked()) {
                    designerLabelBean.setChecked(false);
                    labelName.setTextColor(getResources().getColor(R.color.a4a4a4));
                    labelName.setBackground(getResources().getDrawable(R.drawable.bg_goods_spec_item_uncheck));
                    idList.remove(designerLabelBean.getId());
                } else {
                    idList.add(designerLabelBean.getId());
                    designerLabelBean.setChecked(true);
                    labelName.setTextColor(getResources().getColor(R.color.themeColor));
                    labelName.setBackground(getResources().getDrawable(R.drawable.bg_goods_spec_item_check));
                }

            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < adapter.getData().size(); i++) {
                    adapter.getData().get(i).setChecked(false);
                }
                adapter.notifyDataSetChanged();
                idList.clear();

            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                labelPop.dismiss();
            }
        });

        labelPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                pageIndex=1;
                requestData();
            }
        });
    }


    private void setMaterialLabel(List list) {
        Button cancle = labelPop.findViewById(R.id.pop_label_cancle);
        Button confirm = labelPop.findViewById(R.id.pop_label_confirm);
        RecyclerView recyclerView = labelPop.findViewById(R.id.pop_label_recyclerview);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        MaterialLabelAdapter adapter = new MaterialLabelAdapter(R.layout.item_label, list);

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TextView labelName = (TextView) adapter.getViewByPosition(recyclerView, position, R.id.item_label_name);

                MaterialLabelBean materialLabelBean = (MaterialLabelBean) adapter.getData().get(position);

                if (materialLabelBean.isChecked()) {
                    materialLabelBean.setChecked(false);
                    labelName.setTextColor(getResources().getColor(R.color.a4a4a4));
                    labelName.setBackground(getResources().getDrawable(R.drawable.bg_goods_spec_item_uncheck));
                    idList.remove(materialLabelBean.getId());
                } else {
                    idList.add(materialLabelBean.getId());
                    materialLabelBean.setChecked(true);
                    labelName.setTextColor(getResources().getColor(R.color.themeColor));
                    labelName.setBackground(getResources().getDrawable(R.drawable.bg_goods_spec_item_check));
                }

            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < adapter.getData().size(); i++) {
                    adapter.getData().get(i).setChecked(false);
                }
                adapter.notifyDataSetChanged();
                idList.clear();

            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                labelPop.dismiss();
            }
        });

        labelPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                pageIndex=1;
                requestData();
            }
        });
    }


    private void creatLabelPop() {
        labelPop = EasyPopup.create()
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
        idList = new ArrayList<>();

    }

    @Override
    public void showDesignerCategory(List<DesignerLabelBean> labelBeans) {
        setDesignerPopInfo(labelBeans);
    }

    @Override
    public void showMaterialLabel(List<MaterialLabelBean> labelBeans) {
        setMaterialPopInfo(labelBeans);
    }

    private void setMaterialPopInfo(List<MaterialLabelBean> labelBeans) {
        if (ObjectUtils.isEmpty(labelPop)) {
            creatLabelPop();
            setMaterialLabel(labelBeans);
        }
        labelPop.showAtLocation(searchLayoutContent, Gravity.TOP, 0, 0);
    }
}