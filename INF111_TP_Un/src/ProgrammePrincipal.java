import modele.centreControle.CentreControle;
import modele.communication.Message;
import modele.rover.Rover;
import modele.satelliteRelai.SatelliteRelai;
import utilitaires.FileChainee;
import utilitaires.Vect2D;

public class ProgrammePrincipal {


	/**
	 * Programme principale, instancie les éléments de la simulation,
	 * les lie entre eux, puis lance la séquence de test.
	 * @param args, pas utilisé
	 */


	public CentreControle centreControle;

	public Rover rover;

	public static void main(String[] args){


		SatelliteRelai satellite = new SatelliteRelai();
		satellite.start();

	}

}
