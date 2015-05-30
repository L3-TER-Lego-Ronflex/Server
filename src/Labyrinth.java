/*
^       N
|       ^
Y     W<+>E
|       v
0-X->   S
*/
public interface Labyrinth {
	public boolean isWall(Position pos, Orientation ori);
	public void setWall(Position pos, Orientation ori, boolean b);
	public boolean isExplored(Position pos);
	public Position setExplored(Position pos, boolean b);
	public int getWidth();
	public int getHeight();
	public Position getStart();
	public Position getEnd();
	public void setEnd(Position pos);
	/**
	 * Find the quickest path to go from the start to the end of the Labyrinth.
	 * @return A sequence of instructions to follow the path
	 */
	public String findPath();
}
