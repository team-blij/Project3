/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.serverapp;

/**
 *
 * @author Hans
 */
public class ServerMain {
    public static ServerGUI s = null;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        s = new ServerGUI();
        s.setVisible(true);
    }
    
    
    
}
