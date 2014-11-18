package entity;

public class Node {

	private String name;
	private int numberOfLines;
	private Node parent;
	private Node next;

	public Node(String name, int numberOfLines, Node parent, Node next) {
		super();
		this.name = name;
		this.numberOfLines = numberOfLines;
		this.parent = parent;
		this.next = next;
	}

	public Node(String name, int numberOfLines, Node parent) {
		super();
		this.name = name;
		this.numberOfLines = numberOfLines;
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfLines() {
		return numberOfLines;
	}

	public void setNumberOfLines(int numberOfLines) {
		this.numberOfLines = numberOfLines;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

}
