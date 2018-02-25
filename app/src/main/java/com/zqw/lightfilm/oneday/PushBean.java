package com.zqw.lightfilm.oneday;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 启文 on 2018/2/24.
 */
public class PushBean {

    /**
     * category : ["休息视频","拓展资源","iOS","前端","Android","福利"]
     * error : false
     * results : {"Android":[{"_id":"5a7c42c8421aa90d24a065d4","createdAt":"2018-02-08T20:30:00.798Z","desc":"一个动画效果的播放控件，播放，暂停，停止之间的动画切换","images":["http://img.gank.io/c1ee3231-648d-4449-a455-04a13731b2e1"],"publishedAt":"2018-02-22T08:24:35.209Z","source":"web","type":"Android","url":"https://github.com/SwiftyWang/AnimatePlayButton","used":true,"who":null},{"_id":"5a7c6094421aa90d21aa114a","createdAt":"2018-02-08T22:37:08.833Z","desc":"漂亮的本地多媒体选择器","publishedAt":"2018-02-22T08:24:35.209Z","source":"web","type":"Android","url":"https://github.com/TonnyL/Charles","used":true,"who":"黎赵太郎"},{"_id":"5a7cf9e7421aa90d21aa114b","createdAt":"2018-02-09T09:31:19.687Z","desc":"开源的 markdown 编辑器","images":["http://img.gank.io/760970ea-daae-4b98-9f87-deecdd3fe1f7","http://img.gank.io/ea49dc41-3435-4126-ab5b-d7b3357ab517"],"publishedAt":"2018-02-22T08:24:35.209Z","source":"chrome","type":"Android","url":"https://github.com/zeleven/mua","used":true,"who":"蒋朋"},{"_id":"5a81333c421aa90d264d0eba","createdAt":"2018-02-12T14:25:00.318Z","desc":"A slider widget with a popup bubble displaying the precise value selected.","images":["http://img.gank.io/fe3c723f-643d-4466-91d5-86d9ed4ca88e"],"publishedAt":"2018-02-22T08:24:35.209Z","source":"web","type":"Android","url":"https://github.com/Ramotion/fluid-slider-android","used":true,"who":"Alex Mikhnev"}],"iOS":[{"_id":"5a72da28421aa90d24a065c0","createdAt":"2018-02-01T17:13:12.171Z","desc":"iOS 中效率极高的多 event 定时器，适用于复杂的轮询任务管理！","images":["http://img.gank.io/ac28f08b-1f5b-4f43-8fd6-dd350b6c5cda"],"publishedAt":"2018-02-22T08:24:35.209Z","source":"web","type":"iOS","url":"https://github.com/CNKCQ/GlobalTimer.git","used":true,"who":"CNKCQ"},{"_id":"5a8a904d421aa91331a69d82","createdAt":"2018-02-19T16:52:29.771Z","desc":"GarlandView seamlessly transitions between multiple lists of content. Made by @Ramotion","images":["http://img.gank.io/87e49a0b-feb8-47b2-b731-d9fc3cd6f485"],"publishedAt":"2018-02-22T08:24:35.209Z","source":"web","type":"iOS","url":"https://github.com/Ramotion/garland-view","used":true,"who":"Alex Mikhnev"}],"休息视频":[{"_id":"59fefc13421aa90fe72535fc","createdAt":"2017-11-05T19:54:59.820Z","desc":"100年来，看看中国人是如何拍照的。看到哪一个触动了你 \u200b","publishedAt":"2018-02-22T08:24:35.209Z","source":"chrome","type":"休息视频","url":"https://weibo.com/tv/v/Fcbf78DEK?fid=1034:e95c9d521d94f4ce5ba8a4a0e5eda105&display=0&retcode=6102","used":true,"who":"lxxself"}],"前端":[{"_id":"5a795384421aa90d2cd3d7e9","createdAt":"2018-02-06T15:04:36.955Z","desc":"Readline utils, for moving the cursor, clearing lines, creating a readline interface, and more.","publishedAt":"2018-02-22T08:24:35.209Z","source":"web","type":"前端","url":"https://github.com/enquirer/readline-utils","used":true,"who":"鑫花璐放"}],"拓展资源":[{"_id":"5a72cffe421aa90d21aa1135","createdAt":"2018-02-01T16:29:50.735Z","desc":"nuster - 高性能 web 缓存服务器 v1.7.9.3发布","publishedAt":"2018-02-22T08:24:35.209Z","source":"web","type":"拓展资源","url":"https://github.com/jiangwenyuan/nuster/releases/tag/v1.7.9.3","used":true,"who":null}],"福利":[{"_id":"5a8e0c41421aa9133298a259","createdAt":"2018-02-22T08:18:09.547Z","desc":"2-22","publishedAt":"2018-02-22T08:24:35.209Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1foowtrkpvkj20sg0izdkx.jpg","used":true,"who":"代码家"}]}
     */

    private boolean error;
    private ResultsBean results;
    private List<String> category;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public static class ResultsBean {
        private List<AndroidBean> Android;
        private List<IOSBean> iOS;
        @SerializedName("休息视频")
        private List<VideoBean> Video;
        @SerializedName("前端")
        private List<WebBean> Web;
        @SerializedName("拓展资源")
        private List<ExpandBean> Expand;
        @SerializedName("福利")
        private List<WelfareBean> Welfare;

        public List<AndroidBean> getAndroid() {
            return Android;
        }

        public void setAndroid(List<AndroidBean> Android) {
            this.Android = Android;
        }

        public List<IOSBean> getIOS() {
            return iOS;
        }

        public void setIOS(List<IOSBean> iOS) {
            this.iOS = iOS;
        }

        public List<VideoBean> getVideo() {
            return Video;
        }

        public void setVideo(List<VideoBean> Video) {
            this.Video = Video;
        }

        public List<WebBean> getWeb() {
            return Web;
        }

        public void setWeb(List<WebBean> Web) {
            this.Web = Web;
        }

        public List<ExpandBean> getExpand() {
            return Expand;
        }

        public void setExpand(List<ExpandBean> Expand) {
            this.Expand = Expand;
        }

        public List<WelfareBean> getWelfare() {
            return Welfare;
        }

        public void setWelfare(List<WelfareBean> Welfare) {
            this.Welfare = Welfare;
        }

        public static class AndroidBean {
            /**
             * _id : 5a7c42c8421aa90d24a065d4
             * createdAt : 2018-02-08T20:30:00.798Z
             * desc : 一个动画效果的播放控件，播放，暂停，停止之间的动画切换
             * images : ["http://img.gank.io/c1ee3231-648d-4449-a455-04a13731b2e1"]
             * publishedAt : 2018-02-22T08:24:35.209Z
             * source : web
             * type : Android
             * url : https://github.com/SwiftyWang/AnimatePlayButton
             * used : true
             * who : null
             */

            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private Object who;
            private List<String> images;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public Object getWho() {
                return who;
            }

            public void setWho(Object who) {
                this.who = who;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }

        public static class IOSBean {
            /**
             * _id : 5a72da28421aa90d24a065c0
             * createdAt : 2018-02-01T17:13:12.171Z
             * desc : iOS 中效率极高的多 event 定时器，适用于复杂的轮询任务管理！
             * images : ["http://img.gank.io/ac28f08b-1f5b-4f43-8fd6-dd350b6c5cda"]
             * publishedAt : 2018-02-22T08:24:35.209Z
             * source : web
             * type : iOS
             * url : https://github.com/CNKCQ/GlobalTimer.git
             * used : true
             * who : CNKCQ
             */

            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;
            private List<String> images;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public String getWho() {
                return who;
            }

            public void setWho(String who) {
                this.who = who;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }
        }

        public static class VideoBean {
            /**
             * _id : 59fefc13421aa90fe72535fc
             * createdAt : 2017-11-05T19:54:59.820Z
             * desc : 100年来，看看中国人是如何拍照的。看到哪一个触动了你 ​
             * publishedAt : 2018-02-22T08:24:35.209Z
             * source : chrome
             * type : 休息视频
             * url : https://weibo.com/tv/v/Fcbf78DEK?fid=1034:e95c9d521d94f4ce5ba8a4a0e5eda105&display=0&retcode=6102
             * used : true
             * who : lxxself
             */

            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public String getWho() {
                return who;
            }

            public void setWho(String who) {
                this.who = who;
            }
        }

        public static class WebBean {
            /**
             * _id : 5a795384421aa90d2cd3d7e9
             * createdAt : 2018-02-06T15:04:36.955Z
             * desc : Readline utils, for moving the cursor, clearing lines, creating a readline interface, and more.
             * publishedAt : 2018-02-22T08:24:35.209Z
             * source : web
             * type : 前端
             * url : https://github.com/enquirer/readline-utils
             * used : true
             * who : 鑫花璐放
             */

            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public String getWho() {
                return who;
            }

            public void setWho(String who) {
                this.who = who;
            }
        }

        public static class ExpandBean {
            /**
             * _id : 5a72cffe421aa90d21aa1135
             * createdAt : 2018-02-01T16:29:50.735Z
             * desc : nuster - 高性能 web 缓存服务器 v1.7.9.3发布
             * publishedAt : 2018-02-22T08:24:35.209Z
             * source : web
             * type : 拓展资源
             * url : https://github.com/jiangwenyuan/nuster/releases/tag/v1.7.9.3
             * used : true
             * who : null
             */

            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private Object who;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public Object getWho() {
                return who;
            }

            public void setWho(Object who) {
                this.who = who;
            }
        }

        public static class WelfareBean {
            /**
             * _id : 5a8e0c41421aa9133298a259
             * createdAt : 2018-02-22T08:18:09.547Z
             * desc : 2-22
             * publishedAt : 2018-02-22T08:24:35.209Z
             * source : chrome
             * type : 福利
             * url : https://ws1.sinaimg.cn/large/610dc034ly1foowtrkpvkj20sg0izdkx.jpg
             * used : true
             * who : 代码家
             */

            private String _id;
            private String createdAt;
            private String desc;
            private String publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(String publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public String getWho() {
                return who;
            }

            public void setWho(String who) {
                this.who = who;
            }
        }
    }
}