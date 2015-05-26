
public class Position {
	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public boolean compare(Position p) {
		return this.x == p.x && this.y == p.y;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Position)
			return this.x == ((Position) obj).x && this.y == ((Position) obj).y;
		else
			return super.equals(obj);
	}

	@Override
	public String toString() {
		return this.x + "," + this.y;
	}
}
