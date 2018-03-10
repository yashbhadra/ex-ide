package com.creeps.ex_ide.core;

import android.content.Context;

import com.creeps.ex_ide.core.networking.model.ExhibitWrapper;

/**
 * Created by rohan on 10/3/18.
 * A Singleton to hold a reference of An ExhibitWrapper
 */

public class ExhibitWrapperDelegate {
    private static ExhibitWrapperDelegate mExhitbitWrapperDelegate;
    private ExhibitWrapper reference;
    private Context context;

    private ExhibitWrapperDelegate(Context ctx){
        this.context=ctx;
        this.reference=null;

    }

    public static ExhibitWrapperDelegate getmExhitbitWrapperDelegate(Context context){
        return mExhitbitWrapperDelegate= mExhitbitWrapperDelegate==null?new ExhibitWrapperDelegate(context):mExhitbitWrapperDelegate;
    }
    public void setExhibitWrapper(ExhibitWrapper wrapper){
        this.reference=wrapper;
    }
    public ExhibitWrapper getExhibitWrapper(){return this.reference;}

}
