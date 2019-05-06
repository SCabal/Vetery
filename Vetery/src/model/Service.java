package model;

import java.util.Date;

public class Service {
//atributes	
	private TypeService typeSetService;
	private Date dateReali;
	private Client client;
	private Pet pet;
//builder	
	public Service(TypeService typeSetService, Date dateReali, Client client, Pet pet) {
		this.typeSetService = typeSetService;
		this.dateReali = dateReali;
		this.client = client;
		this.pet = pet;
	}
//getters	
	public TypeService getTypeSetService() {
		return typeSetService;
	}
	public Date getDateReali() {
		return dateReali;
	}
	public Client getClient() {
		return client;
	}
	public Pet getPet() {
		return pet;
	}
//setters	
	public void setTypeSetService(TypeService typeSetService) {
		this.typeSetService = typeSetService;
	}
	public void setDateReali(Date dateReali) {
		this.dateReali = dateReali;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public void setPet(Pet pet) {
		this.pet = pet;
	}	
}
