package vip.dengwj.network;

import static java.net.HttpURLConnection.HTTP_OK;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import vip.dengwj.network.API.API;
import vip.dengwj.network.adapter.RetrofitRecyclerAdapter;
import vip.dengwj.network.domian.CommentItem;
import vip.dengwj.network.domian.GetTextItem;
import vip.dengwj.network.domian.GetTextParam;
import vip.dengwj.network.domian.PostComment;
import vip.dengwj.network.util.RetrofitManager;

public class RetrofitActivity extends AppCompatActivity {
    private RetrofitRecyclerAdapter retrofitRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        findViewById(R.id.get_btn).setOnClickListener(this::handleGetRequest);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        retrofitRecyclerAdapter = new RetrofitRecyclerAdapter();
        recyclerView.setAdapter(retrofitRecyclerAdapter);

        findViewById(R.id.get_param_btn).setOnClickListener(this::handleGetParamRequest);

        findViewById(R.id.get_query_map_btn).setOnClickListener(this::handleGetQueryMapRequest);

        findViewById(R.id.get_query_url_btn).setOnClickListener(this::handleGetParamUrlRequest);

        findViewById(R.id.post_body_btn).setOnClickListener(this::handlePostBodyRequest);
    }

    // post body 注解
    private void handlePostBodyRequest(View view) {
        API api = RetrofitManager.getRetrofit().create(API.class);
        api.postBody(new CommentItem("111", "评论真好")).enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
                if (response.code() == HTTP_OK) {
                    Log.d("pumu", "post body -> " + response.body());
                }
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {

            }
        });
    }

    // Url 注解
    private void handleGetParamUrlRequest(View view) {
        API api = RetrofitManager.getRetrofit().create(API.class);
        String url = "/get/param?keyword=搜索搜索...&page=2&order=0";
        api.getParam(url).enqueue(new Callback<GetTextParam>() {
            @Override
            public void onResponse(Call<GetTextParam> call, Response<GetTextParam> response) {
                if (response.code() == HTTP_OK) {
                    Log.d("pumu", "url -> " + response.body());
                }
            }

            @Override
            public void onFailure(Call<GetTextParam> call, Throwable t) {

            }
        });
    }

    // QueryMap 注解
    private void handleGetQueryMapRequest(View view) {
        API api = RetrofitManager.getRetrofit().create(API.class);
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", "你好你好");
        params.put("page", 3);
        params.put("order", "1");
        api.getParam(params).enqueue(new Callback<GetTextParam>() {
            @Override
            public void onResponse(Call<GetTextParam> call, Response<GetTextParam> response) {
                if (response.code() == HTTP_OK){
                    Log.d("pumu", "data -> " + response.body());
                }
            }

            @Override
            public void onFailure(Call<GetTextParam> call, Throwable t) {

            }
        });
    }

    // 带参数的 get 请求
    private void handleGetParamRequest(View view) {
        API api = RetrofitManager.getRetrofit().create(API.class);
        api.getParam("搜索搜索...", 2, "0").enqueue(new Callback<GetTextParam>() {
            @Override
            public void onResponse(Call<GetTextParam> call, Response<GetTextParam> response) {
                if (response.code() == HTTP_OK) {
                    GetTextParam getTextParam = response.body();
                    Log.d("pumu", "getTextParam -> " + getTextParam);
                }
            }

            @Override
            public void onFailure(Call<GetTextParam> call, Throwable t) {

            }
        });
    }

    // get 请求
    private void handleGetRequest(View view) {
        Retrofit retrofit = RetrofitManager.getRetrofit();

        API api = retrofit.create(API.class);
        Call<GetTextItem> task = api.getJSON();

        task.enqueue(new Callback<GetTextItem>() {
            @Override
            public void onResponse(Call<GetTextItem> call, Response<GetTextItem> response) {
                if (response.code() == HTTP_OK) {
                    GetTextItem getTextItem = response.body();
                    assert getTextItem != null;
                    updateList(getTextItem);
                }
            }

            @Override
            public void onFailure(Call<GetTextItem> call, Throwable t) {

            }
        });
    }

    private void updateList(GetTextItem item) {
        retrofitRecyclerAdapter.updateData(item.getData());
    }
}