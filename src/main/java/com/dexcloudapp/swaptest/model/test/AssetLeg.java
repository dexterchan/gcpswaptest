package com.dexcloudapp.swaptest.model.test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dexter on 7/8/17.
 */
public class AssetLeg {
    String porr; //Pay or receive
    String type;
    String ccy;
    double notional;
    String index;
    double rate;
    double spread;

    List<AssetFlow> cashflows;


    public AssetLeg(){
        cashflows = new LinkedList<AssetFlow>();
    }

    public void insertCashflow(AssetFlow f){
        cashflows.add(f);
    }
    public String getPorr() {
        return porr;
    }

    public void setPorr(String porr) {
        this.porr = porr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public double getNotional() {
        return notional;
    }

    public void setNotional(double notional) {
        this.notional = notional;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getSpread() {
        return spread;
    }

    public void setSpread(double spread) {
        this.spread = spread;
    }

    public List<AssetFlow> getCashflows() {
        return cashflows;
    }

    public void setCashflows(List<AssetFlow> cashflows) {
        this.cashflows = cashflows;
    }


}
