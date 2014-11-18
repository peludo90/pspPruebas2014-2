/*
 * author: Obed Gonzalez
 * 
 * fecha inicio: nov 9 de 2014
 * 
 * descripción: programa para realizar conteo de líneas a un programa que posea el estandar de codidicación prederterminado
 *				a través de un archivo .txt que contiene todas las clases copiadas en el
 */

package principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import entity.LinkedTree;
import entity.Node;

public class Main {

	private static File file = null;
	private static FileReader fReader = null;
	private static BufferedReader bufferReader = null;

	static ArrayList<String> commentType = new ArrayList<String>();

	public static void main(String[] args) {

		int validLines = 0;
		int totalLines = 0;
		int flag = 0;
		int classLines = 0;
		int methodLines = 0;
		Node parentClassNode = null;
		Node methodNode = null;
		LinkedTree listContent = new LinkedTree();
		String className = null;

		commentType.add("//");
		commentType.add("/*");
		commentType.add("*");

		ArrayList<String> visibilityType = new ArrayList<String>();
		visibilityType.add("public");
		visibilityType.add("private");
		visibilityType.add("default");
		visibilityType.add("protected");

		try {
			file = new File("/home/user/Escritorio/sumClases.txt");
			System.out.print("Obteniendo archivo\n");

			fReader = new FileReader(file);
			bufferReader = new BufferedReader(fReader);

			System.out.print("Comenzando a leer información\n");

			String line = null;

			while ((line = bufferReader.readLine()) != null) {
				totalLines++;

				if (isValidLine(line)) {
					validLines++;

					StringTokenizer tokens = new StringTokenizer(line, "  \t");

					ArrayList<String> splittedLine = new ArrayList<String>();

					while (tokens.hasMoreTokens()) {
						splittedLine.add(tokens.nextToken());
					}

					if (splittedLine.get(splittedLine.size() - 1).equals("{")) {
						flag++;
						if ((className = isClass(splittedLine)) != null) {
							parentClassNode = new Node(className, 0, null);
						} else if (visibilityType.contains(splittedLine.get(0))) {
							methodNode = new Node(getMethodName(splittedLine),
									0, parentClassNode);
						}
					}

					if (flag >= 1) {
						classLines++;
					}

					if (flag >= 2) {
						methodLines++;
					}

					if (splittedLine.get(0).equals("}")) {
						flag--;
						if ((flag == 1)) {
							methodNode.setNumberOfLines(methodLines);
							listContent.addNode(methodNode);
							methodLines = 0;
							// methodNode = null;
						} else if (flag == 0) {
							parentClassNode.setNumberOfLines(classLines);
							listContent.addNode(parentClassNode);
							classLines = 0;
							// className = null;
						}
					}

				}
			}

			System.out.println("Resultado de archivo:\n");

			Node node = listContent.iterator();

			System.out.println("Líneas totales : " + totalLines + "\n");
			System.out.println("Líneas válidas : " + validLines + "\n");

			System.out.println("Clase" + "\t" + "Método" + "\t" + "\t"
					+ "Líneas" + "\n");

			while (node != null) {

				if (node.getParent() == null) {
					System.out.println("Nombre de clase: " + node.getName()
							+ "(" + node.getNumberOfLines() + " líneas)\n");

					Node nodeSon = listContent.iterator();
					System.out.println("Métodos de la clase:\n");
					while (nodeSon != null) {
						if (nodeSon.getParent() == node) {
							System.out
									.println("\t" + nodeSon.getName() + "("
											+ nodeSon.getNumberOfLines()
											+ " líneas)\n");
						}
						nodeSon = nodeSon.getNext();
					}

				}

				node = node.getNext();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fReader) {
					fReader.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		System.out.println("Gracias por usar este programa\n");

		System.exit(0);

	}

	public static boolean isValidLine(String line) {

		StringTokenizer tokenizer = new StringTokenizer(line, "\t; ");

		if ((tokenizer.countTokens() != 0)
				&& (!commentType.contains(tokenizer.nextToken()))) {
			return true;
		}

		return false;
	}

	public static String isClass(ArrayList<String> lineSplitted) {
		int i = 0;

		for (i = 0; i < lineSplitted.size(); i++) {
			if (lineSplitted.get(i).equals("class")
					|| lineSplitted.get(i).equals("interface")) {
				StringTokenizer name = new StringTokenizer(
						lineSplitted.get(i + 1), "\\)");
				return name.nextToken();
			}
		}
		return null;

	}

	public static String getMethodName(ArrayList<String> lineSplitted) {
		int i = 0;
		Iterator<String> iterator = lineSplitted.iterator();

		while (iterator.hasNext()) {
			String token = (String) iterator.next();
			if (token.contains("(")) {
				StringTokenizer splittedName = new StringTokenizer(token, "(");
				return splittedName.nextToken();
			}
		}
		return null;
	}
}
