package com.revature.byron_fedele_p0.menus;
import com.revature.byron_fedele_p0.models.Member;
import com.revature.byron_fedele_p0.services.MemberService;
import com.revature.byron_fedele_p0.util.CustomLogger;
import com.revature.byron_fedele_p0.util.MenuRouter;
import com.revature.byron_fedele_p0.util.ConnectionFactory;
import com.revature.byron_fedele_p0.util.exceptions.InvalidUserInputException;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.revature.byron_fedele_p0.util.AppState.shutdown;

public class DashboardMenu extends Menu{
    CustomLogger customLogger = CustomLogger.getLogger(true);
    private final MemberService memberService;

    public DashboardMenu(BufferedReader terminalReader, MenuRouter menuRouter, MemberService memberService) {
        super("Dashboard", "/dashboard", terminalReader, menuRouter);
        this.memberService = memberService;
    }

    @Override
    public void render() throws IOException {

        System.out.println(memberService.getSessionMember().getEmail() +  " Here is your dashboard!\n 1) WITHDRAWAL \n 2) DEPOSIT \n 3) VIEW BALANCE \n 4) LOGOUT" );

        String userInput = terminalReader.readLine();

        switch (userInput){
            case "1":
                System.out.println("How much do you want to WITHDRAW?");
                double withdrawAmount = 0;
                try {
                    withdrawAmount = Double.parseDouble(terminalReader.readLine());
                } catch (NumberFormatException e) {
                    customLogger.warn("Need to enter a number, try again");
                }
                if(withdrawAmount>0) {
                    try (Connection conn = ConnectionFactory.getConnectionFactory().getConnection()) {
                        String sql = "select balance from members where email = ?";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, memberService.getSessionMember().getEmail());
                        ResultSet rs = ps.executeQuery();
                        double newBalance = 0;
                        if (rs.next()) {
                            newBalance = rs.getDouble("balance") - withdrawAmount;
                        }
                        if (newBalance >= 0) {// CHECK THIS --------!!!!!!!!!!
                            System.out.println("Your previous balance was $" + rs.getInt("balance"));
                            System.out.println("You withdrew $" + Double.toString(withdrawAmount));
                            sql = "update members set balance=? where email = ?";
                            ps = conn.prepareStatement(sql);
                            ps.setDouble(1, newBalance);
                            ps.setString(2, memberService.getSessionMember().getEmail());
                            ps.executeUpdate();
                            System.out.println("Your new balance is $" + Double.toString(newBalance));
                            menuRouter.transfer("/dashboard");
                        } else {
                            System.out.println("Your withdraw amount would result in a negative balance");
                            menuRouter.transfer("/dashboard");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    System.out.println("Your withdraw amount has to be positive");
                    menuRouter.transfer("/dashboard");
                }
                break;
            case "2":
                System.out.println("How much do you want to DEPOSIT?");
                double depositAmount = 0;
                try {
                    depositAmount = Double.parseDouble(terminalReader.readLine());
                } catch (NumberFormatException e) {
                    customLogger.warn("Need to enter an Integer, try again");
                }
                if(depositAmount>0){ // CHECK THIS --------!!!!!!!!!!
                    try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){
                        String sql = "select balance from members where email = ?";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, memberService.getSessionMember().getEmail());
                        ResultSet rs = ps.executeQuery();
                        double newBalance=0;
                        if(rs.next()) {
                            newBalance=rs.getDouble("balance")+depositAmount;
                        }
                            System.out.println("Your previous balance was $" + rs.getInt("balance"));
                            System.out.println("You deposited $"+Double.toString(depositAmount));
                            sql="update members set balance=? where email = ?";
                            ps = conn.prepareStatement(sql);
                            ps.setDouble(1, newBalance);
                            ps.setString(2, memberService.getSessionMember().getEmail());
                            ps.executeUpdate();
                            System.out.println("Your new balance is $" +Double.toString(newBalance));
                            menuRouter.transfer("/dashboard");
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                else{
                    System.out.println("Your deposit amount has to be greater than zero");
                    menuRouter.transfer("/dashboard");
                }
                break;
            case "3":
                System.out.println("User has selected VIEW BALANCE");
                try(Connection conn = ConnectionFactory.getConnectionFactory().getConnection()){
                    String sql = "select balance from members where email = ?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, memberService.getSessionMember().getEmail());
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()) {
                        System.out.println("Your balance is $" + rs.getDouble("balance"));
                        menuRouter.transfer("/dashboard");
                    }

                } catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case "4":
                System.out.println("User has selected to logout. Hope to see you soon");
                memberService.logout();
                menuRouter.transfer("/welcome");
                break;
            default:
                System.out.println("Invalid selection please try again");
                menuRouter.transfer("/dashboard");
        }

    }
}