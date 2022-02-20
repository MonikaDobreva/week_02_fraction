package fraction;

/**
 * Immutable Fraction : numerator/denominator.
 *
 * Fraction parts are constant after construction. Immutable also means thread
 * safety. Invariants: Fraction is always normalized (greatest common divisor of
 * denominator and numerator is 1).
 *
 * Denominator is greater or equal to 1.
 *
 * @author Pieter van den Hombergh
 */
//TODO Declare fraction To be Comparable.
public class Fraction {

    //TODO implement all fields
    
    
    /**
     * Create a Fraction.
     *
     * @param numerator numerator
     * @param denominator denominator
     */
    public Fraction( int numerator, int denominator ) {
        //TODO implement constructor
        
    }
    
    /**
     * Compute Greatest Common Divisor. Used to normalize fractions.
     *
     * @param a first number
     * @param b second number, gt 0
     * @return greatest common divisor.
     */
    static int gcd( int a, int b ) {
        // make sure params to computation are positive.
        if ( a < 0 ) {
            a = -a;
        }
        while ( b != 0 ) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }


    /**
     * Get numerator.
     *
     * @return the numerator.
     */
    int getNumerator() {
        //TODO implement getNumerator
        return 1;
    }

    /**
     * Get denominator.
     *
     * @return the denominator.
     */
    int getDenominator() {
        //TODO implement getDenominator
        return 1;
    }
    
    /**
     * Multiply with Fraction.
     *
     * @param other Fraction
     * @return new Multiplied Fraction
     */
    public Fraction times( Fraction other ) {
        //TODO implement times
        return new Fraction( 1, 1 );
    }
    
    //TODO implement all features of part 1,2,3
    
}

