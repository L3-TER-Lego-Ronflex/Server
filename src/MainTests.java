
public class MainTests {
	public static void main(String[] args) {
		// On initialise le robot � 0,0
		Position robot = new Position(0, 0);
		
		// On cr�e le labyrinthe
		// Il n'y a pas de murs par d�faut
		LinkedLabyrinth al = new LinkedLabyrinth();
		
		// On pose des murs sur la case o� est le robot
		al.setWall(robot, Orientation.NORTH, true);
		al.setWall(robot, Orientation.SOUTH, true);
		al.setWall(robot, Orientation.EAST, true);
		
		// On tente d'explorer la case de gauche
		// La position retourn�e par setExplored et la nouvelle position du robot
		robot = al.setExplored(robot.next(Orientation.WEST), true);
		
		// D�s qu'on l'a explor�, on pose des murs sur sa case
		al.setWall(robot, Orientation.NORTH, true);
		al.setWall(robot, Orientation.WEST, true);
		
		// On regarde o� est le robot � quoi ressemble le labyrinthe
		System.out.println(robot);
		System.out.println(al.graphicalRepresentation());
		
		// On continue l'exploration et la carto
		robot = al.setExplored(robot.next(Orientation.SOUTH), true);
		al.setWall(robot, Orientation.WEST, true);
		al.setWall(robot, Orientation.SOUTH, true);
		
		robot = al.setExplored(robot.next(Orientation.EAST), true);
		al.setWall(robot, Orientation.SOUTH, true);
		
		robot = al.setExplored(robot.next(Orientation.EAST), true);
		al.setWall(robot, Orientation.SOUTH, true);
		al.setWall(robot, Orientation.EAST, true);
		
		robot = al.setExplored(robot.next(Orientation.NORTH), true);
		al.setWall(robot, Orientation.NORTH, true);
		al.setWall(robot, Orientation.EAST, true);
		al.setEnd(robot);
		
		// On regarde � quoi ressemble otre labyrinthe final
		System.out.println("Rob: " + robot);
		System.out.println("Start: " + al.getStart());
		System.out.println("End: " + al.getEnd());
		System.out.println(al.graphicalRepresentation());
		System.out.println(al.toString());
		
		// On recr�e le labyrinthe
		LinkedLabyrinth nal = new LinkedLabyrinth();
		nal.fromString(al.toString());
		System.out.println(nal.graphicalRepresentation());
		
		// V�rifications de conversions
		System.out.println("Both graphical equals: " + al.graphicalRepresentation().equals(nal.graphicalRepresentation()));
		System.out.println("Both String equals: " + al.toString().equals(nal.toString()));
	}
}
