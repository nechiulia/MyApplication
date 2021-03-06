package com.example.docta.myapplication.Classes.util;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
//private Long id;
    private String username;
    private byte[] avatar;
    private int age;
    private int gender;
    private double score;
    private int noCorrect;
  //  private Integer id_teacher;
    private String email_teacher;




//    public Student(String username, byte[] avatar, int age, int gender, Double score, Integer id_teacher) {
//        this.username = username;
//        this.avatar = avatar;
//        this.age = age;
//        this.gender = gender;
//        this.score = score;
//        this.id_teacher = id_teacher;
//    }

    public Student(){
    }


    public Student(String username, byte[] avatar, int age, int gender, double score, String email_teacher) {
        this.username = username;
        this.avatar = avatar;
        this.age = age;
        this.gender = gender;
        this.score = score;
        this.email_teacher = email_teacher;
    }

    public Student(String username){
        this.username = username;
    }

    public int getNoCorrect() {
        return noCorrect;
    }

    public void setNoCorrect(int noCorrect) {
        this.noCorrect = noCorrect;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getEmail_teacher() {
        return email_teacher;
    }

    public void setEmail_teacher(String email_teacher) {
        this.email_teacher = email_teacher;
    }

    public Student(String username, byte[] avatar, int age,double score, int gender) {
        this.username = username;
        this.avatar = avatar;
        this.age = age;
        this.gender = gender;
        this.score = score;
    }


    protected Student(Parcel in) {
        username = in.readString();
        avatar = in.createByteArray();
        age = in.readInt();
        gender = in.readInt();
        score = in.readDouble();
   //     id_teacher = in.readInt();
        email_teacher = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

//    public Integer getId_teacher() {
//        return id_teacher;
//    }
//
//    public void setId_teacher(Integer id_teacher) {
//        this.id_teacher = id_teacher;
//    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeByteArray(avatar);
        dest.writeInt(age);
        dest.writeInt(gender);
        dest.writeDouble(score);
  //      dest.writeInt(id_teacher);
        dest.writeString(email_teacher);
    }
}
