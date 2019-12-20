package com.surhoo.sh.address.present;

import com.surhoo.sh.address.bean.AddressBean;
import com.surhoo.sh.address.view.EditAddressView;
import com.surhoo.sh.base.BasePresenter;

public interface EditAddressPresent extends BasePresenter<EditAddressView> {


    void addAddress(AddressBean addressBean);
    void updateAddress(AddressBean addressBean);

}
