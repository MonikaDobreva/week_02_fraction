package fraction;

import java.util.Objects;

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

public class Fraction implements Comparable<Fraction> {

    private final int numerator;
    private final int denominator;
    
    
    /**
     * Create a Fraction.
     *
     * @param numerator numerator
     * @param denominator denominator
     */
    public Fraction( int numerator, int denominator ) {
        if (denominator == 0) {
            throw new IllegalArgumentException("The denominator cannot be equal to zero!");
        }

        int gcd = gcd(numerator, denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
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
        return this.numerator;
    }

    /**
     * Get denominator.
     *
     * @return the denominator.
     */
    int getDenominator() {
        return this.denominator;
    }

    /**
     * Multiply with Fraction.
     *
     * @param other Fraction
     * @return new Multiplied Fraction
     */
    public Fraction times( Fraction other ) {
        return times(other.getNumerator(), other.getDenominator());
    }

    public Fraction times(int otherN, int otherD){
        int numerator = this.numerator * otherN;
        int denominator = this.denominator * otherD;
        return new Fraction(numerator, denominator);
    }

    @Override
    public int compareTo(Fraction frac) {
        float first = (float) this.numerator / this.denominator;
        float second = (float) frac.getNumerator() / frac.getDenominator();
        if (first == second) {
            return 0;
        } else if (first > second) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        if (this.denominator == 1) {
            return this.numerator + "";
        }
        int result = this.numerator / this.denominator;

        if (Math.abs(this.numerator) > Math.abs(this.denominator)) {
            if(this.numerator < 0 && this.denominator > 0){
                return "-(" + Math.abs(result) + "+(" + Math.abs(this.numerator % this.denominator) + "/" + Math.abs(this.denominator) + "))";
            }
            if(this.numerator < 0 && this.denominator < 0){
                return "(" + Math.abs(result) + "+(" + Math.abs(this.numerator % this.denominator) + "/" + Math.abs(this.denominator) + "))";
            }
            if(this.numerator > 0 && this.denominator < 0){
                return "-(" + Math.abs(result) + "+(" + Math.abs(this.numerator % this.denominator) + "/" + Math.abs(this.denominator) + "))";
            }
            return "(" + result + "+(" + this.numerator % this.denominator + "/" + this.denominator + "))";
        }
        if (this.numerator < 0 && this.denominator < 0){
            return "(" + Math.abs(this.numerator) + "/" + Math.abs(this.denominator) + ")";
        }
        if (this.numerator < 0 && this.denominator > 0){
            return "-(" + Math.abs(this.numerator) + "/" + Math.abs(this.denominator) + ")";
        }
        if (this.numerator > 0 && this.denominator < 0){
            return "-(" + Math.abs(this.numerator) + "/" + Math.abs(this.denominator) + ")";
        }

        return "(" + this.numerator + "/" + this.denominator + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return numerator == fraction.numerator && denominator == fraction.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }


    
}

