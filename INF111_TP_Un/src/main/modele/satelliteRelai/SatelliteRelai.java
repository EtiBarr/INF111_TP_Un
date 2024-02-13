package main.modele.satelliteRelai;

/*
  Classe simulant le satellite relai

  Le satellite ne se contente que de transferer les messages du Rover vers le centre de contrôle
  et vice-versa.

  Le satellite exécute des cycles à intervale de TEMPS_CYCLE_MS. Période à
  laquelle tous les messages en attente sont transmis. Ceci est implémenté à
  l'aide d'une tâche (Thread).

  Le relai satellite simule également les interférence dans l'envoi des messages.

  Services offerts:
   - lierCentrOp
   - lierRover
   - envoyerMessageVersCentrOp
   - envoyerMessageVersRover

  @author Frederic Simard, ETS
 * @version Hiver, 2024
 */

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import main.modele.centreControle.CentreControle;
import main.modele.communication.Message;
import main.modele.rover.Rover;
import main.utilitaires.FileChainee;

public class SatelliteRelai extends Thread{

	FileChainee messageCentreOp = new FileChainee();
	FileChainee messageRover = new FileChainee();


	static final int TEMPS_CYCLE_MS = 500;
	static final double PROBABILITE_PERTE_MESSAGE = 0.15;
	
	ReentrantLock lock = new ReentrantLock();
	
	private Random rand = new Random();


	/**
	 * Méthode permettant d'envoyer un message vers le centre d'opération
	 * @param msg, message à envoyer
	 */
	public void envoyerMessageVersCentrOp(Message msg) {

		lock.lock();

		
		try {

			if(rand.nextDouble() > PROBABILITE_PERTE_MESSAGE){

				messageCentreOp.ajouterElement(msg);

				//for testing of methode. this makes it so that it prints the messages right away
				// and then i can see what is being done
				System.out.println(messageCentreOp);

			}
			
		}finally {
			lock.unlock();
		}
	}
	
	/**
	 * Méthode permettant d'envoyer un message vers le rover
	 * @param msg, message à envoyer
	 */
	public void envoyerMessageVersRover(Message msg) {


		lock.lock();
		
		try {

			if(rand.nextDouble() > PROBABILITE_PERTE_MESSAGE){

				messageRover.ajouterElement(msg);

				//for testing of methode. this makes it so that it prints the messages right away
				// and then i can see what is being done
				System.out.println(messageRover);
			}
			
		}finally {
			lock.unlock();
		}
	}

	@Override
	public void run() {
		
		while(true) {

			messageCentreOp.enleverElement();
			messageRover.enleverElement();


			// attend le prochain cycle
			try {
				Thread.sleep(TEMPS_CYCLE_MS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public CentreControle centreControle;
	public static void lierCentreOp(CentreControle centreControle){

			//must register in the appropriate variable

	}

	public Rover rover;

	public static void lierRover(Rover rover){

		//must register in the appropriate variable

	}
	

}