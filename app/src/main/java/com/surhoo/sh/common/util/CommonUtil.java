package com.surhoo.sh.common.util;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

import java.util.List;

public class CommonUtil {


    public static void setBannerInfo(ConvenientBanner convenientBanner, List<String> bannerDatas) {
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, bannerDatas);
//        scenarioLayoutViewHolder.convenientBanner.setPageIndicator(new int[]{R.mipmap.banner_unchoiced, R.mipmap.banner_choiced});
        convenientBanner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        //设置如果只有一组数据时不能滑动
        convenientBanner.setPointViewVisible(bannerDatas.size() == 1 ? false : true); // 指示器
        convenientBanner.setManualPageable(bannerDatas.size() == 1 ? false : true);//设置false,手动影响（设置了该项无法手动切换）
    }

}
