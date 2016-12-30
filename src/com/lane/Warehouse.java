package com.lane;
//仓库
public class Warehouse {
	private static int CAPACITY=11;//容量
	private Product[] products;//产品数组
	
	//[front,end] 容量
	private int front=0;
	private int end=0;
	
	public Warehouse(){
		this.products=new Product[CAPACITY];
	}
	
	public Warehouse(int capacity){
		this();
		if(capacity>0){
			CAPACITY=capacity+1;
			this.products=new Product[CAPACITY];
		}
	}
	
	//获得产品
	public Product getProduct() throws InterruptedException{
		synchronized(this){
			boolean consumerRunning=true;//消费者线程
			Thread currentThread=Thread.currentThread();//当前线程
			if(currentThread instanceof Consumer){
				consumerRunning=((Consumer)currentThread).isRunning();//获取当前拿产品线程的运行状态
			}else{
				return null;
			}
			
			//如果当前front==end则代表仓库为空且消费线程状态仍为true
			while((front==end)&&consumerRunning){
				wait();//使当前线程处于wait状态，放弃对象锁，进入等待此对象的等待锁定池
				consumerRunning=((Consumer)currentThread).isRunning();
			}
			
			//如果当前消费者线程已经没有活动状态
			if(!consumerRunning){
				return null;
			}
			
			//获得产品
			Product product=products[front];
			System.out.println("Consumer["+currentThread.getName()+"] getProduct:"+product);
			//重新计算还有多少产品
			front=(front+1+CAPACITY)%CAPACITY;
			System.out.println("仓库中还有:"+((end-front)+CAPACITY)%CAPACITY);
			notify();//唤醒等待锁定池中的生产者线程
			return product;
			}
		}
	
	//存储产品
	public void storageProduct(Product product)throws InterruptedException{
		synchronized(this){//当前对象
			boolean producterRunning=true;
			Thread currentThread=Thread.currentThread();
			if(currentThread instanceof Producter){
				producterRunning=((Producter)currentThread).isRunning();
			}else{
				return;
			}
			
			//当仓库已满并且进入该方法的生产者线程仍处于活动状态
			while(((end+1)%CAPACITY==front)&&producterRunning){
				wait();
				producterRunning=((Producter)currentThread).isRunning();
			}
			
			if(!producterRunning){
				return;
			}
			
			//储存产品
			products[end]=product;
			System.out.println("Producter["+currentThread.getName()+"] storageProduct:"+product);
			end=(end+1)%CAPACITY;
			System.out.println("当前仓库还有:"+(end-front+CAPACITY)%CAPACITY);
			notify();//唤醒在等待锁定池中的消费者线程
		}
	}
		
	}
