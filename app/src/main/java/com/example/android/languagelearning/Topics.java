package com.example.android.languagelearning;

class Topics {

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

    public Topics(int id, String title,String progressText) {
        this.id = id;
        this.title = title;
        this.progressText = progressText;
    }




}
