package com.surhoo.sh.goods.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.surhoo.sh.R;
import com.surhoo.sh.goods.bean.GoodDetailBean;
import com.surhoo.sh.goods.view.impl.OnDDD;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

public class GoodsSpecPopAdapter extends BaseQuickAdapter<GoodDetailBean.SpecListBean, BaseViewHolder> {


    public GoodsSpecPopAdapter(int layoutResId, @Nullable List<GoodDetailBean.SpecListBean> data) {
        super(layoutResId, data);
    }

    OnDDD onDDD;

    public void setOnDDD(OnDDD onDDD) {
        this.onDDD = onDDD;
    }

    //默认需要选择的sku
    List<GoodDetailBean.SkuListBean> skuListBeans;

    public List<GoodDetailBean.SkuListBean> getSkuListBeans() {
        return skuListBeans;
    }

    public void setSkuListBeans(List<GoodDetailBean.SkuListBean> skuListBeans) {
        this.skuListBeans = skuListBeans;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GoodDetailBean.SpecListBean item) {

        TextView title = (TextView) helper.getView(R.id.item_goods_spec_title);

        TagFlowLayout flowLayout = (TagFlowLayout) helper.getView(R.id.item_goods_spec_flowlayout);


        title.setText(item.getGoodsSpecName());

        List<String> strings = new ArrayList<>();



        String[] split = skuListBeans.get(0).getGoodsSkuName().split("_");


        int checked = 0;

        for (int j = 0; j < split.length; j++) {
            for (int i = 0; i < item.getGoodsSkuSpecVals().size(); i++) {
                if(StringUtils.equals(split[j],item.getGoodsSkuSpecVals().get(i).getGoodsSkuSpecValName())){
                    checked  = i;
                }
            }
        }

        for (int i = 0; i < item.getGoodsSkuSpecVals().size(); i++) {
            strings.add(item.getGoodsSkuSpecVals().get(i).getGoodsSkuSpecValName());
        }


        TagAdapter<String> tagAdapter = new TagAdapter<String>(strings) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mLayoutInflater.inflate(R.layout.item_goods_spec_flow, null);
                tv.setText(s);
                return tv;
            }
        };

        tagAdapter.setSelectedList(checked);
        flowLayout.setAdapter(tagAdapter);

        flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {

                for (int i = 0; i < item.getGoodsSkuSpecVals().size(); i++) {
                    item.getGoodsSkuSpecVals().get(i).setChecked(false);
                }

                item.getGoodsSkuSpecVals().get(position).setChecked(true);


                onDDD.onddd(helper.getLayoutPosition(),item.getGoodsSkuSpecVals().get(position).getGoodsSkuSpecValName());

                return true;
            }
        });

    }
}
