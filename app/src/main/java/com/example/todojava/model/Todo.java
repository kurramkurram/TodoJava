package com.example.todojava.model;

public class Todo {

    private int mUid;
    private String mTask;
    private boolean mIsCompleted;

    public Todo(int uid, String task, boolean isCompleted) {
        this.mUid = uid;
        this.mTask = task;
        this.mIsCompleted = isCompleted;
    }

    public int getUid() {
        return this.mUid;
    }

    public String getTask() {
        return this.mTask;
    }

    public Boolean isCompleted() {
        return this.mIsCompleted;
    }

    public void setCompleted(boolean isComplete) {
        this.mIsCompleted = isComplete;
    }
}
