package com.lane;

//产品
public class Product {
	private String name;
	public Product(String name){
		this.name=name;
	}
	
	public String toString(){
		return "product-"+name;
	}
}
