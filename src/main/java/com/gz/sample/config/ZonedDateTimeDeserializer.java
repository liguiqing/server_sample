
package com.gz.sample.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parsing ISO Date String into ZoneDateTime with RestTemplate in Spring
 *
 * @author liguiqing created at 2020-08-30
 * @since V1.0.0
 **/
public class ZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {
    @Override
    public ZonedDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
        throws IOException {
        return ZonedDateTime.parse(
            jsonParser.getText(),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
        );
    }
}
