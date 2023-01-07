package duynn.gotogether.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import duynn.gotogether.dto.response.JwtResponse;
import duynn.gotogether.entity.Client;
import duynn.gotogether.entity.Fullname;
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

        public static void main(String args[]) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            Fullname fullname = new Fullname(0L,"nguyen","ngoc","duy","duy");
            JwtResponse jwtResponse = new JwtResponse("token",new Client());
            jwtResponse.getClient().setFullname(fullname);
            String s = mapper.writeValueAsString(jwtResponse);

            System.out.println(s);
        }
}
