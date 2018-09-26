package mypack;

import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int number, id;

	public Message(int number, int id) {
		this.number = number;
		this.id = id;
	}

	public String toString() {
		return "Message with number " + number + " and ID " + id;
	}

}
