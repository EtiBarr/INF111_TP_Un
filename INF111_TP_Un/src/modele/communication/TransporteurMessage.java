package modele.communication;
/**
 * Classe qui implémente le protocol de communication entre le Rover
 * et le Centre d'opération.
 * 
 * Il se base sur une interprétation libre du concept de Nack:
 * 	https://webrtcglossary.com/nack/
 *  
 * Les messages envoyés sont mémorisé. À l'aide du compte unique
 * le transporteur de message peut identifier les Messages manquant
 * dans la séquence et demander le renvoi d'un Message à l'aide du Nack.
 * 
 * Pour contourner la situation ou le Nack lui-même est perdu, le Nack
 * est renvoyé periodiquement, tant que le Message manquant n'a pas été reçu.
 * 
 * C'est également cette classe qui gère les comptes unique.
 * 
 * Les messages reçu sont mis en file pour être traité.
 * 
 * La gestion des messages reçu s'effectue comme une tâche s'exécutant indépendamment (Thread)
 * 
 * Quelques détails:
 *  - Le traitement du Nack a priorité sur tout autre message.
 *  - Un message NoOp est envoyé périodiquement pour s'assurer de maintenir
 *    une communication active et identifier les messages manquants en bout de séquence.
 * 
 * Services offerts:
 *  - TransporteurMessage
 *  - receptionMessageDeSatellite
 *  - run
 * 
 * @author Frederic Simard, ETS
 * @version Hiver, 2024
 */

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;



/*
NoOp (No Operation):

Responsibility: The NoOp, short for No Operation, refers to a type of message that serves as a placeholder or a non-functional message.
Role in the System:
The NoOp messages are periodically sent by Agent 1 (center of control or rover) to ensure that there is continuous communication even when there is no actual data to transmit.
These messages help maintain a minimal level of communication, acting as a kind of heartbeat or keep-alive signal.
The periodic transmission of NoOp messages helps in situations where the last message may be lost, and without any subsequent communication, it would be impossible to confirm if it was received.


Nack (Negative Acknowledgment):

Responsibility: The Nack, short for negative acknowledgment, is a response from Agent 2 (center of control or rover) indicating the detection of missing or lost messages.
Role in the System:
Agent 2 keeps track of the incoming messages, and if it detects a missing message (using sequence numbers assigned to each message), it generates a Nack.
The Nack includes information about the missing message, such as its sequence number.
When Agent 1 receives the Nack, it retransmits the requested message, addressing the issue of potential message loss.
The Nack mechanism allows for the detection and recovery of lost messages, ensuring that critical information is not permanently lost due to interference.
 */

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

	//can use linkedList to make this
	public void receptionMessageDeSatellite(Message msg) {
		lock.lock();
		
		try {
			
			/*
			 * (6.3.3) Insérer votre code ici
			 *
			 * S’il s’agit d’un Nack (voir instanceof)
			 ajouter le message au début de la liste des messages reçu.
			Sinon
			 évalue la position du message dans la liste, déterminée à
			 partir du compte du message (voir définition de Message)
			 * et
			 ajoute le message à la position trouvé. Les messages avec les
			 nombres les plus bas doivent être au début de la liste à
			 l’exception des Nack qui ont priorités.
			 */

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

	//could use a HashMap here
	public void run() {
		
		int compteCourant = 0;
		
		while(true) {
			
			lock.lock();
			
			try {

				/*
				 * (6.3.4) Insérer votre code ici 
				 */
			
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
