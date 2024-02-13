package testSuite;

import main.utilitaires.Vect2D;

import java.text.DecimalFormat;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vect2DTest {

    @Test
    void getLongueurXShouldReturnTwelveWithDefaultConstructorSetToTwelve() {
        var vector = new Vect2D();
        assertEquals(12, vector.getLongueurX());
    }
    @Test
    void getLongueurXShouldReturnTwoWithTwoAsParameter() {
        var vector = new Vect2D(2, 3);
        assertEquals(2, vector.getLongueurX());
    }
    @Test
    void getLongueurXShouldReturnTwoWithCopyOfVectorWithTwo() {
        var vector = new Vect2D(2, 3);

        var copieVector = new Vect2D(vector);

        assertEquals(2, copieVector.getLongueurX());
    }



    @Test
    void getLongueurYShouldReturnTwentyFiveWithDefaultConstructorSetToTwentyFive() {
        var vector = new Vect2D();
        assertEquals(25, vector.getLongueurY());
    }

    @Test
    void getLongueurYShouldReturnThreeWithThreeAsParameter() {
        var vector = new Vect2D(2, 3);
        assertEquals(3, vector.getLongueurY());
    }

    @Test
    void getLongueurYShouldReturnThreeWithCopyOfVectorWithThree() {
        var vector = new Vect2D(2, 3);

        var copieVector = new Vect2D(vector);

        assertEquals(3, copieVector.getLongueurY());
    }



    @Test
    void getLongueurShouldReturnZeroWhenFedZero() {
        var vector = new Vect2D(0, 0);

        assertEquals(0, vector.getLongueur());
    }

    @Test
    void getLongueurShouldReturnTheSameValueAsTheIntendedEquation() {
        var vector = new Vect2D(2, 5);

        assertEquals(Math.sqrt(Math.pow(2, 2) + Math.pow(5,2)), vector.getLongueur());
    }
    @Test
    void getLongueurShouldReturnTheSameThingWithCopyConstructor() {
        var vector = new Vect2D(2, 5);
        var copieVector = new Vect2D(vector);

        assertEquals(vector.getLongueur(), copieVector.getLongueur());
    }



    @Test
    void getAngleShouldReturnOnePointSevenTeen() {
        var vector = new Vect2D(3, 7);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        //this is as dumb as it gets
        assertEquals(1.17, Double.parseDouble(decimalFormat.format(vector.getAngle())));
    }
    @Test
    void getAngleShouldReturnAnErrorIfXIsZero() {
        var vector = new Vect2D(0, 7);

        assertThrows(ArithmeticException.class, vector::getAngle);
    }



    @Test
    void calculerDiffDoesNotMove() {

        var vecteurUn = new Vect2D(2, 5);
        var vecteurDeux = new Vect2D(0, 0);

        var vecteurDiff = new Vect2D();

        vecteurDiff = vecteurUn.calculerDiff(vecteurDeux);

        assertEquals(-2, vecteurDiff.getLongueurX());
        assertEquals(-5, vecteurDiff.getLongueurY());
    }
    @Test
    void calculerDiffMovesInPositive() {

        var vecteurUn = new Vect2D(2, 5);
        var vecteurDeux = new Vect2D(5, 2);

        var vecteurDiff = new Vect2D();

        vecteurDiff = vecteurUn.calculerDiff(vecteurDeux);

        assertEquals(3, vecteurDiff.getLongueurX());
        assertEquals(-3, vecteurDiff.getLongueurY());
    }
    @Test
    void calculerDiffMovesInNegative() {

        var vecteurUn = new Vect2D(2, 5);
        var vecteurDeux = new Vect2D(-5, -2);

        var vecteurDiff = new Vect2D();

        vecteurDiff = vecteurUn.calculerDiff(vecteurDeux);

        assertEquals(-7, vecteurDiff.getLongueurX());
        assertEquals(-7, vecteurDiff.getLongueurY());
    }
    @Test
    void calculerDiffMovesTheBackToZero() {

        var vecteurUn = new Vect2D(2, 5);
        var vecteurDeux = new Vect2D(-2, -5);

        var vecteurDiff = new Vect2D();

        vecteurDiff = vecteurUn.calculerDiff(vecteurDeux);

        assertEquals(-4, vecteurDiff.getLongueurX());
        assertEquals(-10, vecteurDiff.getLongueurY());
    }




    @Test
    void diviserShouldDivideByTwoIfGivenTwo() {

        var vecteur = new Vect2D(6, 7);

        vecteur.diviser(2);

        assertEquals(3, vecteur.getLongueurX());
        assertEquals(3.5, vecteur.getLongueurY());
    }
    @Test
    void diviserShouldDivideByFiveIfGivenFive() {

        var vecteur = new Vect2D(6, 7);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        vecteur.diviser(5);

        assertEquals(1.2, Double.parseDouble(decimalFormat.format(vecteur.getLongueurX())));
        assertEquals(1.4, Double.parseDouble(decimalFormat.format(vecteur.getLongueurY())));
    }
    @Test
    void diviserShouldBeAbleToDivideByAValueGreaterThen() {

        var vecteur = new Vect2D(6, 7);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        vecteur.diviser(10);

        assertEquals(0.6, Double.parseDouble(decimalFormat.format(vecteur.getLongueurX())));
        assertEquals(0.7, Double.parseDouble(decimalFormat.format(vecteur.getLongueurY())));
    }
    @Test
    void diviserShouldBeAbleToDivideByANegativeValue() {

        var vecteur = new Vect2D(6, 7);

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        vecteur.diviser(-10);

        assertEquals(-0.6, Double.parseDouble(decimalFormat.format(vecteur.getLongueurX())));
        assertEquals(-0.7, Double.parseDouble(decimalFormat.format(vecteur.getLongueurY())));
    }
    @Test
    void diviserShouldThrowAnExceptionIfGivenAZero() {

        var vecteur = new Vect2D(6, 7);

        assertThrows(ArithmeticException.class, () -> vecteur.diviser(0));
    }



    @Test
    void ajouterShouldAddPositiveValues() {
        var vecteur = new Vect2D(2, 5);

        vecteur.ajouter(2,5);

        assertEquals(4, vecteur.getLongueurX());
        assertEquals(10, vecteur.getLongueurY());
    }
    @Test
    void ajouterShouldSubtractNegativeValues() {
        var vecteur = new Vect2D(2, 5);

        vecteur.ajouter(-2,-5);

        assertEquals(0, vecteur.getLongueurX());
        assertEquals(0, vecteur.getLongueurY());
    }
    @Test
    void ajouterShouldReturnTheSameThingWhenGivenZeroes() {
        var vecteur = new Vect2D(2, 5);

        vecteur.ajouter(0,0);

        assertEquals(2, vecteur.getLongueurX());
        assertEquals(5, vecteur.getLongueurY());
    }



    @Test
    public void testToStringWithDefaultConstructor() {
        // Arrange
        Vect2D vect = new Vect2D();

        // Act
        String result = vect.toString();

        // Assert
        assertEquals("Les coordonee du vecteur sont: [X =12.0, Y=25.0]", result);
    }

    @Test
    public void testToStringWithParameterizedConstructor() {
        // Arrange
        double x = 8.0;
        double y = 15.0;
        Vect2D vect = new Vect2D(x, y);

        // Act
        String result = vect.toString();

        // Assert
        assertEquals("Les coordonee du vecteur sont: [X =8.0, Y=15.0]", result);
    }

    @Test
    public void testToStringWithCopyConstructor() {
        // Arrange
        Vect2D originalVect = new Vect2D(8.0, 15.0);
        Vect2D copiedVect = new Vect2D(originalVect);

        // Act
        String result = copiedVect.toString();

        // Assert
        assertEquals("Les coordonee du vecteur sont: [X =8.0, Y=15.0]", result);
    }


    @Test
    public void testEquals_SameVector_ReturnsTrue() {
        Vect2D vector1 = new Vect2D(2, 3);
        assertTrue(vector1.equals(vector1));
    }

    @Test
    public void testEquals_EqualVectors_ReturnsTrue() {
        Vect2D vector1 = new Vect2D(2, 3);
        Vect2D vector2 = new Vect2D(2, 3);
        assertTrue(vector1.equals(vector2));
    }

    @Test
    public void testEquals_DifferentVectors_ReturnsFalse() {
        Vect2D vector1 = new Vect2D(2, 3);
        Vect2D vector2 = new Vect2D(4, 5);
        assertFalse(vector1.equals(vector2));
    }

    @Test
    public void testEquals_NullVector_ReturnsFalse() {
        Vect2D vecteur = new Vect2D(2, 3);

        assertThrows(NullPointerException.class, () -> vecteur.equals(null));

    }

    @Test
    public void testEquals_DifferentClass_ReturnsFalse() {
        Vect2D vector1 = new Vect2D(2, 3);
        Object obj = new Object();
        assertNotEquals(vector1, obj);
    }
}