package main;

public class CleanerMaster {
	public String id;
	
	private Field field;
	
	CleanerMaster(String id, Arena arena) {
		this.id = id;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}
	
	
}
