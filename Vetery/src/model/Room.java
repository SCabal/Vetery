package model;

public class Room
{
//atributes	
	private boolean availability;
	private int id;
//builder	
	public Room(int id) {
		availability=true;
		this.id=id;
	}
	
//getters
	public int getId() {
		return id;
	}
//setters
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public void setId(int id) {
		this.id = id;
	}
//methods	
	public boolean isAvailability() {
		return availability;
	}
	
}