package vip.dengwj.network.API;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import vip.dengwj.network.domian.CommentItem;
import vip.dengwj.network.domian.GetTextItem;
import vip.dengwj.network.domian.GetTextParam;
import vip.dengwj.network.domian.LoginResult;
import vip.dengwj.network.domian.PostComment;

public interface API {
    @GET("/get/text")
    Call<GetTextItem> getJSON();

    @GET("/get/param")
    Call<GetTextParam> getParam(@Query("keyword") String keyword, @Query("page") int page, @Query("order") String order);

    @GET("/get/param")
    Call<GetTextParam> getParam(@QueryMap Map<String, Object> params);

    @GET
    Call<GetTextParam> getParam(@Url String url);

    @POST("/post/comment")
    Call<PostComment> postBody(@Body CommentItem commentItem);

    @FormUrlEncoded
    @POST("/login")
    Call<LoginResult> login(@Field("userName") String userName, @Field("password") String password);

    @FormUrlEncoded
    @POST("/login")
    Call<LoginResult> login(@FieldMap Map<String, String> params);
}
