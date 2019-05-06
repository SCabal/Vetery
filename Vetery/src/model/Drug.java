package model;

public class Drug
{
//atributes
	private String name;
	private String dose;
	private double costPerDose;
	private int frcuanceAdminforDay;
	
	
//builder	
	public Drug(String name, String dose, double costPerDose, int frcuanceAdmin) {
		
		this.name = name;
		this.dose = dose;
		this.costPerDose = costPerDose;
		this.frcuanceAdminforDay = frcuanceAdmin;
	}
//getters	
	public String getName() {
		return name;
	}
	public String getDose() {
		return dose;
	}	
	public double getCostPerDose() {
		return costPerDose;
	}
	public int getFrcuanceAdmin() {
		return frcuanceAdminforDay;
	}
//setters	
	public void setName(String name) {
		this.name = name;
	}
	public void setDose(String dose) {
		this.dose = dose;
	}
	public void setCostPerDose(double costPerDose) {
		this.costPerDose = costPerDose;
	}
	public void setFrcuanceAdmin(int frcuanceAdmin) {
		this.frcuanceAdminforDay = frcuanceAdmin;
	}		
}