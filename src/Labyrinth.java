import java.io.Serializable;

/*
^       N
|       ^
Y     W<+>E
|       v
0-X->   S

Axiomes:
Labyrinthe l1 = l2.fromString(l1.toString())

l.isWallNorth(Position(x, y)) = l.isWallSouth(Position(x, y + 1))
l.isWallEast(Position(x, y)) = l.isWallWest(Position(x + 1, y))

l.isWallNorth(Position(x, l.getHeight() - 1)) = true
l.isWallSouth(Position(x, 0)) = true
l.isWallEast(Position(l.getWidth() - 1, y)) = true
l.isWallWest(Position(0, y)) = true

String representation:

Example for a 3x3 labyrinth:
bbbbbbbbbbbb
b (0: no wall or 1: wall): the walls, horizontally (south to north), then vertically (west to east), line by line, no edges
*/
public interface Labyrinth extends Serializable {
	public boolean isWall(Position pos, Orientation ori) throws UnexploredPosition;
	public void setWall(Position pos, Orientation ori, boolean b);
	public boolean isExplored(Position pos);
	public boolean explore(Position pos);
	public int getWidth();
	public int getHeight();
	public Position getStart();
	public Position getEnd();
	/**
	 * Find the quickest path to go from the start to the end of the Labyrinth.
	 * @return A sequence of instructions to follow the path
	 */
	public String findPath();
}
