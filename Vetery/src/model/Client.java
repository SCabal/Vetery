package model;
import java.util.ArrayList;

public class Client
{
//atributes
	private String name ;
	private String id;
	private String address;
	private String tel;
	
	private ArrayList<Pet> pets;
//setters
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setPets(ArrayList<Pet> pets) {
		this.pets = pets;
	}

	public void setId(String id) {
		this.id = id;
	}
//getters

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getTel() {
		return tel;
	}


	public ArrayList<Pet> getPets() {
		return pets;
	}

	public String getId() {
		return id;
	}
	public Pet getPetPos(int index) {
		if(index>(pets.size()-1)) {
			return null;
		}
		return pets.get(index);
	}
	
//builder
	public Client(String name, String id, String address, String tel, ArrayList<Pet> pets) {
		
		this.name = name;
		this.id = id;
		this.address = address;
		this.tel = tel;
		this.pets = pets;
	}

//methods
	public Pet findPetByName(String namePet) {
		for (int i = 0; i < pets.size(); i++) {
			if(pets.get(i).getName().equals(namePet)) {
				return pets.get(i);
			}
		}
		return null;
	}

	public void addPet(Pet newPet) {
		pets.add(newPet);
		
	}
	
	
}
