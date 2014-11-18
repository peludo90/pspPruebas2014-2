/*
 * Autor: Obed Andres Gonzalez Vélez
 * 
 * 
 * Fecha de inicio de codificación: 16 de noviembre de 2014
 * 
 * Descripción: Programa que calcule una regresión lineal a partir de unos valor (x,y) dados 
 * 				y despues pueda generar un valor Y para un valor X ingresado
 */

package principal;

import java.util.Scanner;
import java.util.StringTokenizer;

import entity.LinkedList;
import entity.Node;

public class Main {

	public static double betaOne = 0.0;
	public static double betaZero = 0.0;
	public static double valueR = 0.0;

	private static Scanner scanner;

	public static void main(String[] args) {

		scanner = new Scanner(System.in);
		LinkedList list = new LinkedList();
		String line = null;

		do {
			System.out.println("Menú principal.\n" + "\"fin\" para terminar.\n"
					+ "\"ingresar\" para generar regresión lineal.\n"
					+ "\"limpiar\" para borrar datos ingresados.\n"
					+ "\"calcular\" para estimar valor a partir de un X:\n");
			line = scanner.next();

			switch (line) {
			case "fin":
				System.out.println("Gracias por usar este programa =D\n");
				System.exit(0);
				break;
			case "calcular":
				if (!list.isEmpty()) {
					System.out
							.println("Ingresar valor X o \"listo\" para terminar de calcular:\n");
					String numberX = "";
					do {
						numberX = scanner.next();
						if (isDouble(numberX)) {

							System.out
									.println("(x,y) = ( "
											+ numberX
											+ " , "
											+ stimateValueY(Double
													.parseDouble(numberX))
											+ " )\n");
						} else if (!numberX.equalsIgnoreCase("listo\n")) {
							System.out.println("No válido\n");
						}
					} while (!numberX.equalsIgnoreCase("listo"));
					break;

				} else {
					System.out
							.println("No hay números, ingreselos por favor\n");
				}
				break;
			case "ingresar":
				System.out
						.println("Ingresar par de números x,y o \"listo\" para terminar de ingresar:\n");
				line = "";

				do {
					line = scanner.next();
					StringTokenizer tokenizer = new StringTokenizer(line, ",");

					if (tokenizer.countTokens() == 2) {
						String tokenX = tokenizer.nextToken();
						String tokenY = tokenizer.nextToken();

						if (isDouble(tokenX) && isDouble(tokenY)) {
							list.addNode(Double.parseDouble(tokenX),
									Double.parseDouble(tokenY));
							System.out.println("Nuevo par agregado");
						}

					} else if (!line.equalsIgnoreCase("listo")) {
						System.out.println("# de parámetros incorrectos\n");
					}

				} while (!line.equalsIgnoreCase("listo"));

				mostrarLista(list);

				if (!list.isEmpty()) {
					calculateBetas(list);
				} else {
					System.out.println("Lista vacía\n");
				}
				break;
			case "limpiar":
				list = new LinkedList();
				betaOne = betaZero = valueR = 0.0;
				System.out.println("Listado de números borrado\n");
				break;
			default:
				System.out.println("Instrucción no reconocida\n");
				break;
			}

		} while (line != null);
	}

	public static boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static void calculateBetas(LinkedList list) {
		double xValue = 0.0;
		double yValue = 0.0;
		int n = list.size();

		Node iterator = list.iterator();
		double xSummation = 0.0;
		double ySummation = 0.0;
		double xSummationPow = 0.0;
		double ySummationPow = 0.0;
		double xyMultSummation = 0.0;
		double xAvg = 0.0;
		double yAvg = 0.0;

		while (iterator != null) {
			xValue = iterator.getValueX();
			yValue = iterator.getValueY();

			xSummation += xValue;
			ySummation += yValue;
			xSummationPow += Math.pow(xValue, 2);
			ySummationPow += Math.pow(yValue, 2);
			xyMultSummation += xValue * yValue;

			iterator = iterator.getNext();

			System.out.println("xSummation: " + xSummation);
			System.out.println("ySummation: " + ySummation);
			System.out.println("xSummationPow: " + xSummationPow);
			System.out.println("ySummationPow: " + ySummationPow);
			System.out.println("xyMultSummation: " + xyMultSummation);

		}

		xAvg = xSummation / n;
		yAvg = ySummation / n;

		System.out.println("xAvg: " + xAvg);
		System.out.println("yAvg: " + yAvg);

		betaOne = stimateBetaOne(xyMultSummation, xAvg, yAvg, xSummationPow, n);
		betaZero = stimateBetaZero(xAvg, yAvg, betaOne);
		valueR = stimateValueR(n, xyMultSummation, xSummation, ySummation,
				xSummationPow, ySummationPow);

		System.out.println("Valor B1 = " + betaOne + "\n");
		System.out.println("Valor B0 = " + betaZero + "\n");
		System.out.println("Valor R(x,y) = " + valueR + "\n");
		System.out.println("Valor R² = " + Math.pow(valueR, 2) + "\n");

	}

	public static double stimateBetaOne(double xyMultSummation, double xAvg,
			double yAvg, double xSummationPow, int n) {
		return (xyMultSummation - (n * xAvg * yAvg))
				/ (xSummationPow - (n * Math.pow(xAvg, 2)));
	}

	public static double stimateBetaZero(double xAvg, double yAvg,
			double betaOne) {
		return yAvg - (betaOne * xAvg);
	}

	public static double stimateValueR(int n, double xyMultSummation,
			double xSummation, double ySummation, double xSummationPow,
			double ySummationPow) {
		return (n * (xyMultSummation) - (xSummation * ySummation))
				/ Math.sqrt((n * xSummationPow - Math.pow(xSummation, 2))
						* (n * ySummationPow - Math.pow(ySummation, 2)));
	}

	public static double stimateValueY(double valueX) {
		return betaZero + (betaOne * valueX);
	}

	public static void mostrarLista(LinkedList list) {
		Node iterator = list.iterator();

		while (iterator != null) {
			System.out.println("(" + iterator.getValueX() + ","
					+ iterator.getValueY() + ")");
			iterator = iterator.getNext();
		}
	}

}
