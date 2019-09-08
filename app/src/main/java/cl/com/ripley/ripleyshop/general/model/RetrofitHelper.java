package cl.com.ripley.ripleyshop.general.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private Retrofit retrofit;
    private static RetrofitHelper retrofitHelper;

    private RetrofitHelper(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitHelper getInstance(){
        if(retrofitHelper == null)
            retrofitHelper = new RetrofitHelper();
        return retrofitHelper;
    }

    public <T> T callEP(Class<T> classCalled){
        return retrofit.create(classCalled);
    }


}
