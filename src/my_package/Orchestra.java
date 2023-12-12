package my_package;

import java.util.ArrayList;
import java.util.MissingFormatArgumentException;

public class Orchestra {
    private ArrayList<MusicInstrument> musicInstruments;


    public Orchestra(){
        musicInstruments = new ArrayList<MusicInstrument>();
    }
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
}
