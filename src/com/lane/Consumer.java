package com.lane;

//消费者
public class Consumer extends Thread{
	
	private boolean running=false;
	private Warehouse warehouse;
	
	public Consumer(Warehouse warehouse,String name){
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
				product=warehouse.getProduct();
				sleep(500);//在睡眠的过程中并没有失去监听器锁
			}
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
	}
	
	public void stopConsumer(){
		synchronized(warehouse){
			this.running=false;
			warehouse.notifyAll();
		}
		}
	
	public boolean isRunning() {
		return this.running;
	}
	
}
