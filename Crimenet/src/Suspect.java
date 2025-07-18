import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Suspect {

	private String name;
	private String codeName;
	private String town;
	private ArrayList<String> phoneList;
	private ArrayList<Suspect> partners;
	
	//Constructor with parameters
	public Suspect(String name, String codeName, String town){
		this.name = name;
		this.codeName = codeName;
		this.town = town;
		this.phoneList = new ArrayList<String>();
		this.partners = new ArrayList<Suspect>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public ArrayList<String> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(ArrayList<String> phoneList) {
		this.phoneList = phoneList;
	}
	
	public ArrayList<Suspect> getPartners() {
		return partners;
	}

	public void setPartners(ArrayList<Suspect> partners) {
		this.partners = partners;
	}

	//public method to add a phone number to the suspect's phone list
	public void addNumber(String number) {
		phoneList.add(number);
	}
	
	public void addPartner(Suspect aSuspect) {
		//check if the partner is already in the list
		if(!partners.contains(aSuspect)) {
			partners.add(aSuspect);
		}
	}
	
	public boolean  isConnectedTo(Suspect aSuspect) {
		return partners.contains(aSuspect);
	}
	
	public ArrayList<Suspect> getCommonPartners(Suspect aSuspect) {
		//Create an empty ArrayList to store common partners
		ArrayList<Suspect> common = new ArrayList<Suspect>();
		
		//Iterate through each partner in the current suspect's "partners" list
		for(int i=0; i<partners.size(); i++) {
			//Get the partner from the partner's list of the current suspect
			Suspect partner = partners.get(i);
			
			if(aSuspect.partners.contains(partner)) {
				common.add(partner);
			}
		}
		return common;
	}
	
	public void printPartners() {
		for(int i=0; i<partners.size(); i++) {
			Suspect partner = partners.get(i);
			System.out.println(partner.getName() + " " +partner.getCodeName());
		}
	}

	public List<Suspect> getSuggestedSuspects() {
        Set<Suspect> suggestedSuspects = new HashSet<>();  // Using HashSet to avoid duplicates

        // Loop through each of this suspect's partners
        for (Suspect partner : partners) {
            // Loop through each of this partner's partners
            for (Suspect partnerOfPartner : partner.getPartners()) {
                // Suggest this partner's partner if they are not already a direct partner of this suspect
                if (!this.partners.contains(partnerOfPartner) && !partnerOfPartner.equals(this)) {
                    suggestedSuspects.add(partnerOfPartner); // Add to suggested list
                }
            }
        }

        // Convert the set back to a list (if required by the rest of your code)
        return new ArrayList<>(suggestedSuspects);
    }
}

