/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaPlayer;
// import library dari luar source
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

//import javax.swing.JApplet;


/**
 *
 * @author Ricko
 */

public class Gambar{
    // deklarasi attribut
    private Image gambar;
    
    public Gambar()
    {
        gambar = null;
    }
    
    public Gambar(File pictureFile){
        gambar = null;
        this.setGambar(pictureFile);
    }
    // input File Object
    public void setGambar(File newPictureFile)
    {
        try {
            if(newPictureFile != null)
            {
                gambar = ImageIO.read(newPictureFile);
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
    // return Image Object
    public Image getGambar()
    {
        return gambar;
    }
    
}
