package com.example.fengxiaozheng.mynews.retrofit;

import com.example.fengxiaozheng.mynews.entity.BaiSiDetailResult;
import com.example.fengxiaozheng.mynews.entity.BaiSiResult;
import com.example.fengxiaozheng.mynews.entity.BaiSiTabResult;
import com.example.fengxiaozheng.mynews.entity.HomeOneIdResult;
import com.example.fengxiaozheng.mynews.entity.HomeOneResult;
import com.example.fengxiaozheng.mynews.entity.ImageResult;
import com.example.fengxiaozheng.mynews.entity.JokeResult;
import com.example.fengxiaozheng.mynews.entity.TouTiaoVideoResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by fengxiaozheng on 2016/11/4.
 */

public interface ApiStore {

    //http://toutiao.com/api/article/recent/?category=video&as=A135870C95A0924
    @GET("/api/article/recent/")
    Observable<TouTiaoVideoResult> getvideoList(
            @Query("category") String category, @Query("as") String as);

    //https://route.showapi.com/255-1/?page=2&showapi_appid=23885&showapi_timestamp=20160901104936&showapi_sign=04e0e94a0431cd612af4bc818217b595
    @GET("/255-1/")
    Observable<JokeResult> getJokeList(
            @Query("page") int page,
            @Query("showapi_appid") String showapi_appid,
            @Query("showapi_timestamp") String showapi_timestamp,
            @Query("showapi_sign") String showapi_sign);

    //http://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&word=%E7%BE%8E%E5%A5%B3&cl=5
    @GET("/search/acjson ")
    Observable<ImageResult> getImageList(
            @Query("tn") String tn,
            @Query("ipn") String ipn, @Query("word") String word, @Query("cl") String cl);

    //http://v3.wufazhuce.com:8000/api/hp/idlist/0?

    @GET("/api/hp/idlist/0?")
    Observable<HomeOneIdResult> getHomeOneId();

    //http://v3.wufazhuce.com:8000/api/hp/detail/1457?
    @GET("/api/hp/detail/{id}")
    Observable<HomeOneResult> getHomeOneData(@Path("id") String id);

    @GET("/public/list-appbar/budejie-android-6.5.11/")
    Observable<BaiSiTabResult> getTabList();

    //http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.5.11/0-20.json
    @GET("{type}/budejie-android-6.5.11/{page}-20.json")
    Observable<BaiSiResult> getBaiSiList(@Path("type") String type, @Path("page") long page);

    //http://s.budejie.com/
    //http://api.budejie.com/api/api_open.php?a=dataList&c=comment&data_id=20565881
    @GET("api_open.php?a=dataList&c=comment")
    Observable<BaiSiDetailResult> getBaiSiDetailResult(@Query("data_id") String data_id);
}
