package entity;

public class Node {

	private double valueX;
	private double valueY;
	private Node next;

	public Node(double valueX, double valueY, Node next) {
		super();
		this.valueX = valueX;
		this.valueY = valueY;
		this.next = next;
	}

	public Node(double valueX, double valueY) {
		super();
		this.valueX = valueX;
		this.valueY = valueY;
	}

	public double getValueX() {
		return valueX;
	}

	public void setValueX(double valueX) {
		this.valueX = valueX;
	}

	public double getValueY() {
		return valueY;
	}

	public void setValueY(double valueY) {
		this.valueY = valueY;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

}
