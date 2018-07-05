package com.outsoucre.leruyn.baseproject.model;

import com.google.gson.annotations.SerializedName;

/**
 * Copyright (C) 2018 EdgeWorks Software.
 * All rights reserved.
 *
 * @author ruyn.
 * @since 04/07/2018
 */
public class Profile {
    @SerializedName("first_name")
    private String mFirstName;

    @SerializedName("last_name")
    private String mLastName;

    @SerializedName("gender")
    private int mGender;

    @SerializedName("age")
    private int mAge;

    public static Profile getDefault () {
        Profile profile = new Profile();
        profile.setFirstName("Hush");
        profile.setLastName("City");
        profile.setGender(0);
        profile.setAge(0);
        return profile;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public int getGender() {
        return mGender;
    }

    public void setGender(int gender) {
        mGender = gender;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "mFirstName='" + mFirstName + '\'' +
                ", mLastName='" + mLastName + '\'' +
                ", mGender=" + mGender +
                ", mAge=" + mAge +
                '}';
    }
}
