package com.zqw.lightfilm.detail_movie;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zqw.lightfilm.detail_movie.Bean.MovieDetailBean;

/**
 * Created by 启文 on 2018/2/26.
 */
public class PrizeAdapter extends BaseAdapter{

    private Context context;
    private MovieDetailBean.DataBean.BasicBean.AwardBean award;

    public PrizeAdapter(Context context, MovieDetailBean.DataBean.BasicBean.AwardBean award){

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
