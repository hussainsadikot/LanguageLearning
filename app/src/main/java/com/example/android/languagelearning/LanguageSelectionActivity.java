package com.example.android.languagelearning;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class LanguageSelectionActivity extends AppCompatActivity {

    private ListView listViewLanguageSelection;
    private ArrayList<String> arrayListLanguages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        loadlocale();
        setContentView(R.layout.activity_language_selection);
        //        //change actionbar title
//       ActionBar actionBar  = getSupportActionBar();
//        actionBar.setTitle(getResources().getString(R.string.app_name));



        listViewLanguageSelection = findViewById(R.id.language_list);

        final String[] lis_language = new String[]{"English", "Hindi", "Malayalam", "Gujarati"};
        arrayListLanguages.add("English");
        arrayListLanguages.add("Hindi");
        arrayListLanguages.add("Malayalam");
        arrayListLanguages.add("Gujarati");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayListLanguages){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if (position % 2 == 1){
                    view.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                }else {

                    view.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                }

                return view;
            }
        };
        listViewLanguageSelection.setAdapter(arrayAdapter);
        listViewLanguageSelection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                switch(i){
//                    case 0: setLocale("en");
//                            recreate();
//                    case 1: setLocale("hn");
//                            recreate();
//                    case 2: setLocale("ml");
//                            recreate();
//                    case 3: setLocale("gj");
//                            recreate();
//                    default:setLocale("en");
//
//
//                }
                switch(i){
                    case 0:
                        Toast.makeText(LanguageSelectionActivity.this, "English", Toast.LENGTH_SHORT).show();
                        break;
                    case 1: Toast.makeText(LanguageSelectionActivity.this, "Hindi", Toast.LENGTH_SHORT).show();
                        break;
                    case 2: Toast.makeText(LanguageSelectionActivity.this, "Malayalam", Toast.LENGTH_SHORT).show();
                        break;
                    case 3: Toast.makeText(LanguageSelectionActivity.this, "Gujarati", Toast.LENGTH_SHORT).show();
                        break;
                    default:Toast.makeText(LanguageSelectionActivity.this, "English", Toast.LENGTH_SHORT).show();



                }
            }
        });






    }
    private void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        //save data to shared preferences
        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",language);
        editor.apply();

    }
    //load language saved inshared preferences
    public void loadlocale(){
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = sharedPreferences.getString("My_Lang","");
        setLocale(language);
    }



}