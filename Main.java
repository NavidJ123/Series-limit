import java.math.*;
import java.math.BigDecimal;

public class Main {

    public static void main(String args[]) {
        boolean inf = false;
        try {
            double start=0;
            for(int i = 1; i < 100000; i++){
                start = meth(i);
                Fraction fraction = convertToFraction(start);
                System.out.println(fraction.getNumerator() + "/" + fraction.getDenominator());
                System.out.println(start);
                if(start > 1000000000){
                    inf = true;
                }
            }
        }
        catch (NumberFormatException e){
            if (inf){
                System.out.println("Infinite");
            }
            else{
                System.out.println("Zero");
            }

        }



    }

    public static double meth(int in){
        BigDecimal factoral = factoral(in);
        BigDecimal devision = new BigDecimal(Math.pow(1,1));

        double top = Math.log(in + 3);
        double bottom = Math.pow(in,1.0/6.0);

        return top/bottom;
    }

    public static BigDecimal factoral(int n) {
        BigDecimal factorial = new BigDecimal(1.0);
        for (int i = 1; i <= n; i++) {
            factorial = factorial.multiply(BigDecimal.valueOf(i));
        }
        return factorial;
    }

    public static Fraction convertToFraction(double decimalNumber) {
        final double EPSILON = 1.0E-10;
        double x = Math.abs(decimalNumber);
        double h1 = 1;
        double h2 = 0;
        double k1 = 0;
        double k2 = 1;
        double b = x;

        do {
            double a = Math.floor(b);
            double aux = h1;
            h1 = a * h1 + h2;
            h2 = aux;
            aux = k1;
            k1 = a * k1 + k2;
            k2 = aux;
            b = 1 / (b - a);
        } while (Math.abs(x - h1 / k1) > x * EPSILON);

        BigInteger numerator = BigInteger.valueOf((long) (decimalNumber >= 0 ? h1 : -h1));
        BigInteger denominator = BigInteger.valueOf((long) k1);
        return new Fraction(numerator, denominator);
    }


}
