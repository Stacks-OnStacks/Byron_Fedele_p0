package com.revature.byron_fedele_p0.menus;

import java.io.BufferedReader;
import java.io.IOException;

import com.revature.byron_fedele_p0.util.CustomLogger; //importing Custom Logger class
import com.revature.byron_fedele_p0.util.MenuRouter; //importing MenuRouter class

public abstract class Menu { // abstract class means methods will not be concretely defined here, but its children will be required to
    protected String name; // Protected keyword only allows any class within the package or child-class to have access to this attribute
    protected String route;
    protected BufferedReader terminalReader; //Dependency Injection - as a requirement for the class to function that you want to inject at Instantiate
    protected MenuRouter menuRouter;

    public Menu(String name, String route, BufferedReader terminalReader, MenuRouter menuRouter) { // When you create a Menu object, these are the parameter
        this.name = name; // sets the parameters above equal to the Menu's attributes of the same name
        this.route = route;
        this.terminalReader = terminalReader;
        this.menuRouter = menuRouter;
    }

    public String getName() { // when this method is called on a Menu object, it will return the name
        return name;
    }

    public String getRoute() { // when this method is called on a Menu object, it will return the route
        return route;
    }

    // Adding abstract keyword requires that any class that inherits this Menu class MUST implement it
    public abstract void render() throws IOException; // each menu we use will inherit this, and will implement it in a different way (overriding)
}

