package com.surhoo.sh.goods.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.goods.bean.GoodDetailBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

public class GoodsSpecPopAdapter extends BaseQuickAdapter<GoodDetailBean.SpecListBean, BaseViewHolder> {


    public GoodsSpecPopAdapter(int layoutResId, @Nullable List<GoodDetailBean.SpecListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GoodDetailBean.SpecListBean item) {

        TextView title = (TextView) helper.getView(R.id.item_goods_spec_title);

        TagFlowLayout flowLayout = (TagFlowLayout) helper.getView(R.id.item_goods_spec_flowlayout);


        title.setText(item.getGoodsSpecName());

        List<String> strings = new ArrayList<>();

        for (int i = 0; i < item.getGoodsSkuSpecVals().size(); i++) {
            strings.add(item.getGoodsSkuSpecVals().get(i).getGoodsSkuSpecValName());
        }


        flowLayout.setAdapter(new TagAdapter<String>(strings) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mLayoutInflater.inflate(R.layout.item_goods_spec_flow, null);
                tv.setText(s);
                return tv;
            }
        });

    }
}
