package com.example.android.languagelearning;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class WordsList10 extends Fragment {
    private Button iknow, idont, reveal;
    private TextView textView_title_front,textView_title_back, textView_definition, tv_tag, tvProgressMaster, tvProgressLearning, tvProgressReview;
    private ProgressBar progressBarReview, progressBarMaster, progressBarLearning;
    //    private GlobalListOfWordAndDefinition currentGlobalWord;
    private String global= "Global";
    private String master= "Master";
    private String review= "Review";
    private String learning= "Learning";


    private int reviewsize = 1;
    private int learningsize = 1;
    private int mGlobalWordNumber = 0;

    private String[] words = {"Month 1", "Month 2", "Month 3", "Month 4", "Month 5", "Month 6", "Month 7", "Month 8", "Month 9", "Month 10", "Month 11", "Month 12"};
    private String[] words_definition = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private Map<String, String> map = new HashMap<>();
    private String mTag= "Global";
    CardView cardBack,cardFront;
    private boolean flipped;
    public static final String SHARED_PREFS_10 = "sharedPrefs_10";
    public static final String TEXT_MASTER_10 = "text_master_10";
    public static final String PROGRESS_MASTER_10 = "progress_master_10";
    public static final String TEXT_LEARNING_10 = "text_learning_10";
    public static final String PROGRESS_LEARNING_10 = "progress_learning_10";
    public static final String TEXT_REVIEWING_10 = "text_reviewing_10";
    public static final String PROGRESS_REVIEWING_10 = "progress_reviewing_10";
    public static final String TEXT_WORD1_10 = "text_word_10";
    public static final String TEXT_DEFINITION_10 = "text_def_10";
    public static final String WORD_LIST_SIZE_10 = "word_list_size_10";
    public static final String WORD_INDEX_10 = "word_index_10";
    // shared variable default loading
    private   String text_master_10="";
    public Integer progress_master_10=0;
    public  String text_learning_10="";
    public  Integer progress_learning_10=0;
    public String text_reviewing_10="";
    public  Integer progress_reviewing_10=0;
    public  String text_word_10="";
    public  String text_def_10="";
    public int word_list_size_10 =0;
    public int word_index_10;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] words1= getActivity().getResources().getStringArray(R.array.XVIII_Motion_Verbs__XIX_Occupational_Verbs__XX_Culinari_Verbs__Array_List_10);
        String[] words_definition1= getActivity().getResources().getStringArray(R.array.Definition_Array_List_10);
        words = Arrays.copyOf(words1, words1.length);
        words_definition = Arrays.copyOf(words_definition1, words_definition1.length);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_word_display, container, false);
        tv_tag = view.findViewById(R.id.tv_word_tag);
        for (int i = 0; i <= words.length - 1; i++) {
            map.put(words[i], mTag);
        }
        // Bind Card layout

        cardFront = view.findViewById(R.id.front_card);
        cardBack  = view.findViewById(R.id.back_card );


        // Master Progress Initial Settings

        progressBarMaster = view.findViewById(R.id.progressbar_master);
        tvProgressMaster = view.findViewById(R.id.tv_progress_master);
        tvProgressMaster.setText("You have Mastered 0 out of "+words.length);
        progressBarMaster.setProgress(0);
        progressBarMaster.setMax(words1.length);

        // Learning Progress Initial Settings
        progressBarLearning = view.findViewById(R.id.progressbar_learning);
        progressBarLearning.setProgress(0);
        progressBarLearning.setMax(words1.length);

        tvProgressLearning = view.findViewById(R.id.learning_progress_tv);
        tvProgressLearning.setText("You are learning 0 out of "+words.length);

        // Review Progress Initial Settings
        progressBarReview = view.findViewById(R.id.progressbar_review);
        progressBarReview.setMax(words1.length);
        tvProgressReview = view.findViewById(R.id.review_progress_tv);
        tvProgressReview.setText("You are reviewing 0 out of "+words.length);


        textView_title_front = view.findViewById(R.id.tv_word_title_front);
        textView_title_back = view.findViewById(R.id.tv_word_title_back);
        textView_definition = view.findViewById(R.id.tv_definition_word);
        reveal = view.findViewById(R.id.reveal_button_front);
        idont = view.findViewById(R.id.i__dont_know_button);
        iknow = view.findViewById(R.id.i_know_button);


        reveal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flip();
            }
        });
        iknow.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                String iknowword = textView_title_back.getText().toString();
//                findIndex(globalListOfWordAndDefinition.globalWord,iknowword);
                ChangeTag(iknowword);
//                revealNextWord();

//                Toast.makeText(MainActivity.this, "You know this word Fantastic", Toast.LENGTH_SHORT).show();
                updateNextWordfromGlobal();
                flipReverse();
                saveData();
            }
        });
        idont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idontknowword = textView_title_back.getText().toString();
                ChangeTagForIDont(idontknowword);
//                Toast.makeText(MainActivity.this, "You don't know this word, cool", Toast.LENGTH_SHORT).show();
//                revealNextWord();
                updateNextWordfromGlobal();
                saveData();
            }
        });

//        ((Main2Activity) Objects.requireNonNull(getActivity())).updateNextWordfromGlobal();
        updateNextWordfromGlobal();
        return view;




    }
    private void updateNextWordfromGlobal() {
//        if(mGlobalWordNumber==word_index_1){
//            textView_title_front.setText(words[mGlobalWordNumber]);
//            textView_title_back.setText(words[mGlobalWordNumber]);
//            textView_definition.setText(words_definition[mGlobalWordNumber]);
//            mGlobalWordNumber++;
//            return;
//        }
//        if(mGlobalWordNumber!=word_index_1){
//            mGlobalWordNumber=word_index_1;
//            mGlobalWordNumber=mGlobalWordNumber+1;
//            if(mGlobalWordNumber == words.length){
//                mGlobalWordNumber = 0;
//                word_index_1=0;
//            textView_title_front.setText(words[mGlobalWordNumber]);
//            textView_title_back.setText(words[mGlobalWordNumber]);
//            textView_definition.setText(words_definition[mGlobalWordNumber]);
//            word_index_1 =mGlobalWordNumber;
//            mGlobalWordNumber=mGlobalWordNumber+1;
//            word_index_1=word_index_1+1 ;}
//        }
//        else
        if (mGlobalWordNumber == words.length) {
            mGlobalWordNumber = 0;
            textView_title_front.setText(words[mGlobalWordNumber]);
            textView_title_back.setText(words[mGlobalWordNumber]);
            textView_definition.setText(words_definition[mGlobalWordNumber]);
            mGlobalWordNumber=mGlobalWordNumber+1;
//            word_index_1=word_index_1+1 ;
        } else {
//            Random rand = new Random(); //instance of random class
//            int upperbound = words.length;
//            //generate random values from 0-12
//            int int_random = rand.nextInt(upperbound);


            textView_title_front.setText(words[mGlobalWordNumber]);
            textView_title_back.setText(words[mGlobalWordNumber]);
            textView_definition.setText(words_definition[mGlobalWordNumber]);
            mGlobalWordNumber=mGlobalWordNumber+1;
//            word_index_1=word_index_1+1 ;
        }





    }
    private void saveData() {
        SharedPreferences sharedPreferences= Objects.requireNonNull(this.getActivity()).getSharedPreferences(SHARED_PREFS_10, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=  sharedPreferences.edit();
        editor.putString(TEXT_MASTER_10,tvProgressMaster.getText().toString());
        editor.putString(TEXT_REVIEWING_10,tvProgressReview.getText().toString());
        editor.putString(TEXT_LEARNING_10,tvProgressLearning.getText().toString());
        String text_word_to_store= textView_title_back.getText().toString();
        String text_word_def_to_store= textView_definition.getText().toString();
        editor.putString(TEXT_WORD1_10, text_word_to_store);
        editor.putString(TEXT_DEFINITION_10,text_word_def_to_store);
        editor.putInt(PROGRESS_MASTER_10,progressBarMaster.getProgress());
        editor.putInt(PROGRESS_LEARNING_10,progressBarLearning.getProgress());
        editor.putInt(PROGRESS_REVIEWING_10,progressBarReview.getProgress());
        editor.putInt(WORD_LIST_SIZE_10,words.length);
        editor.putInt(WORD_INDEX_10,mGlobalWordNumber);

        editor.apply();
//        Toast.makeText(getActivity(), "Data Saved", Toast.LENGTH_SHORT).show();
//        loadData();
    }
    private void loadData() {
        SharedPreferences sharedPreferences= Objects.requireNonNull(getActivity()).getSharedPreferences(SHARED_PREFS_10, Context.MODE_PRIVATE);
        word_index_10=sharedPreferences.getInt(WORD_INDEX_10,mGlobalWordNumber);
        text_master_10=sharedPreferences.getString(TEXT_MASTER_10,tvProgressMaster.getText().toString());
        progress_master_10=sharedPreferences.getInt(PROGRESS_MASTER_10,progressBarMaster.getProgress());
        text_learning_10=sharedPreferences.getString(TEXT_LEARNING_10,tvProgressLearning.getText().toString());
        progress_learning_10=sharedPreferences.getInt(PROGRESS_LEARNING_10,progressBarLearning.getProgress());
        text_reviewing_10=sharedPreferences.getString(TEXT_REVIEWING_10,tvProgressReview.getText().toString());
        progress_reviewing_10=sharedPreferences.getInt(PROGRESS_REVIEWING_10,progressBarReview.getProgress());

        text_word_10=sharedPreferences.getString(TEXT_WORD1_10,words[0]);




        text_def_10=sharedPreferences.getString(TEXT_DEFINITION_10,words_definition[0]);
        word_list_size_10= sharedPreferences.getInt(WORD_LIST_SIZE_10,words.length);

//        Toast.makeText(getActivity(), "data loaded", Toast.LENGTH_SHORT).show();


    }
    public void updateViewsBySharedPref(){
//        int updateIndex = 0;
//        for (int i =0; i<words.length; i++){
//            if(text_word_1.equals(words[i])){
//                updateIndex = i;
////                return;
//            }
//        }
        tvProgressMaster.setText(text_master_10);
        mGlobalWordNumber=word_index_10;
        tvProgressReview.setText(text_reviewing_10);
        tvProgressLearning.setText(text_learning_10);
        for(int i =0 ; i<words.length; i++){
            if(words[i].equals(text_word_10)){
                word_index_10=i;
            }
        }
        textView_title_front.setText(text_word_10);
        textView_title_back.setText(text_word_10);

        textView_definition.setText(text_def_10);
        progressBarMaster.setProgress((progress_master_10));
        progressBarReview.setProgress((progress_reviewing_10) );
        progressBarLearning.setProgress((progress_learning_10));
//        Toast.makeText(getActivity(), "data updated", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
        updateViewsBySharedPref();
    }


    //    private void revealNextWord() {
//        textView_definition.setVisibility(View.GONE);
//        idont.setVisibility(View.GONE);
//        iknow.setVisibility(View.GONE);
//
//        reveal.setVisibility(View.VISIBLE);
//    }
private void flipReverse() {

    cardFront.setVisibility(View.GONE);
    cardBack.setVisibility(View.GONE);
    if (!(this.flipped)) return;
    this.flipped = false;

    AnimatorSet animationOut = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(), R.animator.card_flip_in_animator);
    AnimatorSet animationIn  = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(), R.animator.card_flip_out_animator);
//        cardBack.setVisibility(View.VISIBLE);
    animationOut.setTarget(this.cardFront);
    animationIn.setTarget(this.cardBack);
    animationOut.start();
    animationIn.start();


    animationIn.addListener(new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animator) {

            cardFront.setVisibility(View.GONE);
            cardBack.setVisibility(View.GONE);
        }

        @Override
        public void onAnimationEnd(Animator animator) {

            cardFront.setVisibility(View.VISIBLE);
            cardBack.setVisibility(View.GONE);
        }

        @Override
        public void onAnimationCancel(Animator animator) {

        }

        @Override
        public void onAnimationRepeat(Animator animator) {

        }
    });


}

    private void flip() {
        if (this.flipped) return;
        this.flipped = true;

        AnimatorSet animationOut = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(), R.animator.card_flip_out_animator);
        AnimatorSet animationIn  = (AnimatorSet) AnimatorInflater.loadAnimator(this.getContext(), R.animator.card_flip_in_animator);
        cardBack.setVisibility(View.VISIBLE);
        animationOut.setTarget(this.cardFront);
        animationIn.setTarget(this.cardBack);


        animationOut.start();
        animationIn.start();


//        cardFront.setVisibility(View.GONE);

    }


    


    private void ChangeTagForIDont(String idontknowword) {
        if ((TextUtils.equals(map.get(idontknowword), global))) {
            map.put(idontknowword, "Learning");
            if (progressBarLearning.getProgress() <= progressBarLearning.getMax()) {
                int currentLearningProgress1 = progressBarLearning.getProgress() + 1;
                progressBarLearning.setProgress(currentLearningProgress1);
                String curruntprogressnumberforLearningglobalToLearning = Integer.toString(progressBarLearning.getProgress());
                String globalToLearning = " You are learning " + curruntprogressnumberforLearningglobalToLearning + " out of "+words.length;
                tvProgressLearning.setText(globalToLearning);

            }
//            globalListOfWordAndDefinition.setTag("Master",indexOfGlobalTag);
//            Toast.makeText(getActivity(), "Tag changed from Global to Learning", Toast.LENGTH_SHORT).show();

            tv_tag.setText(map.get(idontknowword));
        } else if (TextUtils.equals(map.get(idontknowword), "Master")) {
            map.put(idontknowword, "Learning");
            //decrese progress from master
            if (progressBarMaster.getProgress() <= progressBarMaster.getMax()) {
                int currentMasterProgress1 = progressBarMaster.getProgress() - 1;
                progressBarMaster.setProgress(currentMasterProgress1);
                String curruntprogressnumberforMasterMasterToLearning = Integer.toString(progressBarMaster.getProgress());
                String MasterToLearning = " You have mastered " + curruntprogressnumberforMasterMasterToLearning + " out of "+words.length;
                tvProgressMaster.setText(MasterToLearning);
            }
            if (progressBarLearning.getProgress() <= progressBarLearning.getMax()) {
                int currentLearningProgress2 = progressBarLearning.getProgress() + 1;
                progressBarLearning.setProgress(currentLearningProgress2);
                String curruntprogressnumberforLearningMasterToLearning2 = Integer.toString(progressBarLearning.getProgress());
                String MasterToLearning2 = " You are learning " + curruntprogressnumberforLearningMasterToLearning2 + " out of "+words.length;
                tvProgressLearning.setText(MasterToLearning2);

            }
//            globalListOfWordAndDefinition.setTag("Master",indexOfGlobalTag);
//            Toast.makeText(getActivity(), "Tag changed from Master to Learning", Toast.LENGTH_SHORT).show();

            tv_tag.setText(map.get(idontknowword));
        } else if (TextUtils.equals(map.get(idontknowword), "Review")) {
            map.put(idontknowword, "Learning");
//            globalListOfWordAndDefinition.setTag("Master",indexOfGlobalTag);
//            Toast.makeText(getActivity(), "Tag changed from Reviewing to Learning", Toast.LENGTH_SHORT).show();
            if (progressBarReview.getProgress() <= progressBarReview.getMax()) {
                int currentReviewProgress1 = progressBarReview.getProgress() - 1;
                progressBarReview.setProgress(currentReviewProgress1);
                String curruntprogressnumberforReviewreviewToLearning = Integer.toString(progressBarReview.getProgress());
                String reviewToLearning = " You are reviewing " + curruntprogressnumberforReviewreviewToLearning + " out of "+words.length;
                tvProgressReview.setText(reviewToLearning);

            }
            if (progressBarLearning.getProgress() <= progressBarLearning.getMax()) {
                int currentLearningProgress3 = progressBarLearning.getProgress() + 1;
                progressBarLearning.setProgress(currentLearningProgress3);
                String curruntprogressnumberforLearningreviewToLearning2 = Integer.toString(progressBarLearning.getProgress());
                String reviewToLearning2 = " You are learning " + curruntprogressnumberforLearningreviewToLearning2 + " out of "+words.length;
                tvProgressLearning.setText(reviewToLearning2);
            }

            tv_tag.setText(map.get(idontknowword));
        }

    }

    private void ChangeTag(String iknowWord) {
        if (TextUtils.equals(map.get(iknowWord), "Global")) {
            map.put(iknowWord, "Master");
//            globalListOfWordAndDefinition.setTag("Master",indexOfGlobalTag);
//            Toast.makeText(getActivity(), "Tag changed to Master", Toast.LENGTH_SHORT).show();
            if (progressBarMaster.getProgress() <= progressBarMaster.getMax()) {
                int currentMasterProgress2 = progressBarMaster.getProgress() + 1;
                progressBarMaster.setProgress(currentMasterProgress2);
                String curruntprogressnumberforMasterMasterProgress1 = Integer.toString(progressBarMaster.getProgress());
                String MasterProgress1 = " You have mastered " + curruntprogressnumberforMasterMasterProgress1 + " out of "+words.length;
                tvProgressMaster.setText(MasterProgress1);

            }

            tv_tag.setText(map.get(iknowWord));

        } else if (TextUtils.equals(map.get(iknowWord), "Review")) {


            map.put(iknowWord, "Master");
//            globalListOfWordAndDefinition.setTag("Master",indexOfGlobalTag);
            if (progressBarMaster.getProgress() <= progressBarMaster.getMax()) {
                int currentMasterProgress3 = progressBarMaster.getProgress() + 1;
                progressBarMaster.setProgress(currentMasterProgress3);
                String curruntprogressnumberforMasterReviewToMaster = Integer.toString(progressBarMaster.getProgress());
                String ReviewToMaster = " You have mastered " + curruntprogressnumberforMasterReviewToMaster + " out of "+words.length;
                tvProgressMaster.setText(ReviewToMaster);

            }
            if (progressBarReview.getProgress() <= progressBarReview.getMax()) {
                int currentReviewProgress2 = progressBarReview.getProgress() - 1;
                progressBarReview.setProgress(currentReviewProgress2);
                String curruntprogressnumberforReviewReviewToMaster2 = Integer.toString(progressBarReview.getProgress());
                String ReviewToMaster2 = " You are reviewing " + curruntprogressnumberforReviewReviewToMaster2 + " out of "+words.length;
                tvProgressReview.setText(ReviewToMaster2);

            }


//            Toast.makeText(getActivity(), "Tag changed from Review to Master", Toast.LENGTH_SHORT).show();


            tv_tag.setText(map.get(iknowWord));


        } else if (TextUtils.equals(map.get(iknowWord), "Learning")) {
            map.put(iknowWord, "Review");
//            globalListOfWordAndDefinition.setTag("Master",indexOfGlobalTag);
//            Toast.makeText(getActivity(), "Tag changed From Learning to Reviewing", Toast.LENGTH_SHORT).show();
            if (progressBarReview.getProgress() <= progressBarReview.getMax()) {
                int currentReviewProgress3 = progressBarReview.getProgress() + 1;
                progressBarReview.setProgress(currentReviewProgress3);
                String curruntprogressnumberforReviewLearningToReview = Integer.toString(progressBarReview.getProgress());
                String LearningToReview = " You are reviewing " + curruntprogressnumberforReviewLearningToReview + " out of "+words.length;
                tvProgressReview.setText(LearningToReview);

            }
            if (progressBarLearning.getProgress() <= progressBarLearning.getMax()) {
                int currentLearningProgress4 = progressBarLearning.getProgress() - 1;
                progressBarLearning.setProgress(currentLearningProgress4);
                String curruntprogressnumberforLearningLearningToReview2 = Integer.toString(progressBarLearning.getProgress());
                String LearningToReview2 = " You are learning " + curruntprogressnumberforLearningLearningToReview2 + " out of "+words.length;
                tvProgressLearning.setText(LearningToReview2);
            }


            tv_tag.setText(map.get(iknowWord));
        }
    }




}