package com.dexcloudapp.swaptest.utility;

import java.util.UUID;

/**
 * Created by dexter on 7/8/17.
 */
public class TradeIdCounter {


    public static String getTradeId(String location, String tradetype){
        String name;

        name= UUID.randomUUID().toString();
        return name;
    }
}
