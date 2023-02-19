package logic;

public class UnitCard {
  private String name;
  private int bloodCost;
  private int power;
  private int health;

  private String flavorText;

  public UnitCard(String name, int bloodCost, int power, int health, String flavorText) {
    this.name = name.isBlank() ? "Creature" : name;
    this.bloodCost = bloodCost < 0 ? 0 : bloodCost;
    this.power = power < 0 ? 0 : power;
    this.health = health < 1 ? 1 : health;
    this.flavorText = flavorText;
  }

  // Getter

  public String getName() {
    return name;
  }

  public int getBloodCost() {
    return bloodCost;
  }

  public int getPower() {
    return power;
  }

  public int getHealth() {
    return health;
  }

  public String getFlavorText() {
    return flavorText;
  }

  // Setter

  public void setName(String name) {
    this.name = name.isBlank() ? "Creature" : name;
  }

  public void setBloodCost(int bloodCost) {
    if (bloodCost < 0) {
      this.bloodCost = 0;
    } else {
      this.bloodCost = bloodCost;
    }
  }

  public void setPower(int power) {
    if (power < 0) {
      this.power = 0;
    } else {
      this.power = power;
    }
  }

  public void setHealth(int health) {
    this.health = health < 1 ? 1 : health;
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