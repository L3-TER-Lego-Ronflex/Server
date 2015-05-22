import java.awt.image.BufferStrategy;
import java.util.ArrayList;


public class ArrayLabyrinth implements Labyrinth {
	
	private final int width;
	private final int height;
	private boolean[][] horizontalWalls;
	private boolean[][] verticalWalls;
	private boolean[][] explored;
	private Position start;
	private Position end;

	public ArrayLabyrinth(int width, int height, Position start, Position end) {
		this.width = width;
		this.height = height;
		this.horizontalWalls = new boolean[height - 1][width];
		this.verticalWalls = new boolean[height][width - 1];
		this.explored = new boolean[height][width];
	}

	@Override
	public void fromString(String str) {
		int cpt = 0;
		
		for (int i = 0; i < this.height - 1; i++) {
			for (int j = 0; j < this.width; j++) {
				if (str.charAt(cpt) == '1') {
					this.horizontalWalls[i][j] = true;
				} else {
					this.horizontalWalls[i][j] = false;
				}
				cpt++;
			}
		}
		
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width - 1; j++) {
				if (str.charAt(cpt) == '1') {
					this.verticalWalls[i][j] = true;
				} else {
					this.verticalWalls[i][j] = false;
				}
				cpt++;
			}
		}
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		for (int i = 0; i < this.height - 1; i++) {
			for (int j = 0; j < this.width; j++) {
				if (this.horizontalWalls[i][j]) {
					str.append('1');
				} else {
					str.append('0');
				}
			}
		}
		
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width - 1; j++) {
				if (this.verticalWalls[i][j]) {
					str.append('1');
				} else {
					str.append('0');
				}
			}
		}
		
		return str.toString();
	}

	@Override
	public boolean isWallNorth(Position pos) {
		if (pos.getY() == this.height) {
			return true;
		} else {
			return this.horizontalWalls[pos.getY() - 1][pos.getX()];
		}
	}

	@Override
	public boolean isWallSouth(Position pos) {
		if (pos.getY() == 0) {
			return true;
		} else {
			return this.horizontalWalls[pos.getY()][pos.getX()];
		}
	}

	@Override
	public boolean isWallEast(Position pos) {
		if (pos.getX() == this.width) {
			return true;
		} else {
			return this.verticalWalls[pos.getY()][pos.getX()];
		}
	}

	@Override
	public boolean isWallWest(Position pos) {
		if (pos.getX() == 0) {
			return true;
		} else {
			return this.verticalWalls[pos.getY()][pos.getX() - 1];
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
}
