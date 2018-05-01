/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectapplet;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.io.File;
import javax.imageio.ImageIO;

import javax.swing.JApplet;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Ricko
 */
public class Gambar extends JApplet {
    private Image gambar;
    
    public Gambar(){
        gambar = null;
    }
    
    public void init(File pictureFile)
    {
        try {
            if(pictureFile != null)
            {
                gambar = ImageIO.read(pictureFile);
                System.out.print(gambar);  
            }
            else
            {
                throw new Exception("Gambar kosong");
            }
        }
        catch(Exception e)
        {
            System.out.print("Error Run Gambar : "+e);
        }
    }
    
    public Image getGambar()
    {
        return gambar;
    }
    
    private File ambilFile()
    {
        JFileChooser fileChooser =  new JFileChooser();
        File file = null;
        FileFilter filter = new FileNameExtensionFilter("IMAGE FILES","PNG","JPG","GIF");
        try{
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            fileChooser.setFileFilter(filter);
            int result  = fileChooser.showOpenDialog(fileChooser);
            if(result == JFileChooser.APPROVE_OPTION){
                file = fileChooser.getSelectedFile();
                System.out.print("file is : "+fileChooser.getSelectedFile().getAbsolutePath());
            }

            return new File(fileChooser.getSelectedFile().getAbsolutePath());
        }
        catch(Exception e)
        {
            System.out.print("Error Run Gambar : "+e);
            return null;
        }
    }
    
    public void paint(Graphics g){
        super.paint(g);
        if(gambar != null){
            g.drawImage(gambar, 0, 0, this);
        }
    
    }
}
