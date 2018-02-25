package com.zqw.lightfilm.oneday.welfare;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zqw.lightfilm.R;

import java.util.List;

/**
 * Created by 启文 on 2018/2/18.
 */
public class WelFareAdapter extends BaseQuickAdapter<WelFareBean.ResultsBean, BaseViewHolder> {

    private Context context;

    public WelFareAdapter(@LayoutRes int layoutResId, @Nullable List<WelFareBean.ResultsBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, WelFareBean.ResultsBean item) {

        Glide.with(context).load(item.getUrl()).into((ImageView) helper.getView(R.id.iv_image));
    }
}
