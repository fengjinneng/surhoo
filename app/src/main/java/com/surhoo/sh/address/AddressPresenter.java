package com.surhoo.sh.address;

import com.surhoo.sh.base.BasePresenter;

public interface AddressPresenter extends BasePresenter<AddressView> {

    void requestData();

    void deleteAddress(int id);

}
