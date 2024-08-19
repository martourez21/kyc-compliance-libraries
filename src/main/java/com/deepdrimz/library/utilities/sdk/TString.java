package com.deepdrimz.library.utilities.sdk;



import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TString implements Serializable {
    private Map<ISOLang, String> values = new HashMap<>();

    public TString() {
    }

    public TString(Map<ISOLang, String> values) {
        this.values = values;
    }

    @JsonValue
    public Map<ISOLang, String> getValues() {
        return values;
    }

    public void setValues(Map<ISOLang, String> values) {
        this.values = values;
    }

    @JsonAnySetter
    public void addValue(String key, String value) {
        ISOLang lang = ISOLang.valueOf(key.toUpperCase());
        this.values.put(lang, value);
    }

    @Override
    public String toString() {
        return "TString{" +
                "values=" + values +
                '}';
    }
}