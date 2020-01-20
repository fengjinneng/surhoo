package com.surhoo.sh.shop.view.impl;

import android.content.Intent;
import android.graphics.Color;
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
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.common.custom.MyLoadMoreView;
import com.surhoo.sh.common.util.ClickUtil;
import com.surhoo.sh.designer.adapter.DesignerLabelAdapter;
import com.surhoo.sh.designer.bean.SearchLabelBean;
import com.surhoo.sh.search.adapter.SearchShopAdapter;
import com.surhoo.sh.shop.bean.ShopListBean;
import com.surhoo.sh.shop.presenter.ArtistShopListPresent;
import com.surhoo.sh.shop.presenter.impl.ArtistShopListPresentImpl;
import com.surhoo.sh.shop.view.ArtistShopListView;
import com.zyyoona7.popup.EasyPopup;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class ArtistShopListActivity extends BaseActivity implements ArtistShopListView {

    @BindView(R.id.search_layout_content)
    EditText searchLayoutContent;
    @BindView(R.id.search_layout_back)
    ImageView searchLayoutBack;
    @BindView(R.id.search_layout_img)
    ImageView searchLayoutImg;
    @BindView(R.id.activity_artist_shop_list_recyclerView)
    RecyclerView recyclerView;

    private int pageIndex = 1;

    private ArtistShopListPresent artistShopListPresent;
    private SearchShopAdapter searchShopAdapter;

    @Override
    public int getContentView() {
        return R.layout.activity_artist_shop_list;
    }

    @Override
    public boolean isFirstInLoadData() {
        return true;
    }

    @Override
    public void initView() {


        searchLayoutImg.setVisibility(View.VISIBLE);

        initLoadingView();

        searchLayoutContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    KeyboardUtils.hideSoftInput(ArtistShopListActivity.this);
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

        artistShopListPresent = new ArtistShopListPresentImpl();
        artistShopListPresent.bindView(this,this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchShopAdapter = new SearchShopAdapter(R.layout.item_shop_list, null);
        recyclerView.setAdapter(searchShopAdapter);

        searchShopAdapter.setLoadMoreView(new MyLoadMoreView());

        searchShopAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageIndex++;
                requestData();

            }
        },recyclerView);

        searchShopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShopListBean shopListBean = (ShopListBean) adapter.getData().get(position);
                Intent intent = new Intent(ArtistShopListActivity.this,ArtistShopDetailActivity.class);
                intent.putExtra("id",shopListBean.getShopId());
                ActivityUtils.startActivity(intent);
            }
        });
    }

    //选择的标签.
    private ArrayList<Integer> idList;

    @Override
    public void requestData() {

        searchShopAdapter.setEmptyView(loadingView);

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
        artistShopListPresent.requestData(searchLayoutContent.getText().toString().trim(),
                20, pageIndex, tempIdList.toString());

    }

    @OnClick({R.id.search_layout_back, R.id.search_layout_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_layout_back:
                finish();
                break;
            case R.id.search_layout_img:
                if (ObjectUtils.isEmpty(labelPop)) {
                    artistShopListPresent.requestShopLabel("");
                } else {
                    labelPop.showAtLocation(searchLayoutContent, Gravity.TOP, 0, 0);
                }
                break;
        }
    }

    private EasyPopup labelPop;


    private void setLabelPopInfo(List list) {
        if (ObjectUtils.isEmpty(labelPop)) {
            creatLabelPop();

            setDesignLabel(list);
        }
        labelPop.showAtLocation(searchLayoutContent, Gravity.TOP, 0, 0);
    }

    private void setDesignLabel(List<SearchLabelBean> list) {
        Button cancle = labelPop.findViewById(R.id.pop_label_cancle);
        Button confirm = labelPop.findViewById(R.id.pop_label_confirm);
        RecyclerView recyclerView = labelPop.findViewById(R.id.pop_label_recyclerview);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        DesignerLabelAdapter adapter = new DesignerLabelAdapter(R.layout.item_label, list);

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (ClickUtil.isFastClick()) {
                    TextView labelName = (TextView) adapter.getViewByPosition(recyclerView, position, R.id.item_label_name);

                    SearchLabelBean designerLabelBean = (SearchLabelBean) adapter.getData().get(position);

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
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) {

                    for (int i = 0; i < adapter.getData().size(); i++) {
                        adapter.getData().get(i).setChecked(false);
                    }
                    adapter.notifyDataSetChanged();
                    idList.clear();
                }
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
                KeyboardUtils.hideSoftInput(ArtistShopListActivity.this);
                pageIndex = 1;
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


    private View loadingView;
    private View loadingEmptyView;
    private View loadingErrorView;

    private void initLoadingView() {
        loadingView = LayoutInflater.from(this).inflate(R.layout.load_data_loading_view, null);
        loadingEmptyView = LayoutInflater.from(this).inflate(R.layout.load_data_empty_view, null);
        ImageView emptyImg = (ImageView) loadingEmptyView.findViewById(R.id.load_data_empty_view_img);
        emptyImg.setImageDrawable(getResources().getDrawable(R.mipmap.invoice_empty_img));
        TextView emptyText = (TextView) loadingEmptyView.findViewById(R.id.load_data_empty_view_content);
        emptyText.setText("暂无相关店铺!");

        loadingErrorView = LayoutInflater.from(this).inflate(R.layout.load_data_error_view, null);

        TextView errorText = (TextView) loadingErrorView.findViewById(R.id.load_data_error_view_retry);
        errorText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ClickUtil.isFastClick()) {
                    requestData();
                }
            }
        });
    }


    @Override
    public void setHavePageEmptyView() {

        searchShopAdapter.setNewData(null);
        searchShopAdapter.setEmptyView(loadingEmptyView);
    }

    @Override
    public void setHavePageErrorView() {
        searchShopAdapter.setNewData(null);
        searchShopAdapter.setEmptyView(loadingErrorView);
    }

    @Override
    public void loadDataEnd() {
        searchShopAdapter.loadMoreEnd();
    }

    @Override
    public void firstLoadData(List list) {
        searchShopAdapter.setNewData(list);
        searchShopAdapter.loadMoreComplete();
    }

    @Override
    public void loadData(List list) {
        searchShopAdapter.addData(list);
        searchShopAdapter.loadMoreComplete();
    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void setNoPageEmptyView() {

    }

    @Override
    public void setNoPageErrorView() {

    }

    @Override
    public void showNoPageList(String requestTag, List<SearchLabelBean> list) {
        setLabelPopInfo(list);
    }
}
