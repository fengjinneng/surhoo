package com.surhoo.sh.designer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.surhoo.sh.R;
import com.surhoo.sh.designer.bean.DesignerLabelBean;

import java.util.ArrayList;
import java.util.List;

public class DesignerGridViewAdapter extends BaseAdapter {


    private Context context;
    private List<DesignerLabelBean> datas;
    private LayoutInflater layoutInflater;

    private int clicked = -1;

    public void setClicked(int clicked) {
        this.clicked = clicked;
    }

    public DesignerGridViewAdapter(Context context, List<DesignerLabelBean> datas, LayoutInflater layoutInflater) {
        this.context = context;
        this.datas = datas;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DesignerGridViewViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_designer_label, null);

            holder = new DesignerGridViewViewHolder();

            holder.tv = convertView.findViewById(R.id.item_designer_label_name);
            convertView.setTag(holder);

        } else {
            holder = (DesignerGridViewViewHolder) convertView.getTag();
        }

        if (clicked == position) {
            holder.tv.setTextColor(context.getResources().getColor(R.color.themeColor));
            holder.tv.setBackground(context.getResources().getDrawable(R.drawable.background_chanpinfenlei_checked));

        } else {
            holder.tv.setTextColor(context.getResources().getColor(R.color.a4a4a4));
            holder.tv.setBackground(context.getResources().getDrawable(R.drawable.background_chanpinfenlei_unchecked));
        }

        holder.tv.setText(datas.get(position).getName());

        return convertView;
    }


    static class DesignerGridViewViewHolder {
        public TextView tv;
    }
}
