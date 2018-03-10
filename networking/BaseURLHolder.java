package com.creeps.ex_ide.core.networking;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by rohan on 10/3/18.
 */

public class BaseURLHolder {
    public final static String DEFAULT_BASE="192.168.1.1";
    private String baseURL;
    private Context mContext;
    private static BaseURLHolder mHolder;
    private final static String TAG="BaseURLHolder";
    private ArrayList<BaseURLChangedCallback> callbacks;
    private BaseURLHolder(Context context,String baseURL){
        this.baseURL=baseURL;
        this.callbacks=new ArrayList<>();
        this.mContext=context;
        if(baseURL.equals(DEFAULT_BASE))
            Log.i(TAG,"using defaultBaseURL STRING");
    }



    public void addCallback(BaseURLChangedCallback baseURLChangedCallback){
        this.callbacks.add(baseURLChangedCallback);
    }
    public static BaseURLHolder getInstance(Context context,String someURL){
        return mHolder= mHolder==null?new BaseURLHolder(context,someURL==null?DEFAULT_BASE:someURL):mHolder;
    }
    /* Function reinits the value of baseURL
    * @param newBaseURL - the new baseURL thats to be used*/
    public void setBaseURL(String newBaseURL){
        this.baseURL=newBaseURL;
        for(BaseURLChangedCallback base:this.callbacks){
            base.onBaseURLChanged(newBaseURL);
        }
    }
    /* Returns baseURL
    * */
    public String getBaseURL(){return this.baseURL;}


    /* For those classes that wish to be notified of the value of the BaseURL being changed.*/
    public interface BaseURLChangedCallback{
        public void onBaseURLChanged(String newBaseURL);
    }
}
