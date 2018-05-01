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
public class Video extends JFrame {
    private Player videoPlayer;
    public Video()
    {
        videoPlayer = null;
    }
    public Video(File videoFile)
    {
        videoPlayer = null;
        this.setVideo(videoFile);  
    }
    public void setVideo(File newMusicFile)
    {
        try{
            if(newMusicFile!=null){
               videoPlayer = Manager.createPlayer(new MediaLocator(new File(newMusicFile.getAbsolutePath()).toURI().toURL()));
               videoPlayer.addControllerListener( new EventHandler() );
            }
            else
            {
                throw new Exception("Video kosong");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Player getVideo()
    {
        return this.videoPlayer;
    }
    
    public void playVideo()
    {
        if(videoPlayer !=null)
        {
            try{
                setSize( 300, 300 );
                show();
                videoPlayer.start(); 
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Video Kosong");
        }
    }
    
    public void stopVideo()
    {
        videoPlayer.stop();
    }
    
    
    private class EventHandler implements ControllerListener {
      public void controllerUpdate( ControllerEvent e ) {
         if ( e instanceof RealizeCompleteEvent ) {
            Container c = getContentPane();
         
            // load Visual and Control components if they exist
            Component visualComponent = videoPlayer.getVisualComponent();

            if ( visualComponent != null )
               c.add( visualComponent, BorderLayout.CENTER );

            Component controlsComponent = videoPlayer.getControlPanelComponent();

            if ( controlsComponent != null )
               c.add( controlsComponent, BorderLayout.SOUTH );
            pack();
            c.doLayout();
         }
      }
   }
    
}
