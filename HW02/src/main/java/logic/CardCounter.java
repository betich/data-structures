package logic;

public class CardCounter {
	private UnitCard card;
	private int count;

	public CardCounter(UnitCard card, int count) {
		this.card = card;
		this.count = count < 0 ? 0 : count;
	}

	public UnitCard getCard() {
		return card;
	}

	public void setCard(UnitCard card) { this.card = card; }

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		if (count < 0) {
			this.count = 0;
		} else {
			this.count = count;
		}
	}

	public String toString() {
		return  this.getCard() + " x " + this.getCount();
	}

}
