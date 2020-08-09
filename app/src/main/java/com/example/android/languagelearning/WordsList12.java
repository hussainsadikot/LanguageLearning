package com.example.android.languagelearning;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class WordsList12 extends Fragment {
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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] words1= getActivity().getResources().getStringArray(R.array.XXVII_Adjectives__Array_List_12);
        String[] words_definition1= getActivity().getResources().getStringArray(R.array.Definition_Array_List_12);
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
                flipReverse();
            }
        });

//        ((Main2Activity) Objects.requireNonNull(getActivity())).updateNextWordfromGlobal();
        updateNextWordfromGlobal();
        return view;





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


    private void updateNextWordfromGlobal() {


        if (mGlobalWordNumber == words.length) {
            mGlobalWordNumber = 0;
            textView_title_front.setText(words[mGlobalWordNumber]);
            textView_title_back.setText(words[mGlobalWordNumber]);
            textView_definition.setText(words_definition[mGlobalWordNumber]);

        } else {
//            Random rand = new Random(); //instance of random class
//            int upperbound = words.length;
//            //generate random values from 0-12
//            int int_random = rand.nextInt(upperbound);
            textView_title_front.setText(words[mGlobalWordNumber]);
            textView_title_back.setText(words[mGlobalWordNumber]);
            textView_definition.setText(words_definition[mGlobalWordNumber]);
        }


        ++mGlobalWordNumber;

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