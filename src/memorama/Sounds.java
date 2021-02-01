/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package memorama;
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
 
public class Sounds{
	public static Clip getSound(String file){
		try{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src\\sonidos\\" + file));
			AudioFormat format = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip sound = (Clip)AudioSystem.getLine(info);
			sound.open(audioInputStream);
			return sound;
		}
		catch(Exception e){
                    System.out.println("Error al abrir el archivo");
                    return null;
		}
	}
	public static void playSound(Clip clip){            
            clip.stop();
            clip.setFramePosition(0);
            clip.start();            
	}        
}

