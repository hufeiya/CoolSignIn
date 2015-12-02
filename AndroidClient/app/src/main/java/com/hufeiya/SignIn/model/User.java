/*
 * Copyright 2015 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hufeiya.SignIn.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Stores phone and md5 password to identify the server.
 */
public class User implements Parcelable {

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    private final String mPhone;
    private final String mPass;
    private final Avatar mAvatar;

    public User(String phone, String pass, Avatar avatar) {
        mPhone = phone;
        mPass = pass;
        mAvatar = avatar;
    }

    protected User(Parcel in) {
        mPhone = in.readString();
        mPass = in.readString();
        mAvatar = Avatar.values()[in.readInt()];
    }

    public String getPhone() {
        return mPhone;
    }

    public String getPass() {
        return mPass;
    }

    public Avatar getAvatar() {
        return mAvatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mPhone);
        dest.writeString(mPass);
        dest.writeInt(mAvatar.ordinal());
    }

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (mAvatar != user.mAvatar) {
            return false;
        }
        if (!mPhone.equals(user.mPhone)) {
            return false;
        }
        if (!mPass.equals(user.mPass)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = mPhone.hashCode();
        result = 31 * result + mPass.hashCode();
        result = 31 * result + mAvatar.hashCode();
        return result;
    }
}
