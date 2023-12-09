package my_package;

import com.sun.deploy.util.DeployUIManager;

import java.util.ArrayList;

public class DrumSound {
    private static ArrayList<String> possibleSounds; // Доступные звуки
    private String sound;

    public DrumSound() {
        setSound(possibleSounds.get(0));
    }
    public DrumSound(String sound) {
        setSound(sound);
    }

    public void setSound(String sound){
        this.sound = sound;
    }

    public String getSound(){
        return sound;
    }

    public static void addPossibleSound(String sound) {
        possibleSounds.add(sound);
    }

    public static void deletePossibleSound(String sound){
        possibleSounds.remove(sound);
    }

    public static ArrayList<String> getPossibleSounds(){
        return possibleSounds;
    }
}
