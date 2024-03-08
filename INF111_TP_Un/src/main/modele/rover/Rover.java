package main.modele.rover;

import main.modele.communication.Message;
import main.modele.communication.TransporteurMessage;
import main.modele.satelliteRelai.SatelliteRelai;

public class Rover extends TransporteurMessage {

     private SatelliteRelai satelliteRelai;

     public Rover(SatelliteRelai satelliteRelai){

        super();
        this.satelliteRelai = satelliteRelai;
     }
     public void envoyerMessage(Message msg){

         if (satelliteRelai != null) {
             satelliteRelai.envoyerMessageVersCentrOp(msg);
             satelliteRelai.messageCentreOp.ajouterElement(msg); // Ajouter message a la liste des message envoyer
         }else{
             System.out.println("satelliteRelai is null (rover");
         }

         receptionMessageDeSatellite(msg);

     }

     protected void gestionnaireMessage(Message msg){

         System.out.println("Le numero du message recu pour rover:" + msg.getCompte());

     }
}
