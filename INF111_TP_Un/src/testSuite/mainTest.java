package testSuite;

import main.modele.communication.Message;
import main.modele.satelliteRelai.SatelliteRelai;
import main.utilitaires.FileChainee;
import main.utilitaires.Vect2D;

public class mainTest {


    public static void testVect2d(){

        Vect2D vector = new Vect2D(8, 5);


        Vect2D posIni = new Vect2D(7, 5);
        Vect2D posFin = new Vect2D(7, 5);

        double longueur = vector.getLongueur();
        double angle = vector.getAngle();

        Vect2D posDiff = posIni.calculerDiff(posFin);

        System.out.println("longueur est egale a:" + longueur);
        System.out.println("l'angle est egale a:" + angle);
        System.out.println(posDiff);

        System.out.println(vector);
        double a = 2;

        vector.ajouter(3, 4);

        System.out.println(vector);

        System.out.println(posIni);
        System.out.println(posFin);
        boolean answer = posIni.equals(posFin);
        System.out.println(answer);
    }

    public static void testFileChaine(){

        FileChainee file = new FileChainee();

        Message test = new Message(1) {
            @Override
            public long getTempsEnvoi() {
                return super.getTempsEnvoi();
            }
        };
        Message test2 = new Message(2) {
            @Override
            public long getTempsEnvoi() {
                return super.getTempsEnvoi();
            }
        };

        file.ajouterElement(test);
        file.ajouterElement(test2);
        file.ajouterElement(test2);
        file.ajouterElement(test2);
        file.ajouterElement(test2);
        file.ajouterElement(test);
        file.ajouterElement(test);

        file.enleverElement();
        file.enleverElement();
        file.enleverElement();

        System.out.println(file);


    }

    public static void testEnvoyerMessage(){

        SatelliteRelai satellite = new SatelliteRelai();
        Message test = new Message(1) {
            @Override
            public long getTempsEnvoi() {
                return super.getTempsEnvoi();
            }
        };
        Message test2 = new Message(2) {
            @Override
            public long getTempsEnvoi() {
                return super.getTempsEnvoi();
            }
        };


        satellite.envoyerMessageVersRover(test);
        satellite.envoyerMessageVersCentrOp(test);
        satellite.envoyerMessageVersRover(test2);
        satellite.envoyerMessageVersCentrOp(test2);

    }



    public static void main(String[] args) {

        //SatelliteRelai satellite = new SatelliteRelai();
        //satellite.start();
        //testEnvoyerMessage();



        //testVect2d();
        testFileChaine();


    }
}
