package com.example.docta.myapplication.Classes.Database;

public interface DatabaseConstants {

    String DATABASE_NAME="dosbrains.db";
    int DATABASE_VERSION=12;


    ////////////////TEACHER
    String TEACHER_TABLE_NAME = "Teacher";

   // String TEACHER_COLUMN_ID_TEACHER = "id_Teacher";
    String TEACHER_COLUMN_EMAIL ="email_teacher";
    String TEACHER_COLUMN_PASSWORD = "password";

    String CREATE_TABLE_TEACHER = "CREATE TABLE " + TEACHER_TABLE_NAME
            + " ( " +
   //         TEACHER_COLUMN_ID_TEACHER+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            TEACHER_COLUMN_EMAIL + " TEXT PRIMARY KEY, "+
            TEACHER_COLUMN_PASSWORD + " TEXT );";

    String DROP_TABLE_TEACHER = "DROP TABLE IF EXISTS " + TEACHER_TABLE_NAME +";";

    String INSERT_TEACHER ="insert into "+ TEACHER_TABLE_NAME + " (" + TEACHER_COLUMN_EMAIL+", " + TEACHER_COLUMN_PASSWORD + ") values(?,?)";
    String QUERRY_LOGIN_TEACHER = "SELECT * FROM "+ TEACHER_TABLE_NAME+ " WHERE " + TEACHER_COLUMN_EMAIL + "=? AND "+ TEACHER_COLUMN_PASSWORD + "=?";

            /////////STUDENT
    String STUDENT_TABLE_NAME="Studentt";

    String STUDENT_COLUMN_USERNAME="username";
    String STUDENT_COLUMN_CURRENT_AVATAR="current_avatar";
    String STUDENT_COLUMN_GENDER="gender";
    String STUDENT_COLUMN_AGE="age";
    String STUDENT_COLUMN_SCORE="score";
    String STUDENT_COLUMN_FEEDBACK="feedback";
    String STUDENT_COLUMN_RATING="rating";
    String STUDENT_COLUMN_EMAIL_TEACHER="email_teacher";
  //  String STUDENT_COLUMN_ID_TEACHER="teacher_id";

//    String CREATE_TABLE_STUDENT =
//            "CREATE TABLE "+STUDENT_TABLE_NAME+
//            " ( "+STUDENT_COLUMN_USERNAME+ " TEXT PRIMARY KEY, "+
//            STUDENT_COLUMN_CURRENT_AVATAR+ " BLOB, "+
//            STUDENT_COLUMN_GENDER+ " INTEGER, "+
//            STUDENT_COLUMN_AGE+ " INTEGER, "+
//            STUDENT_COLUMN_SCORE+ " REAL, "+
//            STUDENT_COLUMN_ID_TEACHER+ " INTEGER, " +
//            " FOREIGN KEY ( " + STUDENT_COLUMN_ID_TEACHER +
//            " ) REFERENCES "+TEACHER_TABLE_NAME+" ( " + TEACHER_COLUMN_ID_TEACHER + " ));";

    String CREATE_TABLE_STUDENT =
            "CREATE TABLE "+STUDENT_TABLE_NAME+
                    " ( "+STUDENT_COLUMN_USERNAME+ " TEXT PRIMARY KEY, "+
                    STUDENT_COLUMN_CURRENT_AVATAR+ " BLOB, "+
                    STUDENT_COLUMN_GENDER+ " INTEGER, "+
                    STUDENT_COLUMN_AGE+ " INTEGER, "+
                    STUDENT_COLUMN_SCORE+ " REAL, "+
                    STUDENT_COLUMN_FEEDBACK+" TEXT, "+
                    STUDENT_COLUMN_RATING+" REAL, "+
                    STUDENT_COLUMN_EMAIL_TEACHER+ " TEXT, " +
                    " FOREIGN KEY ( " + STUDENT_COLUMN_EMAIL_TEACHER +
                    " ) REFERENCES "+TEACHER_TABLE_NAME+" ( " + TEACHER_COLUMN_EMAIL + " ) ON DELETE SET NULL );";

    String DROP_TABLE_STUDENT = "DROP TABLE IF EXISTS "+STUDENT_TABLE_NAME+";";
    String INSERT_STUDENT = "insert into " + STUDENT_TABLE_NAME + " (" +
            STUDENT_COLUMN_USERNAME +", " +
            STUDENT_COLUMN_CURRENT_AVATAR +", " +
            STUDENT_COLUMN_GENDER +", " +
            STUDENT_COLUMN_AGE + ", " +
            STUDENT_COLUMN_SCORE + ", " +
            STUDENT_COLUMN_FEEDBACK+", "+
            STUDENT_COLUMN_RATING+ ", "+
            STUDENT_COLUMN_EMAIL_TEACHER+ ") values(?,?,?,?,?,?,?,?)";
    String QUERRY_LOGIN_STUDENT = "SELECT " + STUDENT_COLUMN_USERNAME +" FROM " + STUDENT_TABLE_NAME + " WHERE " + STUDENT_COLUMN_USERNAME +" =?";
    String QUERRY_STUDENT_NAMES= "SELECT "+STUDENT_COLUMN_USERNAME +" FROM " + STUDENT_TABLE_NAME + " WHERE " + STUDENT_COLUMN_USERNAME +" =?";
    String QUERRY_STUDENT_LIST = "SELECT * FROM " +STUDENT_TABLE_NAME + " WHERE " + STUDENT_COLUMN_EMAIL_TEACHER + " =?" ;

    ////////////////TESTRESULTS
    String TESTRESULTS_TABLE_NAME="TestResults";

    String TESTRESULTS_COLUMN_ID_TEST="id_test";
    String TESTRESULTS_COLUMN_DIFFICULTY="difficulty";
    String TESTRESULTS_COLUMN_CATEGORY="category";
    String TESTRESULTS_COLUMN_USERNAMESTUD="username";
    String TESTRESULTS_COLUMN_CORRECTANSWERS="correct_answers";
    String TESTRESULTS_COLUMN_SCORE="score";

    String CREATE_TABLE_TESTRESULTS= "CREATE TABLE " + TESTRESULTS_TABLE_NAME
            + " ( " + TESTRESULTS_COLUMN_ID_TEST + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TESTRESULTS_COLUMN_DIFFICULTY + " TEXT, " +
            TESTRESULTS_COLUMN_CATEGORY + " TEXT, " +
            TESTRESULTS_COLUMN_CORRECTANSWERS + " INTEGER, " +
            TESTRESULTS_COLUMN_SCORE + " REAL, "+
            TESTRESULTS_COLUMN_USERNAMESTUD + " TEXT ); "/*+
            FOREIGN KEY ( "+TESTRESULTS_COLUMN_USERNAMESTUD+
            ") REFERENCES "+STUDENT_TABLE_NAME+" ( "+STUDENT_COLUMN_USERNAME+" ));"*/;

    String DROP_TABLE_TESTRESULTS ="DROP TABLE IF EXISTS " + TESTRESULTS_TABLE_NAME +";";


    ////////////TASKS

    String TASKS_TABLE_NAME="Tasks";

    String TASKS_COLUMN_ID_TASK="id_task";
    String TASKS_COLUMN_DATE="date_task";
    String TASKS_COLUMN_INFO="info_task";
    String TASKS_COLUMN_ID_STUDENT="username";

    String CREATE_TABLE_TASKS=" CREATE TABLE "+TASKS_TABLE_NAME+" ( "+
            TASKS_COLUMN_ID_TASK+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            TASKS_COLUMN_DATE+" TEXT, "+
            TASKS_COLUMN_INFO+" TEXT, "+
            TASKS_COLUMN_ID_STUDENT+" TEXT );";

    String DROP_TABLE_TASKS=" DROP TABLE IF EXISTS "+TASKS_TABLE_NAME+" ; ";
    String INSERT_TASKS=" insert into "+TASKS_TABLE_NAME
            +" ( "+ TASKS_COLUMN_DATE+" , "
            +TASKS_COLUMN_INFO+ " , "
            +TASKS_COLUMN_ID_STUDENT+"  )values(?,?,?) ";
    String QUERY_TASKS=" SELECT "+TASKS_COLUMN_ID_TASK+" , "+TASKS_COLUMN_DATE+" , "+TASKS_COLUMN_INFO+ " FROM " +TASKS_TABLE_NAME + " WHERE "+TASKS_COLUMN_ID_STUDENT+" =? ";
    String QUERY_IDTASK = "SELECT " + TASKS_COLUMN_ID_TASK + " FROM " + TASKS_TABLE_NAME + " WHERE " + TASKS_COLUMN_DATE + " =? AND "
                            + TASKS_COLUMN_INFO + " =? AND " + TASKS_COLUMN_ID_STUDENT + " =? ";
    ////////////////AVATARS
    String AVATAR_TABLE_NAME="Avatar";

    String AVATAR_COLUMN_ID_AVATAR="id_avatar";
    String AVATAR_COLUMN_NAME="name_avatar";
    String AVATAR_COLUMN_PRICE="price_avatar";
    String AVATAR_COLUMN_IMAGE="image_avatar";
    String AVATAR_COLUMN_APP_AVATAR="app_avatar";


    String CREATE_TABLE_AVATAR= " CREATE TABLE "+ AVATAR_TABLE_NAME
            +" ( "+ AVATAR_COLUMN_ID_AVATAR + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + AVATAR_COLUMN_NAME+ " TEXT, "
            + AVATAR_COLUMN_PRICE+ " REAL, "
            + AVATAR_COLUMN_IMAGE+ " BLOB, "
            + AVATAR_COLUMN_APP_AVATAR + " TEXT );";

    String DROP_TABLE_AVATAR= " DROP TABLE IF EXISTS "+ AVATAR_TABLE_NAME+" ; ";
    String INSERT_AVATAR ="insert into "+ AVATAR_TABLE_NAME
            + " (" + AVATAR_COLUMN_NAME+", " + AVATAR_COLUMN_PRICE
            + ", "+ AVATAR_COLUMN_IMAGE+", "+ AVATAR_COLUMN_APP_AVATAR
            + " ) values(?,?,?,?)";


    ///////////////ASSOCIATIVE
    String ASSOCIATIVE_TABLE_NAME ="Asociere";

    String ASSOCIATIVE_COLUMN_USERNAME= "user_name";
    String ASSOCIATIVE_COLUMN_ID_AVATAR="id_avt";

//    String CREATE_TABLE_ASSOCIATIVE = " CREATE TABLE "+ ASSOCIATIVE_TABLE_NAME+ " ("+
//            ASSOCIATIVE_COLUMN_USERNAME + " TEXT," +
//            " FOREIGN KEY ("+ASSOCIATIVE_COLUMN_USERNAME+ " ) REFERENCES " + STUDENT_TABLE_NAME + " (" +STUDENT_COLUMN_USERNAME + " ));"+
//            ASSOCIATIVE_COLUMN_ID_AVATAR + " INTEGER, "+
//            " FOREIGN KEY ( "+ ASSOCIATIVE_COLUMN_ID_AVATAR+ " ) REFERENCES "+ AVATAR_TABLE_NAME + " (" + AVATAR_COLUMN_ID_AVATAR+ " ));";

    String CREATE_TABLE_ASSOCIATIVE = " CREATE TABLE "+ ASSOCIATIVE_TABLE_NAME+ " ("+
            ASSOCIATIVE_COLUMN_USERNAME + " TEXT, " +
            ASSOCIATIVE_COLUMN_ID_AVATAR + " INTEGER, " +
            " PRIMARY KEY (" + ASSOCIATIVE_COLUMN_USERNAME+ ", " + ASSOCIATIVE_COLUMN_ID_AVATAR+"));";
    String INSERT_ASSOCIATIVE_AVATAR= "insert into "+ ASSOCIATIVE_TABLE_NAME
            + " (" + ASSOCIATIVE_COLUMN_ID_AVATAR+", " +
            ASSOCIATIVE_COLUMN_USERNAME
            + " ) values(?,?)";

    String DROP_TABLE_ASSOCIATIVE= " DROP TABLE IF EXISTS "+ ASSOCIATIVE_TABLE_NAME;
    String QUERRY_ASSOCIATIVE_AVATAR = "SELECT * FROM " +ASSOCIATIVE_TABLE_NAME + " WHERE " + ASSOCIATIVE_COLUMN_USERNAME + " =?" ;

//Queriesssssssss
String QUERY_1 = "SELECT SUM( "+ TESTRESULTS_COLUMN_SCORE + " ) as sumx , SUM( "+ TESTRESULTS_COLUMN_CORRECTANSWERS +" ) as sumy FROM "
        + TESTRESULTS_TABLE_NAME + " WHERE " +
        TESTRESULTS_COLUMN_USERNAMESTUD +" =? AND "+ TESTRESULTS_COLUMN_DIFFICULTY + " =? AND "+ TESTRESULTS_COLUMN_CATEGORY + "=?";

 String QUERY_2 = "SELECT COUNT( "+TESTRESULTS_COLUMN_DIFFICULTY + " ) as count FROM "+ TESTRESULTS_TABLE_NAME+
         " WHERE "+ TESTRESULTS_COLUMN_USERNAMESTUD + " =? AND "+
         TESTRESULTS_COLUMN_DIFFICULTY + " =? AND "+ TESTRESULTS_COLUMN_CATEGORY+" =?" +" GROUP BY "+TESTRESULTS_COLUMN_DIFFICULTY;

 String QUERY_3 = "SELECT SUM( "+ TESTRESULTS_COLUMN_SCORE + " ) as sumx , SUM( "+ TESTRESULTS_COLUMN_CORRECTANSWERS +" ) as sumy FROM "
         + TESTRESULTS_TABLE_NAME + " WHERE " +
         TESTRESULTS_COLUMN_USERNAMESTUD +" =? AND "+ TESTRESULTS_COLUMN_DIFFICULTY + " =?";

 String QUERY_4 = "SELECT COUNT( "+TESTRESULTS_COLUMN_DIFFICULTY + " ) as count FROM "+ TESTRESULTS_TABLE_NAME+
         " WHERE "+ TESTRESULTS_COLUMN_USERNAMESTUD + " =? AND "+
         TESTRESULTS_COLUMN_DIFFICULTY + " =?" +" GROUP BY "+TESTRESULTS_COLUMN_DIFFICULTY;


}
