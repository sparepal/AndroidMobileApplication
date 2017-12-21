package com.example.vikhy.homework02;

import android.graphics.Bitmap;

/**
 * Created by vikhy on 9/14/2017.
 */


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Date;


public class Contact implements Parcelable{

    String first;
    String last;
    String company;
    String email;
    String phone;
    String URL;
    String address;
    String nickname;
    String facebook_url;
    String twitter_url;
    String skype;
    String youtube;
    String birthday;
    Bitmap photo;
    static ArrayList<Contact> allContacts=new ArrayList<Contact>();
    public static ArrayList<Contact> getMyList()
    {
        return allContacts;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (!first.equals(contact.first)) return false;
        if (!last.equals(contact.last)) return false;
        if (company != null ? !company.equals(contact.company) : contact.company != null)
            return false;
        if (email != null ? !email.equals(contact.email) : contact.email != null) return false;
        if (!phone.equals(contact.phone)) return false;
        if (URL != null ? !URL.equals(contact.URL) : contact.URL != null) return false;
        if (address != null ? !address.equals(contact.address) : contact.address != null)
            return false;
        if (nickname != null ? !nickname.equals(contact.nickname) : contact.nickname != null)
            return false;
        if (facebook_url != null ? !facebook_url.equals(contact.facebook_url) : contact.facebook_url != null)
            return false;
        if (twitter_url != null ? !twitter_url.equals(contact.twitter_url) : contact.twitter_url != null)
            return false;
        if (skype != null ? !skype.equals(contact.skype) : contact.skype != null) return false;
        if (youtube != null ? !youtube.equals(contact.youtube) : contact.youtube != null)
            return false;
        if (birthday != null ? !birthday.equals(contact.birthday) : contact.birthday != null)
            return false;
        return photo != null ? photo.equals(contact.photo) : contact.photo == null;

    }

    @Override
    public int hashCode() {
        int result = first.hashCode();
        result = 31 * result + last.hashCode();
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + phone.hashCode();
        result = 31 * result + (URL != null ? URL.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (facebook_url != null ? facebook_url.hashCode() : 0);
        result = 31 * result + (twitter_url != null ? twitter_url.hashCode() : 0);
        result = 31 * result + (skype != null ? skype.hashCode() : 0);
        result = 31 * result + (youtube != null ? youtube.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        return result;
    }

    public Contact(Bitmap photo, String first, String last, String company, String email, String phone, String URL, String address, String nickname, String facebook_url, String twitter_url, String skype, String youtube, String birthday) {
        this.photo=photo;
        this.first = first;
        this.last = last;
        this.company = company;
        this.email = email;
        this.phone = phone;
        this.URL = URL;
        this.address = address;
        this.nickname = nickname;
        this.facebook_url = facebook_url;
        this.twitter_url = twitter_url;
        this.skype = skype;
        this.youtube = youtube;
        this.birthday = birthday;
    }

    protected Contact(Parcel in) {
        //photo = in.readParcelable(Bitmap.class.getClassLoader());
        first = in.readString();
        last = in.readString();
        company = in.readString();
        email = in.readString();
        phone = in.readString();
        URL = in.readString();
        address = in.readString();
        nickname = in.readString();
        facebook_url = in.readString();
        twitter_url = in.readString();
        skype = in.readString();
        youtube = in.readString();
        birthday = in.readString();


    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
       // dest.writeParcelable(photo);
        dest.writeString(first);
        dest.writeString(last);


        dest.writeString(company);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(URL);
        dest.writeString(address);
        dest.writeString(nickname);
        dest.writeString(facebook_url);
        dest.writeString(twitter_url);
        dest.writeString(skype);
        dest.writeString(youtube);
        dest.writeString(birthday);

    }


    @Override
    public String toString() {
        return "Contact{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", company='" + company + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", URL='" + URL + '\'' +
                ", address='" + address + '\'' +
                ", nickname='" + nickname + '\'' +
                ", facebook_url='" + facebook_url + '\'' +
                ", twitter_url='" + twitter_url + '\'' +
                ", skype='" + skype + '\'' +
                ", youtube='" + youtube + '\'' +
                ", birthday=" + birthday +
                ", photo=" + photo +
                '}';
    }
}
