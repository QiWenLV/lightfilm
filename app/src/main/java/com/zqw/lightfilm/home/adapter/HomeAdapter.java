package com.zqw.lightfilm.home.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.zqw.lightfilm.R;
import com.zqw.lightfilm.home.bean.ComingShowBean;
import com.zqw.lightfilm.home.bean.HotShowBean;

import java.util.List;

/**
 * Created by 启文 on 2018/2/3.
 */
public class HomeAdapter extends RecyclerView.Adapter {

    /**
     * 顶部轮播图类型
     */
    public static final int BANNER = 0;
    /**
     * HotMovie类型
     */
    public static final int HOTMOVIE = 1;

    /**
     * ComingMovie类型
     */
    public static final int COMINGMOVIE = 2;



    private int currentType = 0;    //记录当前类型
    private Context context;
    private HotShowBean hotShowBean;
    private ComingShowBean comingShowBean;



    private LayoutInflater from;

    public HomeAdapter(Context context, HotShowBean hotShowBean, ComingShowBean comingShowBean) {
        this.context = context;
        this.hotShowBean = hotShowBean;
        this.comingShowBean = comingShowBean;
        from = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == BANNER){
            return new BannerViewHolder(context, from.inflate(R.layout.banner_viewpager, null));
        }else if(viewType == HOTMOVIE){
            return new HotMovieViewHolder(context, from.inflate(R.layout.hot_movie_item, null));
        }else if(viewType == COMINGMOVIE){
            return new ComingMovieViewHolder(context, from.inflate(R.layout.coming_movie_item, null));
        }

        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (position){
            case BANNER:
                BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
                bannerViewHolder.setData();
                break;
            case HOTMOVIE:
                HotMovieViewHolder hotMovieViewHolder = (HotMovieViewHolder) holder;
                hotMovieViewHolder.setData(hotShowBean);
                break;
            case COMINGMOVIE:
                ComingMovieViewHolder comingMovieViewHolder = (ComingMovieViewHolder) holder;
                comingMovieViewHolder.setData(comingShowBean);
        }
    }

    @Override
    public int getItemViewType(int position) {


        if (position == BANNER) {
            currentType = BANNER;
        } else if (position == HOTMOVIE) {
            currentType = HOTMOVIE;
        } else if(position == COMINGMOVIE){
            currentType = COMINGMOVIE;
        }

        return currentType;
    }

    @Override
    public int getItemCount() {
        return 3;
    }


    /**
     * 顶部轮播图
     */
    private class BannerViewHolder extends RecyclerView.ViewHolder{

        private Context context;
        Banner banner;
        private List<String> imagePath;

        public BannerViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            this.banner = itemView.findViewById(R.id.banner);
        }

        public void setData() {
            //设置Banner的数据

//            banner.setImageLoader(new GlideImageLoader());
//            imagePath = new ArrayList<>();
//            for (int i = 0; i < 3; i++) {
//                String str ="";
//                imagePath.add(str);
//            }
//            banner.setImages(imagePath)
//                    .setDelayTime(4000)
//                    .setOnBannerListener(new OnBannerListener() {
//                        @Override
//                        public void OnBannerClick(int position) {
//                            Toast.makeText(context, "点击了第"+ position + "张图片", Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .start();

        }
    }

    /**
     * HotMovie类型
     */
    private class HotMovieViewHolder extends RecyclerView.ViewHolder{

        private Context context;
        private TextView tv_more;
        private RecyclerView rv_hot_movie;

        private HotMovieAdapter adapter;

        public HotMovieViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            tv_more = itemView.findViewById(R.id.tv_more);
            rv_hot_movie = itemView.findViewById(R.id.rv_hot_movie);
        }

        public void setData(HotShowBean hotShowBean) {

            //设置适配器
            adapter = new HotMovieAdapter(context, hotShowBean.getMs());
            rv_hot_movie.setAdapter(adapter);

            //设置布局管理器
            rv_hot_movie.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

            tv_more.setText(hotShowBean.getMs().size() + "部");
            //设置item的点击事件(接口)
            adapter.setOnHotItemCallBack(new HotMovieAdapter.OnHotItemCallBack() {
                @Override
                public void onClickItem(int movieId) {
                    Toast.makeText(context, "movie"+movieId, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private class ComingMovieViewHolder extends RecyclerView.ViewHolder{

        private Context context;
        private ListView lv_coming;
        private ComingMovieAdapter adapter;


        public ComingMovieViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            lv_coming = itemView.findViewById(R.id.lv_coming);
        }
        public void setData(ComingShowBean comingShowBean){
            adapter = new ComingMovieAdapter(context, comingShowBean);

            lv_coming.setAdapter(adapter);


        }


    }

    public void addSellTickeMovies(HotShowBean hotShowBean){
        this.hotShowBean = hotShowBean;
    }
}
