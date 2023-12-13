package orchestra_package;

public abstract class NoteInstrument extends MusicInstrument{
    private Note minNote;
    private Note maxNote;

    public Note getMinNote(){
        return minNote;
    }

    public Note getMaxNote(){
        return maxNote;
    }

    public void setNoteRange(Note minNote, Note maxNote){
        this.minNote = minNote;
        this.maxNote = maxNote;
    }

    public abstract Note playSound();
}
