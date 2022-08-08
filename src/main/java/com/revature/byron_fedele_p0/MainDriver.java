package com.revature.byron_fedele_p0;

/* Fedele Bank is an application that simulates a simple baking application where you can sign up, log in,
and make basic withdrawal and deposit transactions
*/

/*
any imports that are commented are to be implemented later
import com.revature.byron_fedele_p0.menus.DashboardMenu;
import com.revature.byron_fedele_p0.menus.RegisterMenu;
import com.revature.byron_fedele_p0.models.Member;
*/

import com.revature.byron_fedele_p0.util.MenuRouter;
import com.revature.byron_fedele_p0.menus.WelcomeMenu;
import com.revature.byron_fedele_p0.util.AppState;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Date;


public class MainDriver {


    public static void main(String[] args) {

        AppState appState = new AppState();
        appState.startup();
    }



}