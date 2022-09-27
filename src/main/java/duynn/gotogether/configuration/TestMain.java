package duynn.gotogether.configuration;

import com.google.gson.Gson;
import duynn.gotogether.entity.place.Geometry;
import duynn.gotogether.entity.place.Location;

import java.util.Locale;

public class TestMain {
    public static void main(String[] args) {
        Location location = new Location();
        location.setLat(1.0);
        location.setLng(2.0);
        System.out.println(location);
        Geometry geometry = new Geometry();
        geometry.setLocation(location);
        System.out.println(geometry);
        Gson gson = new Gson();
        String json = gson.toJson(geometry);
        System.out.println(json);

    }
}
