package com.example.docta.myapplication.Activities;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.docta.myapplication.Classes.Database.AssociativeDAO;

import com.example.docta.myapplication.Classes.util.Question;
import com.example.docta.myapplication.Classes.Network.HttpManager;
import com.example.docta.myapplication.Classes.util.QuestionsSet;
import com.example.docta.myapplication.Classes.util.QuestionsSetParser;
import com.example.docta.myapplication.R;
import com.example.docta.myapplication.Classes.util.Constants;

import org.json.JSONException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


public class HomePageActivity extends AppCompatActivity {

    private Button btn_play;
    private Button btn_learn;
    private Button btn_ranking;
    private Button btn_settings;
    private Button btn_my_avatars;
    private Button btn_daily_test;
    private Button btn_daily_question;
    private Button btn_results;
    private ImageButton img_btn_feedback;
    private TextView tv_avatar_name;
    private Button btn_back_teacher;
    private Button btn_tasks;
    private SharedPreferences sharedPreferences;
    private ImageButton img_btn_help;
    private static final String URL = Constants.URL_JSON_TESTS;
    private QuestionsSet questionsSet;
    private ArrayList<Question> dailyTestQuestions;
    private ArrayList<Question> dailyQuestion;
    private ProgressDialog progressDialog;
    Intent intent;
    private Boolean isChecked;
    private SharedPreferences sharedPreferencesSet;
    private SharedPreferences.Editor editor;
    private AssociativeDAO associativeDAO;
    private long dateForQuestion;
//    private static final IntentFilter s_intentFilter;
//    static {
//        s_intentFilter = new IntentFilter();
//       s_intentFilter.addAction(Intent.ACTION_TIME_TICK);
//       s_intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
//       s_intentFilter.addAction(Intent.ACTION_TIME_CHANGED);
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        isChecked = getIntent().getBooleanExtra(Constants.DOWNLOAD_DONE,false);
        sharedPreferencesSet = getSharedPreferences(getString(R.string.home_page_text_set_intrebari), MODE_PRIVATE);
        if(savedInstanceState==null){
            String title = getString(R.string.Titlu_PaginaPrincipalaJoc);
            this.setTitle(title);
        }
                @SuppressLint("StaticFieldLeak") HttpManager manager = new HttpManager() {
                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        if(!isChecked) {
                                getInfos();
                            }
                    }

                    @Override
                    protected String doInBackground(String... strings) {
                        return super.doInBackground(strings);

                    }

                    @Override
                    protected void onPostExecute(String s) {
                        try {
                            questionsSet = QuestionsSetParser.fromJson(s);
                            if(!isChecked) {
                                progressDialog.dismiss();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), getString(R.string.jucam_parsare_eroare), Toast.LENGTH_LONG).show();
                        }
                    }

                };
        manager.execute(URL);
        initComponents();
        QuestionDayDelayed();


    }

    private void QuestionDayDelayed() {
        //               milli min  hour  day
//          long one_Day = 1000 * 60 * 60 * 24;
        long one_minute = 1000 * 60*60*24;
        SharedPreferences pref = getSharedPreferences(Constants.TIME_PREF, MODE_PRIVATE);
        Long oldTime = pref.getLong("smstimestamp", 0);
        long newTime = oldTime + one_minute; // //4:18 + 3  = 4:21 // 4:20 //60000
        Date d = new Date(newTime);
        // Long oldT = Long.parseLong(oldTime);
//        DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
//        df.setTimeZone(TimeZone.getTimeZone("GMT+0"));

        @SuppressLint("SimpleDateFormat") DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.format(d);
        Calendar cal = Calendar. getInstance();
        cal. setTime(d);
        cal.setTimeZone(null);
        if (System.currentTimeMillis() - oldTime > one_minute) {
            btn_daily_question.setEnabled(true);
            btn_daily_question.setBackgroundResource(R.drawable.rounded_button_general);
            Toast.makeText(getApplicationContext(),"Intrebarea zilei este gata sa fie rezolvata " ,Toast.LENGTH_LONG).show();
        } else {
            btn_daily_question.setEnabled(false);
            Toast.makeText(getApplicationContext(),"Intrebarea zilei v-a fi activa la data de: " + cal.getTime(),Toast.LENGTH_LONG).show();
            btn_daily_question.setBackgroundResource(R.drawable.rounded_button_invalidate);
        }
    }

    public static String getDurationBreakdown(long millis) {
        if(millis < 0) {
            throw new IllegalArgumentException("Duration must be greater than zero!");
        }

        long days = TimeUnit.MILLISECONDS.toDays(millis);
        millis -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        StringBuilder sb = new StringBuilder(64);
        sb.append(days);
        sb.append(" Days ");
        sb.append(hours);
        sb.append(" Hours ");
        sb.append(minutes);
        sb.append(" Minutes ");
        sb.append(seconds);
        sb.append(" Seconds");

        return(sb.toString());
    }


    private void getInfos(){
          //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            progressDialog = new ProgressDialog(HomePageActivity.this);
            progressDialog.setMax(100);
            progressDialog.setMessage(getString(R.string.ppj_progress_incarcare));
            progressDialog.setTitle(getString(R.string.ppj_progress_descarcare));
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();
            progressDialog.setCancelable(false);
            progressDialog.getButton(ProgressDialog.BUTTON_NEUTRAL).setVisibility(View.INVISIBLE);

        @SuppressLint({"StaticFieldLeak", "HandlerLeak"}) Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    progressDialog.incrementProgressBy(1);
                }
            };

            new Thread(() -> {
                while (progressDialog.getProgress() <= progressDialog.getMax()) {
                    try {
                        Thread.sleep(20);
                        handler.sendMessage(handler.obtainMessage());
                        if (progressDialog.getProgress() == progressDialog.getMax()) {

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            //getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @SuppressLint("SetTextI18n")
    private void initComponents(){
        btn_play =findViewById(R.id.home_btn_play);
        btn_learn =findViewById(R.id.home_btn_learn);
        btn_my_avatars =findViewById(R.id.home_btn_myavatars);
        btn_ranking =findViewById(R.id.home_btn_ranking);
        btn_daily_question =findViewById(R.id.home_btn_dailyquestion);
        btn_daily_test =findViewById(R.id.home_btn_dailytest);
        btn_settings =findViewById(R.id.home_btn_settings);
        img_btn_feedback =findViewById(R.id.home_imgBtn_star);
        tv_avatar_name =findViewById(R.id.home_tv_welcome);
        btn_back_teacher =findViewById(R.id.home_btn_backtoteacher);
        img_btn_help =findViewById(R.id.home_imgBtn_question);
        btn_tasks =findViewById(R.id.home_btn_tasks);
        btn_results = findViewById(R.id.home_btn_statistics);
        associativeDAO = new AssociativeDAO(this);
        associativeDAO.open();
        associativeDAO.close();
        sharedPreferences= getSharedPreferences(Constants.CONT_STATUT_PREF, MODE_PRIVATE);
        String user = sharedPreferences.getString(Constants.USER_STATUT_PREF, getString(R.string.ppj_utilizator_default_pref));

        if(user.compareTo(getString(R.string.principala_utilizator_profesor_pref_message))==0){
            btn_back_teacher.setVisibility(View.VISIBLE);
            btn_results.setVisibility(View.INVISIBLE);
        }
        else {
            btn_back_teacher.setVisibility(View.INVISIBLE);
            btn_results.setVisibility(View.VISIBLE);
        }
        String nume= getIntent().getStringExtra(Constants.NAME_KEY);
        if (nume==null){
            tv_avatar_name.setText(getString(R.string.ppj_tv_bine_ai_venit_null));
        }
        else {
        tv_avatar_name.setText(getString(R.string.ppj_tv_bineAiVenit)+ nume);
        }


        btn_learn.setOnClickListener(startToLearn());
        btn_play.setOnClickListener(startToPlay());
        btn_daily_question.setOnClickListener(openDailyQuestion());
        btn_daily_test.setOnClickListener(openDailyTest());
        btn_my_avatars.setOnClickListener(openAvatars());
        btn_ranking.setOnClickListener(openRanking());
        btn_settings.setOnClickListener(openSettings());
        img_btn_feedback.setOnClickListener(openFeedback());
        btn_back_teacher.setOnClickListener(backToTeacher());
        img_btn_help.setOnClickListener(openHelp());
        btn_tasks.setOnClickListener(openTasks());
        btn_results.setOnClickListener(openResults());
   }

   private View.OnClickListener openTasks()
   {
       return new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),TasksActivity.class);
            String nume= getIntent().getStringExtra(Constants.NAME_KEY);
            intent.putExtra(Constants.NAME_KEY, nume);
            startActivity(intent);
           }
       };
   }
   private View.OnClickListener backToTeacher(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
        }};
   }
   private View.OnClickListener startToLearn(){
       return new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(), LetsLearnActivity.class);
               startActivity(intent);
           }
       };
    }
    private View.OnClickListener startToPlay(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LetsPlayActivity.class);
                intent.putExtra(Constants.QUESTIONS_SET_KEY, questionsSet);
                startActivity(intent);
            }
        };
    }
    private View.OnClickListener openDailyQuestion() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btn_daily_question.isEnabled()) {
                } else {
                    SharedPreferences sr = getSharedPreferences(Constants.CATEG_PREF, MODE_PRIVATE);
                    editor = sr.edit();
                    editor.putString(Constants.GET_CATEG, Constants.QUESTION_OF_THE_DAY);
                    editor.apply();
                    dailyQuestion = questionsSet.getDailyQuestion();
                    Collections.shuffle(dailyQuestion);
                    Intent intent = new Intent(getApplicationContext(), DailyQuestionActivity.class);
                    intent.putExtra(Constants.DAILY_QUESTION_KEY, dailyQuestion.get(0));

                    startActivity(intent);
                }
            }
        };
    }
    private View.OnClickListener openDailyTest(){

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dailyTestQuestions = questionsSet.getHard();
                Intent intent = new Intent(getApplicationContext(), QuestionsActivity.class);
                intent.putExtra(Constants.DAILY_TEST,getString(R.string.Valoare_TestulZilei));
                intent.putExtra(Constants.QUESTIONS_LIST_KEY, dailyTestQuestions);
                startActivity(intent);
                finish();

            }

        };

    }
    private View.OnClickListener openResults(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),StudentStatistics.class));
            }
        };
    }

    private View.OnClickListener openAvatars(){

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyAvatarsActivity.class);
                String nume= getIntent().getStringExtra(Constants.NAME_KEY);
                intent.putExtra(Constants.NAME_KEY, nume);
                startActivity(intent);
            }
        };

    }

    private View.OnClickListener openRanking(){

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RankingActivity.class);
                String nume= getIntent().getStringExtra(Constants.NAME_KEY);
                intent.putExtra(Constants.NAME_KEY, nume);
                startActivity(intent);
            }
        };

    }

    private View.OnClickListener openSettings(){

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StudentSettingsActivity.class);
                String nume= getIntent().getStringExtra(Constants.NAME_KEY);
                intent.putExtra(Constants.NAME_KEY, nume);
                startActivity(intent);
            }

        };

    }

    private View.OnClickListener openFeedback(){

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FeedbackActivity.class);
                startActivity(intent);
            }

        };

    }
    private View.OnClickListener openHelp(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(intent);
            }
        };
    }




//    public void startt() {
//        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        int interval = 20000;
//
//        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
//        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
//        btn_daily_question.setEnabled(false);
//    }
//
//    public void startAt10() {
//        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        int interval = 1000 * 60 * 20;
//
//        /* Set the alarm to start at 10:30 AM */
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.set(Calendar.HOUR_OF_DAY, 4);
//        calendar.set(Calendar.MINUTE, 37);
//
//        /* Repeating on every 20 minutes interval */
//        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                1000 * 60 , pendingIntent);
//        btn_daily_question.setEnabled(true);
//    }
//
//    public void cancel() {
//        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        manager.cancel(pendingIntent);
//        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
//    }

//    public void startAlarm(View view){
//        long two_min = 1000 * 10;
//        Long alertTime = new GregorianCalendar().getTimeInMillis() + two_min;
//        Long alertAlternative = System.currentTimeMillis() + two_min;
//        Intent intentAlert = new Intent(getApplicationContext(),AlertReceiver.class);
//
//        //   long one_minute = 1000 * 60*3;
//        SharedPreferences pref = getSharedPreferences(Constants.TIME_PREF, MODE_PRIVATE);
//        Long oldTime = pref.getLong("smstimestamp", 0);
//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        alarmManager.set(AlarmManager.RTC_WAKEUP,alertAlternative,PendingIntent.getBroadcast(getApplicationContext(),1,intentAlert,PendingIntent.FLAG_UPDATE_CURRENT));
        // Long oldT = Long.parseLong(oldTime);
//        if (System.currentTimeMillis() - oldTime > two_min) {
//            btn_daily_question.setEnabled(true);
//            btn_daily_question.setBackgroundResource(R.drawable.rounded_button_general);
//        } else {
//            btn_daily_question.setEnabled(false);
//            btn_daily_question.setBackgroundResource(R.drawable.rounded_button_invalidate);
//        }

//    private void startAlarma(boolean isNotification, boolean isRepeat) {
//        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        Intent myIntent;
//        PendingIntent pendingIntent;
//
//        // SET TIME HERE
//        Calendar calendar= Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY,15);
//        calendar.set(Calendar.MINUTE,32);
//
//
//        myIntent = new Intent(HomePageActivity.this,AlertReceiver.class);
//        pendingIntent = PendingIntent.getBroadcast(this,0,myIntent,0);
//
//
//        if(!isRepeat)
//            manager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime()+3000,pendingIntent);
//        else
//            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,pendingIntent);
//    }

}

