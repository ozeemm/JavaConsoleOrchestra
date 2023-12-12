package my_package;

import javax.sound.midi.Instrument;
import javax.xml.soap.Text;
import javax.xml.transform.TransformerException;
import java.util.ArrayList;

public class MenuController {

    private static Orchestra orchestra;

    private static void initTestData(){
        orchestra = new Orchestra();

        StringedInstrument guitar = new StringedInstrument();
        guitar.setName("Акустическая гитара");
        guitar.setIsAcoustic(true);
        guitar.setNoteRange(new Note("E", 3), new Note("D", 6));
        guitar.setFrets(22);
        guitar.setStrings(6);
        guitar.setIsBow(false);
        guitar.setStringBreakChance(0.01f);
        orchestra.addInstrument(guitar);

        StringedInstrument elGuitar = new StringedInstrument();
        elGuitar.setName("Электрогитара");
        elGuitar.setIsAcoustic(false);
        elGuitar.setNoteRange(new Note("E", 3), new Note("D", 6));
        elGuitar.setFrets(22);
        elGuitar.setStrings(6);
        elGuitar.setIsBow(false);
        elGuitar.setStringBreakChance(0.01f);
        orchestra.addInstrument(elGuitar);

        StringedInstrument oldGuitar = new StringedInstrument();
        oldGuitar.setName("Старая советская гитара");
        oldGuitar.setIsAcoustic(true);
        oldGuitar.setNoteRange(new Note("E", 3), new Note("F", 6));
        oldGuitar.setFrets(19);
        oldGuitar.setStrings(6);
        oldGuitar.setIsBow(false);
        oldGuitar.setStringBreakChance(0.75f);
        orchestra.addInstrument(oldGuitar);

        KeyboardInstrument royal = new KeyboardInstrument();
        royal.setName("Рояль");
        royal.setIsAcoustic(true);
        royal.setNoteRange(new Note("A", 0), new Note("C", 8));
        royal.setKeys(88);
        orchestra.addInstrument(royal);

        WindInstrument flute = new WindInstrument();
        flute.setName("Флейта");
        flute.setIsAcoustic(true);
        flute.setNoteRange(new Note("C", 3), new Note("D", 6));
        flute.setMaterial("Деревянный");
        flute.setType("Лабиальный");
        flute.setNotEnoughBreathChance(0.02f);
        orchestra.addInstrument(flute);

        WindInstrument saxofone = new WindInstrument();
        saxofone.setName("Саксофон");
        saxofone.setIsAcoustic(true);
        saxofone.setNoteRange(new Note("Db", 2), new Note("Ab", 4));
        saxofone.setMaterial("Медный");
        saxofone.setType("Тростевой");
        saxofone.setNotEnoughBreathChance(0.05f);
        orchestra.addInstrument(saxofone);

        WindInstrument trumpet = new WindInstrument();
        trumpet.setName("Труба");
        trumpet.setIsAcoustic(true);
        trumpet.setNoteRange(new Note("F#", 2), new Note("С", 5));
        trumpet.setMaterial("Медный");
        trumpet.setType("Амбушюрный");
        trumpet.setNotEnoughBreathChance(0.075f);
        orchestra.addInstrument(trumpet);
    }

    public static void start(){
        initTestData();
        printInstrumentsTypes();
    }

    private static void printInstrumentsTypes() {
        TextPanel panel = new TextPanel("Список инструментов",  new String[]{ "Струнные", "Клавишные", "Духовые" }, false);
        int choice = panel.getChoice();

        ArrayList<MusicInstrument> instruments = new ArrayList<MusicInstrument>();

        switch (choice){
            case 1:
                instruments.addAll(getStringedInstruments());
                break;

            case 2:
                instruments.addAll(getKeyboardInstruments());
                break;

            case 3:
                instruments.addAll(getWindInstruments());
                break;
        }

        printAllInstruments(instruments, choice-1);
    }

    private static void printAllInstruments(ArrayList<MusicInstrument> instruments, int instrumentsType) {
        String title = "";
        if(instrumentsType == 0)
            title = "Струнные инструменты";
        else if (instrumentsType == 1)
            title = "Клавишные инструменты";
        else if (instrumentsType == 2)
            title = "Духовые инструменты";

        ArrayList<String> panelVariants = new ArrayList<String>();
        panelVariants.add("Добавить инструмент");

        if(!instruments.isEmpty()) {
            panelVariants.add("Удалить инструмент");

            for (MusicInstrument instrument : instruments) {
                panelVariants.add(instrument.getName());
            }
        }

        TextPanel instrumentsPanel = new TextPanel(title, panelVariants.toArray(new String[panelVariants.size()]));
        int choice = instrumentsPanel.getChoice();
        if(choice > 2) {
            MusicInstrument instrument = instruments.get(choice-3);
            if(instrument instanceof NoteInstrument)
                printNoteInstrumentInfo((NoteInstrument) instrument);

            printAllInstruments(instruments, instrumentsType);
        }
        else if(choice == 2){
            int instrumentNum = 0;
            do{
                instrumentNum = TextPanel.readInt("Номер инструмента") - 3;
            } while (instrumentNum < 0 || instrumentNum >= instruments.size());
            System.out.println("Удалён инструмент: " + instruments.get(instrumentNum).getName());
            orchestra.deleteInstrument(instruments.get(instrumentNum));
            instruments.remove(instruments.get(instrumentNum));
            printAllInstruments(instruments, instrumentsType);
        }
        else if(choice == 1){
            if(instrumentsType == 0){
                StringedInstrument instrument = readStringInstrument();
                orchestra.addInstrument(instrument);
                instruments.add(instrument);
            }
            if(instrumentsType == 1){
                KeyboardInstrument instrument = readKeyboardInstrument();
                orchestra.addInstrument(instrument);
                instruments.add(instrument);
            }
            if(instrumentsType == 2){
                WindInstrument instrument = readWindInstruments();
                orchestra.addInstrument(instrument);
                instruments.add(instrument);
            }

            System.out.println("Новый инструмент добавлен!");
            printAllInstruments(instruments, instrumentsType);
        }
        else if(choice == 0){
            printInstrumentsTypes();
        }
    }

    private static ArrayList<StringedInstrument> getStringedInstruments(){
        ArrayList<StringedInstrument> stringedInstruments = new ArrayList<StringedInstrument>();
        for(MusicInstrument instrument : orchestra.getInstruments()){
            if(instrument instanceof StringedInstrument){
                stringedInstruments.add((StringedInstrument)instrument);
            }
        }
        return stringedInstruments;
    }

    private static ArrayList<KeyboardInstrument> getKeyboardInstruments(){
        ArrayList<KeyboardInstrument> keyboardInstruments = new ArrayList<KeyboardInstrument>();
        for(MusicInstrument instrument : orchestra.getInstruments()){
            if(instrument instanceof KeyboardInstrument){
                keyboardInstruments.add((KeyboardInstrument)instrument);
            }
        }
        return keyboardInstruments;
    }

    private static ArrayList<WindInstrument> getWindInstruments(){
        ArrayList<WindInstrument> windInstruments = new ArrayList<WindInstrument>();
        for(MusicInstrument instrument : orchestra.getInstruments()){
            if(instrument instanceof WindInstrument){
                windInstruments.add((WindInstrument)instrument);
            }
        }
        return windInstruments;
    }
    private static StringedInstrument readStringInstrument(){
        StringedInstrument instrument = new StringedInstrument();

        String name = TextPanel.readString("Название");
        instrument.setName(name);
        boolean isAcoustic = TextPanel.readYesOrNo("Акустический ли инструмент");
        instrument.setIsAcoustic(isAcoustic);
        String minNoteText = TextPanel.readString("Минимальная нота");
        Note minNote = new Note(minNoteText.split(" ")[0], Integer.parseInt(minNoteText.split(" ")[1]));
        String maxNoteText = TextPanel.readString("Максимальная нота");
        Note maxNote = new Note(maxNoteText.split(" ")[0], Integer.parseInt(maxNoteText.split(" ")[1]));
        instrument.setNoteRange(minNote, maxNote);
        int strings = TextPanel.readInt("Количество струн");
        instrument.setStrings(strings);
        int frets = TextPanel.readInt("Количество ладов");
        instrument.setFrets(frets);
        boolean isBow = TextPanel.readYesOrNo("Использется ли смычок");
        instrument.setIsBow(isBow);
        float chance = TextPanel.readFloat("Шанс лопания струны");
        instrument.setStringBreakChance(chance);

        return instrument;
    }

    private static KeyboardInstrument readKeyboardInstrument(){
        KeyboardInstrument instrument = new KeyboardInstrument();

        String name = TextPanel.readString("Название");
        instrument.setName(name);
        boolean isAcoustic = TextPanel.readYesOrNo("Акустический ли инструмент");
        instrument.setIsAcoustic(isAcoustic);
        String minNoteText = TextPanel.readString("Минимальная нота");
        Note minNote = new Note(minNoteText.split(" ")[0], Integer.parseInt(minNoteText.split(" ")[1]));
        String maxNoteText = TextPanel.readString("Максимальная нота");
        Note maxNote = new Note(maxNoteText.split(" ")[0], Integer.parseInt(maxNoteText.split(" ")[1]));
        instrument.setNoteRange(minNote, maxNote);

        int keys = TextPanel.readInt("Количество клавиш");
        instrument.setKeys(keys);

        return instrument;
    }

    private static WindInstrument readWindInstruments(){
        WindInstrument instrument = new WindInstrument();

        String name = TextPanel.readString("Название");
        instrument.setName(name);
        boolean isAcoustic = TextPanel.readYesOrNo("Акустический ли инструмент");
        instrument.setIsAcoustic(isAcoustic);
        String minNoteText = TextPanel.readString("Минимальная нота");
        Note minNote = new Note(minNoteText.split(" ")[0], Integer.parseInt(minNoteText.split(" ")[1]));
        String maxNoteText = TextPanel.readString("Максимальная нота");
        Note maxNote = new Note(maxNoteText.split(" ")[0], Integer.parseInt(maxNoteText.split(" ")[1]));
        instrument.setNoteRange(minNote, maxNote);

        String material = TextPanel.readString("Материал инструмента");
        instrument.setMaterial(material);
        String type = TextPanel.readString("Тип инструмента");
        instrument.setType(type);
        float chance = TextPanel.readFloat("Шанс того, что музыканту не хватит воздуха");
        instrument.setNotEnoughBreathChance(chance);

        return instrument;
    }

    private static void printNoteInstrumentInfo(NoteInstrument instrument){
        System.out.println("Название: " + instrument.getName());
        System.out.println("Акустический: " + (instrument.getIsAcoustic() ? "Да" : "Нет"));
        System.out.println("Нотный диапазон: от " + instrument.getMinNote().toString() + " до " + instrument.getMaxNote().toString());

        if(instrument instanceof StringedInstrument)
            printInstrumentInfo((StringedInstrument) instrument);
        else if(instrument instanceof KeyboardInstrument)
            printInstrumentInfo((KeyboardInstrument) instrument);
        else if(instrument instanceof WindInstrument)
            printInstrumentInfo((WindInstrument) instrument);
    }
    private static void printInstrumentInfo(StringedInstrument instrument) {
        System.out.println("Тип: Струнный");
        System.out.println("Количество струн: " + instrument.getStrings());
        System.out.println("Количество ладов: " + instrument.getFrets());
        System.out.println("Смычок: " + (instrument.getIsBow() ? "Да" : "Нет"));
        System.out.println("Шанс лопания струны: " + instrument.getStringBreakChance() * 100 + "%");
        playNotesPanel(instrument, "Струна лопнула");
    }

    private static void printInstrumentInfo(KeyboardInstrument instrument){
        System.out.println("Тип: Клавишный");
        System.out.println("Количество клавиш: " + instrument.getKeys());
        playNotesPanel(instrument, null);
    }

    private static void printInstrumentInfo(WindInstrument instrument){
        System.out.println("Тип: Духовой");
        System.out.println("Материал: " + instrument.getMaterial());
        System.out.println("Тип: " + instrument.getType());
        System.out.println("Шанс того, что музыканту не хватит воздуха: " + instrument.getNotEnoughBreathChance() * 100 + "%");

        playNotesPanel(instrument, "Музыканту не хватило дыхания");
    }

    private static void playNotesPanel(NoteInstrument instrument, String msgIfNoteIsNull) {
        int choice = 1;
        do {
            TextPanel panel = new TextPanel(null, new String[]{"Сыграть на инструменте"});
            choice = panel.getChoice();
            if(choice == 1){
                Note randSound = instrument.playSound();
                if(randSound == null)
                    System.out.println("Не удалось сыграть ноту: " + msgIfNoteIsNull);
                else
                    System.out.println("Инструмент сыграл ноту: " + randSound.toRuString() + " октавы");
            }
        } while (choice != 0);
    }
}
