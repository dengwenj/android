package vip.dengwj.network;

import static java.net.HttpURLConnection.HTTP_OK;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import vip.dengwj.network.API.API;
import vip.dengwj.network.adapter.RetrofitRecyclerAdapter;
import vip.dengwj.network.domian.GetTextItem;

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
    }

    private void handleGetRequest(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:9102")
                .build();

        API api = retrofit.create(API.class);
        Call<ResponseBody> task = api.getJSON();

        task.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == HTTP_OK) {
                    try {
                        assert response.body() != null;
                        String json = response.body().string();
                        Gson gson = new Gson();
                        GetTextItem getTextItem = gson.fromJson(json, GetTextItem.class);
                        updateList(getTextItem);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void updateList(GetTextItem item) {
        retrofitRecyclerAdapter.updateData(item.getData());
    }
}