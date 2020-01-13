package com.surhoo.sh;

import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.goyourfly.multi_picture.ImageLoader;
import com.goyourfly.multi_picture.MultiPictureView;
import com.surhoo.sh.order.OrderEvaluationActivity;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivityActivity extends AppCompatActivity {


    @BindView(R.id.multi_image_view)
    MultiPictureView multiImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_activity);
        ButterKnife.bind(this);


        String img = "https://shanghusm-shanghai-picture.oss-cn-shanghai.aliyuncs.com/img/xcx/material/Zy3bnSJEBN3WGyQbQHGJaSYCEMyFpnRF.jpg";

        ArrayList<Uri> da = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            da.add(Uri.parse(img));
        }

        multiImageView.setList(da);

        MultiPictureView.setImageLoader(new ImageLoader() {
            @Override
            public void loadImage(@NotNull ImageView imageView, @NotNull Uri uri) {
                Glide.with(TestActivityActivity.this).load(uri).into(imageView);
            }
        });

        multiImageView.setItemClickCallback(new MultiPictureView.ItemClickCallback() {
            @Override
            public void onItemClicked(@NotNull View view, int i, @NotNull ArrayList<Uri> arrayList) {
            }
        });

        multiImageView.setAddClickCallback(new MultiPictureView.AddClickCallback() {
            @Override
            public void onAddClick(@NotNull View view) {

            }
        });

        multiImageView.setDeleteClickCallback(new MultiPictureView.DeleteClickCallback() {
            @Override
            public void onDeleted(@NotNull View view, int i) {
                multiImageView.removeItem(i);
            }
        });

    }

}
