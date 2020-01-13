package com.surhoo.sh.order;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.UriUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.goyourfly.multi_picture.ImageLoader;
import com.goyourfly.multi_picture.MultiPictureView;
import com.surhoo.sh.R;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.bean.order.response.OrderDetailReturnBean;
import com.surhoo.sh.common.util.ClickUtil;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.order.adapter.OrderEvaluationAdapter;
import com.surhoo.sh.order.bean.UpLoadEvaluationBean;
import com.surhoo.sh.order.present.IOrderEvaluationPresent;
import com.surhoo.sh.order.present.impl.OrderEvaluationPresentImpl;
import com.surhoo.sh.order.view.IOrderEvaluationView;
import com.zhihu.matisse.Matisse;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class OrderEvaluationActivity extends BaseActivity implements IOrderEvaluationView {

    @BindView(R.id.toolbar_layout_back)
    ImageView toolbarLayoutBack;
    @BindView(R.id.toolbar_layout_title)
    TextView toolbarLayoutTitle;
    @BindView(R.id.activity_order_evaluation_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.activity_order_evaluation_submit)
    Button activityOrderEvaluationSubmit;

    private OrderDetailReturnBean orderDetailReturnBean;

    private OrderEvaluationAdapter adapter;

    @Override
    public int getContentView() {
        return R.layout.activity_order_evaluation;
    }

    @Override
    public boolean isFirstInLoadData() {
        return false;
    }

    private ArrayList<UpLoadEvaluationBean> upLoadEvaluationBeans;

    private IOrderEvaluationPresent present;

    @Override
    public void initView() {

        toolbarLayoutTitle.setText("评价");


        MultiPictureView.setImageLoader(new ImageLoader() {
            @Override
            public void loadImage(@NotNull ImageView imageView, @NotNull Uri uri) {
//                Glide.with(OrderEvaluationActivity.this).load(uri).into(imageView);
                GlideUtil.loadDefaultImg(OrderEvaluationActivity.this,String.valueOf(uri),imageView);
            }
        });

        upLoadEvaluationBeans = new ArrayList<>();

        orderDetailReturnBean = getIntent().getParcelableExtra("orderListBean");

        for (int i = 0; i < orderDetailReturnBean.getOrderDataList().size(); i++) {

            UpLoadEvaluationBean upLoadEvaluationBean = new UpLoadEvaluationBean();
            upLoadEvaluationBean.setOrderDataId(orderDetailReturnBean.getOrderDataList().get(i).getId());
            upLoadEvaluationBean.setOrderId(orderDetailReturnBean.getId());
            upLoadEvaluationBean.setPosition(i);
            upLoadEvaluationBean.setImg(orderDetailReturnBean.getOrderDataList().get(i).getGoodsImg());

//            upLoadEvaluationBean.setEvaluateName(adapter.getData().get(i).getEvaluateName());

            upLoadEvaluationBeans.add(upLoadEvaluationBean);
        }

    }



    //选择图片的最大数量
    private int maxCount = 6;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            List<Uri> mSelected = Matisse.obtainResult(data);
            maxCount = maxCount - mSelected.size();
            setData(requestCode, mSelected);
        }

    }

    @Override
    public void initData() {

        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            arrayList.add(new OrderDetailReturnBean());
        }

        adapter = new OrderEvaluationAdapter(R.layout.item_evaluation, upLoadEvaluationBeans);

        adapter.setActivity(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);


        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (ClickUtil.isFastClick()) {
                    setRating(adapter, view, position);
                }

            }
        });
        present = new OrderEvaluationPresentImpl();
        present.bindView(this,this);

    }

    private void setRating(BaseQuickAdapter adapter, View view, int position) {
        UpLoadEvaluationBean upLoadEvaluationBean = (UpLoadEvaluationBean) adapter.getData().get(position);
        ImageView rating1 = (ImageView) adapter.getViewByPosition(recyclerView, position, R.id.item_evaluation_rating_1);
        ImageView rating2 = (ImageView) adapter.getViewByPosition(recyclerView, position, R.id.item_evaluation_rating_2);
        ImageView rating3 = (ImageView) adapter.getViewByPosition(recyclerView, position, R.id.item_evaluation_rating_3);
        ImageView rating4 = (ImageView) adapter.getViewByPosition(recyclerView, position, R.id.item_evaluation_rating_4);
        ImageView rating5 = (ImageView) adapter.getViewByPosition(recyclerView, position, R.id.item_evaluation_rating_5);

        switch (view.getId()) {
            case R.id.item_evaluation_rating_1:
                rating1.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_checked));
                rating2.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_unchecked));
                rating3.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_unchecked));
                rating4.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_unchecked));
                rating5.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_unchecked));
                upLoadEvaluationBean.setMark(1);
                break;
            case R.id.item_evaluation_rating_2:
                rating1.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_checked));
                rating2.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_checked));
                rating3.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_unchecked));
                rating4.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_unchecked));
                rating5.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_unchecked));
                upLoadEvaluationBean.setMark(2);
                break;
            case R.id.item_evaluation_rating_3:
                rating1.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_checked));
                rating2.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_checked));
                rating3.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_checked));
                rating4.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_unchecked));
                rating5.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_unchecked));
                upLoadEvaluationBean.setMark(3);
                break;
            case R.id.item_evaluation_rating_4:
                rating1.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_checked));
                rating2.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_checked));
                rating3.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_checked));
                rating4.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_checked));
                rating5.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_unchecked));
                upLoadEvaluationBean.setMark(4);
                break;
            case R.id.item_evaluation_rating_5:
                rating1.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_checked));
                rating2.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_checked));
                rating3.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_checked));
                rating4.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_checked));
                rating5.setImageDrawable(getResources().getDrawable(R.mipmap.evalution_star_checked));
                upLoadEvaluationBean.setMark(5);
                break;
        }
    }


    private void setData(int position, List<Uri> mSelected) {

        MultiPictureView multiPictureView = (MultiPictureView) adapter.
                getViewByPosition(recyclerView, position, R.id.item_evaluation_multiPictureView);

        adapter.getData().get(position).setMaxCount(adapter.getData().get(position).getMaxCount() - mSelected.size());

        List<File> files = new ArrayList<>();

        for (int i = 0; i < mSelected.size(); i++) {

            File file = UriUtils.uri2File(mSelected.get(i), "111");

            files.add(file);

        }

        adapter.getData().get(position).setImgFiles(files);

        multiPictureView.addItem(mSelected);

    }

    @Override
    public void requestData() {




    }


    @OnClick({R.id.toolbar_layout_back,R.id.activity_order_evaluation_submit})
    public void onViewClicked(View view) {

        switch (view.getId()){
            case R.id.toolbar_layout_back:
                finish();
                break;

            case R.id.activity_order_evaluation_submit:

                for (int i = 0; i < upLoadEvaluationBeans.size(); i++) {
                    if(upLoadEvaluationBeans.get(i).getMark()==0){
                        ToastUtils.showShort("您还有未打分的!");
                        return;
                    }
                    if(StringUtils.isEmpty(upLoadEvaluationBeans.get(i).getEvaluateName())){
                        ToastUtils.showShort("您还有未填写内容的!");
                        return;
                    }
                }


                present.saveEvaluation(upLoadEvaluationBeans);

                break;
        }
    }

    @Override
    public void showToastMsg(String msg) {
        ToastUtils.showShort(msg);
    }


    @Override
    public void showStringData(String requestTag, String s) {
        LogUtils.v("asdsadsadsa",s);
    }
}
