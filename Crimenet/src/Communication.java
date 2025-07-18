
public abstract class Communication {
	
	private String number1;
	private String number2;
	private int day;
	private int month;
	private int year;
	
	public Communication(String number1, String number2, int day, int month, int year) {
		this.number1 = number1;
		this.number2 = number2;
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public String getNumber1() {
		return number1;
	}

	public void setNumber1(String number1) {
		this.number1 = number1;
	}

	public String getNumber2() {
		return number2;
	}

	public void setNumber2(String number2) {
		this.number2 = number2;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public abstract void printInfo();
}
