package com.lane;

//生产者/工厂
public class Producter extends Thread{
	private Warehouse warehouse;//仓库
	private static int productName=0;//产品数量
	private boolean running=false;//是否运行状态
	
	public Producter(Warehouse warehouse,String name){
		super(name);
		this.warehouse=warehouse;
	}
	
	public void start(){
		this.running=true;
		super.start();
	}
	
	public void run(){
		Product product;
		try{
			while(running){
				product=new Product((++productName)+"");
				this.warehouse.storageProduct(product);
				sleep(300);//每300毫秒生产一个商品
			}
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
	}
	
	public void stopProducter(){
		synchronized(warehouse){
			this.running=false;
			warehouse.notifyAll();//使某个消费者线程从阻塞队列获得监听器锁
		}
	}
	
	public boolean isRunning() {
		return running;//判断是否处于运行状态
	}
	
	
}
