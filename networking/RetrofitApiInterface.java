package com.creeps.ex_ide.core.networking;

import com.creeps.sl_app.core_services.utils.modal.Student;

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
    public Call<Student> getStudenInfo(@Field("username") String username, @Field("password") Long password, @Field("api_key") String apiKey);
    @FormUrlEncoded
    @POST("/quizapp/studentdetail.php")
    public Call<Student> getSomeDetails(@Field("user_id") Long userId, @Field("api_key") String apiKey);




}
