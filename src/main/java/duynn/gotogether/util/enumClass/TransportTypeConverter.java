package duynn.gotogether.util.enumClass;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;
import java.util.stream.Stream;

//https://www.baeldung.com/jpa-persisting-enums-in-jpa
@Converter
public class TransportTypeConverter implements AttributeConverter<TransportType, String> {
    @Override
    public String convertToDatabaseColumn(TransportType transportType) {
        if (transportType == null) {
            return null;
        }
        return transportType.getCode();
    }

    @Override
    public TransportType convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(TransportType.values())
                .filter(c -> Objects.equals(c.getCode(), code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
