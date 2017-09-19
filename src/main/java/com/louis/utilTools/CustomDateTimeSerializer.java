package com.louis.utilTools;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

/**
 *
 * property Datetime serialise customizedly
 */
public class CustomDateTimeSerializer extends JsonSerializer<DateTime> {
	private DateTimeFormatter formatter = DateTimeFormat
			.forPattern("yyyy-MM-dd HH:mm:ss");

	@Override
	public void serialize(DateTime dateTime, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider) throws IOException,
			JsonProcessingException {
		jsonGenerator.writeString(formatter.print(dateTime));
	}
}