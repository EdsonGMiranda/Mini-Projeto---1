// Plot do gráfico de treinamento

package app;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

public class Plot extends JPanel{

	DataSet trainSet;
	int height;
	int width;
	int marg = 20;
	double scaleY;
	double scaleX;

	public Plot(DataSet trainSet) {
		this.trainSet = trainSet;
	}

	private void drawAxis(Graphics2D graph) {
		graph.draw(new Line2D.Double(marg, marg, marg, height));
		graph.draw(new Line2D.Double(0, height - marg, width - marg, height - marg));
		graph.drawString("Gasto Mensal com Combustível (1000$)", 5 ,15);
		graph.drawString("Kilometragem (1000km)",  width - marg * 6, height - marg - 7);
		graph.drawString(String.format("Iterações: %d", trainSet.iterations), 400, 20);
		graph.drawString(String.format("Precisão Média: %d%%", trainSet.precision), 600, 20);
		graph.draw(new Line2D.Double(marg, marg, marg + 3, marg + 10));
		graph.draw(new Line2D.Double(marg, marg, marg - 3, marg + 10));
		graph.draw(new Line2D.Double(width - marg, height - marg, width - marg - 10, height - marg - 3));
		graph.draw(new Line2D.Double(width - marg, height - marg, width - marg - 10, height - marg + 3));
		
		for (int i = 0; i < 240; i = i + 10) {
			graph.draw(new Line2D.Double(marg + i * scaleX, height - marg - 2, marg + i * scaleX, height - marg + 2));
			graph.drawString(String.format("%d", i), (int)(marg / 2 + i * scaleX), height - 5);
		}

		for (int i = 1; i < 9; i++) {
			graph.draw(new Line2D.Double(marg - 2, height - marg - i * scaleY, marg + 2, height - marg - i * scaleY));
			graph.drawString(String.format("%d", i), marg - 10, (int)(height - marg + 4 - i * scaleY));
		}

	}

	private void drawPoints(Graphics2D graph) {
		
		graph.setPaint(Color.RED);
		
		for(int i = 0; i < trainSet.trainData.size(); i++){
			double x1 = marg + trainSet.trainData.get(i)[0] * scaleX;
			double y1 = height - marg - trainSet.trainData.get(i)[1] * scaleY;
			graph.fill(new Ellipse2D.Double(x1 - 2, y1 - 2, 4, 4));
		}
	}

	private void drawLine(Graphics2D graph) {
		
		graph.setPaint(Color.BLUE);
		double x1 = 0;
		double y1 = (trainSet.theta0 + x1 * trainSet.theta1);
		double x2 = trainSet.maxMilage;
		double y2 = (trainSet.theta0 + trainSet.theta1 * x2);
		graph.draw(new Line2D.Double(x1 * scaleX + marg, height - marg - y1 * scaleY, x2 * scaleX + marg, height - marg - y2 * scaleY));
	}

	protected void paintComponent(Graphics grf){
		super.paintComponent(grf);
		Graphics2D graph = (Graphics2D)grf;
		graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		width = getWidth();
		height = getHeight();
		scaleY = (double)(height - 2 * marg) / trainSet.maxPrice;
		scaleX = (double)(width - 2 * marg) / trainSet.maxMilage;

		drawAxis(graph);
		drawPoints(graph);
		drawLine(graph);
	}
}
