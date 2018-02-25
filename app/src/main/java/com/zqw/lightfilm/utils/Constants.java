package com.zqw.lightfilm.utils;

/**
 * Created by 启文 on 2018/2/3.
 *
 * API地址
 */
public class Constants {

    /**
     * 正在售票
     */
    public static String SELLTICKET_URL = "https://api-m.mtime.cn/PageSubArea/HotPlayMovies.api?locationId=561";

    /**
     * 正在热映
     */
    public static String HOTSHOW_URL = "https://api-m.mtime.cn/Showtime/LocationMovies.api?locationId=561";

    /**
     * 即将上映
     */
    public static String COMINGSHOW_URL = "https://api-m.mtime.cn/Movie/MovieComingNew.api?locationId=561";


    /**
     * 电影详细信息
     */
    public static String DETAIL_URL = "https://ticket-api-m.mtime.cn/movie/detail.api?locationId=561&movieId=";

    /**
     * 电影剧照
     */
    public static String STILLS_URL = "https://api-m.mtime.cn/Movie/ImageAll.api?movieId=";


    /**
     * 福利
     */
    public static String GANK_URL = "http://gank.io/api/data/";

    public static final String[] GANK_TYPE = {"福利", "Android", "iOS", "休息视频", "拓展资源", "前端", "all"};






}