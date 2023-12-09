package my_package;

import java.util.Scanner;

public class TextPanel {
    private String title;
    private String[] variants;
    private boolean isBackVariant = true; // Есть ли вариант вернуться назад?

    public TextPanel(){
        title = "Title";
        variants = new String[] { "Вариант1", "Вариант2" };
        isBackVariant = true;
    }

    public TextPanel(String title, String[] variants){
        setTitle(title);
        setVariants(variants);
    }

    public TextPanel(String title, String[] variants, boolean isBackVariant){
        setTitle(title);
        setVariants(variants);
        setIsBackVariant(isBackVariant);
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setVariants(String[] variants){
        this.variants = variants.clone();
    }

    public void setIsBackVariant(boolean isBackVariant){
        this.isBackVariant = isBackVariant;
    }

    // Вывод заголовка с украшением
    private String getFormattedTitle(){
        final char highlightChar = '='; // Символ для выделения заголовка
        final int highlightCharsNum = 7; // Количество таких символов в заголовке с каждой из сторон

        StringBuilder formattedTitle = new StringBuilder(); // Для сборки строк лучше использовать его
        for(int i = 0; i < highlightCharsNum; i++){
            formattedTitle.append(highlightChar);
        }
        formattedTitle.append(" " + title + " ");
        for(int i = 0; i < highlightCharsNum; i++){
            formattedTitle.append(highlightChar);
        }
        return formattedTitle.toString();
    }

    // Вывод элементов меню
    private void printPanel(){
        System.out.println(getFormattedTitle());
        for(int i = 0; i < variants.length; i++){
            System.out.println((i+1) + ". " + variants[i]);
        }
        if(isBackVariant){
            System.out.println("0. Выход");
        }
    }

    // Считываем выбранный вариант
    private int readChoice(){
        Scanner sc = new Scanner(System.in);
        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine()); // Для очистки буфера сканера
        }
        catch (NumberFormatException ex){
            System.out.println("Введите число");
            return readChoice();
        }

        if((choice > 0 && choice <= variants.length) || (choice == 0 && isBackVariant)){
            return choice;
        }
        else {
            System.out.println("Вводите только значения пунктов меню");
            return readChoice();
        }
    }

    public int getChoice() {
        printPanel();
        return readChoice();
    }

    /*
    // Считывание положительного числа
    public int readPositiveInt(String title){
        System.out.print(title + ": ");
        Scanner sc = new Scanner(System.in);
        int number;
        try {
            number = Integer.parseInt(sc.nextLine()); // Для очистки буфера сканера
        }
        catch (NumberFormatException ex){
            System.out.println("Введите число");;
            return readPositiveInt(title);
        }

        if(number > 0){
            return number;
        }
        else {
            System.out.println("Число должно быть положительным");
            return readPositiveInt(title);
        }
    }

    // Считывание строки
    public String readFileName(String title, String extension){
        System.out.print(title + ": ");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        String lastSymbols = "";

        if(fileName.length() > extension.length())
            lastSymbols = fileName.substring(fileName.length() - extension.length());

        if(!lastSymbols.equals(extension))
            fileName += extension;
        return fileName;
    }
    */
}
