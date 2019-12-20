package com.surhoo.sh.address.present;

import com.surhoo.sh.address.view.AddressView;
import com.surhoo.sh.base.BasePresenter;

public interface AddressPresenter extends BasePresenter<AddressView> {

    void requestData();

    void deleteAddress(int id,int position);

}
