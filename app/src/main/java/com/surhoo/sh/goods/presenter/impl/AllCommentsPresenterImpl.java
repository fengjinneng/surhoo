package com.surhoo.sh.goods.presenter.impl;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.surhoo.sh.common.util.Api;
import com.surhoo.sh.goods.bean.CommentBean;
import com.surhoo.sh.goods.presenter.AllCommentsPresenter;
import com.surhoo.sh.goods.view.AllCommentsView;

import java.util.List;

public class AllCommentsPresenterImpl implements AllCommentsPresenter {

    private static final String TAG ="AllCommentsPresenterImpl" ;
    AllCommentsView allCommentsView;

    private Context context;

    @Override
    public void bindView(Context ctx, AllCommentsView view) {
        this.context = ctx;
        allCommentsView = view;
    }

    @Override
    public void unBindView() {
        allCommentsView = null;
    }



    @Override
    public void requestData(int pageSize, int pageIndex, int goodsId) {


        String url = Api.ALLGOODSCOMMENTS;
        GetRequest<String> request = OkGo.<String>get(url)
                .tag(context)
                .headers("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJuaWNrbmFtZSI6IlJIRVRUIiwiaGVhZGltZ3VybCI6Imh0dHBzOi8vd3gucWxvZ28uY24vbW1vcGVuL3ZpXzMyL3R3MUxPU0piT1h6aFhPOERTOWljajZBdXhRRVNEakhsWU1uOHZJaGxDNGlhc0Qza055U0UyYlM2VUY5OU9hTWx0Qjl4dEJmdmtJUEFHRmNBV28yeW9taWNBLzEzMiIsImlkIjoxMDAxMDA4NSwiZXhwIjoxNTY5MjA0MDMzLCJvcGVuaWQiOiJvbDR5bDVQNHl3MmozTmJqa1UzMHkyVnJhMEh3IiwiYXBwaWQiOiJ3eDQxMDU2ODQ5OGYzYzljYmEifQ.UAmnuKj__D2XHWIIQDkOv7omnZUSyEcCTmRb-xhaAA8NdewES3YLl6df2JUQODIzvvImJzQ9XiDf-bwELIUIWQ")
                .params("pageSize", pageSize)
                .params("pageIndex", pageIndex)
                .params("goodsId", goodsId);


        StringCallback stringCallback = new StringCallback() {

            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v(TAG, response.body());
                try {
                    if (response.code() == 200) {
                        JSONObject jsonObject = JSONObject.parseObject(response.body());
                        if (!StringUtils.isEmpty(jsonObject.getString("code"))) {
                            ToastUtils.showShort(jsonObject.getString("msg"));
                            return;
                        }
                        CommentBean commentBean = jsonObject.toJavaObject(CommentBean.class);
                        if (commentBean.getTotal() == 0) {
                            return;
                        }
                        List<CommentBean.ListBean> listBeans = commentBean.getList();
                        boolean hasNextPage = commentBean.getHasNextPage();
                        if (pageIndex == 1) {
                            allCommentsView.refresh(listBeans);
                            if (!hasNextPage) {
                                allCommentsView.loadEnd();
                            }
                            return;
                        }
                        allCommentsView.loadData(listBeans);
                        if (!hasNextPage) {
                            allCommentsView.loadEnd();
                        }
//

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                allCommentsView.showToastMsg(response.message());
            }
        };

        request.execute(stringCallback);


    }
}
