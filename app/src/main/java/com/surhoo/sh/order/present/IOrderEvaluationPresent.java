package com.surhoo.sh.order.present;

import com.surhoo.sh.base.BasePresenter;
import com.surhoo.sh.order.bean.UpLoadEvaluationBean;
import com.surhoo.sh.order.view.IOrderEvaluationView;

import java.util.ArrayList;

public interface IOrderEvaluationPresent extends BasePresenter<IOrderEvaluationView> {


    void saveEvaluation(ArrayList<UpLoadEvaluationBean> upLoadEvaluationBean);

}
