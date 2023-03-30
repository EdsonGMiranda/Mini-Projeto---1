// Classe para representar o schema de um automóvel

// Pacote
package mp8;

// Classe
public class Car {
    
    // Atributos
    
    private String Name;
    private Double Miles_per_Gallon;
    private Long Cylinders;
    private Double Displacement;
    private Long Horsepower;
    private Long Weight_in_lbs;
    private Double Acceleration;
    private String Year;
    private String Origin;
	
	// Métodos

	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}
	
	public Double getMiles_per_Gallon() {
		return Miles_per_Gallon;
	}
	
	public void setMiles_per_Gallon(Double miles_per_Gallon) {
		Miles_per_Gallon = miles_per_Gallon;
	}
	
	public Long getCylinders() {
		return Cylinders;
	}
	
	public void setCylinders(Long cylinders) {
		Cylinders = cylinders;
	}
	
	public Double getDisplacement() {
		return Displacement;
	}
	
	public void setDisplacement(Double displacement) {
		Displacement = displacement;
	}
	
	public Long getHorsepower() {
		return Horsepower;
	}
	
	public void setHorsepower(Long horsepower) {
		Horsepower = horsepower;
	}
	
	public Long getWeight_in_lbs() {
		return Weight_in_lbs;
	}
	
	public void setWeight_in_lbs(Long weight_in_lbs) {
		Weight_in_lbs = weight_in_lbs;
	}
	
	public Double getAcceleration() {
		return Acceleration;
	}
	
	public void setAcceleration(Double acceleration) {
		Acceleration = acceleration;
	}
	
	public String getYear() {
		return Year;
	}
	
	public void setYear(String year) {
		Year = year;
	}
	
	public String getOrigin() {
		return Origin;
	}
	
	public void setOrigin(String origin) {
		Origin = origin;
	}
	
    @Override
    public String toString() {
        return "Car(Name=" + this.getName() + ", Miles_per_Gallon=" + this.getMiles_per_Gallon() + ", Cylinders=" + this.getCylinders() + ", Displacement=" + this.getDisplacement() + ", Horsepower=" + this.getHorsepower() + ", Weight_in_lbs=" + this.getWeight_in_lbs() + ", Acceleration=" + this.getAcceleration() + ", Year=" + this.getYear() + ", Origin=" + this.getOrigin() + ")";
    }

	public Double businessLogicMethod() {
		return Cylinders * Horsepower / Displacement;
	}
}
