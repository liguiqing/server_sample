
package com.gz.sample.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parsing ISO Date String into LocalDateTime with RestTemplate in Spring
 *
 * @author liguiqing created at 2020-08-30
 * @since V1.0.0
 **/
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    private DateTimeFormatter formatter;

    public LocalDateTimeDeserializer() {
        this(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    public LocalDateTimeDeserializer(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
        throws IOException {
        return LocalDateTime.parse(
            jsonParser.getText(),
            this.formatter
        );
    }
}
