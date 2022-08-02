package com.revature.byron_fedele_p0.util;

import com.revature.byron_fedele_p0.menus.Menu;

import java.io.IOException;

public class MenuRouter {//this class will hold all the menus that my application will utilize

    private final Menu[] menus; // this array will hold the different menus

    //IMPORTANT- IntellijA says 3 related problems here. My theory is that MenuRouter is protected in Menu class so it cannot see MenuRouter since it is in a different package.
    public MenuRouter(){ // when a menuRouter object is created it will automatically create an array of length 10 to hold the different menus. All 10 elements in this array are Null by default
        menus = new Menu[10];
    }
    // the void keyword allows us to create a method that is not expected to return something
    public void addMenu(Menu addedMenu){// this method takes in a Menu object called addedMenu. It then reassigns it to the first element of the array it finds that is Null. Therefore, that element is no longer Null but rather the addedMenu.
        for(int i = 0; i < menus.length; i++){
            if(menus[i] == null){ //if the element is null
                menus[i] = addedMenu;// reassign that null element to be the addedMenu
                break;// this break statement stops the loop
            } /*else if(menus[i] != null){
                System.out.println("Next index please.");
            }*/ // This else if statement is not needed but just to demonstrate how it finds an element of the array that is NOT Null, and moves to the next element.
        }
    }

    public void transfer(String route){
        for(Menu menu: menus){
            if(menu == null) break;
            if(menu.getRoute().equals(route)){
                try {
                    menu.render();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

}