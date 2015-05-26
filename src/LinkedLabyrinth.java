import java.util.LinkedList;


public class LinkedLabyrinth implements Labyrinth {
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
		this.horizontalWalls.add(l);
		l = new LinkedList<Boolean>();
		l.add(false);
		this.horizontalWalls.add(l);
		
		this.verticalWalls = new LinkedList<LinkedList<Boolean>>();
		l = new LinkedList<Boolean>();
		l.add(false);
		l.add(false);
		this.verticalWalls.add(l);
		
		this.explored = new LinkedList<LinkedList<Boolean>>();
		l = new LinkedList<Boolean>();
		l.add(false);
		this.explored.add(l);
		
		this.start = new Position(0, 0);
		// this.end = null;
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
		if (ori.equals(Orientation.EAST)) {
			return this.verticalWalls.get(pos.getY()).get(pos.getX() + 1);
		} else if (ori.equals(Orientation.NORTH)) {
			return this.horizontalWalls.get(pos.getY() + 1).get(pos.getX());
		} else if (ori.equals(Orientation.WEST)) {
			return this.verticalWalls.get(pos.getY()).get(pos.getX());
		} else if (ori.equals(Orientation.SOUTH)) {
			return this.horizontalWalls.get(pos.getY()).get(pos.getX());
		}
		return true;
	}
	
	@Override
	public void setWall(Position pos, Orientation ori, boolean b) {
		if (ori.equals(Orientation.EAST)) {
			this.verticalWalls.get(pos.getY()).set(pos.getX() + 1, b);
		} else if (ori.equals(Orientation.NORTH)) {
			this.horizontalWalls.get(pos.getY() + 1).set(pos.getX(), b);
		} else if (ori.equals(Orientation.WEST)) {
			this.verticalWalls.get(pos.getY()).set(pos.getX(), b);
		} else if (ori.equals(Orientation.SOUTH)) {
			this.horizontalWalls.get(pos.getY()).set(pos.getX(), b);
		}
	}

	@Override
	public boolean isExplored(Position pos) {
		return this.explored.get(pos.getY()).get(pos.getX());
	}

	@Override
	public Position setExplored(Position pos, boolean b) {
		Position modifiedPos;
		if (pos.getX() < 0) {
			extend(Orientation.WEST);
			modifiedPos = new Position(pos.getX() + 1, pos.getY());
		} else if (pos.getX() >= this.width) {
			extend(Orientation.EAST);
			modifiedPos = pos;
		} else if (pos.getY() < 0) {
			extend(Orientation.SOUTH);
			modifiedPos = new Position(pos.getX(), pos.getY() + 1);
		} else if (pos.getY() >= this.height) {
			extend(Orientation.NORTH);
			modifiedPos = pos;
		} else {
			modifiedPos = pos;
		}
		
		this.explored.get(modifiedPos.getY()).set(modifiedPos.getX(), b);
		return modifiedPos;
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
	
	@Override
	public String findPath() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void extend(Orientation ori) {
		if (ori.equals(Orientation.EAST)) {
			
			for (int i = 0; i < this.height; i++) {
				this.verticalWalls.get(i).addLast(false);
				this.horizontalWalls.get(i).addLast(false);
				this.explored.get(i).addLast(false);
			}
			this.horizontalWalls.get(this.height).addLast(false);
			
			this.width++;
			
		} else if (ori.equals(Orientation.NORTH)) {
			
			LinkedList<Boolean> v = new LinkedList<Boolean>();
			LinkedList<Boolean> h = new LinkedList<Boolean>();
			LinkedList<Boolean> e = new LinkedList<Boolean>();
			for (int i = 0; i < this.width; i++) {
				v.add(false);
				h.add(false);
				e.add(false);
			}
			v.add(false);
			this.verticalWalls.addLast(v);
			this.horizontalWalls.addLast(h);
			this.explored.addLast(e);
			
			this.height++;
			
		} else if (ori.equals(Orientation.WEST)) {
			
			for (int i = 0; i < this.height; i++) {
				this.verticalWalls.get(i).addFirst(false);
				this.horizontalWalls.get(i).addFirst(false);
				this.explored.get(i).addFirst(false);
			}
			this.horizontalWalls.get(this.height).addFirst(false); // Bottom wall
			
			this.width++;
			
			this.start = new Position(this.start.getX() + 1, this.start.getY());
			if (this.end != null) {
				this.end = new Position(this.end.getX() + 1, this.end.getY());
			}
		} else if (ori.equals(Orientation.SOUTH)) {
			
			LinkedList<Boolean> v = new LinkedList<Boolean>();
			LinkedList<Boolean> h = new LinkedList<Boolean>();
			LinkedList<Boolean> e = new LinkedList<Boolean>();
			for (int i = 0; i < this.width; i++) {
				v.add(false);
				h.add(false);
				e.add(false);
			}
			v.add(false);
			this.verticalWalls.addFirst(v);
			this.horizontalWalls.addFirst(h);
			this.explored.addFirst(e);
			
			this.height++;
			
			this.start = new Position(this.start.getX(), this.start.getY() + 1);
			if (this.end != null) {
				this.end = new Position(this.end.getX(), this.end.getY() + 1);
			}
			
		}
	}
	
	public String graphicalRepresentation() {
		// Width: 2 * width + 1
		// Height: 2 * height + 1
		StringBuffer sb = new StringBuffer((2 * this.width + 2) * (2 * this.height + 1) - 1);
		
		//for (int i = 0; i < this.height; i++) {
		for (int i = this.height - 1; i >= 0; i--) {
			// Horizontal walls line
			for (int j = 0; j < this.width; j++) {
				sb.append('+');
				if (this.horizontalWalls.get(i + 1).get(j)) {
					sb.append('-');
				} else {
					sb.append(' ');
				}
			}
			sb.append("+\n");
			
			// Vertical walls line
			for (int j = 0; j < this.width; j++) {
				//sb.append(' ');
				if (this.verticalWalls.get(i).get(j)) {
					sb.append('|');
				} else {
					sb.append(' ');
				}
				sb.append(' ');
			}
			if (this.verticalWalls.get(i).get(this.width)) {
				sb.append('|');
			} else {
				sb.append(' ');
			}
			sb.append('\n');
		}
		
		// Last line
		for (int i = 0; i < this.width; i++) {
			sb.append('+');
			if (this.horizontalWalls.get(0).get(i)) {
				sb.append('-');
			} else {
				sb.append(' ');
			};
		}
		sb.append("+\n");
		
		return sb.toString();
	}
}
