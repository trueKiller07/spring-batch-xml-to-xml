package com.trax.europeangateway.util;

import java.sql.Timestamp;
import java.time.Instant;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class InstantConverter implements AttributeConverter<Instant, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(Instant attributeValue) {
		if (attributeValue != null) {
			return Timestamp.from(attributeValue);
		}
		return null;
	}

	@Override
	public Instant convertToEntityAttribute(Timestamp databaseValue) {
		if (databaseValue != null) {
			return databaseValue.toInstant();
		}
		return null;
	}
}
