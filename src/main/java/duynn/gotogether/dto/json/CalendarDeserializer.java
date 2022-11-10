package duynn.gotogether.dto.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarDeserializer extends JsonDeserializer<Calendar> {

    private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    @Override
    public Calendar deserialize(JsonParser jsonParser,
                                DeserializationContext deserializationContext) throws IOException {

        String dateAsString = jsonParser.getText();

        try {
            Date date = formatter.parse(dateAsString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}