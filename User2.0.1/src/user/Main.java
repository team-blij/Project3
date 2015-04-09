/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author Nisha
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       Login newJPanel3 = new Login();
       JFrame jFrame = new JFrame();
       jFrame.setSize(400,400);
       jFrame.add(newJPanel3, BorderLayout.CENTER); 
       jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       jFrame.setVisible(true);

    }
}
