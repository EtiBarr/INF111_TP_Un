package testSuite;

import org.junit.jupiter.api.Test;
import main.utilitaires.FileChainee;
import main.modele.communication.Message;


import static org.junit.jupiter.api.Assertions.*;
class FileChaineeTest {

    private static class MockMessage extends Message {
        public MockMessage(int compte) {
            super(compte);
        }
    }

    @Test
    void testAjouterElement() {
        FileChainee file = new FileChainee();
        Message msg = new MockMessage(1);
        file.ajouterElement(msg);
        assertFalse(file.estVide());
    }

    @Test
    void testEnleverElement() {
        FileChainee file = new FileChainee();
        Message msg = new MockMessage(1);
        file.ajouterElement(msg);
        file.enleverElement();
        assertTrue(file.estVide());
    }

    @Test
    void testPop() {
        FileChainee file = new FileChainee();
        Message msg = new MockMessage(1);
        file.ajouterElement(msg);
        Message poppedMsg = file.pop();
        assertEquals(msg, poppedMsg);
        assertTrue(file.estVide());
    }

    @Test
    void testEstVide() {
        FileChainee file = new FileChainee();
        assertTrue(file.estVide());
        Message msg = new MockMessage(1);
        file.ajouterElement(msg);
        assertFalse(file.estVide());
    }


    @Test
    void testAjouterEtEnleverElement() {
        FileChainee file = new FileChainee();
        Message msg1 = new MockMessage(1);
        Message msg2 = new MockMessage(2);

        file.ajouterElement(msg1);
        file.ajouterElement(msg2);
        assertEquals(msg1, file.pop());
        assertFalse(file.estVide());

        file.enleverElement();
        assertNull(file.pop());
        assertTrue(file.estVide());
    }

    @Test
    void testPopFromEmptyQueue() {
        FileChainee file = new FileChainee();
        assertNull(file.pop());
    }


    @Test
    void testAjouterElementWithDifferentValues() {
        FileChainee file = new FileChainee();
        Message msg = new MockMessage(5);
        file.ajouterElement(msg);
        assertFalse(file.estVide());
    }

    @Test
    void testEnleverElementWithDifferentValues() {
        FileChainee file = new FileChainee();
        Message msg = new MockMessage(3);
        file.ajouterElement(msg);
        file.enleverElement();
        assertTrue(file.estVide());
    }

    @Test
    void testPopWithDifferentValues() {
        FileChainee file = new FileChainee();
        Message msg = new MockMessage(7);
        file.ajouterElement(msg);
        Message poppedMsg = file.pop();
        assertEquals(msg, poppedMsg);
        assertTrue(file.estVide());
    }


    @Test
    void testAjouterEtEnleverElementWithDifferentValues() {
        FileChainee file = new FileChainee();
        Message msg1 = new MockMessage(2);
        Message msg2 = new MockMessage(6);

        file.ajouterElement(msg1);
        file.ajouterElement(msg2);
        assertEquals(msg1, file.pop());
        assertFalse(file.estVide());

        file.enleverElement();
        assertNull(file.pop());
        assertTrue(file.estVide());
    }



}