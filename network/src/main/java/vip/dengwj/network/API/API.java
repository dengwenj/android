package vip.dengwj.network.API;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("/get/text")
    Call<ResponseBody> getJSON();
}
