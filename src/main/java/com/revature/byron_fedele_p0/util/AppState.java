package com.revature.byron_fedele_p0.util;

import com.revature.byron_fedele_p0.daos.MemberDao;
//import com.revature.byron_fedele_p0.menus.DashboardMenu;
//import com.revature.byron_fedele_p0.menus.LoginMenu;
import com.revature.byron_fedele_p0.menus.RegisterMenu;

import com.revature.byron_fedele_p0.menus.WelcomeMenu;
import com.revature.byron_fedele_p0.services.MemberService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.revature.byron_fedele_p0.daos.MemberDao;
import com.revature.byron_fedele_p0.services.MemberService;
public class AppState {

    private static boolean isRunning; // this boolean will hold wether the application is running
    private final MenuRouter menuRouter; // this will hold the menus and allow us to navigate to other menus
    private final CustomLogger customLogger = CustomLogger.getLogger(true); // this will log our actions

    public AppState() {
        isRunning = true; // automatically set to true since it should be running right away
        menuRouter = new MenuRouter(); // create the menu router

        BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in)); //this creates the BufferedReader which take an InputStreamReader as a parameter so we can get user input
        MemberDao memberDao = new MemberDao();
        MemberService memberService = new MemberService(memberDao);

        WelcomeMenu welcomeMenu = new WelcomeMenu(terminalReader, menuRouter, memberService); // , memberService will be final parameter here
        RegisterMenu registerMenu = new RegisterMenu(terminalReader, menuRouter, memberService);
        //DashboardMenu dashboardMenu = new DashboardMenu(terminalReader, menuRouter, memberService);
        //LoginMenu loginMenu = new LoginMenu(terminalReader, menuRouter, memberService);

        menuRouter.addMenu(welcomeMenu); //
        menuRouter.addMenu(registerMenu);
        //menuRouter.addMenu(dashboardMenu);
        //menuRouter.addMenu(loginMenu);

        customLogger.info("Application initialized");

    }

    public void startup() {
        while (isRunning) {
            customLogger.logMessageToFile("Routing to welcome menu...");
            menuRouter.transfer("/welcome");
        }
    }

    public static void shutdown() {

        isRunning = false;

    }
}