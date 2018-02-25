package com.zqw.lightfilm.detail_movie;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zqw.lightfilm.R;
import com.zqw.lightfilm.detail_movie.Bean.MovieDetailBean;

import java.util.List;

/**
 * Created by 启文 on 2018/2/13.
 */
public class BaseActorsAdapter extends BaseQuickAdapter<MovieDetailBean.DataBean.BasicBean.ActorsBean, BaseViewHolder> {



    public BaseActorsAdapter(@LayoutRes int layoutResId, @Nullable List<MovieDetailBean.DataBean.BasicBean.ActorsBean> data) {
        super(layoutResId, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, MovieDetailBean.DataBean.BasicBean.ActorsBean item) {

        //viewHolder.getLayoutPosition() 获取当前item的position

        helper.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_role_name, "饰: " + item.getRoleName());

        Glide.with(mContext).load(item.getImg()).crossFade().into((ImageView)helper.getView(R.id.iv_actors_image));


    }
}
