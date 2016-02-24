package designpattern.oberver;

public class User implements Observer{
	public String userName;
	
	public User(String userName){
		this.userName = userName;
	}
	
	@Override
	public void update(float price){
		System.out.println(userName + " : " + price);
	}
}
