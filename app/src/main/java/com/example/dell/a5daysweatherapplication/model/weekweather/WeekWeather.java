
package com.example.dell.a5daysweatherapplication.model.weekweather;

import java.util.HashMap;
import java.util.Map;


public class WeekWeather {

    private String cod;
    private Double message;
    private Integer cnt;
    private java.util.List<MyList> list;
    private City city;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public static java.util.List<MyList> getList() {
        return getList();
    }

    public void setList(java.util.List<MyList> list) {
        this.list = list;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
