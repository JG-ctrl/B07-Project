package com.example.b07project;

public class ActivityLoginPagePresenter {

    ActivityLoginPageView view;
    ActivityLoginPageModel model;

    public ActivityLoginPagePresenter(ActivityLoginPageView view, ActivityLoginPageModel model) {
        this.view = view;
        this.model = model;
    }

    public void checkDBShopper(String username, String password) {
        model.queryDBShopper(username, password, this);
    }

    public void loginShopper(Boolean valid) {
        if (valid) { // if username is valid AND password matches
            view.openActivity(ActivityShopperView1.class); // proceed to next screen
        } else {
            view.writeInvalid(); // otherwise give message that something is wrong
        }
    }

    public void checkDBOwner(String username, String password) {
        model.queryDBOwner(username, password, this);
    }

    public void loginOwner(Boolean valid) {
        if (valid) { // if username is valid AND password matches
            view.openActivity(ActivityOwnerView1.class); // proceed to next screen
        } else {
            view.writeInvalid(); // otherwise give message that something is wrong
        }
    }
}
