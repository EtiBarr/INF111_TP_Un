package main.programme;

import main.modele.centreControle.CentreControle;
import main.modele.rover.Rover;
import main.modele.satelliteRelai.SatelliteRelai;


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
