package com.deepdrimz.library.converters;


import com.deepdrimz.library.utilities.sdk.ISOLang;
import com.deepdrimz.library.utilities.sdk.TString;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.Map;


@Converter(autoApply = true)
public class TStringConverter implements AttributeConverter<TString, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(TString attribute) {
        try {
            return objectMapper.writeValueAsString(attribute.getValues());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting TString to JSON", e);
        }
    }

    @Override
    public TString convertToEntityAttribute(String dbData) {
        try {
            Map<ISOLang, String> values = objectMapper.readValue(dbData, new TypeReference<Map<ISOLang, String>>() {});
            TString tString = new TString();
            tString.setValues(values);
            return tString;
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON to TString", e);
        }
    }
}

