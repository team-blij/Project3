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
public class User {

    public static NewJPanel3 newJPanel2;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       newJPanel2 = new NewJPanel3();
       JFrame jFrame = new JFrame();
       jFrame.setSize(625,600);
       jFrame.add(newJPanel2, BorderLayout.CENTER); 
       jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       jFrame.setVisible(true);
       CreateChart cc = new CreateChart();
      
    }
    
}
