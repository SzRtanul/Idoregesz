/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program;

import com.sun.jna.NativeLibrary;
import java.awt.Canvas;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import uk.co.caprica.vlcj.binding.lib.LibVlc;
import uk.co.caprica.vlcj.binding.support.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

/**
 *
 * @author SzabóRoland(SZOFT_20
 */
public class Test {

private final JFrame frame;

private final EmbeddedMediaPlayerComponent mediaPlayerComponent;

public static void main(final String[] args) {
    System.out.println(RuntimeUtil.getLibVlcLibraryName());
    NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files\\VideoLAN\\VLC\\");
    System.setProperty("VLC_PLUGIN_PATH",  "C:\\Program Files\\VideoLAN\\VLC\\plugins");
    System.out.println(LibVlc.libvlc_get_version());
    
    
    
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            Test t = new Test(args);
            
        }
    });
   }

public Test(String[] args) {
    frame = new JFrame("My First Media Player");
    frame.setBounds(100, 100, 600, 400);
    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    frame.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            mediaPlayerComponent.release();
            System.exit(0);
        }
    });
    mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
    
    JPanel jp = new JPanel();
    Canvas c = new Canvas();
    //c.add(mediaPlayerComponent);
    jp.setBounds(100, 100, 600, 400);
    jp.add(mediaPlayerComponent);
    jp.setVisible(true);
    frame.add(mediaPlayerComponent);
    
    jp.setLocation(0, 0);
    frame.setVisible(true);
    mediaPlayerComponent.mediaPlayer().media().play("VW1.9TDIEngineSound.mp4");
    
}
   }
