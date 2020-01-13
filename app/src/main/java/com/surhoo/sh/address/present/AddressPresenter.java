package com.surhoo.sh.address.present;

import com.surhoo.sh.address.view.AddressView;
import com.surhoo.sh.base.BasePresenter;

public interface AddressPresenter extends BasePresenter<AddressView> {

    void requestAddressList(String requestTag);

    void deleteAddress(String requestTag,int id);

}
