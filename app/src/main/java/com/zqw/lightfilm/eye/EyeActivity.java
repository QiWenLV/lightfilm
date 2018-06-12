package com.zqw.lightfilm.eye;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zqw.lightfilm.R;
import com.zqw.lightfilm.eye.eye_bean.EyeHomeBean;
import com.zqw.lightfilm.eye.eye_bean.EyeHotBean;
import com.zqw.lightfilm.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 开眼主页信息包括：热门，分类，作者
 */
public class EyeActivity extends AppCompatActivity {


    @Bind(R.id.lv_eye_hot)
    ListView lvEyeHot;
    private ListView lv_eye_hot;
    private EyeHotListAdapter eyeHotListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eey);
        ButterKnife.bind(this);

        lv_eye_hot = (ListView) findViewById(R.id.lv_eye_hot);

        getEyeHomeData();

        lv_eye_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(EyeActivity.this, EyeVideoPlayActivity.class));
            }
        });
    }

    /**
     * 获取开眼主页信息
     */
    public void getEyeHomeData() {

        String url = Constants.EYE_HOME;


        OkGo.<String>post(url)
                .retryCount(2)
                .cacheTime(5000)
                .execute(new StringCallback() {

                    /**
                     * 对返回数据进行操作的回调， UI线程
                     */
                    @Override
                    public void onSuccess(Response<String> response) {
                        getEyeHotData(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }


    /**
     * 获取开眼热门内容信息
     *
     * @param body
     */
    private void getEyeHotData(String body) {

        EyeHomeBean eyeHomeBean = JSON.parseObject(body, EyeHomeBean.class);
        String url = eyeHomeBean.getTabInfo().getTabList().get(0).getApiUrl();  //热门API

        OkGo.<String>post(url)
                .retryCount(2)
                .cacheTime(5000)
                .execute(new StringCallback() {

                    /**
                     * 对返回数据进行操作的回调， UI线程
                     */
                    @Override
                    public void onSuccess(Response<String> response) {
                        processData(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });


    }


    /**
     * 解析热门内容
     *
     * @param body
     */
    public void processData(String body) {

        EyeHotBean eyeHotBean = JSON.parseObject(body, EyeHotBean.class);

        List<EyeHotBean.ItemListBeanX> eyeHotAllList = eyeHotBean.getItemList();

        List<EyeHotBean.ItemListBeanX> eyeHotVideoList = new ArrayList<>();

        for (int i = 0; i < eyeHotAllList.size(); i++) {

            if (eyeHotAllList.get(i).getType().equals("video")) {
                eyeHotVideoList.add(eyeHotAllList.get(i));
            }
        }

        Log.i("wee", "标题：" + eyeHotVideoList.get(2).getData().getTitle());

        eyeHotListAdapter = new EyeHotListAdapter(this, eyeHotVideoList);

        lv_eye_hot.setAdapter(eyeHotListAdapter);


    }
}
