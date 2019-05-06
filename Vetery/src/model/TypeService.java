package model;

public class TypeService {
//atributes
	private String typeService;
	private double cost;
//builder	
	public TypeService(String typeService, double cost) {
		this.typeService = typeService;
		this.cost = cost;
	}
//getters	
	public String getTypeService() {
		return typeService;
	}
	public double getCost() {
		return cost;
	}
//setters	
	public void setTypeService(String typeService) {
		this.typeService = typeService;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
}
