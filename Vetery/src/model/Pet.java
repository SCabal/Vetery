package model;

public class Pet {
//constants
	public static final String BIRD = "bird";
	public static final String DOG = "dog";
	public static final String CAT = "cat";
	public static final String OTHER = "other";
//atributes
	private String name;
	private String type;
	private int age;
	private double weight;
	private double height;
//builder
	public Pet(String name, String type, int age, double weight, double height) {
		this.name = name;
		this.type = type;
		this.age = age;
		this.weight = weight;
		this.height = height;
	}
//getters	
	public String getName() {
		return name;
	}

	public double getHeight() {
		return height;
	}

	public String getType() {
		return type;
	}

	public int getAge() {
		return age;
	}

	public double getWeight() {
		return weight;
	}
//setters
	public void setName(String name) {
		this.name = name;
	}


	public void setType(String type) {
		this.type = type;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}


	public void setHeight(double height) {
		this.height = height;
	}
//methods
	/**
	 * Description This method allows to calculate the body mass index for a pet.
	 * pre: The pet was created before and its attributes height and weight are not
	 * null neither height must be zero.
	 * post: The BMI is calculated.
	 * @return The pet body mass index. Returns -1 if the height is zero due to the
	 *         division on zero does not exist.
	 */
	public double CalculateIMC() {
		if(height==0) {
			return -1;
		}
		return (weight)/Math.pow(height, 2);
	}

}
