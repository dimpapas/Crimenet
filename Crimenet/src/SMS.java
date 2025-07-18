
public class SMS extends Communication {
	
	private String content;
	
	public SMS(String number1, String number2, int day, int month, int year, String content) {
		super(number1, number2, day, month, year);
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	@Override
	public void printInfo() {
		System.out.println("This SMS has the following info");
		System.out.println("Between " + getNumber1() + " --- " + getNumber2());
		System.out.println("on " + getYear() + "/" + getMonth() + "/" + getDay());
		System.out.println("Text: " + getContent());
	}
}
