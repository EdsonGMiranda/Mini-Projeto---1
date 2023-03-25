// Treinamento do modelo

package app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import javax.swing.*;
import java.io.PrintWriter;

class DataSet {
	public List<double[]> trainData = new ArrayList<>();
	public double maxMilage = 0.0;
	public double minMilage = 0.0;
	public double maxPrice = 0.0;
	public double minPrice = 0.0;
	public double theta0 = 0.0;
	public double theta1 = 0.0;
	public int iterations = 0;
	public int precision = 0;

	public DataSet(String fileName[]) {
		readCsv(fileName);
	}

	private void readCsv(String fileName[]) {
		Scanner scanner;
		String line;
		String values[];
		double	data[];

		if (fileName.length == 0) {
			System.out.println("Erro: arquivo de entrada não encontrado.");
			System.exit(1);
		}

		try {
			scanner = new Scanner(new File(fileName[0]));
			line = scanner.nextLine();
			values = line.split(",");
			if (!(values.length == 2 && values[0].equals("km") && values[1].equals("gastomensal"))) {
				System.out.println("A primeira linha do arquivo está inválida. Esperado: \"km,gastomensal\"");
				System.exit(1);
			}

			while (scanner.hasNext()) {
				line = scanner.nextLine();
				values =  line.split(",");
				if (values.length != 2) {
					System.out.println("A primeira linha do arquivo está inválida. Esperado\"int, int\"");
					System.exit(1);
				}

				data = new double[2];
				data[0] = Double.parseDouble(values[0]);
				data[1] = Double.parseDouble(values[1]);
				trainData.add(data);

				if (trainData.size() == 1) {
					maxMilage = data[0];
					minMilage = data[0];
					maxPrice = data[1];
					minPrice = data[1];
				} else {
					maxMilage = (maxMilage < data[0]) ? data[0] : maxMilage;
					minMilage = (minMilage > data[0]) ? data[0] : minMilage;
					maxPrice = maxPrice < data[1] ? data[1] : maxPrice;
					minPrice = minPrice > data[1] ? data[1] : minPrice;
				}
			}

			scanner.close();
		} catch (Exception e) {
			System.out.println("Erro ao ler o dataset " + e.getMessage());
			System.exit(1);
		}
	}

	public void scale() {

		double data[];

		for (int i = 0; i < trainData.size(); i++) {
			data = trainData.get(i);
			data[0] = data[0] / 1000;
			data[1] = data[1] / 1000;
			trainData.set(i, data);
		}
		maxMilage /= 1000;
		maxPrice /= 1000;
		minMilage /= 1000;
		minPrice /= 1000;
	}

	public void printData() {
		for (int i = 0; i < trainData.size(); i++) {
			System.out.printf("%f : %f\n", trainData.get(i)[0], trainData.get(i)[1]);
		}
	}

	public void writeTheta() {
			PrintWriter writer;

			try {
				writer = new PrintWriter("modelo.txt");
				writer.println(String.format(Locale.US, "%f,%f", theta0, theta1));
				writer.close();
			} catch (Exception e) {
				System.out.println("Erro ao salvar o modelo " + e.getMessage());
				System.exit(1);
			}
		}
}

class TreinaModelo {

	static JFrame frame;

	static void plot(DataSet dataSet) {
		if (frame == null) {
			frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Plot plot = new Plot(dataSet);
			frame.add(plot);
			frame.setSize(800, 600);
			frame.setLocation(200, 200);
			frame.setVisible(true);
		} else {
			SwingUtilities.updateComponentTreeUI(frame);
		}

		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			System.out.println("Erro no plot " + e.getMessage());
			System.exit(1);
		}
	}

	static double estimatePrice(int i, DataSet dataSet) {
		return dataSet.theta0 + dataSet.trainData.get(i)[0] * dataSet.theta1;
	}

	public static int calculateError(DataSet dataSet) {
		double error = 0;

		for (int i = 0; i < dataSet.trainData.size(); i++) {
			error += Math.abs((dataSet.trainData.get(i)[1] - estimatePrice(i, dataSet)) / dataSet.trainData.get(i)[1]);
		}
		error /= dataSet.trainData.size();
		return (int)(error * 100);
	}

	public static void main(String args[]) {

		DataSet dataSet = new DataSet(args);
		dataSet.scale();
		double learningRate = 0.0001;
		int iterations = 300000;
		double summ1 = 0.0;
		double summ2 = 0.0;
		plot(dataSet);

		for (int j = 0; j <= iterations; j++) {
			if (j % 1000 == 0 || j == 0 || j == iterations - 1) {
				dataSet.iterations = j;
				plot(dataSet);
			}
			summ1 = 0;
			summ2 = 0;
			for (int i = 0; i < dataSet.trainData.size(); i++) {
				double ePrice = estimatePrice(i, dataSet);
				summ1 += (ePrice - dataSet.trainData.get(i)[1]);
				summ2 += (ePrice - dataSet.trainData.get(i)[1]) * dataSet.trainData.get(i)[0];
			}
			
			summ1 = learningRate * (summ1 / dataSet.trainData.size());
			summ2 = learningRate * (summ2 / dataSet.trainData.size());
			dataSet.theta0 -= summ1;
			dataSet.theta1 -= summ2;
			dataSet.precision = 100 - calculateError(dataSet);
		}
		dataSet.writeTheta();
	}
}
