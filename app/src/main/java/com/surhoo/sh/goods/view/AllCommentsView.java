package com.surhoo.sh.goods.view;

import com.surhoo.sh.base.BaseView;
import com.surhoo.sh.goods.bean.CommentBean;
import java.util.List;

public interface AllCommentsView extends BaseView {


    void firstInEmpty();

    void loadEnd();

    void refresh(List<CommentBean.ListBean> list);

    void loadData(List<CommentBean.ListBean> list);


}
