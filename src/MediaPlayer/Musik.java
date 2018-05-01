/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaPlayer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.io.File;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.PlugInManager;
import javax.media.RealizeCompleteEvent;
import javax.media.format.AudioFormat;
import javax.swing.JFrame;

/**
 *
 * @author Ricko
 */
public class Musik  extends JFrame{
    private Player musicPlayer;
    public Musik()
    {
        musicPlayer = null;
    }
    public Musik(File musicFile)
    {
        musicPlayer = null;
        this.setMusik(musicFile);  
    }
    public void setMusik(File newMusicFile)
    {
        try{
            Format INPUT_MP3 = new AudioFormat(AudioFormat.MPEGLAYER3);
            Format INPUT_MPEG = new AudioFormat(AudioFormat.MPEG);
            Format OUTPUT = new AudioFormat(AudioFormat.LINEAR);

            PlugInManager.addPlugIn("com.sun.media.codec.audio.mp3.JavaDecoder", 
                    new Format[] {INPUT_MP3, INPUT_MPEG},
                    new Format[] {OUTPUT},
                    PlugInManager.CODEC
            );
            
            if(newMusicFile!=null){
               musicPlayer = Manager.createPlayer(new MediaLocator(new File(newMusicFile.getAbsolutePath()).toURI().toURL()));
               musicPlayer.addControllerListener( new EventHandler() );
            }
            else
            {
                throw new Exception("Musik kosong");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Player getMusic()
    {
        return this.musicPlayer;
    }
    
    public void playMusic()
    {
        if(musicPlayer !=null)
        {
            try{
                setSize( 300, 300 );
                show();
                musicPlayer.start(); 
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Musik Kosong");
        }
    }
    
    public void stopMusic()
    {
        musicPlayer.stop();
    }
    
    
    private class EventHandler implements ControllerListener {
      public void controllerUpdate( ControllerEvent e ) {
         if ( e instanceof RealizeCompleteEvent ) {
            Container c = getContentPane();
         
            // load Visual and Control components if they exist
            Component visualComponent = musicPlayer.getVisualComponent();

            if ( visualComponent != null )
               c.add( visualComponent, BorderLayout.CENTER );

            Component controlsComponent = musicPlayer.getControlPanelComponent();

            if ( controlsComponent != null )
               c.add( controlsComponent, BorderLayout.SOUTH );
            pack();
            c.doLayout();
         }
      }
   }
}
