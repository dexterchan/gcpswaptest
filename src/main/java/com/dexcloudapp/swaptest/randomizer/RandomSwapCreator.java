package com.dexcloudapp.swaptest.randomizer;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dexcloudapp.swaptest.model.test.AssetFlow;
import com.dexcloudapp.swaptest.model.test.AssetLeg;
import com.dexcloudapp.swaptest.model.test.SwapTrade1;
import com.dexcloudapp.swaptest.utility.TradeIdCounter;



/**
 * Created by dexter on 7/8/17.
 */
public class RandomSwapCreator {
	Logger logger = LoggerFactory.getLogger(RandomSwapCreator.class);
    private static RandomSwapCreator ourInstance = new RandomSwapCreator();

    public static RandomSwapCreator getInstance() {
        return ourInstance;
    }

    protected Random random= new Random(System.currentTimeMillis() * Thread.currentThread().getId());
    private RandomSwapCreator() {
        //Random random  = new Random(System.currentTimeMillis() * Thread.currentThread().getId());
    	logger.debug("Call constructuor");
    }

    protected List<String> bookList=null;

    protected List<String> custList=null;
    protected List<String > locList=null;
    protected int maxTenor;
    protected int minTenor;


	private String getRandomBook(){
        int num = random.nextInt(bookList.size());

        return bookList.get(num);
    }
    private String getRandomLocation(){
    	
        int num = random.nextInt(locList.size());

        return locList.get(num);
    }
    //	private String getRandomTradeStatus(){
//		TradeStatusEnum status=TradeStatusEnum.NEW;
//		int num = random.nextInt(4);
//		//status = TradeStatusEnum.values()[num];
//
//		return TradeStatus[num];
//	}
    private SwapTrade1.TradeStatusEnum getRandomTradeStatus(){
        SwapTrade1.TradeStatusEnum status= SwapTrade1.TradeStatusEnum.NEW;
        int num = random.nextInt(4-1);
        status = SwapTrade1.TradeStatusEnum.values()[num];

        return status;
    }
    private String getRandomCust(){
        int num = random.nextInt(custList.size());

        return custList.get(num);
    }
    private int getRandomTenor(){
        int t = random.nextInt(maxTenor-minTenor);
        return minTenor+t;
    }




    public int getMinTenor() {
		return minTenor;
	}
	public void setMinTenor(int minTenor) {
		this.minTenor = minTenor;
	}
	public int getMaxTenor() {
		return maxTenor;
	}
	public void setMaxTenor(int maxTenor) {
		this.maxTenor = maxTenor;
	}
	
	public SwapTrade1 prepareRandomIRS(double ntl, String ccy, String index, double rate, boolean randomNtl, boolean gencashflow){
        SwapTrade1 swp = new SwapTrade1();
        String location=this.getRandomLocation();
        
        ntl = (randomNtl? ntl + Math.abs(random.nextGaussian()) * ntl : ntl);

        swp.setTradeId(TradeIdCounter.getTradeId(location,"SWAP"));
        int tenor = this.getRandomTenor();
        swp.setBook(this.getRandomBook());
        swp.setLocation(location);
        swp.setCustomer(this.getRandomCust());
        Calendar c = Calendar.getInstance();
        swp.setStartDate(c.getTime());
        Calendar e = (Calendar)c.clone();
        e.add(Calendar.YEAR, tenor);
        swp.setEndDate(e.getTime());
        swp.setSwapType("IRS");
        swp.setTradeStatus(getRandomTradeStatus());


        double rr = random.nextDouble();
        boolean payFix=rr>0.5?true:false;


        //prepare Fix asset
        AssetLeg FixLeg = new AssetLeg();
        FixLeg.setCcy(ccy);
        FixLeg.setIndex("Fixed");
        FixLeg.setNotional(ntl);
        FixLeg.setPorr( payFix?"P":"R");
        FixLeg.setRate(rate);
        FixLeg.setSpread(0);
        FixLeg.setType("IRS");
        for(int i=0;i<2*tenor && gencashflow;i++){
            AssetFlow f = new AssetFlow();
            f.setCcy(ccy);
            double amt = ntl * rate/100 /2 * (payFix?-1:1);
            f.setAmount(amt);
            Calendar st = Calendar.getInstance();
            st.setTime(swp.getStartDate());
            Calendar et = Calendar.getInstance();
            et.setTime(swp.getStartDate());
            st.add(Calendar.MONTH, 6*i);
            et.add(Calendar.MONTH, 6*(i+1));
            f.setStartDate(st.getTime());
            f.setEndDate(et.getTime());
            f.setPayEnd(true);
            FixLeg.insertCashflow(f);
        }
        swp.insertAsset(FixLeg);

        AssetLeg fltLeg = new AssetLeg();
        fltLeg.setCcy(ccy);
        fltLeg.setIndex(index);
        fltLeg.setNotional(ntl);
        fltLeg.setPorr(payFix?"R":"P");
        fltLeg.setRate(0);
        fltLeg.setSpread(0);
        fltLeg.setType("IRS");
        for(int i=0;i<2*tenor && gencashflow;i++){
            AssetFlow f = new AssetFlow();
            f.setCcy(ccy);
            double amt = ntl * rate/100 /2 * (payFix?1:-1);
            f.setAmount(amt);
            Calendar st = Calendar.getInstance();
            st.setTime(swp.getStartDate());
            Calendar et = Calendar.getInstance();
            et.setTime(swp.getStartDate());
            st.add(Calendar.MONTH, 6*i);
            et.add(Calendar.MONTH, 6*(i+1));
            f.setStartDate(st.getTime());
            f.setEndDate(et.getTime());
            f.setPayEnd(true);
            fltLeg.insertCashflow(f);
        }
        swp.insertAsset(fltLeg);


        return swp;
    }

    public List<String> getBookList() {
		return bookList;
	}
	public void setBookList(List<String> bookList) {
		this.bookList = bookList;
	}
	public List<String> getCustList() {
		return custList;
	}
	public void setCustList(List<String> custList) {
		this.custList = custList;
	}
	public List<String> getLocList() {
		return locList;
	}
	public void setLocList(List<String> locList) {
		this.locList = locList;
	}
}
