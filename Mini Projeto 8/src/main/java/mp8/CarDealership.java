// Classe para representar o fabricante de automóveis e checar a idade de cada automóvel

// Pacote
package mp8;

// Classe
public class CarDealership {
	public static void order(Car car) {
		if(Integer.parseInt(car.getYear().substring(0, 4)) > 1981 )
			System.out.println("Este carro foi produzido antes de 1981 - " + car);
		else
			System.out.println("Este carro foi produzido depois de 1981 - " + car);
	}
}
