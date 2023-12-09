package my_package;

public class WindInstrument extends NoteInstrument {

    private String type; // Тип духового инструмента

    public void setType(String type) { this.type = type; }
    public String getKeys(){ return type; }
    @Override
    public Note playSound() {
        return Note.getRandomNote(getMinNote(), getMaxNote());
    }
}
