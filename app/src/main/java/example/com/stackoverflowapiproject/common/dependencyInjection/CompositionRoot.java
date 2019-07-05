package example.com.stackoverflowapiproject.common.dependencyInjection;

import example.com.stackoverflowapiproject.common.Constants;
import example.com.stackoverflowapiproject.networking.StackOverflowApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CompositionRoot {


    private Retrofit mRetrofit;

    private Retrofit getRetrofit(){
        if (mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    public StackOverflowApi getStackOverflowApi() {

        return getRetrofit().create(StackOverflowApi.class);
    }

}
