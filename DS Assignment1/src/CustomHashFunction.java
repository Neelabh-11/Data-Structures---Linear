
public class CustomHashFunction {

	public int generateHash1(String x) {
		int primeNumber = 31;
		String c = "neelabh";
		int a = 1;
		int hash = x.hashCode();
		for(int i = 0; i < c.length(); i++) {
			a = (primeNumber * a) + c.charAt(i) + hash;
		}
		return a;
	}

	public int generateHash2(String x) {
		int primeNumber = 17;
		int a = 1;
		String c = "abhishek";
		int hash = x.hashCode();
		for(int i = 0; i < c.length(); i++) {
			a = (primeNumber * a) + c.charAt(i) + hash;
		}
		return a;
	}

}
