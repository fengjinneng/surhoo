package com.surhoo.sh.scenario.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.surhoo.sh.common.util.Api;
import com.surhoo.sh.common.util.NetworkReturnUtil;
import com.surhoo.sh.scenario.bean.ScenarioBean;
import com.surhoo.sh.scenario.view.IScenarioView;


public class ScenarioPresenterImpl implements IScenarioPresenter {


    public ScenarioPresenterImpl() {
    }

    IScenarioView scenarioView;

    private Context context;

    @Override
    public void bindView(Context ctx, IScenarioView view) {

        context = ctx;
        scenarioView = view;
    }

    @Override
    public void unBindView() {
        scenarioView = null;
    }

    @Override
    public void requestData(int sceneId) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("sceneId",sceneId);

        NetworkReturnUtil.requestOne(scenarioView,context,Api.SCENARIODETAIL,httpParams,ScenarioBean.class);

    }


    public void requestData2(String url, int sceneId) {

        GetRequest<String> request = OkGo.<String>get(url)
                .tag(this)
                .headers("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJuaWNrbmFtZSI6IlJIRVRUIiwiaGVhZGltZ3VybCI6Imh0dHBzOi8vd3gucWxvZ28uY24vbW1vcGVuL3ZpXzMyL3R3MUxPU0piT1h6aFhPOERTOWljajZBdXhRRVNEakhsWU1uOHZJaGxDNGlhc0Qza055U0UyYlM2VUY5OU9hTWx0QlZ2R0R4cjZ6aWFtMThoV3RDOTZGNXRnLzEzMiIsImlkIjoxMDAxMDA4NSwiZXhwIjoxNTY4MTg0OTY4LCJvcGVuaWQiOiJvbDR5bDVQNHl3MmozTmJqa1UzMHkyVnJhMEh3IiwiYXBwaWQiOiJ3eDQxMDU2ODQ5OGYzYzljYmEifQ.2r1BKcYbxd_NWkEB6OVAn1KKX2EfJIOC-7sHG59FDzHPqtGjyDM94CAZfSuuU7v01t4K2Wf6ugmSUh_td-NOLg")
                .params("sceneId", sceneId);


        StringCallback stringCallback = new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.v("DSADASSADA", response.body());

                try {
                    if (response.code() == 200) {

//                        NetworkReturnUtil.parseArray(scenarioView, JSONObject.parseObject(response.body()), ScenarioBean.class, pageIndex);

                        JSONObject jsonObject = JSONObject.parseObject(response.body());

                        if (!StringUtils.isEmpty(jsonObject.getString("code"))) {
                            ToastUtils.showShort(jsonObject.getString("msg"));
                            return;
                        }

                        Integer total = jsonObject.getInteger("total");
                        if (total == 0) {
                            return;
                        }

//
//                        JSONArray array = jsonObject.getJSONArray("list");
//
//                        if (ObjectUtils.isEmpty(array)) {
//                            //第一次进来就是空的
//                            if (pageIndex == 1) {
//                                scenarioView.firstInEmpty();
//
//                            }
//                            scenarioView.loadEnd();
//
//                            return;
//                        }
//
//                        Boolean hasNextPage = jsonObject.getBoolean("hasNextPage");
//                        List<ScenarioBean> beans = JSONObject.parseArray(array.toString(), ScenarioBean.class);
//
//                        if (pageIndex == 1) {
//                            scenarioView.refresh(beans);
//                            if (!hasNextPage) {
//                                scenarioView.loadEnd();
//                            }
//                            return;
//                        }
//
//                        scenarioView.loadData(beans);
//                        if (!hasNextPage) {
//                            scenarioView.loadEnd();
//                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                scenarioView.showToastMsg(response.message());
            }
        };

        request.execute(stringCallback);

    }





}
