package com.zqw.lightfilm.oneday.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zqw.lightfilm.R;
import com.zqw.lightfilm.oneday.welfare.WelFareAdapter;
import com.zqw.lightfilm.oneday.welfare.WelFareBean;
import com.zqw.lightfilm.utils.Constants;

import java.util.List;

/**
 * Created by 启文 on 2018/2/18.
 */
public class WelFareFragment extends Fragment{

    private RecyclerView rv_welfare;
    private Context context;

    public WelFareFragment(Context context) {
        this.context = context;
    }

    private WelFareAdapter welFareAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_welfare, container, false);
        rv_welfare = view.findViewById(R.id.rv_welfare);


        initNet();

        return view;
    }

    private void initNet() {


        String url = Constants.GANK_URL + Constants.GANK_TYPE[0] + "/20/1";


        OkGo.<String>get(url)
                .retryCount(2)
                .cacheTime(5000)
                .execute(new StringCallback() {

                    /**
                     * 对返回数据进行操作的回调， UI线程
                     */
                    @Override
                    public void onSuccess(Response<String> response) {
                        processData(response.body(), 1);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });

    }


    private void processData(String body, int page){

        WelFareBean welFareBean = JSON.parseObject(body, WelFareBean.class);

        List<WelFareBean.ResultsBean> results = welFareBean.getResults();

        welFareAdapter = new WelFareAdapter(R.layout.item_oneday_welfare, results, context);

        //设置布局管理器
        rv_welfare.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        rv_welfare.setAdapter(welFareAdapter);

    }

}
