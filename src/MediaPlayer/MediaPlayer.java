/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaPlayer;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.io.File;
import javax.imageio.ImageIO;

import javax.swing.JApplet;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
/**
 *
 * @author Ricko
 */
public class MediaPlayer extends JApplet implements ActionListener{

    private JButton btnClose, btnRun;
    private Image gambar;
    private JPanel panel1, panel2;
    private JComboBox comboMenu;
    private int selectedNow;
    private Gambar gambarApplet;
    private Musik musikApplet;
    private Video videoApplet;
    
    public void init() {        
        this.generateComponent();
        gambarApplet = new Gambar();
        musikApplet = new Musik();
        videoApplet = new Video();
        selectedNow = 0;
    }
    // untuk membuat komponen GUI
    public void generateComponent()
    {
        // init panel
        panel1 = new JPanel();
        panel2 = new JPanel();
        
        this.setSize(500,50);
        this.setLayout(new BorderLayout());
                
        this.add(panel1,BorderLayout.CENTER);
        this.add(panel2,BorderLayout.CENTER);
        
        String[] choices = { "Gambar", "Music", "Video" };
        
        comboMenu = new JComboBox(choices);
        comboMenu.addItemListener(
                (ItemEvent e) ->
                {
                    selectedNow = comboMenu.getSelectedIndex();
                }
        );
        
        btnRun = new JButton("Run");
        add(btnRun);
        btnRun.addActionListener(this);
        
        // init button
        btnClose = new JButton("Close");
        add(btnClose);
        btnClose.addActionListener(this);
        btnClose.setVisible(false);
                
        panel1.setSize(getWidth(),50);
        panel1.add(comboMenu, null);
        panel1.add(btnRun,null);
        panel1.add(btnClose,null);
        
        panel2.setSize(getWidth(), 500);
        panel2.setLocation(0, 50);
    }
    // tampilkan layar utama
    public void setVisibleTrue()
    {
        comboMenu.setVisible(true);
        btnRun.setVisible(true);
        btnClose.setVisible(false);
    }
    // sembunyikan layar utama
    public void setVisibleFalse()
    {
        comboMenu.setVisible(false);
        btnRun.setVisible(false);
    }
    // ambil file dari direktori
    private File ambilFile()
    {
        // deklarasi alat pengambil file
        JFileChooser fileChooser =  new JFileChooser();
        File file = null;
        FileFilter filter = null;
        
        if(selectedNow == 0) // jika gambar
            filter = new FileNameExtensionFilter("IMAGE FILES","PNG","JPG","GIF");
        else if(selectedNow == 1) // jika musik
        {
            filter = new FileNameExtensionFilter("MUSIC FILES","MP3","WAV");
        }
        else if(selectedNow == 2) // jika video
        {
            filter = new FileNameExtensionFilter("VIDEO FILES","MPEG","MPG");
        }
        // mengambil file
        try{ // coba
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            fileChooser.setFileFilter(filter);
            int result  = fileChooser.showOpenDialog(fileChooser);
            if(result == JFileChooser.APPROVE_OPTION){
                file = fileChooser.getSelectedFile();
                System.out.print("file is : "+fileChooser.getSelectedFile().getAbsolutePath());
            }

            return new File(fileChooser.getSelectedFile().getAbsolutePath()); // kalo berhasil ini returnnya
        }
        catch(Exception e) // pencegahan jika mengalami kegagalan
        {
            System.out.print("Error Run Gambar : "+e);
            return null;
        }
    }
    
    // action action GUI
    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton button = (JButton) ae.getSource();
        
        if(button.getLabel().equals("Run"))
        {
            switch(selectedNow){
                case 0 : // gambar
                    try{
                        gambarApplet.setGambar(this.ambilFile());
                        if(gambarApplet.getGambar() != null)
                        {
                            this.setSize(500,500);
                            panel1.setSize(500,50);
                            panel2.setSize(500, panel2.getHeight());
                            btnClose.setVisible(true);
                            this.setVisibleFalse();
                        }
                        else
                            throw new Exception("Gambar Error karena kosong");
                    }
                    catch(Exception e)
                    {
                        System.out.println("Error ambil file pada : "+e);
                    }
                    break; 
                case 1 : // musik
                    try{
                        musikApplet.setMusik(this.ambilFile());
                        musikApplet.playMusic();
                        btnClose.setVisible(true);
                        this.setVisibleFalse();
                    }
                    catch(Exception e)
                    {
                        System.out.println("Error ambil file pada : "+e);
                    }
                    break;
                case 2 :// video
                    try{
                        videoApplet.setVideo(this.ambilFile());
                        videoApplet.playVideo();
                        btnClose.setVisible(true);
                        this.setVisibleFalse();
                    }
                    catch(Exception e)
                    {
                        System.out.println("Error ambil file pada : "+e);
                    }
                    break;
                default :
                    break;
            }
        }
        else if(button.getLabel().equals("Close"))
        {
            this.setSize(500,50);                
            panel1.setSize(getWidth(),50);
            panel2.setSize(getWidth(), 500);
            panel2.setLocation(0, 50);
            
            btnClose.setVisible(false);
            this.setVisibleTrue();
            if(selectedNow == 1)
            {
                musikApplet.stopMusic();
                musikApplet.dispose();
            }
            else if(selectedNow == 2)
            {
                videoApplet.stopVideo();
                videoApplet.dispose();
            }
            this.setVisibleTrue();
        }
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        if(gambarApplet != null){
            g.drawImage(gambarApplet.getGambar(), 0, panel1.getHeight(),getWidth(),panel2.getHeight(),this);
           
        }
    
    }
}
