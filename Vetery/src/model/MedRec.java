package model;

import java.util.ArrayList;
import java.util.Date;

public class MedRec {
//constants
	public final static boolean OPEN = true;
	public final static boolean CLOSE = false;
//atributes	
	private boolean status;
	private Pet pet;
	private Client client;
	private Date enter;
	private ArrayList<String> symptoms;
	private String diagnose;

	private ArrayList<Drug> drugAplicated;
	private Room room;
//builder
	public MedRec(Client aux, Pet pet, Date date, ArrayList<String> symptom, String diagnosis, ArrayList<Drug> drugs,
			Room openRoom) {
		
		status = OPEN;
		this.client = aux;
		this.pet = pet;
		enter = date;
		this.symptoms = symptom;
		this.diagnose = diagnosis;
		drugAplicated = drugs;
		room = openRoom;

	}

	@Override
	public String toString() {
		
		return client.getName() + " con ID:" + client.getId() + ". Mascota:" + pet.getName() + "(" + pet.getType()
				+ ").";
	}
//getters	
	public Pet getPet() {
		return pet;
	}
	public Client getClient() {
		return client;
	}

	public Date getEnter() {
		return enter;
	}
	public String getDiagnose() {
		return diagnose;
	}

	public ArrayList<String> getSymptoms() {
		return symptoms;
	}
	public ArrayList<Drug> getDrugAplicated() {
		return drugAplicated;
	}
	public Room getRoom() {
		return room;
	}
//setters
	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public void setSymptoms(ArrayList<String> symptoms) {
		this.symptoms = symptoms;
	}
	public void setClient(Client client) {
		this.client = client;
	}

	public void setEnter(Date enter) {
		this.enter = enter;
	}
	public void setDrugAplicated(ArrayList<Drug> drugAplicated) {
		this.drugAplicated = drugAplicated;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
//methods
	public double CostPetHosp() {
		
		Date today = new Date();
		int days = (int) ((today.getTime() - enter.getTime()) / 86400000);
		System.out.println("Hay " + days + " dias de diferencia");
		double costBas=costBase();
		return costBas*days;
	}

	private double costBase() {
		double costBas =0.0;
		if (pet.getType().equals(Pet.CAT)) {
			costBas = 10000;
			if (pet.getWeight() > 3 && pet.getWeight() <= 10) {
				costBas = 12000;
			} else if (pet.getWeight() > 10 && pet.getWeight() <= 20) {
				costBas = 15000;
			} else if (pet.getWeight() > 20) {
				costBas = 20000;
			}
		}
		else if (pet.getType().equals(Pet.DOG)) {
			costBas = 15000;
			if (pet.getWeight() > 3 && pet.getWeight() <= 10) {
				costBas = 17000;
			} else if (pet.getWeight() > 10 && pet.getWeight() <= 20) {
				costBas = 20000;
			} else if (pet.getWeight() > 20) {
				costBas = 25000;
			}
		}
		else if (pet.getType().equals(Pet.BIRD)) {
			costBas = 10000;
			if (pet.getWeight() > 3 && pet.getWeight() <= 10) {
				costBas = 12000;
			} else if (pet.getWeight() > 10 && pet.getWeight() <= 20) {
				costBas = 20000;
			} else if (pet.getWeight() > 20) {
				costBas = 25000;
			}
		}else {
			costBas = 10000;
			if (pet.getWeight() > 3 && pet.getWeight() <= 10) {
				costBas = 17000;
			} else if (pet.getWeight() > 10 && pet.getWeight() <= 20) {
				costBas = 30000;
			} else if (pet.getWeight() > 20) {
				costBas = 33000;
			}
		}
		return costBas;
	}

	public double CostTotal() {
		return CostPetHosp() + CostDrug();
	}

	private double CostDrug() {
		double count=0;
		Date today = new Date();
		int days = (int) ((today.getTime() - enter.getTime()) / 86400000);
		for (int i = 0; i < drugAplicated.size(); i++) {
			double cost=drugAplicated.get(i).getCostPerDose()*(double)Math.floor(days/(drugAplicated.get(i).getFrcuanceAdmin()));
			count+=cost;
		}
		return count;
	}

	public void addDrug(Drug drug) {
		drugAplicated.add(drug);
		
	}

	public void addNoteDiagnostic(String noteDiagnostic) {
		if(noteDiagnostic!=null&&!noteDiagnostic.isEmpty()) {
			diagnose+="\nAdd Note: "+noteDiagnostic;
		}
		
	}

	public void addSymtom(String symptom) {
		if(symptom!=null&&!symptom.isEmpty()) {
			symptoms.add(symptom);
		}
		
	}

}
