import modele.centreControle.CentreControle;
import modele.rover.Rover;
import modele.satelliteRelai.SatelliteRelai;


public class ProgrammePrincipal {


	/**
	 * Programme principale, instancie les éléments de la simulation,
	 * les lie entre eux, puis lance la séquence de test.
     */


	public CentreControle centreControle;

	public Rover rover;

	public static void main(String[] args){


		SatelliteRelai satellite = new SatelliteRelai();
		satellite.start();

	}

}
