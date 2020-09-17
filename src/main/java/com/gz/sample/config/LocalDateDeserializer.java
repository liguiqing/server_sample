
package com.gz.sample.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Parsing ISO Date String into LocalDateTime with RestTemplate in Spring
 *
 * @author liguiqing created at 2020-08-30
 * @since V1.0.0
 **/
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
    private DateTimeFormatter formatter;

    public LocalDateDeserializer() {
        this(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    public LocalDateDeserializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
        throws IOException {
        return LocalDate.parse(
            jsonParser.getText(),
            this.formatter
        );
    }
}
