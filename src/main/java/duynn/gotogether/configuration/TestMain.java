package duynn.gotogether.configuration;

import com.google.gson.Gson;
import duynn.gotogether.entity.place.Geometry;
import duynn.gotogether.entity.place.Location;

import java.util.Locale;
import java.util.Scanner;

public class TestMain {
    static class Test extends Thread  {
        StringBuilder sbr;

        public Test() {
            sbr = new StringBuilder();
        }

        public String hello() {
            sbr.append("Hello World");
            return(sbr.toString());
        }
    }

        public static void main(String args[]) {
            String str = "1,0";
            Double d = Double.parseDouble(str);
            System.out.println(d);
        }
}
