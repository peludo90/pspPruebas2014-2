package entity;

public class Node {

	private double value;
	private Node next;

	public Node(double value, Node next) {
		this.value = value;
		this.next = next;
	}

	public Node(double value) {
		this.value = value;
	}

	public double getValue() {
		return this.value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Node getNext() {
		return this.next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public boolean hasNext() {
		return next != null;
	}
}
