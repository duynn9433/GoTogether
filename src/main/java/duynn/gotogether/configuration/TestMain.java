package duynn.gotogether.configuration;

import com.google.gson.Gson;
import duynn.gotogether.entity.place.Geometry;
import duynn.gotogether.entity.place.Location;

import java.util.Locale;
import java.util.Map;
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
//            temp: {Location(id=253128, lat=20.980510609000078, lng=105.78749262700006)=0,
//            Location(id=253129, lat=21.07527969100005, lng=105.77282615500008)=1,
//            Location(id=253130, lat=21.0229841, lng=105.806509)=2,
//            Location(id=253127, lat=20.988488589000042, lng=105.79589990200009)=3}
//            startLocation: Location(id=null, lat=20.980510609000078, lng=105.78749262700006)
//            endLocation: Location(id=null, lat=21.0229841, lng=105.806509)
            Location l0 = new Location(253128L, 20.980510609000078, 105.78749262700006);
            Location l1 = new Location(253129L, 21.07527969100005, 105.77282615500008);
            Location l2 = new Location(253130L, 21.0229841, 105.806509);
            Location l3 = new Location(253127L, 20.988488589000042, 105.79589990200009);

            Location location1 = new Location(null, 20.980510609000078, 105.78749262700006);
            Location location2 = new Location(null, 21.0229841, 105.806509);

            Map<Location,Long> map = Map.of(l0, 0L, l1, 1L, l2, 2L, l3, 3L);
            System.out.println(map.toString());
            System.out.println(map.get(location1));
            System.out.println(l0.equals(location1));
            System.out.println(map.get(location2));

        }
}
