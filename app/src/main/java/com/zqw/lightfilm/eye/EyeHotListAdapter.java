package com.zqw.lightfilm.eye;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zqw.lightfilm.R;
import com.zqw.lightfilm.eye.eye_bean.EyeHotBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 启文 on 2018/6/6.
 */
public class EyeHotListAdapter extends BaseAdapter {

    private Context context;
    private List<EyeHotBean.ItemListBeanX> data;

    EyeHotListAdapter(Context context, List<EyeHotBean.ItemListBeanX> eyeHotVideoList) {
        this.context = context;
        this.data = eyeHotVideoList;

    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;

        if (view == null) {
            view = View.inflate(context, R.layout.item_eye_hot, null);

            holder = new ViewHolder(view);

            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        EyeHotBean.ItemListBeanX.DataBeanX videoData = this.data.get(i).getData();

        holder.tvVideoTitle.setText(videoData.getTitle());

        String type = videoData.getCategory() + " / " + sTom(videoData.getDuration());
        holder.tvVideoType.setText(type);

        Glide.with(context).load(videoData.getCover().getFeed()).into(holder.ivVideoBg);


        return view;
    }


    static class ViewHolder {
        @Bind(R.id.iv_video_bg)
        ImageView ivVideoBg;
        @Bind(R.id.tv_video_title)
        TextView tvVideoTitle;
        @Bind(R.id.tv_video_type)
        TextView tvVideoType;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    /**
     * 秒To分
     * @param s
     * @return
     */
    public String sTom(int s){
        int m = s / 60;
        int s0 = s % 60;

        return m + "'" + s0;
    }
}
