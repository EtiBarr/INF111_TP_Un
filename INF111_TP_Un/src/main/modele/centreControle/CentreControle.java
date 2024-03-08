package main.modele.centreControle;

import main.modele.communication.Message;
import main.modele.communication.TransporteurMessage;
import main.modele.satelliteRelai.SatelliteRelai;

//make sure that both this and rover has it's own list ***************************

public  class CentreControle extends TransporteurMessage {

    private SatelliteRelai satelliteRelai;

    public CentreControle(SatelliteRelai satelliteRelai){
        super();
        this.satelliteRelai = satelliteRelai; //link to satelliteRelai
    }
     public void envoyerMessage(Message msg){

         if (satelliteRelai != null) {
             satelliteRelai.envoyerMessageVersRover(msg);
             satelliteRelai.messageRover.ajouterElement(msg); // Ajouter message a la liste des message envoyer
         }else{
             System.out.println("satelliteRelai is null (centre controle");
         }

         receptionMessageDeSatellite(msg);

     }


     protected void gestionnaireMessage(Message msg){

        System.out.println("Le numero du message recu pour centreOp:" + msg.getCompte());

     }


}
