package my_package;

import javax.sound.midi.Instrument;
import javax.xml.soap.Text;
import javax.xml.transform.TransformerException;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class MenuController {

    private static Orchestra orchestra;

    // Начальные значения
    private static void initTestData(){
        orchestra = new Orchestra();
        orchestra.setName("Солнышко");

        // Нотные инструменты
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

        // Звуки
        NonNoteSound.addPossibleSound("Бас-бочка");
        NonNoteSound.addPossibleSound("Малый барабан");
        NonNoteSound.addPossibleSound("Подвесный том");
        NonNoteSound.addPossibleSound("Напольный том");
        NonNoteSound.addPossibleSound("Закрытый хай-хэт");
        NonNoteSound.addPossibleSound("Открытый хай-хэт");
        NonNoteSound.addPossibleSound("Крэш");
        NonNoteSound.addPossibleSound("Райд");
        NonNoteSound.addPossibleSound("Взмах дирижёрской палочкой №1");
        NonNoteSound.addPossibleSound("Взмах дирижёрской палочкой №2");
        NonNoteSound.addPossibleSound("Взмах дирижёрской палочкой №3");

        ArrayList<String> possibleSounds = NonNoteSound.getPossibleSounds();

        // Безнотные инструменты
        NonNoteInstrument drumSet = new NonNoteInstrument();
        drumSet.setName("Барабанная установка №1");
        drumSet.setIsAcoustic(true);
        for(int i = 0; i < 8; i++){
            drumSet.addSound(new NonNoteSound(possibleSounds.get(i)));
        }
        orchestra.addInstrument(drumSet);

        NonNoteInstrument conductorStick = new NonNoteInstrument();
        conductorStick.setName("Дирижёрская палочка");
        conductorStick.setIsAcoustic(true);
        for(int i = 8; i < 11; i++){
            conductorStick.addSound(new NonNoteSound(possibleSounds.get(i)));
        }
        orchestra.addInstrument(conductorStick);

        for(int i = 0; i < orchestra.getInstruments().size(); i++){
            System.out.println(i + " | " + orchestra.getInstruments().get(i).getName());
        }

        // Музыканты
        Musician musician = new Musician();
        musician.setName("Иванов Иван");
        musician.setAge(27);
        musician.setJoiningOrchestraYear(2020);
        musician.setInstrument(orchestra.getInstruments().get(1)); // Электрогитара
        orchestra.addMusician(musician);

        musician = new Musician();
        musician.setName("Александров Александр");
        musician.setAge(26);
        musician.setJoiningOrchestraYear(2020);
        musician.setInstrument(orchestra.getInstruments().get(7)); // Барабанная установка №1
        orchestra.addMusician(musician);

        musician = new Musician();
        musician.setName("Сергеев Сергей");
        musician.setAge(47);
        musician.setJoiningOrchestraYear(2014);
        musician.setInstrument(orchestra.getInstruments().get(8)); // Дирижёрская палочка
        orchestra.addMusician(musician);

        musician = new Musician();
        musician.setName("Трубов Трубач");
        musician.setAge(37);
        musician.setJoiningOrchestraYear(2016);
        musician.setInstrument(orchestra.getInstruments().get(8)); // Труба
        orchestra.addMusician(musician);

        musician = new Musician();
        musician.setName("Трубов Трубач младший");
        musician.setAge(13);
        musician.setJoiningOrchestraYear(2018);
        musician.setInstrument(orchestra.getInstruments().get(8)); // Труба
        orchestra.addMusician(musician);

        musician = new Musician();
        musician.setName("Саксофонов Саксофонист");
        musician.setAge(34);
        musician.setJoiningOrchestraYear(2017);
        musician.setInstrument(orchestra.getInstruments().get(5)); // Саксофон
        orchestra.addMusician(musician);

        musician = new Musician();
        musician.setName("Петров Пётр");
        musician.setAge(19);
        musician.setJoiningOrchestraYear(2021);
        musician.setInstrument(orchestra.getInstruments().get(3)); // Рояль
        orchestra.addMusician(musician);
    }

    public static void start(){
        initTestData();
        printOrchestraPanel();
    }

    private static void printOrchestraPanel() {
        TextPanel panel = new TextPanel("Оркестр " + orchestra.getName(), new String[]{"Список участников", "Список инструментов"});
        int choice = panel.getChoice();
        switch (choice){
            case 1: // Список участников
                printMusicians();
                break;

            case 2: // Список инструментов
                printInstrumentsTypes();
                break;

            // 0 не обрабатываем, так как при нём подразумевается выход из программы, а он и так будет, т.к. метод завершится
        }
    }

    private static void printMusicians(){
        ArrayList<Musician> musicians = orchestra.getMusicians();

        for(int i = 0; i < musicians.size(); i++){
            System.out.print(i+1 + ". ");
            System.out.println(musicians.get(i).getName() + " : " + musicians.get(i).getInstrument().getName());
        }
    }

    private static void printInstrumentsTypes() {
        TextPanel panel = new TextPanel("Список инструментов",  new String[]{ "Струнные", "Клавишные", "Духовые", "Безнотные" }, true);
        int choice = panel.getChoice();

        ArrayList<MusicInstrument> instruments = new ArrayList<MusicInstrument>();

        switch (choice){
            case 1:
                instruments.addAll(getStringedInstruments());
                printAllInstruments(instruments, choice-1);
                break;

            case 2:
                instruments.addAll(getKeyboardInstruments());
                printAllInstruments(instruments, choice-1);
                break;

            case 3:
                instruments.addAll(getWindInstruments());
                printAllInstruments(instruments, choice-1);
                break;

            case 4:
                instruments.addAll(getNonNoteInstruments());
                printAllInstruments(instruments, choice-1);
                break;

            case 0: // Назад
                printOrchestraPanel();
                break;
        }
    }

    private static void printAllInstruments(ArrayList<MusicInstrument> instruments, int instrumentsType) {
        String title = "";
        if(instrumentsType == 0)
            title = "Струнные инструменты";
        else if (instrumentsType == 1)
            title = "Клавишные инструменты";
        else if (instrumentsType == 2)
            title = "Духовые инструменты";
        else if(instrumentsType == 3)
            title = "Безнотные инструменты";

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
            else if(instrument instanceof NonNoteInstrument)
                printNonNoteInstrumentInfo((NonNoteInstrument) instrument);

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
                WindInstrument instrument = readWindInstrument();
                orchestra.addInstrument(instrument);
                instruments.add(instrument);
            }
            if(instrumentsType == 3){
                NonNoteInstrument instrument = readNonNoteInstrument();
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

    private static ArrayList<NonNoteInstrument> getNonNoteInstruments(){
        ArrayList<NonNoteInstrument> nonNoteInstruments = new ArrayList<NonNoteInstrument>();
        for(MusicInstrument instrument : orchestra.getInstruments()){
            if(instrument instanceof NonNoteInstrument){
                nonNoteInstruments.add((NonNoteInstrument) instrument);
            }
        }
        return nonNoteInstruments;
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

    private static WindInstrument readWindInstrument(){
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

    private static NonNoteInstrument readNonNoteInstrument(){
        NonNoteInstrument instrument = new NonNoteInstrument();

        String name = TextPanel.readString("Название");
        instrument.setName(name);
        boolean isAcoustic = TextPanel.readYesOrNo("Акустический ли инструмент");
        instrument.setIsAcoustic(isAcoustic);

        int actionsChoice = 0;
        do {
            TextPanel panel = new TextPanel("Действия", new String[]{"Добавить звуки инструменту", "Расширить список доступных звуков", "Завершить"}, false);
            actionsChoice = panel.getChoice();

            ArrayList<String> possibleSounds = NonNoteSound.getPossibleSounds();

            if(actionsChoice == 1)
            {
                System.out.println("Доступные звуки:");
                for (int i = 0; i < possibleSounds.size(); i++) {
                    System.out.print(i + 1 + ". ");
                    System.out.println(possibleSounds.get(i).toString());
                }

                System.out.println("Вводите номера звуков, которые хотите добавить инструмету");
                System.out.println("Введите 0 для завершения ввода");

                do{
                    int soundInd = TextPanel.readInt("Номер звука") - 1;
                    if(soundInd == -1)
                        break;
                    else if(soundInd >= possibleSounds.size())
                        System.out.println("Звука с таким номером не существует");
                    else {
                        NonNoteSound sound = new NonNoteSound(possibleSounds.get(soundInd));
                        instrument.addSound(sound);
                        System.out.println("Добавлен звук: " + sound.toString());
                    }
                } while (true);
            }
            else if (actionsChoice == 2)
            {
                System.out.println("Вводите названия звуков, которые хотите добавить в общий список доступных звуков");
                System.out.println("Введите 0 для завершения ввода");

                do{
                    String soundName = TextPanel.readString("Название звука");
                    if(soundName.equals("0"))
                        break;
                    NonNoteSound.addPossibleSound(soundName);
                    NonNoteSound sound = new NonNoteSound(soundName);
                    System.out.println("Добавлен звук: " + sound.toString());
                } while (true);
            }

        } while (actionsChoice != 3);

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
        int choice = 0;
        do {
            TextPanel panel = new TextPanel("Действия", new String[]{"Сыграть на инструменте"});
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

    private static void printNonNoteInstrumentInfo(NonNoteInstrument instrument){
        System.out.println("Название: " + instrument.getName());
        System.out.println("Акустический: " + (instrument.getIsAcoustic() ? "Да" : "Нет"));
        System.out.println("Звуки инструмента:");

        ArrayList<NonNoteSound> instrumentSounds = instrument.getSounds();
        for(int i = 0; i < instrumentSounds.size(); i++){
            System.out.println(instrumentSounds.get(i).toString());
        }

        playNonNotesPanel(instrument);
    }

    private static void playNonNotesPanel(NonNoteInstrument instrument){
        int choice = 0;
        do {
            TextPanel panel = new TextPanel("Действия", new String[]{"Сыграть на инструменте"});
            choice = panel.getChoice();
            if(choice == 1){
                NonNoteSound randSound = instrument.playSound();
                System.out.println("Инструмент сыграл звук: " + randSound.toString());
            }
        } while (choice != 0);
    }
}
