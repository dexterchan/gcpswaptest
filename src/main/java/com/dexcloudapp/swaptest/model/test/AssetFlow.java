package com.dexcloudapp.swaptest.model.test;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by dexter on 7/8/17.
 */
public class AssetFlow {

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")
    Date startDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")
    Date endDate;
    boolean payEnd;
    String ccy;
    double amount;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isPayEnd() {
        return payEnd;
    }

    public void setPayEnd(boolean payEnd) {
        this.payEnd = payEnd;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
