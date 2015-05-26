
public class MainTests {
	public static void main(String[] args) {
		// On initialise le robot à 0,0
		Position robot = new Position(0, 0);
		
		// On crée le labyrinthe
		// Il n'y a pas de murs par défaut
		LinkedLabyrinth al = new LinkedLabyrinth();
		
		// On pose des murs sur la case où est le robot
		al.setWall(robot, Orientation.NORTH, true);
		al.setWall(robot, Orientation.SOUTH, true);
		al.setWall(robot, Orientation.EAST, true);
		
		// On tente d'explorer la case de gauche
		// La position retournée par setExplored et la nouvelle position du robot
		robot = al.setExplored(new Position(-1, 0), true);
		
		// Dés qu'on l'a exploré, on pose des murs sur sa case
		al.setWall(robot, Orientation.NORTH, true);
		al.setWall(robot, Orientation.WEST, true);
		
		// On regarde à quoi ressemble le labyrinthe
		System.out.println(al.graphicalRepresentation());
	}
}
