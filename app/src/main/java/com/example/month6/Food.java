package com.example.month6;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable , Parcelable.Creator<Food> {
    String str;

    protected Food(Parcel in) {
        str = in.readString();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(str);
    }

    @Override
    public Food createFromParcel(Parcel source) {
        return null;
    }

    @Override
    public Food[] newArray(int size) {
        return new Food[0];
    }
}
