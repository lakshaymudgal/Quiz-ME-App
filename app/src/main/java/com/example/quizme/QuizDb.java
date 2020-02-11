package com.example.quizme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quizme.QuestionContract.*;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class QuizDb extends SQLiteOpenHelper {

    private static final String DbName = "QuizMe.db";
    private static final int DbVersion = 1;

    private static QuizDb instance;
    private SQLiteDatabase db;

    private QuizDb(@Nullable Context context) {
        super(context, DbName, null, DbVersion);
    }

    public static synchronized QuizDb getInstance(Context context){
        if(instance == null){

            instance = new QuizDb(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String CATEGORIES_TABLE_CREATE = " CREATE TABLE " + CategoriesTable.TABLE_NAME + " ( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COL_NAME + " Text " + " ) ";


        final String QUESTION_TABLE_CREATE = " CREATE TABLE " +  QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT  ,  " +
                QuestionTable.COL_QUES + " Text , " +
                QuestionTable.COL_OPTN_1 + " Text , " +
                QuestionTable.COL_OPTN_2 + " Text , " +
                QuestionTable.COL_OPTN_3 + " Text , " +
                QuestionTable.COL_ANS + " Integer , " +
                QuestionTable.COL_DIFFICULTY + " Text , " +
                QuestionTable.COL_CATEGORIES + " Integer ," +
                " FOREIGN KEY (" + QuestionTable.COL_CATEGORIES + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID  +")" + " ON DELETE CASCADE " +
                ")";
        db.execSQL(CATEGORIES_TABLE_CREATE);
        db.execSQL(QUESTION_TABLE_CREATE);
        fillCategTable();
        fillQuesTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategTable(){
        Categories c1 = new Categories("Sports");
        addCateg(c1);
        Categories c2 = new Categories("Gk");
        addCateg(c2);
        Categories c3 = new Categories("History");
        addCateg(c3);
    }

    private void addCateg(Categories categories){
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COL_NAME, categories.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuesTable(){
//        <-----------------------Sports Category-------------------->
        Questions q1 = new Questions("Which cricket team has won the 2017 Vijay Hazare Trophy?",
                "Karnataka", "Gujarat", "Tamil Nadu",
                3, Questions.DIFFICULTY_EASY, Categories.SPORTS);
        quesAdd(q1);
        Questions q2 = new Questions("Who was the first Indian in independent India to have won a medal in an individual Olympic event ?",
                "K D Jadhav", "Dhyanchand", "Prithipal Singh",
                1, Questions.DIFFICULTY_MEDIUM, Categories.SPORTS);
        quesAdd(q2);
        Questions q3 = new Questions("The 2017 National Sports Day (NSD) is celebrated on which day in India?",
                "August 28", "August 27", "August 29",
                3, Questions.DIFFICULTY_HARD, Categories.SPORTS);
        quesAdd(q3);
        Questions q4 = new Questions("What does the Olympic Flame symbolize?",
                "zeal to play sports", "Continuity", "Challenge",
                2, Questions.DIFFICULTY_EASY, Categories.SPORTS);
        quesAdd(q4);
        Questions q5 = new Questions("Which among the following is the women’s equivalent of the Davis Cup?",
                "Hopman Cup", "Fed Cup", "BMW Open",
                2, Questions.DIFFICULTY_MEDIUM, Categories.SPORTS);
        quesAdd(q5);
        Questions q6 = new Questions("Sandy storm” is the autobiography of which among the following veteran cricketers?",
                "Dilip Vengsarkar", "Mohinder Amarnath", "Sandeep Patil",
                3, Questions.DIFFICULTY_HARD, Categories.SPORTS);
        quesAdd(q6);
        Questions q7 = new Questions("The name of S Vijayalakshmi is famous in which among the following games / sports?",
                "Badminton", "Chess", "Table Tennis",
                2, Questions.DIFFICULTY_EASY, Categories.SPORTS);
        quesAdd(q7);
        Questions q8 = new Questions("The famous football player Maradona belongs to which among the following countries?",
                "Argentina", "Chile", "Brazil",
                1, Questions.DIFFICULTY_MEDIUM, Categories.SPORTS);
        quesAdd(q8);
        Questions q9 = new Questions("“Electra Gold Cup” is associated with which sports?",
                "Lawn Tennis", "Table Tennis", "Football",
                2, Questions.DIFFICULTY_HARD, Categories.SPORTS);
        quesAdd(q9);
        Questions q10 = new Questions("When was IPL started?",
                "2008", "2009", "2007",
                3, Questions.DIFFICULTY_EASY, Categories.SPORTS);
        quesAdd(q10);
        Questions q11 = new Questions("Antony De Mello Trophy is associated with test cricket series played between",
                "Australia and India", " England and India", "South Africa and India",
                2, Questions.DIFFICULTY_MEDIUM, Categories.SPORTS);
        quesAdd(q11);
        Questions q12 = new Questions("Which is the first of the four Grand Slam tennis tournaments to be held each year?",
                "U.S. Open", " Australian Open", "Wimbledon",
                2, Questions.DIFFICULTY_HARD, Categories.SPORTS);
        quesAdd(q12);
        Questions q13 = new Questions("Durand Cup is associated with?",
                "Football", "Swimming", "Hockey",
                1, Questions.DIFFICULTY_EASY, Categories.SPORTS);
        quesAdd(q13);
        Questions q14 = new Questions("Who among the following was the First Indian Cricketer to bag 500 wickets in Test matches?",
                "Kapil Dev", "Srinath", "Anil Kumble",
                3, Questions.DIFFICULTY_MEDIUM, Categories.SPORTS);
        quesAdd(q14);
        Questions q15 = new Questions("Who among the following was the first Indian to win an Olympic medal ?",
                "K D Jadhav", "Leander paes", "P T Usha",
                1, Questions.DIFFICULTY_HARD, Categories.SPORTS);
        quesAdd(q15);

//      <-----------------GK Category ------------->

        Questions q16 = new Questions(" ........... is the first woman to head a public sector bank.",
                " Arundhati Bhattacharya", "Chanda Kochar", "Shikha Sharma",
                1, Questions.DIFFICULTY_EASY, Categories.GK);
        quesAdd(q16);
        Questions q17 = new Questions("World Tourism Day is celebrated on-",
                "September 25", "September 27", "September 12",
                2, Questions.DIFFICULTY_MEDIUM, Categories.GK);
        quesAdd(q17);
        Questions q18 = new Questions("Where is Bose Institute ?",
                "Dispur", "Mumbai", "Kolkata",
                3, Questions.DIFFICULTY_HARD, Categories.GK);
        quesAdd(q18);
        Questions q19 = new Questions("When is the International Yoga Day celebrated ?",
                "June 21", " March 21", "May 31",
                1, Questions.DIFFICULTY_EASY, Categories.GK);
        quesAdd(q19);
        Questions q20 = new Questions("The motif of 'Hampi with Chariot' is printed on the reverse of which currency note ?",
                "One Rupee Note", "Rs. 50 note", "Rs. 500 note",
                2, Questions.DIFFICULTY_MEDIUM, Categories.GK);
        quesAdd(q20);
        Questions q21 = new Questions(" 'Line of Blood' is a book written by whom?",
                "Ursula Vernon", "Amal EI-Mohtar", "None of the above",
                3, Questions.DIFFICULTY_HARD, Categories.GK);
        quesAdd(q21);
        Questions q22 = new Questions("'SHIKHAR SE PUKAR' is a documentary film related to which topic?",
                "Water Conservation", "Girl Education", "None of the above",
                1, Questions.DIFFICULTY_EASY, Categories.GK);
        quesAdd(q22);
        Questions q23 = new Questions("The largest biotechnology meeting of India was organized in which city?",
                "New Delhi", "Leh", " Manesar",
                1, Questions.DIFFICULTY_MEDIUM, Categories.GK);
        quesAdd(q23);
        Questions q24 = new Questions("Dallol Geothermal Field belongs to which country?",
                "Russia", "Sudan", "Ethiopia",
                3, Questions.DIFFICULTY_HARD, Categories.GK);
        quesAdd(q24);
        Questions q25 = new Questions("In which state living root bridges are found?",
                "Assam", "Meghalaya", "Odisha",
                2, Questions.DIFFICULTY_EASY, Categories.GK);
        quesAdd(q25);
        Questions q26 = new Questions("Anita Anand,who is an Indian born politician has become part of which country's cabinet?",
                " Russia", "United Kingdom", "Canada",
                3, Questions.DIFFICULTY_MEDIUM, Categories.GK);
        quesAdd(q26);
        Questions q27 = new Questions("AIIA will work with which country for integrating Ayurveda principles with modern medicine?",
                " Russia", " Japan", "Australia",
                3, Questions.DIFFICULTY_HARD, Categories.GK);
        quesAdd(q27);
        Questions q28 = new Questions("Who became the youngest artist in the history of Grammys to score six Gammy Award nominations?",
                "Billie Eilish", "Swae Lee", "Lana Del Rey",
                1, Questions.DIFFICULTY_EASY, Categories.GK);
        quesAdd(q28);
        Questions q29 = new Questions("Indian Army celebrated the Poonch Link-up Day on which date?",
                "November 24", "November 23", "November 25",
                2, Questions.DIFFICULTY_MEDIUM, Categories.GK);
        quesAdd(q29);
        Questions q30 = new Questions(" Which city hosted the 5th India – Europe 29 Business Forum?",
                "Gurugram", "Bangalore", "New Delhi",
                3, Questions.DIFFICULTY_HARD, Categories.GK);
        quesAdd(q30);

//        <--------------------History Category ------------------->

        Questions q31 = new Questions("Which among the following is not a correct pair?",
                "Ellora Caves – Rastrakuta Rulers", "Khajuraho – Chandellas", "Elephanta Caves – Mauyra Era",
                3, Questions.DIFFICULTY_EASY, Categories.HISTORY);
        quesAdd(q31);
        Questions q32 = new Questions("The eighth-century tripartite power struggle was among which of the following?",
                "Chalukyas, Pallavas and Pandyas", " Cholas, Rastrakutas and Yadavas,", "None of the above",
                1, Questions.DIFFICULTY_MEDIUM, Categories.HISTORY);
        quesAdd(q32);
        Questions q33 = new Questions("Which among the following is not correct?",
                "The capital of pandyas was Madurai", "Capital of the Videha Kingdom – Mithila", "The capital of Cheras was Vanchi",
                2, Questions.DIFFICULTY_HARD, Categories.HISTORY);
        quesAdd(q33);
        Questions q34 = new Questions("Which king started the organization of Kumbh fair at Allahabad?",
                "Dhruvasena Ii", "Narshimhvarman", "None of the above",
                3, Questions.DIFFICULTY_EASY, Categories.HISTORY);
        quesAdd(q34);
        Questions q35 = new Questions("Upnishads are books on :",
                "Politics", "Philosophy", "Social life",
                2, Questions.DIFFICULTY_MEDIUM, Categories.HISTORY);
        quesAdd(q35);
        Questions q36 = new Questions("Who was the first Indian ruler who had territory outside India?",
                "Ashoka", "Chandragupta Maurya", "Kanishka",
                3, Questions.DIFFICULTY_HARD, Categories.HISTORY);
        quesAdd(q36);
        Questions q37 = new Questions("Which of the following statement is wrong?",
                "Sunga dynasty was founded by pushyamitra", "Ashoka invaded the kalinga in 261 BC", "None of the above",
                3, Questions.DIFFICULTY_EASY, Categories.HISTORY);
        quesAdd(q37);
        Questions q38 = new Questions("Who among the following was worshipped during Early Vedic Civilization?",
                " Varuna", " Indra", "All the above",
                3, Questions.DIFFICULTY_MEDIUM, Categories.HISTORY);
        quesAdd(q38);
        Questions q39 = new Questions("Where were the hymns of Rigveda composed?",
                "Punjab", "Gujarat", "Rajasthan",
                1, Questions.DIFFICULTY_HARD, Categories.HISTORY);
        quesAdd(q39);
        Questions q40 = new Questions("What led to the end of Indus Valley Civilization?",
                " Invasion of Aryans", "Earthquakes", "All the above",
                1, Questions.DIFFICULTY_EASY, Categories.HISTORY);
        quesAdd(q40);
        Questions q41 = new Questions("Who was the main male God worshipped by Indus people?",
                "Lord Vishnu", "Vishnu", "Brahma",
                1, Questions.DIFFICULTY_MEDIUM, Categories.HISTORY);
        quesAdd(q41);
        Questions q42 = new Questions("Which empire lasted the longest among the following?",
                " The Palas", "The Pratiharas", "The Rashtrakutas",
                3, Questions.DIFFICULTY_HARD, Categories.HISTORY);
        quesAdd(q42);
        Questions q43 = new Questions("Who was the ruler of the kingdom between the rivers Jhelum and Chenab?",
                "Alexander the Great", "Darius III", "King Porus",
                3, Questions.DIFFICULTY_EASY, Categories.HISTORY);
        quesAdd(q43);
        Questions q44 = new Questions("Who was the last Hindu emperor of northern India?",
                "Harsha", "Pulakesin II", "Rajyavardhana",
                1, Questions.DIFFICULTY_MEDIUM, Categories.HISTORY);
        quesAdd(q44);
        Questions q45 = new Questions("The rulers of which dynasty make land grants to Brahmanas?",
                "Maurya", "Satvahana", "Sunga",
                2, Questions.DIFFICULTY_HARD, Categories.HISTORY);
        quesAdd(q45);
    }

    private void quesAdd(Questions questions){

        ContentValues contentValues = new ContentValues();
        contentValues.put(QuestionTable.COL_QUES, questions.getQUESTION());
        contentValues.put(QuestionTable.COL_OPTN_1, questions.getOPTION_1());
        contentValues.put(QuestionTable.COL_OPTN_2, questions.getOPTION_2());
        contentValues.put(QuestionTable.COL_OPTN_3, questions.getOPTION_3());
        contentValues.put(QuestionTable.COL_ANS, questions.getANSWER_NO());
        contentValues.put(QuestionTable.COL_DIFFICULTY, questions.getDIFFICULTY());
        contentValues.put(QuestionTable.COL_CATEGORIES, questions.getCATEGORIES_ID());

        db.insert(QuestionTable.TABLE_NAME, null, contentValues);

    }

    public List<Categories> getAllCateg(){
        List<Categories> categoriesList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);
        if(cursor.moveToFirst()){
            do{
                Categories categories = new Categories();
                categories.setId(cursor.getInt(cursor.getColumnIndex(CategoriesTable._ID)));
                categories.setName(cursor.getString(cursor.getColumnIndex(CategoriesTable.COL_NAME)));
                categoriesList.add(categories);
            }while (cursor.moveToNext());
        }
         cursor.close();
        return categoriesList;
    }

    public ArrayList<Questions> getAllQues(){

        ArrayList<Questions> questionsList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME, null);
        if(cursor.moveToFirst()){
            do{

                Questions questions = new Questions();
                questions.setID(cursor.getInt(cursor.getColumnIndex(QuestionTable._ID)));
                questions.setQUESTION(cursor.getString(cursor.getColumnIndex(QuestionTable.COL_QUES)));
                questions.setOPTION_1(cursor.getString(cursor.getColumnIndex(QuestionTable.COL_OPTN_1)));
                questions.setOPTION_2(cursor.getString(cursor.getColumnIndex(QuestionTable.COL_OPTN_2)));
                questions.setOPTION_3(cursor.getString(cursor.getColumnIndex(QuestionTable.COL_OPTN_3)));
                questions.setANSWER_NO(cursor.getInt(cursor.getColumnIndex(QuestionTable.COL_ANS)));
                questions.setDIFFICULTY(cursor.getString(cursor.getColumnIndex(QuestionTable.COL_DIFFICULTY)));
                questions.setCATEGORIES_ID(cursor.getInt(cursor.getColumnIndex(QuestionTable.COL_CATEGORIES)));

                questionsList.add(questions);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return questionsList;
    }

    public ArrayList<Questions> getQues(int category_id, String difficulty){

        ArrayList<Questions> questionsList = new ArrayList<>();
        db = getReadableDatabase();

        String select = QuestionTable.COL_CATEGORIES + " = ?" + " AND " +
                         QuestionTable.COL_DIFFICULTY + " = ?";

        String[] selectionArgs = new String[]{String.valueOf(category_id), difficulty};

        Cursor cursor = db.query(QuestionTable.TABLE_NAME, null, select, selectionArgs,
                null, null, null);

        if(cursor.moveToFirst()){
            do{

                Questions questions = new Questions();
                questions.setID(cursor.getInt(cursor.getColumnIndex(QuestionTable._ID)));
                questions.setQUESTION(cursor.getString(cursor.getColumnIndex(QuestionTable.COL_QUES)));
                questions.setOPTION_1(cursor.getString(cursor.getColumnIndex(QuestionTable.COL_OPTN_1)));
                questions.setOPTION_2(cursor.getString(cursor.getColumnIndex(QuestionTable.COL_OPTN_2)));
                questions.setOPTION_3(cursor.getString(cursor.getColumnIndex(QuestionTable.COL_OPTN_3)));
                questions.setANSWER_NO(cursor.getInt(cursor.getColumnIndex(QuestionTable.COL_ANS)));
                questions.setDIFFICULTY(cursor.getString(cursor.getColumnIndex(QuestionTable.COL_DIFFICULTY)));
                questions.setCATEGORIES_ID(cursor.getInt(cursor.getColumnIndex(QuestionTable.COL_CATEGORIES)));

                questionsList.add(questions);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return questionsList;
    }
}
