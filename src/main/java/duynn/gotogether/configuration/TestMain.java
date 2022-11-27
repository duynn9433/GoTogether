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
            Test thread1 = new Test();
            thread1.start();
            Test thread2 = new Test();
            thread2.start();
            System.out.println(thread1.hello());
            System.out.println(thread2.hello());
        }
}
