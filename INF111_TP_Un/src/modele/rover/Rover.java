package modele.rover;

import modele.communication.Message;
import modele.communication.TransporteurMessage;
import modele.satelliteRelai.SatelliteRelai;

public class Rover extends TransporteurMessage {

     private SatelliteRelai satelliteRelai;

     public Rover(SatelliteRelai satelliteRelai){

        super();
        this.satelliteRelai = satelliteRelai;
     }
     protected void envoyerMessage(Message msg){

         satelliteRelai.envoyerMessageVersCentrOp(msg);

         receptionMessageDeSatellite(msg); // not sure that this is the right method to call

     }

     protected void gestionnaireMessage(Message msg){

         System.out.println("Nom de la classe" + msg.getClass() + "Le numero du message recu:" + msg.getCompte());

     }
}
