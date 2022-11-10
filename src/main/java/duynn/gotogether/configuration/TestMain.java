package duynn.gotogether.configuration;

import com.google.gson.Gson;
import duynn.gotogether.entity.place.Geometry;
import duynn.gotogether.entity.place.Location;

import java.util.Locale;
import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 1;
        while(true){
            String s = scanner.nextLine();
            if(s.equals("exit")){
                break;
            }
            if(s.length() == 0){
                continue;
            }
            System.out.println("Question " + count + ": " + s + "\n" + "A: Simple sentence\n" +
                    "B: Compound sentence\n" +
                    "C: Complex sentence\n" +
                    "D: Compound-complex sentence\n\n");
            count++;
        }

    }
}
