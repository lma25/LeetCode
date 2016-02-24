package designpattern.oberver;

import java.util.ArrayList;

public class Stock implements Subject{
	private ArrayList<Observer> observers;
	private float price;
	
	public Stock(float price){
		this.observers = new ArrayList<>();
		this.setPrice(price);
	}
	
	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for(Observer observer : observers){
			observer.update(price);
		}
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
		notifyObservers();
	}
	public static void main(String[] args){
		User a = new User("Li");
		User b = new User("Xue");
		Stock sk = new Stock((float) 1.123);
		sk.registerObserver(a);
		sk.registerObserver(b);
		sk.setPrice(2);
		sk.setPrice(5);
	}
}
