package vip.dengwj.network.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vip.dengwj.network.domian.GetTextItem;
import vip.dengwj.network.domian.GetTextParam;

public interface API {
    @GET("/get/text")
    Call<GetTextItem> getJSON();

    @GET("/get/param")
    Call<GetTextParam> getParam(@Query("keyword") String keyword, @Query("page") int page, @Query("order") String order);
}
