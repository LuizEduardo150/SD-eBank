package utils;
import java.util.Scanner;


public class KeyBoardReader {

    Scanner scanner = null;

    public KeyBoardReader(){
        scanner = new Scanner(System.in);
    }

    public String readString(String outputText){
        System.out.print(outputText);
        String text = scanner.nextLine();
        return text;
    }

    public int readInt(String outputText){
        System.out.print(outputText);
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    public void close(){
        scanner.close();
    }

}
