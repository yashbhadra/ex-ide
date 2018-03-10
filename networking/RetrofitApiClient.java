package com.creeps.ex_ide.core.networking;

import android.content.Context;
import android.util.Log;

import com.creeps.sl_app.R;
import com.creeps.sl_app.core_services.utils.modal.DataHolder;
import com.creeps.sl_app.core_services.utils.modal.Student;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * Created by rohan on 10/3/18.
         * The compiles dependencies required for this class are as follows
        compile 'com.squareup.retrofit2:retrofit:2.3.0'
        compile 'com.squareup.retrofit2:converter-gson:2.3.0'
        compile 'com.squareup.retrofit2:converter-scalars:2.3.0'

        This class is Singleton wrapper around the RetrofitClient and RetrofitInterface. Additionally it includes a callback Reverb that notifies the user of the server response
        Interface Reverb
            public void reverb(Object x)- Callback fired whenever the server sends a response. Will decode the data to specified model class. It is the responsibility of the overriding class to cast it.
*/

public class RetrofitApiClient implements BaseURLHolder.BaseURLChangedCallback{
    private static RetrofitApiClient mRetrofitApiClient;
    private Context mContext;
    private String BASE_URL;
    private Retrofit mRetrofitInstance;
    private RetrofitApiInterface mRetrofitApiInterface;
    private final static String TAG="RetrofitApiClient";
    private BaseURLHolder baseURLHolder;


    private final static String API_KEY=null;


    /* Context is being takes as a param to prevent the object from getting gc'd. As long as a reference to the other object is alive and is in the young pool the other object can
    * not be gc'd hence the current wont be gcd*/
    private RetrofitApiClient(Context context){
        this.mContext=context;
        this.baseURLHolder=BaseURLHolder.getInstance(this.mContext,null);
        this.baseURLHolder.addCallback(this);
        this.BASE_URL=this.baseURLHolder.getBaseURL();
        this.initRetrofitInstance();

    }
    private void initRetrofitInstance(){
        this.mRetrofitInstance= new Retrofit.Builder().baseUrl(this.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        this.mRetrofitApiInterface= this.mRetrofitInstance.create(RetrofitApiInterface.class);
    }

    /* use this factory method to obtain an instance of the class*/
    public static RetrofitApiClient getInstance(Context context){
        if(RetrofitApiClient.mRetrofitApiClient==null){
            RetrofitApiClient.mRetrofitApiClient = new RetrofitApiClient(context);
        }
        return RetrofitApiClient.mRetrofitApiClient;
    }
    /*this function is a generalized one to be used for all call objects ... will pass in the value returned by response ...
    it is the responsibility of the class which implements Reverberator to cast it to his/her liking
    * */

    private void enque(Call call,final Reverberator reverberator){
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(reverberator!=null) {
                    reverberator.reverb(response.body());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG,"Couldnt fetch data from server ... read the stackTrace for more info");
                t.printStackTrace();
                if(reverberator!=null)
                    reverberator.reverb(null);
            }
        });
    }

    public String getBaseUrl() {
        return this.BASE_URL;
    }



    /* Overriding baseURLChanged callbacks*/
    @Override
    public void onBaseURLChanged(String newBaseURL{
        this.BASE_URL=newBaseURL;
        this.initRetrofitInstance();
    }


    public void

    /* Callback fired for every ServerResponse*/
    public interface Reverberator{
        public void reverb(Object response);
    }



}
