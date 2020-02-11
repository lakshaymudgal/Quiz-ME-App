package com.example.quizme;

import android.provider.BaseColumns;

public final class QuestionContract {

    private  QuestionContract(){}

    public static class CategoriesTable implements BaseColumns{
        public static final String TABLE_NAME = "tablename";
        public static final String COL_NAME = "name";
    }

    public static class QuestionTable implements BaseColumns {

        public static final String TABLE_NAME = "ques_table";
        public static final String COL_QUES = "col_ques";
        public static final String COL_OPTN_1 = "col_optn_1";
        public static final String COL_OPTN_2 = "col_optn_2";
        public static final String COL_OPTN_3 = "col_optn_3";
        public static final String COL_ANS = "col_ans";
        public static final String COL_DIFFICULTY = "col_difficulty";
        public static final String COL_CATEGORIES = "col_categories";

    }
}
