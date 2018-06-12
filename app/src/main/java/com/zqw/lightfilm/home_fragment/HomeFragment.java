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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.zqw.lightfilm.R;
import com.zqw.lightfilm.detail_movie.DetailMovieActivity;
import com.zqw.lightfilm.eye.EyeActivity;
import com.zqw.lightfilm.home.adapter.ComingMovieAdapter;
import com.zqw.lightfilm.home.adapter.HomeAdapter;
import com.zqw.lightfilm.home.adapter.HotMovieAdapter;
import com.zqw.lightfilm.home.bean.ComingShowBean;
import com.zqw.lightfilm.home.bean.HotShowBean;
import com.zqw.lightfilm.re.ListViewForScrollView;
import com.zqw.lightfilm.utils.CacheUtils;
import com.zqw.lightfilm.utils.Constants;
import com.zqw.lightfilm.utils.Tools;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;

/**
 * Created by 启文 on 2018/2/3.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    /**
     * HotMovie类型
     */
    public static final int HOTMOVIE = 1;

    /**
     * ComingMovie类型
     */
    public static final int COMINGMOVIE = 2;

    @Bind(R.id.tv_more)
    TextView tvMore;
    @Bind(R.id.rv_hot_movie)
    RecyclerView rvHotMovie;
    @Bind(R.id.lv_coming)
    ListViewForScrollView lvComing;
    @Bind(R.id.menu_one)
    TextView menuOne;
    @Bind(R.id.menu_two)
    TextView menuTwo;
    @Bind(R.id.menu_three)
    TextView menuThree;


    private HomeAdapter adapter;
    private HotMovieAdapter hotMovieAdapter;
    private ComingMovieAdapter comingMovieAdapter;
    private Context context;

    private boolean isShowComingListView = true;


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

        /**
         * 当内存中有hot数据和coming数据, 并且刷新时间小于一小时，就直接访问本地数据
         */
        if (!TextUtils.isEmpty(CacheUtils.getString(context, "hot")) && !TextUtils.isEmpty(CacheUtils.getString(context, "coming")) && Tools.isRefreshTime(context)) {
            setHotMovie(CacheUtils.getString(context, "hot"));
            setComingMovie(CacheUtils.getString(context, "coming"));
        } else {
            if (!TextUtils.isEmpty(CacheUtils.getString(context, "local_id"))) {
                getDataFromNet(Integer.valueOf(CacheUtils.getString(context, "local_id")));   //联网请求
            } else {
                getDataFromNet(561);
            }

        }

        menuOne.setOnClickListener(this);
        menuTwo.setOnClickListener(this);
        menuThree.setOnClickListener(this);

        return view;
    }


    public void setHotMovie(String hotString) {

        HotShowBean hotShowBean = JSON.parseObject(hotString, HotShowBean.class);

        //设置适配器
        hotMovieAdapter = new HotMovieAdapter(context, hotShowBean.getMs());

        if (rvHotMovie != null) {
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

    public void setComingMovie(String comingString) {
        ComingShowBean comingShowBean = JSON.parseObject(comingString, ComingShowBean.class);

        comingMovieAdapter = new ComingMovieAdapter(context, comingShowBean);

        if (lvComing != null) {
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

    private void inputDetail(int movieId) {
        Bundle b = new Bundle();
        b.putString("movie_id", movieId + "");
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
                String localId = bundle.getString("local", "");
                String refreshTime = String.valueOf(System.currentTimeMillis());    //得到当前的时间（毫秒数）
                CacheUtils.saveString(context, "hot", hotString);
                CacheUtils.saveString(context, "coming", comingString);
                CacheUtils.saveString(context, "local_id", localId);         //保存位置信息
                CacheUtils.saveString(context, "refresh_time", refreshTime);


                //适配
                setHotMovie(hotString);
                setComingMovie(comingString);

            }
        }
    };


    public void getDataFromNet(final int localId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response1 = OkGo.get(Constants.HOTSHOW_URL + localId)
                            .tag(this)
                            .retryCount(2)
                            .cacheTime(5000)
                            .execute();
                    String data1 = response1.body().string();


                    Response response2 = OkGo.get(Constants.COMINGSHOW_URL + localId)
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
                    bundle.putString("local", localId + "");
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


    public void refushLocal(int localId) {
        if (localId != -1) {
            getDataFromNet(localId);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.menu_one:
                Toast.makeText(context, "1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_two:
                break;
            case R.id.menu_three:
                startActivity(new Intent(context, EyeActivity.class));
                break;
        }
    }
}
