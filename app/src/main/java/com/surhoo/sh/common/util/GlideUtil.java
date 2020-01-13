package com.surhoo.sh.common.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.surhoo.sh.R;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class GlideUtil {


    public static void loadCenterCropImg(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.default_img_square)
                .error(R.mipmap.default_img_square)
                .centerCrop()
                //.priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(url+"?x-oss-process=image/resize,w_320").thumbnail(0.1f).apply(options).into(imageView);

    }

    /**
     * 加载普通图片
     */
    public static void loadDefaultImg(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.default_img_square)
                .error(R.mipmap.default_img_square)

                //.priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(url).thumbnail(0.1f).apply(options).into(imageView);

    }


    /**
     * 加载上边是有圆角的图片，商品列表的
     */
    public static void loadBannerImage(Context context, String url, ImageView imageView) {


        RequestOptions options = new RequestOptions()


                .placeholder(R.mipmap.default_img_rect)
                .error(R.mipmap.default_img_rect)

                //.priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL)

//                .bitmapTransform(new CropSquareTransformation());
                .bitmapTransform(new RoundedCornersTransformation( 10, 0));

        Glide.with(context).load(url).thumbnail(0.1f).apply(options).into(imageView);

    }


    /**
     * 加载上边是有圆角的图片，商品列表的
     */
    public static void loadGoodsImage(Context context, String url, ImageView imageView) {


        RequestOptions options = new RequestOptions()

                .placeholder(R.mipmap.default_img_square)
                .error(R.mipmap.default_img_square)

                //.priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL)

//                .bitmapTransform(new CropSquareTransformation());
                .bitmapTransform(new RoundedCornersTransformation( 10, 0, RoundedCornersTransformation.CornerType.TOP));

        Glide.with(context).load(url).thumbnail(0.1f).apply(options).into(imageView);

    }

    /**
     * 加载圆形图片
     */
    public static void loadCircleImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .circleCrop()//设置圆形
                .placeholder(R.mipmap.default_img_round)
                .error(R.mipmap.default_img_round)

                //.priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(url+"?x-oss-process=image/resize,w_320").thumbnail(0.1f).apply(options).into(imageView);

    }

}
