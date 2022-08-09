package com.revature.byron_fedele_p0.menus;

import com.revature.byron_fedele_p0.models.Member;
import com.revature.byron_fedele_p0.services.MemberService;
import com.revature.byron_fedele_p0.util.CustomLogger;
import com.revature.byron_fedele_p0.util.MenuRouter;
import com.revature.byron_fedele_p0.util.exceptions.UnauthorizedException;

import java.io.BufferedReader;
import java.io.IOException;

public class LoginMenu extends Menu{

    private final MemberService memberService;
    CustomLogger customLogger = CustomLogger.getLogger(true);

    public LoginMenu(BufferedReader terminalReader, MenuRouter menuRouter, MemberService memberService) {
        super("Login", "/login", terminalReader, menuRouter);
        this.memberService = memberService;
    }

    @Override
    public void render() throws IOException {
        System.out.print("Please enter email: \n>"); // \n is a new line character, aka return or enter
        String email = terminalReader.readLine();

        System.out.print("Please enter your password: \n>");
        String password = terminalReader.readLine();

        try {
            Member member = memberService.login(email, password);
            if (member == null) {
                throw new UnauthorizedException("Invalid information provided. Please try again");

            }
            menuRouter.transfer("/dashboard");
        } catch (UnauthorizedException e){
            customLogger.warn(e.getMessage());
            System.out.println("No member with those credentials. Try logging in again or creating a new account");
        }
    }
}