package com.surhoo.sh;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

public class App extends Application {


    private static boolean isDebug = true;


    @Override
    public void onCreate() {
        super.onCreate();

        initOkGo();


        if (isDebug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化


    }

    /**
     * 初始化OKGO
     */
    private void initOkGo() {
        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
        HttpHeaders headers = new HttpHeaders();

        headers.put("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJuaWNrbmFtZSI6IlJIRVRUIiwiaGVhZGltZ3VybCI6Imh0dHBzOi8vd3gucWxvZ28uY24vbW1vcGVuL3ZpXzMyL3R3MUxPU0piT1h6aFhPOERTOWljajZBdXhRRVNEakhsWU1uOHZJaGxDNGlhc0Qza055U0UyYlM2VUY5OU9hTWx0QlZ2R0R4cjZ6aWFtMThoV3RDOTZGNXRnLzEzMiIsImlkIjoxMDAxMDA4NSwiZXhwIjoxNTY4MTg0OTY4LCJvcGVuaWQiOiJvbDR5bDVQNHl3MmozTmJqa1UzMHkyVnJhMEh3IiwiYXBwaWQiOiJ3eDQxMDU2ODQ5OGYzYzljYmEifQ.2r1BKcYbxd_NWkEB6OVAn1KKX2EfJIOC-7sHG59FDzHPqtGjyDM94CAZfSuuU7v01t4K2Wf6ugmSUh_td-NOLg");
//        headers.put("Content-Type", "application/json;charset=UTF-8");

//        headers.put("app_id", "6016011000110001");
//        headers.put("app_key", "NTA1QUEzRTFDQjI1RTYyOEM1MzY0NzkwRTE4M0ZCREU=");
//        headers.put("token", "eyJhbGciOiJIUzUxMiJ9.eyJuaWNrbmFtZSI6IlpKRyIsImhlYWRpbWd1cmwiOiJodHRwczovL3d4LnFsb2dvLmNuL21tb3Blbi92aV8zMi9CVkFzZnFyckg3Vmlja095aWNHcFRZTTdTOUc4eFdwQ0prckFGbWE5aWM2UWVYTUVJYWtGY0YwOWRCWERCOG1OZ3lTQ0k0UWh5Q2pRRUpRaWM2M1BVcHBpY3pRLzEzMiIsImlkIjoxMDAxMDExOSwiZXhwIjoxNTY4MTg0NTIyLCJvcGVuaWQiOiJvbDR5bDVPZXlyclQ1UmdCWVE1RXJJN3FLSy1VIiwiYXBwaWQiOiJ3eDQxMDU2ODQ5OGYzYzljYmEifQ.QT7NLAcl1_GpbY1EDlQ5BhUFYVilSZxhO43uxTIE7Aes6d9KAUTFTdmKuKOjaMJ1FLiLD1YAT7Mc78U424y3NQ");
//        headers.put("deviceId", deviceId);
//        HttpParams params = new HttpParams();
//        params.put("commonParamsKey1", "commonParamsValue1");     //param支持中文,直接传,不要自己编码
//        params.put("commonParamsKey2", "这里支持中文参数");
        //----------------------------------------------------------------------------------------//

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor);                                 //添加OkGo默认debug日志
        //第三方的开源库，使用通知显示当前请求的log，不过在做文件下载的时候，这个库好像有问题，对文件判断不准确
        //builder.addInterceptor(new ChuckInterceptor(this));
//        builder.addNetworkInterceptor(new StethoInterceptor());

        //超时时间设置，默认60秒
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);   //全局的连接超时时间

        //自动管理cookie（或者叫session的保持），以下几种任选其一就行
        //builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));            //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));              //使用数据库保持cookie，如果cookie不过期，则一直有效
        //builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));            //使用内存保持cookie，app退出后，cookie消失

        //https相关设置，以下几种方案根据需要自己设置
        //方法一：信任所有证书,不安全有风险
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
        //方法二：自定义信任规则，校验服务端证书
//        HttpsUtils.SSLParams sslParams2 = HttpsUtils.getSslSocketFactory(new SafeTrustManager());
        //方法三：使用预埋证书，校验服务端证书（自签名证书）
        //HttpsUtils.SSLParams sslParams3 = HttpsUtils.getSslSocketFactory(getAssets().open("srca.cer"));
        //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
//        HttpsUtils.SSLParams sslParams4 = HttpsUtils.getSslSocketFactory(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"));
        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
        //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
//        builder.hostnameVerifier(new SafeHostnameVerifier());

        // 其他统一的配置
        // 详细说明看GitHub文档：https://github.com/jeasonlzy/
        OkGo.getInstance().init(this)                           //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置会使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(5)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .addCommonHeaders(headers);                 //全局公共头
    }


}

