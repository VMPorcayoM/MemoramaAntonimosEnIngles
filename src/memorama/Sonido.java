/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package memorama;
import javax.sound.sampled.Clip;

public class Sonido extends Thread{
    private String audio;

    public Sonido(String audio) {
        this.audio=audio;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
    
    @Override
    public void run(){
        Clip sound = Sounds.getSound(audio+".wav");
	Sounds.playSound(sound);
    }

}