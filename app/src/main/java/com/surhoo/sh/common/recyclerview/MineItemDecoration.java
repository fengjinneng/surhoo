package com.surhoo.sh.common.recyclerview;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.surhoo.sh.R;
import com.surhoo.sh.home.bean.MineItemBean;

import java.util.ArrayList;

public class MineItemDecoration extends RecyclerView.ItemDecoration {


    private Context context;
    private ArrayList<MineItemBean> datas;
    private final Drawable mLine = null;

    public MineItemDecoration(Context context, ArrayList<MineItemBean> datas) {
        this.context = context;
        this.datas = datas;
    }



    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);


        int childAdapterPosition = parent.getChildAdapterPosition(view);
        if (datas.get(childAdapterPosition).getType() == 1) {
            outRect.set(0, 0, 0, context.getResources().getDimensionPixelOffset(R.dimen.dp_1));
        }

        if (datas.get(childAdapterPosition).getType() == 2) {
            outRect.set(0, 0, 0, context.getResources().getDimensionPixelOffset(R.dimen.dp_10));
        }


    }
}
