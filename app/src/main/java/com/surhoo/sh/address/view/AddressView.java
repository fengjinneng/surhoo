package com.surhoo.sh.address.view;

import com.surhoo.sh.address.bean.AddressBean;
import com.surhoo.sh.base.NoPageBaseView;
import com.surhoo.sh.base.NoPageListBaseView;

public interface AddressView extends NoPageListBaseView<AddressBean> {

    void getDeleteResult(int position);

}