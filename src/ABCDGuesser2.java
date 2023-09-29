import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ABCDGuesser2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser2() {
    }

    /**
     * Number used to approximate constant in loop.
     */
    public static final double NEGATIVE_FIVE = -5;
    /**
     * Number used to approximate constant in loop.
     */
    public static final double NEGATIVE_FOUR = -4;
    /**
     * Number used to approximate constant in loop.
     */
    public static final double NEGATIVE_THREE = -3;
    /**
     * Number used to approximate constant in loop.
     */
    public static final double NEGATIVE_TWO = -2;
    /**
     * Number used to approximate constant in loop.
     */
    public static final double NEGATIVE_ONE = -1;
    /**
     * Number used to approximate constant in loop.
     */
    public static final double NEGATIVE_ONEHALF = -1 / 2;
    /**
     * Number used to approximate constant in loop.
     */
    public static final double NEGATIVE_ONETHIRD = -1 / 3;
    /**
     * Number used to approximate constant in loop.
     */
    public static final double NEGATIVE_ONEFOURTH = -1 / 4;
    /**
     * Number used to approximate constant in loop.
     */
    public static final double ONEFOURTH = 1 / 4;
    /**
     * Number used to approximate constant in loop.
     */
    public static final double ONEHALF = 1 / 2;
    /**
     * Number used to approximate constant in loop.
     */
    public static final double ONETHIRD = 1 / 3;
    /**
     * Number used to approximate constant in loop.
     */
    public static final double THREE = 3;
    /**
     * Number used to approximate constant in loop.
     */
    public static final double FOUR = 4;
    /**
     * Number used to approximate constant in loop.
     */
    public static final double FIVE = 5;

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {

        //declaring initial variables
        String userIn;
        double userConstant = -1;

        //entering loop which only ends when the user inputs a valid number
        while (userConstant < 0) {
            //asking and getting user input
            out.print(
                    "Please enter a positive real number to be aproximated: ");
            userIn = in.nextLine();
            //checking if the user input is a double and if true, giving it as a
            //value to double userConstant
            if (FormatChecker.canParseDouble(userIn)) {
                userConstant = Double.parseDouble(userIn);
            }

            if (userConstant < 0) {
                out.println("Input invalid. Please select another number");

            }
        }
        //returning the final value
        return userConstant;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        //declaring initial variables
        String userIn;
        double userNum = 0;

        //entering loop which only ends when the user inputs a valid number
        while (userNum < 1) {
            //asking and getting user input
            out.print(
                    "Please enter a positive real number that is bigger then 1: ");
            userIn = in.nextLine();
            //checking if the user input is a double and if true, giving it as a
            //value to double userConstant
            if (FormatChecker.canParseDouble(userIn)) {
                userNum = Double.parseDouble(userIn);
            }

            if (userNum < 1) {
                out.println("Input invalid. Please select another number");

            }
        }
        //returning the final value
        return userNum;
    }

    /**
     * Method that will approximate the chosen number by using the de Jager
     * formula.
     *
     * @param w
     *            User special number 1.
     * @param x
     *            User special number 2.
     * @param y
     *            User special number 3.
     * @param z
     *            User special number 4.
     * @param u
     *            User constant.
     * @param nums
     *            Array with the numbers needed for the formula loop.
     * @return finalApp, the final approximation.
     */
    private static double getApproximation(double w, double x, double y,
            double z, double u, double[] nums) {

        //initial approximation
        double finalApp = 0;
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            //starting the de jager formula by raising the first special number
            //to the power of one of the numbers in the array
            double a = Math.pow(w, nums[i]);
            //same thing as above for the next 2 inner loops
            for (int j = 0; j < len; j++) {
                double b = Math.pow(x, nums[j]);
                for (int k = 0; k < len; k++) {
                    double c = Math.pow(y, nums[k]);
                    for (int l = 0; l < len; l++) {
                        double d = Math.pow(z, nums[l]);
                        //doing the de Jager formula
                        double currApp = a * b * c * d;
                        //checking to see if the result fro the de Jager is better
                        //then the approximation that the program has. If so,
                        //replace the regular approximation with the approximation
                        //that was calculated
                        if (Math.abs(u - currApp) < Math.abs(u - finalApp)) {
                            finalApp = currApp;
                        }
                    }
                }
            }
        }

        //return the finalized application
        return finalApp;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {

        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        //Numbers used to approximate the constant inside the loop
        double[] loopNumbers = { NEGATIVE_FIVE, NEGATIVE_FOUR, NEGATIVE_THREE,
                NEGATIVE_TWO, NEGATIVE_ONE, NEGATIVE_ONEHALF, NEGATIVE_ONETHIRD,
                NEGATIVE_ONEFOURTH, 0, ONEFOURTH, ONETHIRD, ONEHALF, 1, 2,
                THREE, FOUR, FIVE };

        //getting the user constant from the method
        double userConstant = getPositiveDouble(in, out);

        //getting the user special numbers from the method
        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);

        //using the method above to get the approximation on the number
        double finalApp = getApproximation(w, x, y, z, userConstant,
                loopNumbers);

        out.println("User constant was: " + userConstant);
        out.println("Program approximation was: " + finalApp);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
