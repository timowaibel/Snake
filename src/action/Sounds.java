package action;

import javax.sound.sampled.*;
import java.io.File;

public class Sounds {
    public static File collect;
    public static File theme;
    public static File death;
    public static File nice;
    public static File collectG;

    private final float value;

    public static Clip clip;
    FloatControl control;

    public Sounds() {
        collect = new File("src/res/collect.wav");
        theme = new File("src/res/theme.wav");
        death = new File("src/res/death.wav");
        nice = new File("src/res/nice.wav");
        collectG = new File("src/res/collectG.wav");

        value = -30f;
    }


    public void playSound(File file){
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            control.setValue(value);

            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loopSound(File file){
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));

            control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            control.setValue(value);

            clip.loop(Clip.LOOP_CONTINUOUSLY);

            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
