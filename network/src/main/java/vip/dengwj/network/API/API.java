package vip.dengwj.network.API;

import retrofit2.Call;
import retrofit2.http.GET;
import vip.dengwj.network.domian.GetTextItem;

public interface API {
    @GET("/get/text")
    Call<GetTextItem> getJSON();
}
