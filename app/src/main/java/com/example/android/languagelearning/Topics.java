package com.example.android.languagelearning;

class Topics {
    private Integer masterProgress;
    private int id;
    private String title, progressText;

    public Topics() {
        //empty constructor require
    }

    public String getProgressText() {
        return progressText;
    }

    public int getId() {
        return id;
    }

    public  String getTitle() {
        return title;
    }

    public Topics(int id, String title,String progressText,Integer progress) {
        this.id = id;
        this.title = title;
        this.progressText = progressText;
        this.masterProgress=progress;
    }

    public Integer getMasterProgress() {
        return masterProgress;
    }
}
