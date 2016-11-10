package com.example.fengxiaozheng.mynews.retrofit;

import com.example.fengxiaozheng.mynews.Constants;
import com.example.fengxiaozheng.mynews.entity.BaiSiDetailResult;
import com.example.fengxiaozheng.mynews.entity.BaiSiResult;
import com.example.fengxiaozheng.mynews.entity.BaiSiTabResult;
import com.example.fengxiaozheng.mynews.entity.HomeOneIdResult;
import com.example.fengxiaozheng.mynews.entity.HomeOneResult;
import com.example.fengxiaozheng.mynews.entity.ImageResult;
import com.example.fengxiaozheng.mynews.entity.JokeResult;
import com.example.fengxiaozheng.mynews.entity.TouTiaoVideoResult;
import com.example.fengxiaozheng.mynews.utils.RetrofitUtil;

import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fengxiaozheng on 2016/11/4.
 */

public class AppClient implements ApiStore{


    @Override
    public Observable<TouTiaoVideoResult> getvideoList(
            @Query("category") String category, @Query("as") String as) {
        return RetrofitUtil.getApi(Constants.TOUTIAO_BASE_URL)
                .getvideoList(category, as)
                .subscribeOn(Schedulers.io())
                .observeOn(
                        AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<JokeResult> getJokeList(
            @Query("page") int page,
            @Query("showapi_appid") String showapi_appid,
            @Query("showapi_timestamp") String showapi_timestamp,
            @Query("showapi_sign") String showapi_sign) {
        return RetrofitUtil.getApi(Constants.JOKE_BASE_URL)
                .getJokeList(page, showapi_appid, showapi_timestamp, showapi_sign)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<ImageResult> getImageList(
            @Query("tn") String tn,
            @Query("ipn") String ipn, @Query("word") String word, @Query("cl") String cl) {
        return RetrofitUtil.getApi(Constants.BAIDU_IMAGE_BASE_URL)
                .getImageList(tn, ipn, word, cl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Override public Observable<HomeOneIdResult> getHomeOneId() {
        return RetrofitUtil.getApi(Constants.ONE_BASE_URL)
                .getHomeOneId()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Override public Observable<HomeOneResult> getHomeOneData(@Path("id") String id) {
        return RetrofitUtil.getApi(Constants.ONE_BASE_URL)
                .getHomeOneData(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Override public Observable<BaiSiTabResult> getTabList() {
        return RetrofitUtil.getApi(Constants.BAISI_BASE_URL)
                .getTabList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<BaiSiResult> getBaiSiList(
            @Path("type") String type, @Path("page") long page) {
        return RetrofitUtil.getApi(Constants.BAISI__COMMENT_BASE_URL)
                .getBaiSiList(type, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<BaiSiDetailResult> getBaiSiDetailResult(@Query("data_id") String data_id) {
        return RetrofitUtil.getApi(Constants.BAISI__DETAIL_BASE_URL)
                .getBaiSiDetailResult(data_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
