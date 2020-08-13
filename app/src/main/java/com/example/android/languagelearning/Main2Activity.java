package com.example.android.languagelearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main2Activity extends AppCompatActivity
{
//        implements NavigationView.OnNavigationItemSelectedListener{






    Toolbar toolbar;
//    DrawerLayout drawerLayout;
//    NavigationView navigationView;



    public String x=null;
    public FragmentManager fm = getSupportFragmentManager();
    public FragmentTransaction ft = fm.beginTransaction();



    public static final String SHARED_PREFS_1 = "sharedPrefs_1";
    public static final String TEXT_MASTER_1 = "text_master_1";
    public static final String PROGRESS_MASTER_1 = "progress_master_1";
    public static final String TEXT_LEARNING_1 = "text_learning_1";
    public static final String PROGRESS_LEARNING_1 = "progress_learning_1";
    public static final String TEXT_REVIEWING_1 = "text_reviewing_1";
    public static final String PROGRESS_REVIEWING_1 = "progress_reviewing_1";
    public static final String TEXT_WORD1_1 = "text_wod_1";
    public static final String TEXT_DEFINITION_1 = "text_def_1";
    // shared variable default loading
    private   String text_master_1="";
    public Integer progress_master_1=0;
    public  String text_learning_1="";
    public  Integer progress_learning_1=0;
    public String text_reviewing_1="";
    public  Integer progress_reviewing_1=0;
    public  String text_word_1="";
    public  String text_def_1="";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            x= bundle.getString("array");
        }

        if(x!=null){
            switch (x) {
                case ("Name of Week Days"):
                    Fragment weekDaysFragment = new WeekDaysFragment();

                    ft.replace(R.id.fragment_container,weekDaysFragment).commit();
                    break;
                case ("Names of Months"):
                    Fragment monthsFragment = new MonthsFragment();
                    ft.replace(R.id.fragment_container,monthsFragment).commit();
                    break;

                case ("Words List 1"):
                    Fragment wordsList1 = new WordsList1();
                    ft.replace(R.id.fragment_container,wordsList1).commit();
                    break;
                case ("Words List 2"):
                    Fragment wordsList2 = new WordsList2();
                    ft.replace(R.id.fragment_container,wordsList2).commit();
                    break;
                case ("Words List 3"):
                    Fragment wordsList3 = new WordsList3();
                    ft.replace(R.id.fragment_container,wordsList3).commit();
                    break;
                case ("Words List 4"):
                    Fragment wordsList4 = new WordsList4();
                    ft.replace(R.id.fragment_container,wordsList4).commit();
                    break;
                case ("Words List 5"):
                    Fragment wordsList5 = new WordsList5();
                    ft.replace(R.id.fragment_container,wordsList5).commit();
                    break;
                case ("Words List 6"):
                    Fragment wordsList6 = new WordsList6();
                    ft.replace(R.id.fragment_container,wordsList6).commit();
                    break;
                case ("Words List 7"):
                    Fragment wordsList7 = new WordsList7();
                    ft.replace(R.id.fragment_container,wordsList7).commit();
                    break;
                case ("Words List 8"):
                    Fragment wordsList8 = new WordsList8();
                    ft.replace(R.id.fragment_container,wordsList8).commit();
                    break;
                case ("Words List 9"):
                    Fragment wordsList9 = new WordsList9();
                    ft.replace(R.id.fragment_container,wordsList9).commit();
                    break;
                case ("Words List 10"):
                    Fragment wordsList10 = new WordsList10();
                    ft.replace(R.id.fragment_container,wordsList10).commit();
                    break;
                case ("Words List 11"):
                    Fragment wordsList11 = new WordsList11();
                    ft.replace(R.id.fragment_container,wordsList11).commit();
                    break;
                case ("Words List 12"):
                    Fragment wordsList12 = new WordsList12();
                    ft.replace(R.id.fragment_container,wordsList12).commit();
                    break;
                case ("Words List 13"):
                    Fragment wordsList13 = new WordsList13();
                    ft.replace(R.id.fragment_container,wordsList13).commit();
                    break;
                case ("Words List 14"):
                    Fragment wordsList14 = new WordsList14();
                    ft.replace(R.id.fragment_container,wordsList14).commit();
                    break;
                case ("Words List 15"):
                    Fragment wordsList15 = new WordsList15();
                    ft.replace(R.id.fragment_container,wordsList15).commit();
                    break;


            }
        }
//        drawerLayout = findViewById(R.id.drawer_layout);
//        navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//        toolbar = findViewById(R.id.toolbar);
        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();

    }
    //Getting Data from Fragment
    public void getDataFromFragment1( String text_word, String text_def,
                                     String text_master_tv,
                                     String text_reviewing_tv,
                                     String text_learning_tv,
                                     Integer progress_master,
                                     Integer progress_reviewing,
                                     Integer progress_learning){
        SharedPreferences sharedPreferences= getSharedPreferences("SHARED_PREFS_1", MODE_PRIVATE);
        SharedPreferences.Editor editor1=  sharedPreferences.edit();
        editor1.putString(TEXT_MASTER_1,text_master_tv);
        editor1.putString(TEXT_REVIEWING_1,text_reviewing_tv);
        editor1.putString(TEXT_LEARNING_1,text_learning_tv);
        editor1.putString(TEXT_WORD1_1,text_word);
        editor1.putString(TEXT_DEFINITION_1,text_def);
        editor1.putInt(PROGRESS_MASTER_1,progress_master);
        editor1.putInt(PROGRESS_LEARNING_1,progress_learning);
        editor1.putInt(PROGRESS_REVIEWING_1,progress_reviewing);
        editor1.apply();

        Bundle b1 = new Bundle();




    }
    private void loadData() {
//        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS_1, Context.MODE_PRIVATE);
//
//        text_master_1=sharedPreferences.getString(TEXT_MASTER_1,tvProgressMaster.getText().toString());
//        progress_master_1=sharedPreferences.getInt(PROGRESS_MASTER_1,progressBarMaster.getProgress());
//        text_learning_1=sharedPreferences.getString(TEXT_LEARNING_1,tvProgressLearning.getText().toString());
//        progress_learning_1=sharedPreferences.getInt(PROGRESS_LEARNING_1,progressBarLearning.getProgress());
//        text_reviewing_1=sharedPreferences.getString(TEXT_REVIEWING_1,tvProgressReview.getText().toString());
//        progress_reviewing_1=sharedPreferences.getInt(PROGRESS_REVIEWING_1,progressBarReview.getProgress());
//        text_word_1=sharedPreferences.getString(TEXT_WORD1_1,textView_title_back.getText().toString());
//        text_def_1=sharedPreferences.getString(TEXT_DEFINITION_1,textView_definition.getText().toString());


    }


















//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        int id =item.getItemId();
//        if(id == R.id.nav_profile){
//            Toast.makeText(this, "Profile Clicked", Toast.LENGTH_SHORT).show();
//        }
//        if(id == R.id.nav_lang_selection){
////            Toast.makeText(this, "Language Selection", Toast.LENGTH_SHORT).show();
////            openDialog();
//            startActivity(new Intent(Main2Activity.this, LanguageSelectionActivity.class));
//        }
//
//
//        drawerLayout.closeDrawer(GravityCompat.START);
//        return true;
//    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
        startActivity(new Intent(Main2Activity.this, MainActivity.class));
        finish();
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
    }


}