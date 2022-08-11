package com.revature.byron_fedele_p0.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.byron_fedele_p0.member.MemberDao;
import com.revature.byron_fedele_p0.member.MemberService;
import com.revature.byron_fedele_p0.member.MemberServlet;
import com.revature.byron_fedele_p0.util.web.AuthServlet;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;

public class ServletContext {


    public final void run() {
        // Dependency injection will remain

        String webappDirLocation = new File("src/main/webapp/").getAbsolutePath();
        String additonalClasses = new File("target/classes").getAbsolutePath();

        Tomcat tomcat = new Tomcat();

        try {
            StandardContext standardContext = (StandardContext) tomcat.addWebapp("/", webappDirLocation);
            WebResourceRoot resourceRoot = new StandardRoot(standardContext);
            resourceRoot.addPreResources(new DirResourceSet(resourceRoot, "/WEB-INF/classes", additonalClasses, "/")); // everything you need prior to build the application

            standardContext.setResources(resourceRoot); // at this point the tomcat server now has acces and knowledge of classes information

            // Left for DI for later use
            MemberDao memberDao = new MemberDao();
            MemberService memberService = new MemberService(memberDao);
            ObjectMapper objectMapper = new ObjectMapper();

            // tomcat.setPort(3000); // Do not change port from 8080, leave default. This is just to show you can alter the ports. BEcause certain cloud providers sometimes change their ports. they use just 80 or 8080

            // let's estbalish our MemberServlet inside of tomcat
            // These two ADDITIONAL STEPS ARE REQUIRED OF US FOR DEPENDENCY INJECTION
            tomcat.addServlet("", "MemberServlet", new MemberServlet(memberService, objectMapper));
            standardContext.addServletMappingDecoded("/member", "MemberServlet");

            tomcat.addServlet("", "AuthServlet", new AuthServlet(memberService, objectMapper));
            standardContext.addServletMappingDecoded("/auth", "AuthServlet");

            tomcat.start(); // there is a default port on your computer for testing, 8080 this is a "developers port"
            tomcat.getServer().await();

        } catch (ServletException | LifecycleException e) {
            throw new RuntimeException(e);
        }
    }


}