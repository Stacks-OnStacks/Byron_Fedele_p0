package com.revature.byron_fedele_p0.menus;

import com.revature.byron_fedele_p0.models.Member;
import com.revature.byron_fedele_p0.services.MemberService;
import com.revature.byron_fedele_p0.util.customCollections.List;
import com.revature.byron_fedele_p0.util.CustomLogger;
import com.revature.byron_fedele_p0.util.MenuRouter;

import java.io.*; //used for
// import java.time.LocalDateTime; this may or may not be used

import static com.revature.byron_fedele_p0.util.AppState.shutdown;

public class WelcomeMenu extends Menu { // this is an example of inheritance
    CustomLogger customLogger = CustomLogger.getLogger(true); // create the customLogger object
    private final MemberService memberService; // declaration, technically null

    public WelcomeMenu(BufferedReader terminalReader, MenuRouter menuRouter,MemberService memberService) { //MemberService memberService will be the final parameter for welcome menu after MemberService class is created
        super("Welcome", "/welcome", terminalReader, menuRouter); // calls the parent constructor which is menu with these and sets those attributes to these arguments.
        this.memberService = memberService; // initialize the value of the memberService
    }

    @Override // this indicates we are overriding the method we are inheriting
    public void render() throws IOException {

        String[] welcomeMessages = {"Welcome to Fedele Bank\u00a9", "1) Login", "2) Register", "3) Exit application"};

        System.out.println(welcomeMessages[0]);
        System.out.println(welcomeMessages[1]);
        System.out.println(welcomeMessages[2]);
        System.out.println(welcomeMessages[3]);

        String firstInput = terminalReader.readLine(); // it throws an IOException, this MUST be handled before compile time (checked)


        switch (firstInput) {
            case "1": // if firstInput.equals("1") then this case will execute
                System.out.println("User has selected login....");
                menuRouter.transfer("/login");
                break; // the keyword break prevents any fall-through
            case "2":
                System.out.println("User has selected register....");
                customLogger.info("Now routing user to registration page");
                menuRouter.transfer("/register");
                break;
            case "3":
                System.out.println("User is now exiting. Hope to see you soon!");
                shutdown();
                break;
            default:
                System.out.println("User has not selected valid input....");

        }
    }

}
