package com.creeps.ex_ide.core.networking.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rohan on 10/3/18.
 * A listItem to hold exhibit information.
 *
 */

public class Exhibit {
    @SerializedName("exhibit_id")
    private int id;
    @SerializedName("exhibit_name")
    private String name;
    @SerializedName("exhibit_image_url")
    private String imageURL;
    @SerializedName("exhibit_audio_url")
    private String audioURL;
    @SerializedName("exhibit_description")
    private String exhibitDescription;

    //denotes if its contents have been set or not
    transient private boolean isSet;
    public Exhibit(int id,String name){
        this.id=id;
        this.name=name;
    }
    public Exhibit(int id,String name,String imageURL,String audioURL,String exhibitDescription){
        this(id,name);
        this.imageURL=imageURL;
        this.audioURL=audioURL;
        this.exhibitDescription=exhibitDescription;
    }

    public boolean isSet(){return this.isSet;}
    public void setIsSet(boolean b){this.isSet=b;}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getAudioURL() {
        return audioURL;
    }

    public String getExhibitDescription() {
        return exhibitDescription;
    }


    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setAudioURL(String audioURL) {
        this.audioURL = audioURL;
    }

    public void setExhibitDescription(String exhibitDescription) {
        this.exhibitDescription = exhibitDescription;
    }
}
