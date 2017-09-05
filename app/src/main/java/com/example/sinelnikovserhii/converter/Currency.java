package com.example.sinelnikovserhii.converter;

import java.util.SimpleTimeZone;

/**
 * Created by sinelnikovserhii on 27.08.17.
 */

public class Currency {
    String currency;

    Currency(String currency){
        this.currency = currency;
    }

    public String getCurrency(){
        return this.currency;
    }

    public void setCurrency(String currency){
        this.currency = currency;
    }
}
