package principal;

import java.util.Scanner;

import entity.LinkedList;
import entity.Node;

public class Main {

	private static Scanner scanner;

	public static void main(String[] args) {

		scanner = new Scanner(System.in);
		LinkedList list = new LinkedList();
		String line = null;

		do {
			System.out.println("Menú principal.\n" + "\"fin\" para terminar.\n"
					+ "\"ingresar\" para introducir valores.\n"
					+ "\"limpiar\" para borrar datos ingresados.\n"
					+ "\"calcular\" para obtener media y desviación estandar:");
			line = scanner.next();

			switch (line) {
			case "fin":
				System.out.println("Gracias por usar este programa =D");
				System.exit(0);
				break;
			case "calcular":
				if (!list.isEmpty()) {
					double mean = mean(list);
					System.out.println("Media de números ingresados: " + mean);
					System.out
							.println("Desviación estandar de números ingresados: "
									+ deviationStd(list, mean));

				} else {
					System.out.println("No hay números, ingreselos por favor");
				}
				break;
			case "ingresar":
				System.out
						.println("Ingresar números o \"listo\" para terminar de ingresar:");
				String numberS = "";
				do {
					numberS = scanner.next();
					if (isDouble(numberS)) {
						list.addNode(Double.parseDouble(numberS));
						System.out.println("Nuevo num agregado");
					} else if (!numberS.equalsIgnoreCase("listo")) {
						System.out.println("No válido");
					}
				} while (!numberS.equalsIgnoreCase("listo"));
				break;
			case "limpiar":
				list = new LinkedList();
				System.out.println("Listado de números borrado");
				break;
			default:
				System.out.println("Instrucción no reconocida");
				break;
			}

		} while (line != null);
	}

	public static double mean(LinkedList list) {
		Node iterator = list.iterator();
		double summary = 0.0;
		while (iterator != null) {
			summary += iterator.getValue();
			iterator = iterator.getNext();
		}
		return summary / list.size();
	}

	public static double deviationStd(LinkedList list, double mean) {
		if (list.size() < 2) {
			return 0.0;
		} else {
			double deviation = 0.0;
			Node iterator = list.iterator();
			while (iterator != null) {
				deviation += Math.pow(iterator.getValue() - mean, 2);
				iterator = iterator.getNext();
			}
			deviation = deviation / (list.size() - 1);

			return Math.sqrt(deviation);
		}
	}

	public static boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
