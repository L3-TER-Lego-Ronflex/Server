
public class MainTests {

	public static void main(String[] args) {
		ArrayLabyrinth al = new ArrayLabyrinth(10, 10, new Position(0,0), new Position(2,2));
		al.setWallEast(new Position(0, 0), true);
		System.out.println(al.graphicalRepresentation());
	}

}
