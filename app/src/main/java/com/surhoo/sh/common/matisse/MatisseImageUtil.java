package com.surhoo.sh.common.matisse;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.surhoo.sh.R;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by PiGu on 2017/4/30.
 */
public class MatisseImageUtil {

    /**
     * 知乎的图片选择器matisse
     *
     * @param maxCount            最大选择数量
     * @param REQUEST_CODE_CHOOSE 请求响应吗
     */
    public static void choosePhoto(Activity activity, int maxCount, int REQUEST_CODE_CHOOSE) {

        AndPermission.with(activity)
                .runtime()
                .permission(Permission.CAMERA, Permission.WRITE_EXTERNAL_STORAGE)
                .onGranted(permissions -> {

                    Matisse.from(activity)
                            .choose(MimeType.ofImage())
                            .countable(true)//有序选择图片
                            .maxSelectable(maxCount)//最大选择数
                            .gridExpectedSize(320)//图片显示表格的大小120
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)//图像选择和预览活动所需的方向。
                            .thumbnailScale(0.85f)//缩放比例
//                .theme(R.style.Matisse_Zhihu)//主题  知乎de主题
                            .theme(R.style.Matisse_Dracula)//主题  暗色主题
                            .imageEngine(new MyGlideEngine())//加载方式
                            .showSingleMediaType(true)
                            .capture(true)//设置是否可以拍照  ---> 需要配置清单文件
                            .captureStrategy(new CaptureStrategy(true, "com.surhoo.sh.fileprovider"))//存储到哪里
                            .forResult(REQUEST_CODE_CHOOSE);// 请求响应吗

                })
                .onDenied(permissions -> {
                    // Storage permission are not allowed.
                    ToastUtils.showShort(activity.getResources().getString(R.string.applyPermissionFail));
                })
                .start();

    }

    public static void chooseOnlyOnePhoto(Activity activity, int REQUEST_CODE_CHOOSE){


        AndPermission.with(activity)
                .runtime()
                .permission(Permission.CAMERA, Permission.WRITE_EXTERNAL_STORAGE)
                .onGranted(permissions -> {

                    Matisse.from(activity)
                            .choose(MimeType.ofImage())
                            .countable(false)
                            .maxSelectable(1)
                            //是否需要拍照
                           .capture(true)
                            //兼容7.0系统
                            .theme(R.style.Matisse_Dracula)//主题  暗色主题
                            .captureStrategy(new CaptureStrategy(true, "com.surhoo.sh.fileprovider"))
                            .showSingleMediaType(true)
//                        .addFilter(new GifSizeFilter(320))
//                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                            .thumbnailScale(0.85f)
                            .imageEngine(new MyGlideEngine())
                            .forResult(REQUEST_CODE_CHOOSE);

                })
                .onDenied(permissions -> {
                    // Storage permission are not allowed.
                    ToastUtils.showShort(activity.getResources().getString(R.string.applyPermissionFail));
                })
                .start();

    }


    public static void chooseVideo(Activity activity, int REQUEST_CODE_CHOOSE) {
        Matisse.from(activity)
                .choose(MimeType.ofVideo())
                .maxSelectable(1)//最大选择数量为9
//                .gridExpectedSize(320)//图片显示表格的大小120
//                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)//图像选择和预览活动所需的方向。
//                .thumbnailScale(0.85f)//缩放比例
                .theme(R.style.Matisse_Zhihu)//主题  知乎de主题
                //.theme(R.style.Matisse_Dracula)//主题  暗色主题
                .imageEngine(new MyGlideEngine())//加载方式
                .addFilter(new GifSizeFilter(50 * Filter.K * Filter.K))//限制文件大小
//                .capture(true)//设置是否可以拍照  ---> 需要配置清单文件
                .captureStrategy(new CaptureStrategy(true, "com.company.qcy.fileprovider"))//存储到哪里
                .forResult(REQUEST_CODE_CHOOSE);// 请求响应吗
    }



    /**
     *  根据Uri获取文件真实地址
     */
    public static String getRealFilePath(Context context, Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String realPath = null;
        if (scheme == null)
            realPath = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            realPath = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA},
                    null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        realPath = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        if (TextUtils.isEmpty(realPath)) {
            if (uri != null) {
                String uriString = uri.toString();
                int index = uriString.lastIndexOf("/");
                String imageName = uriString.substring(index);
                File storageDir;

                storageDir = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES);
                File file = new File(storageDir, imageName);
                if (file.exists()) {
                    realPath = file.getAbsolutePath();
                } else {
                    storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    File file1 = new File(storageDir, imageName);
                    realPath = file1.getAbsolutePath();
                }
            }
        }
        return realPath;
    }



    // 根据真实路径获取文件名
    public static String getFileName(String realFilePath) {
        return realFilePath.substring(realFilePath.lastIndexOf("/") + 1, realFilePath.length());
    }

    /**
     * 压缩图片大小
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static Bitmap revitionImageSize(String path) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(path)));
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(in, null, options);
        in.close();
        int i = 0;
        Bitmap bitmap = null;
        while (true) {
            if ((options.outWidth >> i <= 1000) && (options.outHeight >> i <= 1000)) {
                in = new BufferedInputStream(new FileInputStream(new File(path)));
                options.inSampleSize = (int) Math.pow(2.0D, i);
                options.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeStream(in, null, options);
                break;
            }
            i += 1;
        }
        return bitmap;
    }


    /**
     * 压缩图片大小
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static Bitmap revitionImageSize(String path, int maxSize) throws IOException {
        Bitmap image = revitionImageSize(path);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > maxSize) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }

        LogUtils.e("baos----" + baos.size());
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;

    }


    /**
     * 保存方法
     */
    public static String saveBitmap(Bitmap bm) {
        LogUtils.e("tag", "保存图片");
        File outDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!outDir.exists()) {
            outDir.mkdirs();
        }
        File f = new File(outDir, System.currentTimeMillis() + ".jpg");
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            LogUtils.i("tag", "已经保存");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f.getAbsolutePath();
    }


    public static Bitmap[] getBitmap(ArrayList<String> picName) throws IOException {
        Bitmap[] b = new Bitmap[picName.size()];
        //根据图片数组长度来，循环获取我们需要的图片数组</span>
        for (int i = 0; i < picName.size(); i++) {
            URL url = new URL(picName.get(i).toString());
            LogUtils.e("picName.get(i).toString()--"+picName.get(i).toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setConnectTimeout(50000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                b[i] = bitmap;
            }
        }

        return b;
    }


    public void inputstreamtofile(InputStream ins, File file) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);

            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Bitmap drawableToBitmap(Drawable drawable) {
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }


}
