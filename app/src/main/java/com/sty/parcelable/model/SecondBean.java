package com.sty.parcelable.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tian on 2018/7/10.
 */

public class SecondBean implements Parcelable{
    private int id;

    public SecondBean(){}

    protected SecondBean(Parcel in) {
        id = in.readInt();
    }

    public static final Creator<SecondBean> CREATOR = new Creator<SecondBean>() {
        @Override
        public SecondBean createFromParcel(Parcel in) {
            return new SecondBean(in);
        }

        @Override
        public SecondBean[] newArray(int size) {
            return new SecondBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
