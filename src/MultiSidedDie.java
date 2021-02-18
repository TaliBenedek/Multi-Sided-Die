
import java.util.*;
import java.io.*;

/**
 *
 * @author TBenedek
 */
public class MultiSidedDie
{
    private final static Scanner kbd = new Scanner(System.in);
    private final static Random randNum = new Random();
    private final static int numOfSides = 20;

    public static void main(String[] args)
    {
        System.out.println("This program simulates a multi sided die getting tossed.");
        Boolean done = false;
        while (!done)
        {
            try
            {
                System.out.println("What should be the name of the file that saves the die tosses? ");
                String fileName = kbd.next();
                PrintWriter file = createFile(fileName);
                int numOfTosses = tossDie(file);
                int[] frequencies = calculateFrequencies(fileName);
                displayFrequencies(frequencies);
                displayMinAndMax(frequencies);
                displayAverage(frequencies, numOfTosses);
                done = true;
            }
            catch (Exception e)
            {
                System.out.println(e.getStackTrace());
            }

        }
    }

    private static PrintWriter createFile(String file)
    {
        PrintWriter outputFile = null;
        try
        {
            outputFile = new PrintWriter(file);
        }
        catch (Exception e)
        {
            System.out.println(e.getStackTrace());
        }
        return outputFile;
    }

    private static int tossDie(PrintWriter file)
    {
        int numberOfTosses = UI.getInt(UI.Source.SCANNER,"How many times "
                + "should the die be tossed? ", 100, 12000);
        for (int j = 0; j < numberOfTosses; j++)
        {
            int num = randNum.nextInt(numOfSides) + 1;
            file.println(num);
        }
        file.close();
        return numberOfTosses;
    }

    private static int[] calculateFrequencies(String file)
    {
        int[] frequencyOfFaces = null;
        try
        {
            File tossesFile = new File(file);
            Scanner inputFile = new Scanner(tossesFile);
            frequencyOfFaces = new int[numOfSides];
            while (inputFile.hasNext())
            {
                int face = inputFile.nextInt();
                ++frequencyOfFaces[face - 1];;
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getStackTrace());
        }
        return frequencyOfFaces;
    }

    private static void displayFrequencies(int[] frequencies)
    {
        try
        {

            System.out.println("Face\tNumber of Rolls\n----    ---------------");
            int index = 1;
            for (int val : frequencies)
            {
                System.out.printf("%2d\t%d\n", index, val);
                index++;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getStackTrace());
        }
    }

    private static void displayMinAndMax(int[] frequencies)
    {
        int min = frequencies[0];
        int max = frequencies[0];
        int face1 = 1;
        int face2 = 1;
        for (int index = 1; index < frequencies.length; index++)
        {
            if (frequencies[index] < min)
            {
                min = frequencies[index];
                face1 = index + 1;
            }
            if (frequencies[index] > max)
            {
                max = frequencies[index];
                face2 = index + 1;
            }
        }
        System.out.println(face1 + " was rolled the least.\n" + face2 + " was rolled the most." );
        for (int index = 0; index < frequencies.length; index++)
        {
            if (frequencies[index] == min && (index + 1) != face1)
            {
                System.out.println((index + 1)
                        + " was also rolled the least.");
            }
            if (frequencies[index] == max && (index + 1) != face2)
            {
                System.out.println((index + 1)
                        + " was also rolled the most.");
            }
        }
    }

    private static void displayAverage(int[] list, int num)
    {
        int face = 1;
        double total = 0;
        for (int val : list)
        {
            total += (face * val);
            face++;
        }
        System.out.printf("The average number tossed was %.2f.\n", (total / num));
    }

}
