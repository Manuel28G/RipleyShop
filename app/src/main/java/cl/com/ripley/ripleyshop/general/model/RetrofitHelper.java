package cl.com.ripley.ripleyshop.general.model;

import android.util.ArrayMap;

import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private Retrofit retrofit;
    private static Map<String ,RetrofitHelper> retrofitHelper = new ArrayMap<>();

    private RetrofitHelper(String uriBase){
        retrofit = new Retrofit.Builder()
                .baseUrl(uriBase)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitHelper getInstance(String uriBase){
        if(retrofitHelper.get(uriBase) == null)
            retrofitHelper.put(uriBase , new RetrofitHelper(uriBase));
        return retrofitHelper.get(uriBase);
    }

    public <T> T callEP(Class<T> classCalled){
        return retrofit.create(classCalled);
    }


}
