import java.util.ArrayList;

public class Registry {

	private ArrayList<Suspect> suspects;
	private ArrayList<Communication> communications;
	
	public Registry() {
		this.suspects = new ArrayList<Suspect>();
		this.communications = new ArrayList<Communication>();
	}
	
	public void addSuspect(Suspect aSuspect) {
		suspects.add(aSuspect);
	}
	
	public ArrayList<Suspect> getSuspects() {
		return suspects;
	}
	
	public void addCommunication(Communication aCommunication) {
		communications.add(aCommunication);
		for(int i=0; i<suspects.size(); i++) {
			Suspect suspect = suspects.get(i);
			
			if(suspect.getPhoneList().contains(aCommunication.getNumber1())) {
				for(int j=0; j<suspects.size(); j++) {
					Suspect other = suspects.get(j);
					
					if(other.getPhoneList().contains(aCommunication.getNumber2())) {
						suspect.addPartner(other);
						other.addPartner(suspect);
					}
				}
			}
		}
	}
	
	public ArrayList<Communication> getCommunications(){
		return communications;
	}
	
	public Suspect getSuspectWithMostPartners() {
		Suspect topSuspect = null;
		int maxPartners = 0;
		for(int i=0; i<suspects.size(); i++) {
			Suspect suspect = suspects.get(i);
			
			if(suspect.getPartners().size() > maxPartners) {
				maxPartners = suspect.getPartners().size();
				topSuspect = suspect;
			}
		}
		return topSuspect;
	}
	
	public PhoneCall getLongestPhoneCallBetween(String number1, String number2) {
		PhoneCall longestCall = null;
		int maxDuration = 0;
		for(int i=0; i<communications.size(); i++) {
			Communication comm = communications.get(i);
			
			if(comm instanceof PhoneCall) {
				PhoneCall call = (PhoneCall) comm;
				
				if((call.getNumber1().equals(number1) && call.getNumber2().equals(number2)) || (call.getNumber1().equals(number2) && call.getNumber2().equals(number1))) {
					if(call.getDuration() > maxDuration) {
						maxDuration = call.getDuration();
						longestCall = call;
					}
				}
			}
					
		}
		return longestCall;
	}
	
	public ArrayList<SMS> getMessagesBetween(String number1, String number2){
		ArrayList<SMS> message = new ArrayList<>();
		for(int i=0; i<communications.size(); i++) {
			Communication comm = communications.get(i);			
			if(comm instanceof SMS) {
				SMS sms = (SMS) comm;
				
				if((sms.getNumber1().equals(number1) && sms.getNumber2().equals(number2)) || (sms.getNumber1().equals(number2) && sms.getNumber2().equals(number1))) {
					String content = sms.getContent();
					if(content.contains("Bomb") || content.contains("Attack") ||content.contains("Explosives") ||content.contains("Gun")) {
						message.add(sms);
					}
				}
			}
		}
		return message;
	}
}
