package com.zqw.lightfilm.home_fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.zqw.lightfilm.R;
import com.zqw.lightfilm.detail_movie.DetailMovieActivity;
import com.zqw.lightfilm.home.adapter.ComingMovieAdapter;
import com.zqw.lightfilm.home.adapter.HomeAdapter;
import com.zqw.lightfilm.home.adapter.HotMovieAdapter;
import com.zqw.lightfilm.home.bean.ComingShowBean;
import com.zqw.lightfilm.home.bean.HotShowBean;
import com.zqw.lightfilm.re.ListViewForScrollView;
import com.zqw.lightfilm.utils.CacheUtils;
import com.zqw.lightfilm.utils.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;

/**
 * Created by 启文 on 2018/2/3.
 */
public class HomeFragment extends Fragment {

    /**
     * HotMovie类型
     */
    public static final int HOTMOVIE = 1;

    /**
     * ComingMovie类型
     */
    public static final int COMINGMOVIE = 2;
    @Bind(R.id.rv_home)
    RecyclerView rvHome;
    @Bind(R.id.tv_more)
    TextView tvMore;
    @Bind(R.id.rv_hot_movie)
    RecyclerView rvHotMovie;
    @Bind(R.id.ll_root)
    LinearLayout llRoot;
    @Bind(R.id.lv_coming)
    ListViewForScrollView lvComing;


    private HomeAdapter adapter;
    private HotMovieAdapter hotMovieAdapter;
    private ComingMovieAdapter comingMovieAdapter;
    private Context context;


    public String datajson = null; //网络数据

    public HomeFragment(Context context) {
        this.context = context;
    }

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        if(!TextUtils.isEmpty(CacheUtils.getString(context, "hot")) && !TextUtils.isEmpty(CacheUtils.getString(context, "coming"))){
            setHotMovie(CacheUtils.getString(context, "hot"));
            setComingMovie(CacheUtils.getString(context, "coming"));
        } else {
            getDataFromNet();   //联网请求
        }


        // 写入数据

        return view;
    }


    public void setHotMovie(String hotString) {
        Log.i("TAG", "zh:::" + CacheUtils.getString(context, "hot"));
        HotShowBean hotShowBean = JSON.parseObject(hotString, HotShowBean.class);

        Log.i("TAG", "345:::" +  hotShowBean.getDate());


        //设置适配器
        hotMovieAdapter = new HotMovieAdapter(context, hotShowBean.getMs());

        if(rvHotMovie != null){
            //设置布局管理器
            rvHotMovie.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

            rvHotMovie.setAdapter(hotMovieAdapter);

            tvMore.setText(hotShowBean.getMs().size() + "部");
        }



        //设置item的点击事件(接口)
        hotMovieAdapter.setOnHotItemCallBack(new HotMovieAdapter.OnHotItemCallBack() {
            @Override
            public void onClickItem(int movieId) {
                inputDetail(movieId);
            }
        });
    }

    public void setComingMovie(String comingString){
        ComingShowBean comingShowBean = JSON.parseObject(comingString, ComingShowBean.class);

        comingMovieAdapter = new ComingMovieAdapter(context, comingShowBean);

        if(lvComing != null){
            lvComing.setAdapter(comingMovieAdapter);
        }


        //设置item的点击事件(接口)
        comingMovieAdapter.setOnComingItemCallBack(new ComingMovieAdapter.OnComingItemCallBack() {
            @Override
            public void onClickItem(int movieId) {
                inputDetail(movieId);
            }
        });


    }

    private void inputDetail(int movieId){
        Bundle b = new Bundle();
        b.putString("movie_id", movieId+"");
        Intent i = new Intent(context, DetailMovieActivity.class);
        i.putExtra("movie_id", b);
        startActivity(i);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                Bundle bundle = msg.getData();

                /**
                 * 存入本地
                 */
                String hotString = bundle.getString("hot", "");
                String comingString = bundle.getString("coming", "");
                CacheUtils.saveString(context, "hot", hotString);
                CacheUtils.saveString(context, "coming", comingString);


                //适配
                setHotMovie(hotString);
                setComingMovie(comingString);

            }
        }
    };


    public void getDataFromNet() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response1 = OkGo.get(Constants.HOTSHOW_URL)
                            .tag(this)
                            .retryCount(2)
                            .cacheTime(5000)
                            .execute();
                    String data1 = response1.body().string();



                    Response response2 = OkGo.get(Constants.COMINGSHOW_URL)
                            .tag(this)
                            .retryCount(2)
                            .cacheTime(5000)
                            .execute();
                    String data2 = response2.body().string();


                    Message message = Message.obtain();
                    message.what = 0;
                    Bundle bundle = new Bundle();
                    bundle.putString("hot", data1);
                    bundle.putString("coming", data2);
                    message.setData(bundle);

                    handler.sendMessage(message);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
