package com.surhoo.sh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.SPUtils;
import com.githang.statusbar.StatusBarCompat;
import com.surhoo.sh.base.BaseActivity;
import com.surhoo.sh.common.util.NetworkImageHolderView;
import com.surhoo.sh.home.view.MainActivity;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        if (SPUtils.getInstance().getInt("firstIn")==-1||SPUtils.getInstance().getInt("firstIn")==0) {
            ActivityUtils.startActivity(GuideActivity.class);
            SPUtils.getInstance().put("firstIn",1);
        } else {
            ActivityUtils.startActivity(MainActivity.class);
        }

        finish();
    }
}
