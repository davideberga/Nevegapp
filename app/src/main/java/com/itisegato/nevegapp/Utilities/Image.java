package com.itisegato.nevegapp.Utilities;


import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable {

    private String title;
    private int thumbnail;

    public Image(String title, int thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }

    protected Image(Parcel in) {
        title = in.readString();
        thumbnail = in.readInt();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(thumbnail);
    }
}
