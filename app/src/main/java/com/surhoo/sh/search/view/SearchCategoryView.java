package com.surhoo.sh.search.view;

import com.surhoo.sh.base.PagerBaseView;
import com.surhoo.sh.designer.bean.DesignerLabelBean;
import com.surhoo.sh.material.bean.MaterialLabelBean;

import java.util.List;


public interface SearchCategoryView extends PagerBaseView {


    void showDesignerCategory(List<DesignerLabelBean> labelBeans);

    void showMaterialLabel(List<MaterialLabelBean> labelBeans);

}
