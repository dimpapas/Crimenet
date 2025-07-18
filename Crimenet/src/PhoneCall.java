
public class PhoneCall extends Communication {
	
	private int duration;
	
	public PhoneCall(String number1, String number2, int day, int month, int year, int duration) {
		super(number1, number2, day, month, year);
		this.duration = duration;
	}

	public int getDuration() {
		return duration;
	}
	
	@Override
	public void printInfo() {
		System.out.println("This phone call has the following info");
		System.out.println("Between " + getNumber1() + " --- " + getNumber2());
		System.out.println("on " + getYear() + "/" + getMonth() + "/" + getDay());
		System.out.println("Duration: " + getDuration());
	}
}
