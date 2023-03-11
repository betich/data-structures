package logic;

public class UnitCard {
	private String name;
	private int bloodCost;
	private int power;
	private int health;
	private String flavorText;

	public UnitCard(String name, int bloodCost, int power, int health, String flavorText) {
		this.name = name.isBlank() ? "Creature" : name;
		this.bloodCost = Math.max(bloodCost, 0);
		this.power = Math.max(power, 0);
		this.health = Math.max(health, 1);
		this.flavorText = flavorText;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name.isBlank()) {
			this.name = "Creature";
		} else {
			this.name = name;
		}
	}

	public int getBloodCost() {
		return bloodCost;
	}

	public void setBloodCost(int bloodCost) {
		if (bloodCost < 0) {
			this.bloodCost = 0;
		} else {
			this.bloodCost = bloodCost;
		}
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		if (power < 0) {
			this.power = 0;
		} else {
			this.power = power;
		}
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		if (health < 1) {
			this.health = 1;
		} else {
			this.health = health;
		}
	}

	public String getFlavorText() {
		return flavorText;
	}

	public void setFlavorText(String flavorText) {
		this.flavorText = flavorText;
	}

	public boolean equals(UnitCard other) {
		return this.name.equals(other.name);
	}

	public String toString() {
		return this.getName() + " (POW: " + this.getPower() + ", HP: " + this.getHealth() + ")";
	}
}
