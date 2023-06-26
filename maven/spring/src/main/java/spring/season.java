package spring;

//설문조사 - Module
public class season {

	public int data(String postdata) {
		int total = 0;
		
		switch(postdata) {
		case "봄":
			total = 1200;
		break;
		
		case "여름":
			total = 1000;
		break;
		
		case "가을":
			total = 500;
		break;
		
		case "겨울":
			total = 900;
		break;
		}
			
		return total;
	}
	
	
}
