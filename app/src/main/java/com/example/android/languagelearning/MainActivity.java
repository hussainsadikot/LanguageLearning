package com.example.android.languagelearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.languagelearning.WordsList1.PROGRESS_MASTER_1;
import static com.example.android.languagelearning.WordsList1.SHARED_PREFS_1;
import static com.example.android.languagelearning.WordsList1.WORD_LIST_SIZE_1;
import static com.example.android.languagelearning.WordsList10.PROGRESS_MASTER_10;
import static com.example.android.languagelearning.WordsList10.SHARED_PREFS_10;
import static com.example.android.languagelearning.WordsList10.WORD_LIST_SIZE_10;
import static com.example.android.languagelearning.WordsList11.PROGRESS_MASTER_11;
import static com.example.android.languagelearning.WordsList11.SHARED_PREFS_11;
import static com.example.android.languagelearning.WordsList11.WORD_LIST_SIZE_11;
import static com.example.android.languagelearning.WordsList12.PROGRESS_MASTER_12;
import static com.example.android.languagelearning.WordsList12.SHARED_PREFS_12;
import static com.example.android.languagelearning.WordsList12.WORD_LIST_SIZE_12;
import static com.example.android.languagelearning.WordsList13.PROGRESS_MASTER_13;
import static com.example.android.languagelearning.WordsList13.SHARED_PREFS_13;
import static com.example.android.languagelearning.WordsList13.WORD_LIST_SIZE_13;
import static com.example.android.languagelearning.WordsList14.PROGRESS_MASTER_14;
import static com.example.android.languagelearning.WordsList14.SHARED_PREFS_14;
import static com.example.android.languagelearning.WordsList14.WORD_LIST_SIZE_14;
import static com.example.android.languagelearning.WordsList15.PROGRESS_MASTER_15;
import static com.example.android.languagelearning.WordsList15.SHARED_PREFS_15;
import static com.example.android.languagelearning.WordsList15.WORD_LIST_SIZE_15;
import static com.example.android.languagelearning.WordsList2.PROGRESS_MASTER_2;
import static com.example.android.languagelearning.WordsList2.SHARED_PREFS_2;
import static com.example.android.languagelearning.WordsList2.WORD_LIST_SIZE_2;
import static com.example.android.languagelearning.WordsList3.PROGRESS_MASTER_3;
import static com.example.android.languagelearning.WordsList3.SHARED_PREFS_3;
import static com.example.android.languagelearning.WordsList3.WORD_LIST_SIZE_3;
import static com.example.android.languagelearning.WordsList4.PROGRESS_MASTER_4;
import static com.example.android.languagelearning.WordsList4.SHARED_PREFS_4;
import static com.example.android.languagelearning.WordsList4.WORD_LIST_SIZE_4;
import static com.example.android.languagelearning.WordsList5.PROGRESS_MASTER_5;
import static com.example.android.languagelearning.WordsList5.SHARED_PREFS_5;
import static com.example.android.languagelearning.WordsList5.WORD_LIST_SIZE_5;
import static com.example.android.languagelearning.WordsList6.PROGRESS_MASTER_6;
import static com.example.android.languagelearning.WordsList6.SHARED_PREFS_6;
import static com.example.android.languagelearning.WordsList6.WORD_LIST_SIZE_6;
import static com.example.android.languagelearning.WordsList7.PROGRESS_MASTER_7;
import static com.example.android.languagelearning.WordsList7.SHARED_PREFS_7;
import static com.example.android.languagelearning.WordsList7.WORD_LIST_SIZE_7;
import static com.example.android.languagelearning.WordsList8.PROGRESS_MASTER_8;
import static com.example.android.languagelearning.WordsList8.SHARED_PREFS_8;
import static com.example.android.languagelearning.WordsList8.WORD_LIST_SIZE_8;
import static com.example.android.languagelearning.WordsList9.PROGRESS_MASTER_9;
import static com.example.android.languagelearning.WordsList9.SHARED_PREFS_9;
import static com.example.android.languagelearning.WordsList9.WORD_LIST_SIZE_9;

public class MainActivity extends AppCompatActivity
//        implements NavigationView.OnNavigationItemSelectedListener
{
    NavigationView navigationViewMain;
    Toolbar toolbarMain;
    RecyclerView recyclerView;
    TopicAdapter topicAdapter;
    List<Topics> topics;
    SharedPreferences prefs = null;
    final String[] lis_language = new String[]{"English", "Hindi", "Malayali", "Gujarati"};

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    // Shared Data
  public   SharedPreferences getSharedDataFromFragment1,getSharedDataFromFragment2,getSharedDataFromFragment3,getSharedDataFromFragment4,getSharedDataFromFragment5,getSharedDataFromFragment6,getSharedDataFromFragment7,getSharedDataFromFragment8,getSharedDataFromFragment9,getSharedDataFromFragment10,getSharedDataFromFragment11,getSharedDataFromFragment12,getSharedDataFromFragment13,getSharedDataFromFragment14,getSharedDataFromFragment15 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbarMain =  findViewById(R.id.toolbar);
        getSharedDataFromFragment1 = getSharedPreferences(SHARED_PREFS_1,MODE_PRIVATE);
        getSharedDataFromFragment2 = getSharedPreferences(SHARED_PREFS_2,MODE_PRIVATE);
        getSharedDataFromFragment3 = getSharedPreferences(SHARED_PREFS_3,MODE_PRIVATE);
        getSharedDataFromFragment4 = getSharedPreferences(SHARED_PREFS_4,MODE_PRIVATE);
        getSharedDataFromFragment5 = getSharedPreferences(SHARED_PREFS_5,MODE_PRIVATE);
        getSharedDataFromFragment6 = getSharedPreferences(SHARED_PREFS_6,MODE_PRIVATE);
        getSharedDataFromFragment7 = getSharedPreferences(SHARED_PREFS_7,MODE_PRIVATE);
        getSharedDataFromFragment8 = getSharedPreferences(SHARED_PREFS_8,MODE_PRIVATE);
        getSharedDataFromFragment9 = getSharedPreferences(SHARED_PREFS_9,MODE_PRIVATE);
        getSharedDataFromFragment10 = getSharedPreferences(SHARED_PREFS_10,MODE_PRIVATE);
        getSharedDataFromFragment11 = getSharedPreferences(SHARED_PREFS_11,MODE_PRIVATE);
        getSharedDataFromFragment12 = getSharedPreferences(SHARED_PREFS_12,MODE_PRIVATE);
        getSharedDataFromFragment13 = getSharedPreferences(SHARED_PREFS_13,MODE_PRIVATE);
        getSharedDataFromFragment14 = getSharedPreferences(SHARED_PREFS_14,MODE_PRIVATE);
        getSharedDataFromFragment15 = getSharedPreferences(SHARED_PREFS_15,MODE_PRIVATE);


//        setSupportActionBar(toolbarMain);
//
//        drawerLayout = findViewById(R.id.drawer_layout_main);
//        toolbarMain = findViewById(R.id.toolbar_main);
//        setSupportActionBar(toolbarMain);
//        ActionBarDrawerToggle toggle1 = new ActionBarDrawerToggle(this, drawerLayout, toolbarMain, R.string.open_drawer, R.string.close_drawer);
//        drawerLayout.addDrawerListener(toggle1);
//        toggle1.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        navigationViewMain = findViewById(R.id.nav_view_main);
//        navigationViewMain.setNavigationItemSelectedListener(this);

//        prefs = getSharedPreferences("com.mycompany.myAppName", MODE_PRIVATE);




//        Integer wordList_1_progress_master_1 =
//        int wordList_size_1 =;
//        String WordList_1_progress_master_1 = Integer.toString(wordList_1_progress_master_1);
        recyclerView = findViewById(R.id.recycler_view);
        topics = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        topics.add(new Topics(2, "Level 1", getSharedDataFromFragment1.getInt(PROGRESS_MASTER_1,0)+" of "+getSharedDataFromFragment1.getInt(WORD_LIST_SIZE_1,0)+ "  words mastered",getSharedDataFromFragment1.getInt(PROGRESS_MASTER_1,0)));
        topics.add(new Topics(3, "Level 2",getSharedDataFromFragment2.getInt(PROGRESS_MASTER_2,0)+" of "+getSharedDataFromFragment2.getInt(WORD_LIST_SIZE_2,0)+ "  words mastered",getSharedDataFromFragment2.getInt(PROGRESS_MASTER_2,0)));
        topics.add(new Topics(4, "Level 3", getSharedDataFromFragment3.getInt(PROGRESS_MASTER_3,0)+" of "+getSharedDataFromFragment3.getInt(WORD_LIST_SIZE_3,0)+ "  words mastered",getSharedDataFromFragment3.getInt(PROGRESS_MASTER_3,0)));
        topics.add(new Topics(5, "Level 4",getSharedDataFromFragment4.getInt(PROGRESS_MASTER_4,0)+" of "+getSharedDataFromFragment4.getInt(WORD_LIST_SIZE_4,0)+ "  words mastered",getSharedDataFromFragment4.getInt(PROGRESS_MASTER_4,0)));
        topics.add(new Topics(6, "Level 5", getSharedDataFromFragment5.getInt(PROGRESS_MASTER_5,0)+" of "+getSharedDataFromFragment5.getInt(WORD_LIST_SIZE_5,0)+ "  words mastered",getSharedDataFromFragment5.getInt(PROGRESS_MASTER_5,0)));
        topics.add(new Topics(7, "Level 6",getSharedDataFromFragment6.getInt(PROGRESS_MASTER_6,0)+" of "+getSharedDataFromFragment6.getInt(WORD_LIST_SIZE_6,0)+ "  words mastered",getSharedDataFromFragment6.getInt(PROGRESS_MASTER_6,0)));
        topics.add(new Topics(8, "Level 7", getSharedDataFromFragment7.getInt(PROGRESS_MASTER_7,0)+" of "+getSharedDataFromFragment7.getInt(WORD_LIST_SIZE_7,0)+ "  words mastered",getSharedDataFromFragment7.getInt(PROGRESS_MASTER_7,0)));
        topics.add(new Topics(9, "Level 8",getSharedDataFromFragment8.getInt(PROGRESS_MASTER_8,0)+" of "+getSharedDataFromFragment8.getInt(WORD_LIST_SIZE_8,0)+ "  words mastered",getSharedDataFromFragment8.getInt(PROGRESS_MASTER_8,0)));
        topics.add(new Topics(10, "Level 9",getSharedDataFromFragment9.getInt(PROGRESS_MASTER_9,0)+" of "+getSharedDataFromFragment9.getInt(WORD_LIST_SIZE_9,0)+ "  words mastered",getSharedDataFromFragment9.getInt(PROGRESS_MASTER_9,0)));
        topics.add(new Topics(11, "Level 10",getSharedDataFromFragment10.getInt(PROGRESS_MASTER_10,0)+" of "+getSharedDataFromFragment10.getInt(WORD_LIST_SIZE_10,0)+ "  words mastered",getSharedDataFromFragment10.getInt(PROGRESS_MASTER_10,0)));
        topics.add(new Topics(12, "Level 11",getSharedDataFromFragment11.getInt(PROGRESS_MASTER_11,0)+" of "+getSharedDataFromFragment11.getInt(WORD_LIST_SIZE_11,0)+ "  words mastered",getSharedDataFromFragment11.getInt(PROGRESS_MASTER_11,0)));
        topics.add(new Topics(13, "Level 12",getSharedDataFromFragment12.getInt(PROGRESS_MASTER_12,0)+" of "+getSharedDataFromFragment12.getInt(WORD_LIST_SIZE_12,0)+ "  words mastered",getSharedDataFromFragment12.getInt(PROGRESS_MASTER_12,0)));
        topics.add(new Topics(14, "Level 13",getSharedDataFromFragment13.getInt(PROGRESS_MASTER_13,0)+" of "+getSharedDataFromFragment13.getInt(WORD_LIST_SIZE_13,0)+ "  words mastered",getSharedDataFromFragment13.getInt(PROGRESS_MASTER_13,0)));
        topics.add(new Topics(15, "Level 14",getSharedDataFromFragment14.getInt(PROGRESS_MASTER_14,0)+" of "+getSharedDataFromFragment14.getInt(WORD_LIST_SIZE_14,0)+ "  words mastered",getSharedDataFromFragment14.getInt(PROGRESS_MASTER_14,0)));
        topics.add(new Topics(16, "Level 15",getSharedDataFromFragment15.getInt(PROGRESS_MASTER_15,0)+" of "+getSharedDataFromFragment15.getInt(WORD_LIST_SIZE_15,0)+ "  words mastered",getSharedDataFromFragment15.getInt(PROGRESS_MASTER_15,0)));
        topicAdapter = new TopicAdapter(this, topics);

        topicAdapter.setOnItemClickListener(new TopicAdapter.OnItemClickListener() {

            @Override
            public void onButtonClick(int position) {

                getthiscard(position);

//                Toast.makeText(MainActivity.this, "Go to List of Words"+position, Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

        recyclerView.setAdapter(topicAdapter);


    }

    private void getthiscard(int position) {
        Intent i = new Intent(MainActivity.this, Main2Activity.class);

        switch (position) {
//            case 0:
//                i.putExtra("array", "Name of Week Days");
//                startActivity(i);
//                break;
//            case 1:
//                i.putExtra("array", "Names of Months");
//                startActivity(i);
//                break;
            case 0:
                i.putExtra("array", "Words List 1");
                startActivity(i);
                break;
            case 1:
                i.putExtra("array", "Words List 2");
                startActivity(i);
                break;
            case 2:
                i.putExtra("array","Words List 3");
                startActivity(i);
                break;
            case 3:
                i.putExtra("array","Words List 4");
                startActivity(i);
                break;
            case 4:
                i.putExtra("array","Words List 5");
                startActivity(i);
                break;
            case 5:
                i.putExtra("array","Words List 6");
                startActivity(i);
                break;
            case 6:
                i.putExtra("array","Words List 7");
                startActivity(i);
                break;
            case 7:
                i.putExtra("array","Words List 8");
                startActivity(i);
                break;
            case 8:
                i.putExtra("array","Words List 9");
                startActivity(i);
                break;
            case 9:
                i.putExtra("array","Words List 10");
                startActivity(i);
                break;
            case 10:
                i.putExtra("array","Words List 11");
                startActivity(i);
                break;
            case 11:
                i.putExtra("array","Words List 12");
                startActivity(i);
                break;
            case 12:
                i.putExtra("array","Words List 13");
                startActivity(i);
                break;
            case 13:
                i.putExtra("array","Words List 14");
                startActivity(i);
                break;
            case 14:
                i.putExtra("array","Words List 15");
                startActivity(i);
                break;
                default:
                break;
    }


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.language_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.language_selection_item_in_menu:
//                openDialog();
//
//
//        }

//        return super.onOptionsItemSelected(item);
//    }

    private void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.select_language_string)
                .setItems(lis_language, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        // The 'which' argument contains the index position
                        // of the selected item
                        Toast.makeText(MainActivity.this, lis_language[which] + " is selected", Toast.LENGTH_SHORT).show();
                    }

                }).setNeutralButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.nav_profile) {
//            Toast.makeText(this, "Profile Clicked", Toast.LENGTH_SHORT).show();
//        }
//        if (id == R.id.nav_lang_selection) {
//            Toast.makeText(this, "Language Selection", Toast.LENGTH_SHORT).show();
////            openDialog();
//            startActivity(new Intent(MainActivity.this, LanguageSelectionActivity.class));
//        }
//
//        drawerLayout.closeDrawer(GravityCompat.START);
//        return true;
//
//    }

    private void showFragments(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
    }

}