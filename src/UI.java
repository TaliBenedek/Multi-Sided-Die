/**
 * A class to get a valid user input through three overloaded static methods:
 * getInt, getDouble, getString a good exercise for the next course is to
 * combine getInt and getDouble into a polymorphic getNumber
 *
 * @author Dr. Andrew Katz, professor at Touro College
 */
import java.util.Scanner;
import javax.swing.JOptionPane;

public class UI // User Input
{

    /**
     * source of input JOPTION - javax.swing.JOptionPane <br>
     * SCANNER - java.util.Scanner
     */
    public static enum Source
    {
        SCANNER, JOPTION
    };

    private static final Scanner KBD = new Scanner(System.in);

    //<editor-fold defaultstate="collapsed" desc="getInt methods">
    /**
     * getInt - no args version
     *
     * @return int: an integer supplied by the user
     */
    public static int getInt()
    {
        return getInt(Source.SCANNER);
    }

    /**
     * getInt - source specifying version
     *
     * @param source either SCANNER or JOPTION
     * @return int: an integer supplied by the user
     */
    public static int getInt(Source source)
    {
        return getInt(source, "Please enter an integer");
    }

    /**
     * getInt - user-supplied prompt version
     *
     * @param prompt : String displayed
     * @return int: an integer supplied by the user
     */
    public static int getInt(String prompt)
    {
        return getInt(Source.SCANNER, prompt);
    }

    /**
     * getInt - user specifies source and prompt
     *
     * @param source either SCANNER or JOPTION
     * @param prompt : String displayed
     * @return int: an integer supplied by the user
     */
    public static int getInt(Source source, String prompt)
    {
        return getInt(source, prompt, -Integer.MAX_VALUE, Integer.MAX_VALUE, false);
    }

    /**
     * getInt - user specifies source, prompt, and bounds
     *
     * @param source either SCANNER or JOPTION
     * @param prompt : String displayed
     * @param min : int minimal valid number
     * @param max : int maximal valid number
     * @return int: an integer supplied by the user
     */
    public static int getInt(Source source, String prompt, int min, int max)
    {
        return getInt(source, prompt, min, max, true);
    }

    /**
     * getInt - the actual work-horse no reason to expose him to the public
     *
     * @param source either SCANNER or JOPTION
     * @param prompt : String displayed
     * @param min : int minimal valid number
     * @param max : int maximal valid number
     * @param showBounds : boolean display the bounds or not
     * @return int: an integer supplied by the user
     */
    private static int getInt(
            Source source,
            String prompt,
            int min,
            int max,
            boolean showBounds)
    {
        int retVal = 0;
        String strUserInput = "";

        String fullPrompt = showBounds
                ? prompt + " between " + min + " and " + max
                : prompt;

        boolean done = false;
        do
        {
            strUserInput = getString(source, fullPrompt);
            try
            {
                retVal = Integer.parseInt(strUserInput);
                if (retVal < min || retVal > max)
                {
                    scoldUser(source, strUserInput + " is not a valid number");
                }
                else
                {
                    done = true;
                }
            }
            catch (Exception ex)
            {
                scoldUser(source, strUserInput + " is not a number");
            }
        } while (!done);

        return retVal;

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getDouble methods">
    /**
     * getDouble - no args version
     *
     * @return double: a double supplied by the user
     */
    public static double getDouble()
    {
        return getDouble(Source.SCANNER);
    }

    /**
     * getDouble - source specifying version
     *
     * @param source either SCANNER or JOPTION
     * @return double: a double supplied by the user
     */
    public static double getDouble(Source source)
    {
        return getDouble(source, "Please enter a number");
    }

    /**
     * getDouble - user-supplied prompt version
     *
     * @param prompt : String displayed
     * @return double: a double supplied by the user
     */
    public static double getDouble(String prompt)
    {
        return getDouble(Source.SCANNER, prompt);
    }

    /**
     * getDouble - user specifies source and prompt
     *
     * @param source either SCANNER or JOPTION
     * @param prompt : String displayed
     * @return double: a double supplied by the user
     */
    public static double getDouble(Source source, String prompt)
    {
        return getDouble(source, prompt, -Double.MAX_VALUE, Double.MAX_VALUE, false);
    }

    /**
     * getDouble - user specifies source, prompt, and bounds
     *
     * @param source either SCANNER or JOPTION
     * @param prompt : String displayed
     * @param min : double minimal valid number
     * @param max : double maximal valid number
     * @return double: a double supplied by the user
     */
    public static double getDouble(
            Source source,
            String prompt,
            double min,
            double max)
    {
        return getDouble(source, prompt, min, max, true);
    }

    /**
     * getDouble - the actual work-horse
     *
     * @param source either SCANNER or JOPTION
     * @param prompt : String displayed
     * @param min : double minimal valid number
     * @param max : double maximal valid number
     * @param showBounds : boolean display the bounds or not
     * @return double: a double supplied by the user
     */
    public static double getDouble(
            Source source,
            String prompt,
            double min,
            double max,
            boolean showBounds)
    {
        double retVal = 0;
        String strUserInput = "";

        String fullPrompt = showBounds
                ? prompt + " between " + min + " and " + max
                : prompt;

        boolean done = false;
        do
        {
            strUserInput = getString(source, fullPrompt);
            try
            {
                retVal = Double.parseDouble(strUserInput);
                if (retVal < min || retVal > max)
                {
                    scoldUser(source, strUserInput + " is not a valid number");
                }
                else
                {
                    done = true;
                }
            }
            catch (Exception ex)
            {
                scoldUser(source, strUserInput + " is not a number");
            }
        } while (!done);

        return retVal;

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getString method">
    public static String getString(Source source, String prompt)
    {
        String strUserInput = "";
        if (source == Source.JOPTION)
        {
            strUserInput = JOptionPane.showInputDialog(null, prompt);
        }
        else // Scanner
        {
            System.out.println(prompt);
            strUserInput = KBD.nextLine();
        }
        return strUserInput;
    }
    //</editor-fold>

    private static void scoldUser(Source src, String scold)
    {
        if (src == Source.SCANNER)
        {
            System.out.println(scold);
        }
        else //JOPTION
        {
            JOptionPane.showMessageDialog(null, scold);
        }
    }
}

