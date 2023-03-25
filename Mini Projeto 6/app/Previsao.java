// Previsão com o modelo treinado

package app;

import java.io.File;
import java.util.Scanner;

public class Previsao {

	public static void main(String args[]) {

		Scanner scanner = null;
		double milage = 0;
		double theta0 = 0;
		double theta1 = 0;
		int scale = 1000;

		try {
			scanner = new Scanner(new File("modelo.txt"));
			String line = scanner.nextLine();
			theta0 = Double.parseDouble(line.split(",")[0]);
			theta1 = Double.parseDouble(line.split(",")[1]);
		} catch (Exception e) {
			theta0 = 0;
			theta1 = 0;
		}

		if (scanner != null)
			scanner.close();

		scanner = new Scanner(System.in);
		System.out.print("Bem-Vindo(a) ao Modelo de Machine Learning em Java\n");
		System.out.print("Digite a Kilometragem: ");
		
		try {
			milage = Double.parseDouble(scanner.nextLine());
		} catch (Exception e) {
			System.out.println("Valor Inválido");
			System.exit(1);
		}
		
		System.out.printf("Previsão de Gasto Mensal com Combustível: $%d\n", (int)((theta0 + theta1 * milage / scale) * scale));
	}
}
