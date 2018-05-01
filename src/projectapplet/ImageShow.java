/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectapplet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
/**
 *
 * @author Ricko
 */
public class ImageShow extends JApplet{
    private Image image1;
    private ImageIcon image2;
        
    public ImageShow()
    {
        image1 = getImage(getDocumentBase(),"C:\\Users\\Ricko\\Pictures/nilai.png");
        image2 = new ImageIcon("C:\\Users\\Ricko\\Pictures/nilai.png");
    }
    
    public Image getImage()
    {
        return image1;
    }
    
    public ImageIcon getImageIcon()
    {
        return image2;
    }
    
}
