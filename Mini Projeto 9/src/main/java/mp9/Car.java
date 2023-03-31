// Classe para representar o schema de um automóvel

// Pacote
package mp9;

// Classe
public class Car {

	// Atributos
	private double mpg;
	private int cylinders;
	private double displacement;
	private double horsepower;
	private int weight;
	private double acceleration;
	private int modelYear;
	private String origin;
	private String name;

	// Métodos

	// Método para validar e converter cada linha
	public static Car String2Car(String line) {
		Car car = null;

		String[] fields = line.split(",");
		if (fields.length != Car.class.getDeclaredFields().length)
			System.err.println("Linha com erro " + line);
		else
			try {
				double mpg = Double.valueOf(fields[0]);
				int cylinders = Integer.valueOf(fields[1]);
				double displacement = Double.valueOf(fields[2]);
				double horsepower = Double.valueOf(fields[3]);
				int weight = Integer.valueOf(fields[4]);
				double acceleration = Double.valueOf(fields[5]);
				int modelYear = Integer.valueOf(fields[6]);
				String origin = fields[7].trim();
				String name = fields[8].trim();
				car = new Car(mpg, cylinders, displacement, horsepower, weight, acceleration, modelYear, origin, name);
			} catch (NumberFormatException ex) {
				System.err.println("Erro ao converter a linha: " + line);
			}

		return car;
	}

	// Construtor
	public Car() {
	}

	/**
	 *
	 * @param acceleration
	 * @param horsepower
	 * @param mpg
	 * @param origin
	 * @param name
	 * @param weight
	 * @param displacement
	 * @param modelYear
	 * @param cylinders
	 */
	public Car(double mpg, int cylinders, double displacement, double horsepower, int weight, double acceleration,
			int modelYear, String origin, String name) {
		super();
		this.mpg = mpg;
		this.cylinders = cylinders;
		this.displacement = displacement;
		this.horsepower = horsepower;
		this.weight = weight;
		this.acceleration = acceleration;
		this.modelYear = modelYear;
		this.origin = origin;
		this.name = name;
	}

	public double getMpg() {
		return mpg;
	}

	public void setMpg(double mpg) {
		this.mpg = mpg;
	}

	public int getCylinders() {
		return cylinders;
	}

	public void setCylinders(int cylinders) {
		this.cylinders = cylinders;
	}

	public double getDisplacement() {
		return displacement;
	}

	public void setDisplacement(double displacement) {
		this.displacement = displacement;
	}

	public double getHorsepower() {
		return horsepower;
	}

	public void setHorsepower(double horsepower) {
		this.horsepower = horsepower;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public double getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

	public int getModelYear() {
		return modelYear;
	}

	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Car.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
		sb.append("mpg");
		sb.append('=');
		sb.append(this.mpg);
		sb.append(',');
		sb.append("cylinders");
		sb.append('=');
		sb.append(this.cylinders);
		sb.append(',');
		sb.append("displacement");
		sb.append('=');
		sb.append(this.displacement);
		sb.append(',');
		sb.append("horsepower");
		sb.append('=');
		sb.append(this.horsepower);
		sb.append(',');
		sb.append("weight");
		sb.append('=');
		sb.append(this.weight);
		sb.append(',');
		sb.append("acceleration");
		sb.append('=');
		sb.append(this.acceleration);
		sb.append(',');
		sb.append("modelYear");
		sb.append('=');
		sb.append(this.modelYear);
		sb.append(',');
		sb.append("origin");
		sb.append('=');
		sb.append(((this.origin == null) ? "<null>" : this.origin));
		sb.append(',');
		sb.append("name");
		sb.append('=');
		sb.append(((this.name == null) ? "<null>" : this.name));
		sb.append(',');
		if (sb.charAt((sb.length() - 1)) == ',') {
			sb.setCharAt((sb.length() - 1), ']');
		} else {
			sb.append(']');
		}
		return sb.toString();
	}
}
