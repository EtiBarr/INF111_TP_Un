package main.programme;

import main.modele.centreControle.CentreControle;
import main.modele.communication.Message;
import main.modele.rover.Rover;
import main.modele.satelliteRelai.SatelliteRelai;


public class ProgrammePrincipal {


	/**
	 * Programme principale, instancie les éléments de la simulation,
	 * les lie entre eux, puis lance la séquence de test.
     */



	public static void main(String[] args){


		SatelliteRelai satellite = new SatelliteRelai();	//instance de satelite
		satellite.start();

		CentreControle centreControle = new CentreControle(satellite);	//instance de controle center
		centreControle.start();
		satellite.lierCentreOp(centreControle); //linking

		Rover rover = new Rover(satellite);	//instance de rover
		rover.start();
		satellite.lierRover(rover); //linking

		/*
		Message msg0 = new Message (0);
		Message msg1 = new Message (1);
		Message msg2 = new Message (2);
		Message msg3 = new Message (3);

		rover.envoyerMessage(msg0);
		rover.envoyerMessage(msg1);
		rover.envoyerMessage(msg2);
		rover.envoyerMessage(msg3);
		*/

	}

}
