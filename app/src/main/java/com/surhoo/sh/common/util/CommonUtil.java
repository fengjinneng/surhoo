package com.surhoo.sh.common.util;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.surhoo.sh.R;
import com.surhoo.sh.goods.bean.GoodDetailBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonUtil {


    public static void setBannerInfo(ConvenientBanner convenientBanner, List<String> bannerDatas) {
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, bannerDatas);
//        convenientBanner.setPageIndicator(new int[]{R.mipmap.banner_unchoiced, R.mipmap.banner_choiced});
        convenientBanner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        //设置如果只有一组数据时不能滑动
        convenientBanner.setPointViewVisible(bannerDatas.size() == 1 ? false : true); // 指示器
        convenientBanner.setManualPageable(bannerDatas.size() == 1 ? false : true);//设置false,手动影响（设置了该项无法手动切换）
    }


    public static GoodDetailBean.SkuListBean  getSku(List<GoodDetailBean.SkuListBean> skuListBeans,String... spec){

        int length = 0;

        for (int i = 0; i < skuListBeans.size(); i++) {

            String[] split = skuListBeans.get(i).getGoodsSkuName().split("_");

            List<String> strings = Arrays.asList(split);

            for (int j = 0; j < spec.length; j++) {

                if (strings.contains(spec[j])) {
                    length++;
                }
            }

            if(length==spec.length){
                return skuListBeans.get(i);
            }else {
                length=0;
            }
        }
        return null;
    }
}
