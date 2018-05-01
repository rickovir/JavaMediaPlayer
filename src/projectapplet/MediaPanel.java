/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectapplet;

import java.net.URL;
import java.awt.BorderLayout;
import java.awt.Component;
import java.io.IOException;

import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.swing.JPanel;
/**
 *
 * @author Ricko
 */
public class MediaPanel extends JPanel{

    public MediaPanel(URL mediaURL) {
        setLayout(new BorderLayout());
        //use light weight components for swing compatibility
        Manager.setHint(Manager.LIGHTWEIGHT_RENDERER, true);
        try{
            //create a player to play the media specified in the URL
            Player mediaPlayer = Manager.createRealizedPlayer(mediaURL);
            
            //get the components for the video and the playback controls
            Component video = mediaPlayer.getVisualComponent();
            Component controls = mediaPlayer.getControlPanelComponent();
            if(video != null)
                add(video, BorderLayout.CENTER);
            if(controls != null)
                add(controls, BorderLayout.SOUTH);
            mediaPlayer.start();
        }
        catch(NoPlayerException noPlayerException)
        {
            System.err.println("Could not realize media player");
        }
        catch(IOException iOExceoption)
        {
            System.err.println("Error reading from the source");
        }
        catch(CannotRealizeException cannotRealizeException)
        {
            System.err.println("Error reading from the source");
        }
    }
    
}
