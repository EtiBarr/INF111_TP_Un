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
    void testToString() {
        FileChainee file = new FileChainee();
        Message msg1 = new MockMessage(1);
        Message msg2 = new MockMessage(2);
        file.ajouterElement(msg1);
        file.ajouterElement(msg2);

        String expected = "The object holds: [" + msg1 + ", " + msg2 + "]";
        assertEquals(expected, file.toString());
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
        assertEquals(msg2, file.pop());
        assertTrue(file.estVide());
    }

    @Test
    void testPopFromEmptyQueue() {
        FileChainee file = new FileChainee();
        assertNull(file.pop());
    }

    @Test
    void testToStringEmptyQueue() {
        FileChainee file = new FileChainee();
        assertEquals("La file est vide", file.toString());
    }

    @Test
    void testToStringNonEmptyQueue() {
        FileChainee file = new FileChainee();
        Message msg1 = new MockMessage(1);
        Message msg2 = new MockMessage(2);
        file.ajouterElement(msg1);
        file.ajouterElement(msg2);

        String expected = "The object holds: [" + msg1.toString() + ", " + msg2.toString() + "]";
        assertEquals(expected, file.toString());
    }




}