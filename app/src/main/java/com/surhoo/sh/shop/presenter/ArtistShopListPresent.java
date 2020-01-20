package com.surhoo.sh.shop.presenter;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.shop.view.ArtistShopListView;

public interface ArtistShopListPresent extends BasePresenter<ArtistShopListView> {


    void requestData(String searchName,
                     int pageSize, int pageIndex, String idList);

    void requestShopLabel(String requestTag);

}
