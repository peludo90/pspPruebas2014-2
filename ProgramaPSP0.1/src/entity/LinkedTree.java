package entity;

public class LinkedTree {

	Node header;
	Node tail;
	int size;

	public LinkedTree() {
		header = null;
		tail = null;
		size = 0;
	}

	public void addNode(String name, int numberOfLines, Node parent) {
		Node node = new Node(name, numberOfLines, parent);

		if (header != null) {
			tail.setNext(node);
			node.setNext(null);
			tail = node;
		} else {
			header = node;
			tail = node;
		}
		size++;
	}

	public void addNode(Node node) {

		if (header != null) {
			tail.setNext(node);
			node.setNext(null);
			tail = node;
		} else {
			header = node;
			tail = node;
		}
		size++;
	}

	public void deleteNode(int index) {

		if (!isEmpty()) {

			int count = 0;
			Node node = null;
			Node nodeAux = header;

			while (count < index) {
				node = nodeAux;
				nodeAux = node.getNext();
				count++;
			}

			node.setNext(nodeAux.getNext());

		} else {
			System.out.println("Lista vacía");
		}
	}

	public Node getNode(int index) {
		if (!isEmpty() && index < size) {
			Node node = header;
			int count = 0;

			while (count < index) {
				node = node.getNext();
				count++;
			}
			return node;
		} else {
			return null;
		}
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public Node iterator() {
		return this.header;
	}

	public int size() {
		return this.size;
	}

}
