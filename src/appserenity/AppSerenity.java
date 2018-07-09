/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appserenity;

import java.awt.EventQueue;
import javax.swing.JFrame;


public class AppSerenity extends JFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
                EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                Desplazamiento ex = new Desplazamiento();
                ex.setVisible(true);
            }
        });
    }

}
