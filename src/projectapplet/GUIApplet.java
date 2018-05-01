/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectapplet;

import javax.swing.JApplet;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.applet.Applet;
import javax.swing.ImageIcon;
//import java.awt.Graphics;
//import java.awt.Image;
/**
 *
 * @author Ricko
 */
public class GUIApplet extends Applet {

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    public JPanel panel1, panel2;
    
    Gambar gambar;
    
    public void init() {
        // TODO start asynchronous download of heavy resources
        this.createGUI();
    }
    
    private void createGUI(){        
        panel1 = new JPanel();
        panel2 = new JPanel();
        
        this.setSize(500,300);
        this.setLayout(new BorderLayout());
        
        
        this.add(panel1,BorderLayout.CENTER);
        this.add(panel2,BorderLayout.CENTER);
        
        JButton button = new JButton("Test");
        add(button, BorderLayout.CENTER);
        
        panel1.setSize(getWidth(),50);
        panel1.add(button,null);
               
        panel2.setSize(getWidth(), 500);
        
        button.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent actionEvent)
                    {
                        JOptionPane.showMessageDialog(null, "Buka image");
                        gambar = new Gambar();
                        try {
                            gambar.init();
                            setSize(500,500);
                            panel1.setSize(800,50);
                            panel2.setSize(800,panel2.getHeight());
                            button.setVisible(false);
                           
                        }
                        catch(Exception ex)
                        {
                            System.out.println(ex);
                        }
                    }
        });
        JButton button1 = new JButton("Test");
        button1.setHorizontalAlignment(JButton.CENTER);
        add(button1, BorderLayout.CENTER);
        
        panel1.setSize(getWidth(),50);
        panel1.add(button1,null);
    }
    public void paint(Graphics g)
    {
        super.paint(g);
        if(gambar != null){
            g.drawImage(gambar.getGambar(), 0, panel1.getHeight(),getWidth(),panel2.getHeight(),this);
           System.out.println("gambar ada");
//           gambar.getGambar2().paintIcon(this, g, 180, 0);
        }
        
    }
    // TODO overwrite start(), stop() and destroy() methods
}
