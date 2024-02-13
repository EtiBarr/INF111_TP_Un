package main.modele.communication;
/*
  Classe qui implémente le protocol de communication entre le Rover
  et le Centre d'opération.

  Il se base sur une interprétation libre du concept de Nack:
  	https://webrtcglossary.com/nack/

  Les messages envoyés sont mémorisé. À l'aide du compte unique
  le transporteur de message peut identifier les Messages manquant
  dans la séquence et demander le renvoi d'un Message à l'aide du Nack.

  Pour contourner la situation ou le Nack lui-même est perdu, le Nack
  est renvoyé periodiquement, tant que le Message manquant n'a pas été reçu.

  C'est également cette classe qui gère les comptes unique.

  Les messages reçu sont mis en file pour être traité.

  La gestion des messages reçu s'effectue comme une tâche s'exécutant indépendamment (Thread)

  Quelques détails:
   - Le traitement du Nack a priorité sur tout autre message.
   - Un message NoOp est envoyé périodiquement pour s'assurer de maintenir
     une communication active et identifier les messages manquants en bout de séquence.

  Services offerts:
   - TransporteurMessage
   - receptionMessageDeSatellite
   - run

  @author Frederic Simard, ETS
 * @version Hiver, 2024
 */

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.LinkedList;


//questions to ask the prof
//do we need to use out own linked queue or do we need to implement a ready made list (the guide says that we have to implement two)
//where do we actually make the list of messages, sent and tosend
//is it an issue if i name a little bit in english
public abstract class TransporteurMessage extends Thread {
	
	// compteur de message
	protected CompteurMessage compteurMsg;
	// lock qui protège la liste de messages reçu
	private ReentrantLock lock = new ReentrantLock();
	
	/**
	 * Constructeur, initialise le compteur de messages unique
	 */
	public TransporteurMessage() {
		compteurMsg = new CompteurMessage();		
	}
	
	/**
	 * Méthode gérant les messages reçu du satellite. La gestion se limite
	 * à l'implémentation du Nack, les messages spécialisé sont envoyés
	 * aux classes dérivés
	 * @param msg, message reçu
	 */

	public void receptionMessageDeSatellite(Message msg) {
		lock.lock();
		
		try {

			ArrayList<Message> arrayMessage = new ArrayList<Message>();

			int nbNack = 0;
				if(msg instanceof Nack){
					//addFirst will add the message at the start of the list, ass desired
					arrayMessage.addFirst(msg);
					nbNack++;
				}else{

					// this is to account for the position of the message according to the amount of nacks that were placed in the array
					int position = nbNack + msg.getCompte();
					//add the new message at the desired position
					arrayMessage.add(position, msg);
				}
		}finally {
			lock.unlock();
		}
	}


	@Override
	/**
	 * Tâche effectuant la gestion des messages reçu
	 */

	public void run() {
		
		int compteCourant = 0;
		
		while(true) {
			
			lock.lock();
			
			try {

				//would i not have to use the linked list tha i made?
				//i could maybe use the queue that i made myself and then make a copy of the first one and use the copy
				//instead of having to unstack and restack
				LinkedList<Message> listMessage = new LinkedList<Message>();
				LinkedList<Message> listMessageEnvoyer = new LinkedList<Message>();

				boolean nackSent = false;

				while(!listMessage.isEmpty() && !nackSent){

						Message nextMessage = listMessage.getFirst();

						if(nextMessage instanceof Nack){

							int nextMessageCompte = nextMessage.getCompte();

							Message messageAEnvoyer = listMessageEnvoyer.peek();

							envoyerMessage(messageAEnvoyer);

							//remove the first element, which should be the nack that we just did
							listMessage.remove(nextMessage);

						}else if(nextMessage.getCompte() != compteCourant){

							//need to create nack here with the missing message
							Message messageNack = new Nack(compteCourant);
							envoyerMessage(messageNack);

							nackSent = true; //set nackSent to true to leave the while loop
						}else if(nextMessage.getCompte() < compteCourant){

							listMessageEnvoyer.removeFirst(); //remove this message since it's a double
						}else{
							gestionnaireMessage(nextMessage);
							listMessage.remove(nextMessage);
							compteCourant++;
						}
					}

				int compteUnique = compteurMsg.getCompteActuel();
				Message noOpMessage = new NoOp(compteUnique);

				envoyerMessage(noOpMessage);

			
			}finally{
				lock.unlock();
			}
			
			// pause, cycle de traitement de messages
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * méthode abstraite utilisé pour envoyer un message
	 * @param msg, le message à envoyer
	 */
	abstract protected void envoyerMessage(Message msg);

	/**
	 * méthode abstraite utilisé pour effectuer le traitement d'un message
	 * @param msg, le message à traiter
	 */
	abstract protected void gestionnaireMessage(Message msg);

	

}
