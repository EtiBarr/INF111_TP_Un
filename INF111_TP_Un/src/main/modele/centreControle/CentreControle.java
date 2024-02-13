package main.modele.centreControle;

import main.modele.communication.Message;
import main.modele.communication.TransporteurMessage;
import main.modele.satelliteRelai.SatelliteRelai;

public  class CentreControle extends TransporteurMessage {

    private SatelliteRelai satelliteRelai;

    public CentreControle(SatelliteRelai satelliteRelai){
        super();
        this.satelliteRelai = satelliteRelai;
    }
     protected void envoyerMessage(Message msg){

        satelliteRelai.envoyerMessageVersRover(msg);

         receptionMessageDeSatellite(msg); // not sure that this is the right method to call

     }


     protected void gestionnaireMessage(Message msg){

        System.out.println("Nom de la classe" + msg.getClass() + "Le numero du message recu:" + msg.getCompte());

     }


}
