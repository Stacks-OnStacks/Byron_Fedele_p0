package com.revature.byron_fedele_p0.util.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.byron_fedele_p0.member.Member;
import com.revature.byron_fedele_p0.member.MemberService;
import com.revature.byron_fedele_p0.util.web.DTO.LoginCreds;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    private final MemberService memberService;
    private final ObjectMapper objectMapper;

    // Dependency Injection
    public AuthServlet(MemberService memberService, ObjectMapper objectMapper){
        this.memberService = memberService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginCreds loginCreds = objectMapper.readValue(req.getInputStream(), LoginCreds.class); // this provides the body from the request as a JSON, leveraging Reflections

        Member member = memberService.login(loginCreds.getEmail(), loginCreds.getPassword());

        HttpSession httpSession = req.getSession(true);
        httpSession.setAttribute("authMember", member);
        resp.getWriter().write("Welcome back to Fedele Bank " + member.getEmail());

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate(); // this is how you kill/delete/logout of the session/cookie
        resp.getWriter().write("Member has successfully logged out");
    }
}