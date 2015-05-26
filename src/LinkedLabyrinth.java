import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.LinkedList;


public class LinkedLabyrinth implements Labyrinth {
	private static final long serialVersionUID = 1L;
	private LinkedList<LinkedList<Boolean>> horizontalWalls;
	private LinkedList<LinkedList<Boolean>> verticalWalls;
	private LinkedList<LinkedList<Boolean>> explored;
	private Position start;
	private Position end;
	private int width;
	private int height;

	public LinkedLabyrinth() {
		this.width = 1;
		this.height = 1;
		
		LinkedList<Boolean> l;
		
		this.horizontalWalls = new LinkedList<LinkedList<Boolean>>();
		l = new LinkedList<Boolean>();
		l.add(false);
		l.add(false);
		this.horizontalWalls.add(l);
		
		this.verticalWalls = new LinkedList<LinkedList<Boolean>>();
		l = new LinkedList<Boolean>();
		l.add(false);
		this.verticalWalls.add(l);
		l = new LinkedList<Boolean>();
		l.add(false);
		this.verticalWalls.add(l);
	}

	public void fromString(String str) {
		int cpt = 0;
		
		for (int i = 0; i < this.height + 1; i++) {
			for (int j = 0; j < this.width; j++) {
				if (str.charAt(cpt) == '1') {
					this.horizontalWalls.get(i).set(j, true);
				} else {
					this.horizontalWalls.get(i).set(j, false);
				}
				cpt++;
			}
		}
		
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width + 1; j++) {
				if (str.charAt(cpt) == '1') {
					this.verticalWalls.get(i).set(j, true);
				} else {
					this.verticalWalls.get(i).set(j, false);
				}
				cpt++;
			}
		}
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		for (int i = 0; i < this.height + 1; i++) {
			for (int j = 0; j < this.width; j++) {
				if (this.horizontalWalls.get(i).get(j)) {
					str.append('1');
				} else {
					str.append('0');
				}
			}
		}
		
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width + 1; j++) {
				if (this.verticalWalls.get(i).get(j)) {
					str.append('1');
				} else {
					str.append('0');
				}
			}
		}
		
		return str.toString();
	}
	
	@Override
	public boolean isWall(Position pos, Orientation ori) {
		try {
			if (ori.equals(Orientation.EAST)) {
				return this.verticalWalls.get(pos.getY()).get(pos.getX() + 1);
			} else if (ori.equals(Orientation.NORTH)) {
				return this.horizontalWalls.get(pos.getY() + 1).get(pos.getX());
			} else if (ori.equals(Orientation.WEST)) {
				return this.verticalWalls.get(pos.getY()).get(pos.getX());
			} else if (ori.equals(Orientation.SOUTH)) {
				return this.horizontalWalls.get(pos.getY()).get(pos.getX());
			} else {
				throw 
			}
		}
	}

	@Override
	public boolean isExplored(Position pos) {
		return this.explored[pos.getY()][pos.getX()];
	}

	@Override
	public void setWallNorth(Position pos, boolean b) {
		this.horizontalWalls[pos.getY() - 1][pos.getX()] = b;
	}

	@Override
	public void setWallSouth(Position pos, boolean b) {
		this.horizontalWalls[pos.getY()][pos.getX()] = b;
	}

	@Override
	public void setWallEast(Position pos, boolean b) {
		this.verticalWalls[pos.getY()][pos.getX()] = b;
	}

	@Override
	public void setWallWest(Position pos, boolean b) {
		this.verticalWalls[pos.getY()][pos.getX() - 1] = b;
	}

	@Override
	public void setExplored(Position pos, boolean b) {
		this.explored[pos.getY()][pos.getX()] = b;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public Position getStart() {
		return this.start;
	}

	@Override
	public Position getEnd() {
		return this.end;
	}
	
	public String graphicalRepresentation() {
		// TODO Generate a graphical representation of the labyrinth.
		// Width: 2 * width + 1
		// Height: 2 * height + 1
		StringBuffer sb = new StringBuffer((2 * this.width + 2) * (2 * this.height + 1) - 1);
		
		// First line
		for (int i = 0; i < this.width; i++) {
			sb.append("+-");
		}
		sb.append("+\n");
		
		for (int i = 0; i < this.height; i++) {
			// Vertical walls line
			sb.append("| ");
			for (int j = 0; j < this.width - 1; j++) {
				//sb.append(' ');
				if (this.verticalWalls[i][j]) {
					sb.append('|');
				} else {
					sb.append(' ');
				}
				sb.append(' ');
			}
			sb.append("|\n");
			
			// Horizontal walls line
			if (i < this.height - 1) {
				for (int j = 0; j < this.width; j++) {
					sb.append('+');
					if (this.horizontalWalls[i][j]) {
						sb.append('-');
					} else {
						sb.append(' ');
					}
				}
				sb.append("+\n");
			}
		}
		
		// Last ligne
		for (int i = 0; i < this.width; i++) {
			sb.append("+-");
		}
		sb.append("+");
		
		return sb.toString();
	}

	@Override
	public void setWall(Position pos, Orientation ori, boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean setExplored(Position pos, boolean b) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String findPath() {
		// TODO Auto-generated method stub
		return null;
	}
}
