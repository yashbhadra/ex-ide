package com.creeps.ex_ide.core.networking;

import com.creeps.ex_ide.core.networking.model.ExhibitWrapper;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by rohan on 30/9/17.
 *
 */

public interface RetrofitApiInterface {


    @FormUrlEncoded
    @POST("/quizapp/studentdetail.php")
    public Call<ExhibitWrapper> getBaseInfo(@Field("api_key") String apiKey);



}
