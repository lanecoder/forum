package com.lane;

public class TestProduct {

	/**
	 * @param args
	 * 
	 * 测试
	 */
	public static void main(String[] args) {
		Warehouse warehouse=new Warehouse(10);
		Producter producter1=new Producter(warehouse,"producter-1");
		Producter producter2=new Producter(warehouse,"producter-2");
		Producter producter3=new Producter(warehouse,"producter-3");
		Consumer consumer1=new Consumer(warehouse,"consumer-1");
		Consumer consumer2=new Consumer(warehouse,"consumer-2");
		Consumer consumer3=new Consumer(warehouse,"consumer-3");
		Consumer consumer4=new Consumer(warehouse,"consumer-4");
		producter1.start();
		producter2.start();
		producter3.start();
		consumer1.start();
		consumer2.start();
		consumer3.start();
		consumer4.start();
		
		try{
			Thread.sleep(1600);
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
		
		producter1.stopProducter();
		consumer1.stopConsumer();
		producter2.stopProducter();
		consumer2.stopConsumer();
		producter3.stopProducter();
		consumer3.stopConsumer();	
		consumer4.stopConsumer();
	}

}
