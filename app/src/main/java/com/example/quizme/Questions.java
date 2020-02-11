package com.example.quizme;

import android.os.Parcel;
import android.os.Parcelable;

public class Questions implements Parcelable {

    public static final String DIFFICULTY_EASY = "Easy";
    public static final String DIFFICULTY_MEDIUM = "Medium";
    public static final String DIFFICULTY_HARD = "Hard";

    private int ID;
    private String QUESTION;
    private String OPTION_1;
    private String OPTION_2;
    private String OPTION_3;
    private int ANSWER_NO;
    private String DIFFICULTY;
    private int CATEGORIES_ID;

    public Questions(){}

    public Questions(String QUESTION, String OPTION_1, String OPTION_2, String OPTION_3, int ANSWER_NO, String DIFFICULTY, int CATEGORIES_ID) {
        this.QUESTION = QUESTION;
        this.OPTION_1 = OPTION_1;
        this.OPTION_2 = OPTION_2;
        this.OPTION_3 = OPTION_3;
        this.ANSWER_NO = ANSWER_NO;
        this.DIFFICULTY = DIFFICULTY;
        this.CATEGORIES_ID = CATEGORIES_ID;
    }

    protected Questions(Parcel in) {
        ID = in.readInt();
        QUESTION = in.readString();
        OPTION_1 = in.readString();
        OPTION_2 = in.readString();
        OPTION_3 = in.readString();
        ANSWER_NO = in.readInt();
        DIFFICULTY = in.readString();
        CATEGORIES_ID = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(QUESTION);
        dest.writeString(OPTION_1);
        dest.writeString(OPTION_2);
        dest.writeString(OPTION_3);
        dest.writeInt(ANSWER_NO);
        dest.writeString(DIFFICULTY);
        dest.writeInt(CATEGORIES_ID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Questions> CREATOR = new Creator<Questions>() {
        @Override
        public Questions createFromParcel(Parcel in) {
            return new Questions(in);
        }

        @Override
        public Questions[] newArray(int size) {
            return new Questions[size];
        }
    };

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getQUESTION() {
        return QUESTION;
    }

    public void setQUESTION(String QUESTION) {
        this.QUESTION = QUESTION;
    }

    public String getOPTION_1() {
        return OPTION_1;
    }

    public void setOPTION_1(String OPTION_1) {
        this.OPTION_1 = OPTION_1;
    }

    public String getOPTION_2() {
        return OPTION_2;
    }

    public void setOPTION_2(String OPTION_2) {
        this.OPTION_2 = OPTION_2;
    }

    public String getOPTION_3() {
        return OPTION_3;
    }

    public void setOPTION_3(String OPTION_3) {
        this.OPTION_3 = OPTION_3;
    }

    public int getANSWER_NO() {
        return ANSWER_NO;
    }

    public void setANSWER_NO(int ANSWER_NO) {
        this.ANSWER_NO = ANSWER_NO;
    }

    public int getCATEGORIES_ID() {
        return CATEGORIES_ID;
    }

    public void setCATEGORIES_ID(int CATEGORIES_ID) {
        this.CATEGORIES_ID = CATEGORIES_ID;
    }

    public String getDIFFICULTY() {
        return DIFFICULTY;
    }

    public void setDIFFICULTY(String DIFFICULTY) {
        this.DIFFICULTY = DIFFICULTY;
    }

    public static String[] getDifficultyLevel(){
        return new String[]{
                DIFFICULTY_EASY,
                DIFFICULTY_MEDIUM,
                DIFFICULTY_HARD
        };

    }
}
