package com.surhoo.sh.order.adapter;

import android.app.Activity;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goyourfly.multi_picture.MultiPictureView;
import com.surhoo.sh.R;
import com.surhoo.sh.common.matisse.MatisseImageUtil;
import com.surhoo.sh.common.util.GlideUtil;
import com.surhoo.sh.order.OrderEvaluationActivity;
import com.surhoo.sh.order.bean.OrderListBean;
import com.surhoo.sh.order.bean.UpLoadEvaluationBean;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OrderEvaluationAdapter extends BaseQuickAdapter<UpLoadEvaluationBean, BaseViewHolder> {


    private Activity activity;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public OrderEvaluationAdapter(int layoutResId, @Nullable List<UpLoadEvaluationBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, UpLoadEvaluationBean item) {

        helper.addOnClickListener(R.id.item_evaluation_rating_1,
                R.id.item_evaluation_rating_2,R.id.item_evaluation_rating_3,
                R.id.item_evaluation_rating_4,R.id.item_evaluation_rating_5);

        ImageView imageView = (ImageView) helper.getView(R.id.item_evaluation_img);

        GlideUtil.loadDefaultImg(mContext, item.getImg(), imageView);

        MultiPictureView pictureView = (MultiPictureView) helper.getView(R.id.item_evaluation_multiPictureView);

        LogUtils.v("sadsadasada",pictureView);

        helper.addOnClickListener(R.id.item_evaluation_multiPictureView);
//
//        pictureView.setItemClickCallback(new MultiPictureView.ItemClickCallback() {
//            @Override
//            public void onItemClicked(@NotNull View view, int i, @NotNull ArrayList<Uri> arrayList) {
//            }
//        });
//
        pictureView.setAddClickCallback(new MultiPictureView.AddClickCallback() {
            @Override
            public void onAddClick(@NotNull View view) {

                AndPermission.with(mContext)
                        .runtime()
                        .permission(Permission.CAMERA, Permission.WRITE_EXTERNAL_STORAGE)
                        .onGranted(permissions -> {
                            MatisseImageUtil.choosePhoto(activity, item.getMaxCount(), item.getPosition());
                        })
                        .onDenied(permissions -> {
                            // Storage permission are not allowed.
                            ToastUtils.showShort(mContext.getResources().getString(R.string.applyPermissionFail));
                        })
                        .start();
            }
        });

        pictureView.setDeleteClickCallback(new MultiPictureView.DeleteClickCallback() {
            @Override
            public void onDeleted(@NotNull View view, int i) {
                pictureView.removeItem(i);
                item.setMaxCount(item.getMaxCount() + 1);
            }
        });

    }
}
