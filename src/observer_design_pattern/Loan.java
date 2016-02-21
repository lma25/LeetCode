package observer_design_pattern;

import java.util.ArrayList;

public class Loan implements Subject{
	private ArrayList<Observer> observers = new ArrayList<>();
	private String type;
	private float interest;
	private String bank;
	
	public Loan(String type, float interest, String bank){
		this.type = type;
		this.interest = interest;
		this.bank = bank;
	}

	public float getInterest() {
		return interest;
	}

	public void setInterest(float interest) {
		this.interest = interest;
		notifyObservers();
	}

	public String getType() {
		return type;
	}

	public String getBank() {
		return bank;
	}
	
	@Override
	public void registerObserver(Observer observer){
		observers.add(observer);
	}
	
	@Override
	public void removeObserver(Observer observer){
		observers.remove(observer);
	}
	
	@Override
	public void notifyObservers(){
		for(Observer observer : observers){
			observer.update(this.interest);
		}
	}
}
