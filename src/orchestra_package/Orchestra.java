package orchestra_package;

import java.util.ArrayList;
import java.util.Random;

public class Orchestra {
    private String name;
    private ArrayList<Musician> musicians;
    private ArrayList<MusicInstrument> musicInstruments;

    private int minSongSounds = 5; // Минимальное число звуков в песне
    private int maxSongSounds = 10; // Максимальное число звуков в песне
    private int songSoundsDelay = 750; // Задержка между звуками в миллисекундах

    public Orchestra(){
        musicInstruments = new ArrayList<MusicInstrument>();
        musicians = new ArrayList<Musician>();
    }

    public void setName(String name){ this.name = name; }
    public String getName(){ return name; }

    public ArrayList<MusicInstrument> getInstruments(){
        return musicInstruments;
    }
    public void setInstruments(ArrayList<MusicInstrument> instruments){
        musicInstruments = instruments;
    }
    public void addInstrument(MusicInstrument instrument){
        musicInstruments.add(instrument);
    }
    public void deleteInstrument(MusicInstrument instrument){
        musicInstruments.remove(instrument);
    }
    public void setMusicians(ArrayList<Musician> musicians){ this.musicians = musicians; }
    public ArrayList<Musician> getMusicians(){ return musicians; }
    public void addMusician(Musician musician){ musicians.add(musician); }
    public void deleteMusicican(Musician musician){ musicians.remove(musician); }

    public void setSongSoundsDelay(int delay){ songSoundsDelay = delay; }
    public int getSongSoundsDelay(){ return songSoundsDelay; }
    public void setMinSongSounds(int sounds){ minSongSounds = sounds; }
    public int getMinSongSounds(){ return minSongSounds; }
    public void setMaxSongSounds(int sounds){ maxSongSounds = sounds; }
    public int getMaxSongSounds(){ return maxSongSounds; }
    public int getRandomSongSoundsNum(){
        Random rand = new Random();
        if(maxSongSounds == minSongSounds)
            return maxSongSounds;
        else
            return rand.nextInt(maxSongSounds - minSongSounds) + minSongSounds + 1;
    }

    // Сыграть звук песни
    public String playSound(boolean isRuNotes){
        Random random = new Random();
        int musicianInd = random.nextInt(musicians.size());
        Musician musician = musicians.get(musicianInd);
        String sound = musician.getInstrument().orchestraPlay(isRuNotes);

        if(sound != null)
            return musician.getName() + " сыграл на " + musician.getInstrument().getName() + ": " + sound;
        else
            return musician.getName() + " не смог сыграть на " + musician.getInstrument().getName();
    }
}
