package main;

public class CleanerMaster {
	public String id;
	
	public boolean isItAlive;
	
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
	
	public void tick() {}

	public void investigateCollision() {}
}
