package designpattern.singleton;

public class Singleton {
	private static final Singleton instance = new Singleton();
	
	private Singleton(){
		System.out.println("Singleton object is being created...");
	}
	
	public static Singleton getInstance(){
		return instance;
	}
	public void say(){
		System.out.println("This a method of Singleton.");
	}
	
}
