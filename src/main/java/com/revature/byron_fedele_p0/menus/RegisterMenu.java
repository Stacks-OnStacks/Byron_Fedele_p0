package com.revature.byron_fedele_p0.menus;

import com.revature.byron_fedele_p0.models.Member;
import com.revature.byron_fedele_p0.services.MemberService;
import com.revature.byron_fedele_p0.util.CustomLogger;
import com.revature.byron_fedele_p0.util.MenuRouter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

public class RegisterMenu extends Menu{
    CustomLogger customLogger = CustomLogger.getLogger(true);
    private final MemberService memberService;

    public RegisterMenu(BufferedReader terminalReader, MenuRouter menuRouter, MemberService memberService) {
        super("Register", "/register", terminalReader, menuRouter);
        this.memberService = memberService;
    }

    @Override
    public void render() throws IOException{
        System.out.print("Please enter email: \n>"); // \n is a new line character, aka return or enter
        String email = terminalReader.readLine();

        System.out.print("Please enter your password: \n>");
        String password = terminalReader.readLine();
        Member newMember = new Member(email,password);
        Member member = memberService.registerMember(newMember);
        if(member == null){
            System.out.println("Registration failed, please try again");
            menuRouter.transfer("/register");
        } else {
            System.out.println("Registration succeeded! Try logging in");
            //memberService.login(member.getEmail(), member.getPassword());
            //customLogger.info("Navigating to dashboard for " + newMember.getEmail());
            menuRouter.transfer("/welcome");
        }
    }
}