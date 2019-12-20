package com.surhoo.sh.common.matisse;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;

import com.blankj.utilcode.util.LogUtils;
import com.surhoo.sh.R;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.IncapableCause;
import com.zhihu.matisse.internal.entity.Item;
import com.zhihu.matisse.internal.utils.PhotoMetadataUtils;

import java.util.HashSet;
import java.util.Set;


public class GifSizeFilter extends Filter {

    private int mMaxSize;

    public GifSizeFilter(int maxSizeInBytes) {
        mMaxSize = maxSizeInBytes;
    }

    @Override
    public Set<MimeType> constraintTypes() {
        return new HashSet<MimeType>() {{
            add(MimeType.GIF);
            add(MimeType.MP4);
        }};
    }

    @SuppressLint("StringFormatInvalid")
    @Override
    public IncapableCause filter(Context context, Item item) {
        if (!needFiltering(context, item))
            return null;

        Point size = PhotoMetadataUtils.getBitmapBound(context.getContentResolver(), item.getContentUri());
        LogUtils.e("filesize---width:"+size.x+"--height:"+size.y+"--size:"+item.size);
        if (item.size > mMaxSize) {
            return new IncapableCause(IncapableCause.DIALOG, context.getString(R.string.up_load_error,
                    String.valueOf(PhotoMetadataUtils.getSizeInMB(mMaxSize))));
        }
        return null;
    }

}