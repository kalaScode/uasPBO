/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dell
 */
public class LoggedInUser {
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        LoggedInUser.user = user;
    }
}



