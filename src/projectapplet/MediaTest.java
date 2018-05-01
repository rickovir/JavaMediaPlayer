/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectapplet;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
/**
 *
 * @author Ricko
 */
public class MediaTest {
    
    public static void main(String args[])
    {
        // create file chooser
        JFileChooser fileChooser = new JFileChooser();
        // show opern file dialog
        int result = fileChooser.showOpenDialog(null);
        
        if(result == JFileChooser.APPROVE_OPTION) // user chooser a file
        {
            URL mediaURL = null;
             try{
                 mediaURL = fileChooser.getSelectedFile().toURL();
                 
             }
             catch(MalformedURLException malformedURLException)
             {
                 System.err.println("Could not create URL for the file");
             }// end catch
        
            if(mediaURL != null)
            {
                JFrame mediaTest = new JFrame("Media Tester");
                mediaTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                MediaPanel mediaPanel = new MediaPanel(mediaURL);
                mediaTest.add(mediaTest);
                mediaTest.setSize(300,300);
                mediaTest.setVisible(true);
            }
        }
        
    }
    
}
