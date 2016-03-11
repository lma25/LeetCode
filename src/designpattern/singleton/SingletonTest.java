package designpattern.singleton;


class SingletonTest {
	 
	 public static void main(String[] args) throws Exception{
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		s1.say();
	}
}
