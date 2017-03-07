package com.arctouch.wanderley.italo.movieforyou.core.rest;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by italowanderley on 14/02/17.
 */

public class RestClient<RestService> {
    private static final String TAG = RestClient.class.getSimpleName();

    private static RestClient instance;

    private Retrofit retrofit;

    public synchronized static RestClient getInstance(String url, String apiKey) {
        if (instance == null) {
            instance = new RestClient(url, apiKey);
        }
        return instance;
    }

    public synchronized static RestClient getInstance(String url, String apiKey, String user, String password) {
        if (instance == null) {
            instance = new RestClient(url, apiKey, user, password);
        }
        return instance;
    }

    private RestClient(String url, String apiKey) {
        retrofit = prepareRetrofitClient(url, prepareOkHttpClient(apiKey));
    }

    private RestClient(String url, String apiKey, String user, String password) {
        retrofit = prepareRetrofitClient(url, prepareOkHttpClient(apiKey, user, password));
    }

    public RestService getService(Class<RestService> service) {
        return retrofit.create(service);
    }

    public <T> void getRestData(final OnRestListener listener, final Call<T> call) throws IOException {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    listener.onIncomeSuccess(response.body(), call);
                } else {
                    onFailure(call, new Exception("CODE RECEIVED: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                listener.onIncomeFailure(t);
            }
        });
    }

    public <T> T getRestData(final Call<T> call) throws IOException {
        Response<T> response = call.execute();
        Log.d(TAG, "status code: " + response.code());
        if (!response.isSuccessful()) {
            Log.d(TAG, "message: " + response.message());
            Log.d(TAG, "error message: " + response.errorBody().string());
        }
        return response.body();
    }

    private OkHttpClient prepareOkHttpClient(String apiKey) {
        return prepareOkHttpClient(apiKey, null, null);
    }

    private OkHttpClient prepareOkHttpClient(String apiKey, String user, String password) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new HeaderInterceptor(apiKey));

        if (user != null && password != null) {
            return builder.authenticator(getAuthenticator(user, password))
                    .build();
        }

        return builder.build();
    }

    private Authenticator getAuthenticator(final String user, final String password) {
        return new Authenticator() {
            @Override
            public Request authenticate(Route route, okhttp3.Response response) throws IOException {
                String credentials = Credentials.basic(user, password);
                return response.request().newBuilder()
                        .header("Authorization", credentials)
                        .build();
            }
        };
    }

    private Retrofit prepareRetrofitClient(String url, OkHttpClient client) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void dismiss() {
        retrofit = null;
        instance = null;
    }

    static class HeaderInterceptor implements Interceptor {

        private String mApiKey;

        public HeaderInterceptor(String apiKey) {
            mApiKey = apiKey;
        }

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request original = chain.request();

            HttpUrl url = original.url()
                    .newBuilder()
                    .addQueryParameter("api_key", mApiKey)
                    .build();

            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }
    }

    public interface OnRestListener<T> {
        void onIncomeSuccess(T response, Call call);

        void onIncomeFailure(Throwable t);
    }
}
