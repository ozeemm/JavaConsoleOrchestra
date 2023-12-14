package orchestra_package;

import java.util.ArrayList;
import java.util.Random;

public class Orchestra {
    private String name;
    private ArrayList<Musician> musicians;
    private ArrayList<MusicInstrument> musicInstruments;

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

    public String playAll(boolean isRuNotes){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 10; i++){
            int musicianInd = random.nextInt(musicians.size());
            Musician musician = musicians.get(musicianInd);
            String sound = musician.getInstrument().playInOrchestra(isRuNotes);
            String msg = musician.getName() + " сыграл на " + musician.getInstrument().getName() + ": " + sound + "\n";
            //if(sound != null)
            //    msg = musician.getName() + " сыграл на " + musician.getInstrument().getName() + ": " + sound + "\n";
            //else
            //    msg = musician.getName() + " не смог сыграть на " + musician.getInstrument().getName() + "\n";
            sb.append(msg);
        }
        return sb.toString();
    }
}
