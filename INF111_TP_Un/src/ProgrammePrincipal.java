import modele.satelliteRelai.SatelliteRelai;
import utilitaires.Vect2D;

public class ProgrammePrincipal {

	/**
	 * Programme principale, instancie les éléments de la simulation,
	 * les lie entre eux, puis lance la séquence de test.
	 * @param args, pas utilisé
	 */

	public static void testVect2d(){

		Vect2D vector = new Vect2D(8, 5);


		Vect2D posIni = new Vect2D(7, 5);
		Vect2D posFin = new Vect2D(7, 5);

		double longueur = vector.getLongueur();
		double angle = vector.getAngle();

		Vect2D posDiff = posIni.calculerDiff(posFin);

		System.out.println("longueur est egale a:" + longueur);
		System.out.println("l'angle est egale a:" + angle);
		System.out.println(posDiff);

		System.out.println(vector);
		double a = 2;

		vector.ajouter(3, 4);

		System.out.println(vector);

		System.out.println(posIni);
		System.out.println(posFin);
		boolean answer = posIni.equals(posFin);
		System.out.println(answer);
	}



	public static void main(String[] args){
	
		SatelliteRelai satellite = new SatelliteRelai();
		satellite.start();
		testVect2d();
	}

}
