package fraction;

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assumptions.assumeThat;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author Pieter van den Hombergh {@code p.vandehombergh@gmail.com}
 */
@TestMethodOrder( OrderAnnotation.class )
public class FractionTest {

    /**
     * Test getters for numerator and denominator.
     * Use SoftAssertions.assertSoftly to always test both getters even if the
     * first assert fails.
     *
     * @param a     num in frac
     * @param b     denom in frac
     * @param numerator   expected
     * @param denominator expected
     */
    @Order( 1 )
    //@Disabled("Think TDD")
    @ParameterizedTest
    @CsvSource( {
        // message, a,b, num, denom
        "half,1,2,1,2",
        "one third,2,6,1,3",
        "minus half,9,-18,-1,2", } )
    void t1Getters( String message, int a, int b, int numerator, int denominator ) {
        Fraction fraction = new Fraction(numerator, denominator);
        SoftAssertions.assertSoftly(softly -> {
            assumeThat(numerator).as(message).isEqualTo(fraction.getNumerator());
            assumeThat(denominator).as(message).isEqualTo(fraction.getDenominator());
        });
        //fail("method testGetters reached end. You know what to do.");
    }

    /**
     *
     * @param message  per test
     * @param expected value
     * @param num      input
     * @param denom    input
     */
    //@Disabled( "Think TDD" )
    @Order( 2 )
    @ParameterizedTest
    @CsvSource( {
        "whole number, 2, 2, 1 ",
        "two thirds, (2/3), 2, 3",
        "one third, (1/3), -3, -9 ",
        "minus two fifths, -(2/5), 12, -30",
        "one + two fifths, -(1+(2/5)), 35, -25 "
    } )
    void t2fractionToString( String message, String expected, int num, int denom ) {
        Fraction fraction = new Fraction(num, denom);
        assertThat(fraction.toString())
                .isEqualTo(expected);
        //fail("fractionToString completed succesfully; you know what to do");
    }

    /**
     * 
     */
    final Map<String, BiFunction<Fraction, Fraction, Fraction>> operations = Map.of(
            "times", ( f1, f2 ) -> f1.times( f2 ) // on value given
            //TODO add more named lamddas to the map
    
    );

    /**
     * Test the operations defined on fraction. Expected result is expressed as
     * a String.
     *
     * @param args test data object
     */
    @Order( 3 )
//    //@Disabled("Think TDD")
    @ParameterizedTest
    @CsvSource(
             {
                // msg, opname, expected, a,b,c,d
                "'one half times one third is 1 sixth','times',   '(1/6)',   1,2,1,3",
                 } )
    void t3FractionOps( String message, String operation, String expected, int a,
                       int b, int c, int d ) {
        BiFunction<Fraction, Fraction, Fraction> op = operations.get( operation );
        // ignore the test if opName not found
        assumeThat( op ).isNotNull();
        Fraction fraction = new Fraction(a, b);
        Fraction frac = new Fraction(c, d);
        Fraction result = op.apply(fraction, frac);

        SoftAssertions.assertSoftly(softly -> {
            assumeThat(result.toString()).as(message).isEqualTo(expected);
        });
        //fail( "tFractionOps completed succesfully; you know what to do" );
    }

    /**
     * Make sure the fraction throws an IllegalArgumentException
     * when denom == 0.
     */
    @Order( 4 )
    //@Disabled("Think TDD")
    @Test
    void t4DivideByZeroNotAllowed() {
        //TODO 
        fail( "tDivideByZeroNotAllowed completed succesfully; you know what to do" );
    }

    /**
     * Test that the one parameter variant of frac creates a Fraction with a
     * denominator of 1.
     */
    @Order( 5 )
    //@Disabled("Think TDD")
    @Test
    public void t5IntAsFrac() {
        //TODO test frac(int) and indirectly new Fraction(int).
        fail( "tIntAsFrac completed succesfully; you know what to do" );
    }

    /**
     * Use test helper method {
     *
     * @see verifyEqualsAndHashCode}
     *
     */
    @Order( 7 )
    //@Disabled("Think TDD")
    @Test
    public void t7EqualsHashCode() {
        //TODO create enough fraction objects to invoke helper method verifyEqualsAndHashCode
        fail( "tEqualsHasCode completed succesfully; you know what to do" );
    }

    /**
     * The fraction should be comparable.Create a fraction and test signum of
     * compareTo result.
     *
     * @param msg    message
     * @param a      num in 1st frac
     * @param b      denum in 1st frac
     * @param c      num in 2nd frac
     * @param d      denom in 2nd frac
     * @param signum expected sign or 0 if equal.
     */
    @Order( 8 )
    //@Disabled("Think TDD")
    @ParameterizedTest
    @CsvSource( {
        "equal,   1,2,2,4,0",
        "less,    1,2,3,4,-1",
        "greater, 1,2,1,3,1", } )
    public void t8Comparable( String msg, int a, int b, int c, int d, int signum ) {
        //TODO create fractions and test compareTo
        fail( "tComparable completed succesfully; you know what to do" );
    }

    /**
     * Helper for equals tests, which are tedious to get completely covered.
     *
     * @param <T>     type of class to test
     * @param ref     reference value
     * @param equal   one that should test equals true
     * @param unEqual list of elements that should test unequal in all cases.
     */
    public static <T> void verifyEqualsAndHashCode( T ref, T equal, T... unEqual ) {
        Object object = "Hello";
        T tnull = null;
        String cname = ref.getClass().getCanonicalName();
        // I got bitten here, assertJ equalTo does not invoke equals on the
        // object when ref and 'other' are same.
        // THAT's why the first one differs from the rest.
        SoftAssertions.assertSoftly( softly -> {
            softly.assertThat( ref.equals( ref ) )
                    .as( cname + ".equals(this): with self should produce true" )
                    .isTrue();
            softly.assertThat( ref.equals( tnull ) )
                    .as( cname + ".equals(null): ref object "
                            + safeToString( ref ) + " and null should produce false"
                    )
                    .isFalse();
            softly.assertThat( ref.equals( object ) )
                    .as( cname + ".equals(new Object()): ref object"
                            + " compared to other type should produce false"
                    )
                    .isFalse();
            softly.assertThat( ref.equals( equal ) )
                    .as( cname + " ref object [" + safeToString( ref )
                            + "] and equal object [" + safeToString( equal )
                            + "] should report equal"
                    )
                    .isTrue();
            for ( int i = 0; i < unEqual.length; i++ ) {
                T ueq = unEqual[ i ];
                softly.assertThat( ref )
                        .as( "testing supposed unequal objects" )
                        .isNotEqualTo( ueq );
            }
            // ref and equal should have same hashCode
            softly.assertThat( ref.hashCode() )
                    .as( cname + " equal objects "
                            + ref.toString() + " and "
                            + equal.toString() + " should have same hashcode"
                    )
                    .isEqualTo( equal.hashCode() );
        } );
    }


    /**
     * ToString that deals with any exceptions that are thrown during its
     * invocation.
     *
     * When x.toString triggers an exception, the returned string contains a
     * message with this information plus class and system hashCode of the
     * object.
     *
     * @param x to turn into string or a meaningful message.
     * @return "null" when x==null, x.toString() when not.
     */
    public static String safeToString( Object x ) {
        if ( x == null ) {
            return "null";
        }
        try {
            return x.toString();
        } catch ( Throwable e ) {
            return "invoking toString on instance "
                    + x.getClass().getCanonicalName() + "@"
                    + Integer.toHexString( System.identityHashCode( x ) )
                    + " causes an exception " + e.toString();
        }
    }
}
