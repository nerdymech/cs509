package GUI2;

public class testPassedArg {
	
	static String passedStB;
	
	public void printArgPassed(String stB, String stL, String enB, String enL) {
		System.out.println(stB + stL + enB + enL);
		passedStB = stB;
	}
	
	public static void main(String[] args) {
		System.out.println("check when I am called");
		
		testPassedArg tp = new testPassedArg();
		
		tp.printArgPassed(args[0], args[1], args[2], args[3]);
		
		//System.out.println(passedStB);
	}
}
