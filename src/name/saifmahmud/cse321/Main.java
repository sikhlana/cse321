package name.saifmahmud.cse321;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        FileInputStream stream = new FileInputStream("./input.txt");
        Scanner scan = new Scanner(stream);

        int processes = scan.nextInt();
        scan.nextLine();

        int resources = scan.nextInt();
        scan.nextLine();

        int[][] max = new int[processes][resources];
        int[][] alloc = new int[processes][resources];
        int[] available = new int[resources];

        for (int i = 0; i < processes; i++)
        {
            for (int j = 0; j < resources; j++)
            {
                max[i][j] = scan.nextInt();
            }

            scan.nextLine();
        }

        for (int i = 0; i < processes; i++)
        {
            for (int j = 0; j < resources; j++)
            {
                alloc[i][j] = scan.nextInt();
            }

            scan.nextLine();
        }

        for (int i = 0; i < resources; i++)
        {
            available[i] = scan.nextInt();
        }

        int[][] need = new int[processes][resources];
        generateNeedMatrix(need, alloc, max);

        System.out.println("Need Matrix:");
        printMatrix(need);

        try
        {
            char[] sequence = new char[processes];
            computeSafeSequence(sequence, need, alloc, available);

            System.out.println();
            System.out.println("Safe sequence is:");
            printSequence(sequence);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    private static void generateNeedMatrix(int[][] need, int[][] alloc, int[][] max)
    {
        for (int i = 0; i < alloc.length; i++)
        {
            int[] row = alloc[i];

            for (int j = 0; j < row.length; j++)
            {
                need[i][j] = max[i][j] - alloc[i][j];
            }
        }
    }

    private static void computeSafeSequence(char[] sequence, int[][] need, int[][] alloc, int[] available) throws Exception
    {
        int i = 0;
        boolean[] finished = new boolean[sequence.length];
        int count = 0;

        while (!allDone(finished))
        {
            if (count++ > sequence.length * available.length)
            {
                throw new Exception("No safe sequence found!");
            }

            for (int j = 0; j < need.length; j++)
            {
                if (finished[j])
                {
                    continue;
                }

                int[] work = need[j];

                if (!canProcess(available, work))
                {
                    continue;
                }

                addToAvailable(available, alloc[j]);
                sequence[i++] = (char) (65 + j);
                finished[j] = true;
                break;
            }
        }
    }

    private static void addToAvailable(int[] available, int[] work)
    {
        for (int i = 0; i < available.length; i++)
        {
            available[i] += work[i];
        }
    }

    private static boolean allDone(boolean[] finished)
    {
        for (boolean b : finished)
        {
            if (!b)
            {
                return false;
            }
        }

        return true;
    }

    private static boolean canProcess(int[] available, int[] resources)
    {
        for (int i = 0; i < available.length; i++)
        {
            if (available[i] < resources[i])
            {
                return false;
            }
        }

        return true;
    }

    private static int[] getDiff(int[] one, int[] two)
    {
        int[] diff = new int[one.length];

        for (int i = 0; i < one.length; i++)
        {
            diff[i] = one[i] - two[i];
        }

        return diff;
    }

    private static void printMatrix(int[][] matrix)
    {
        for (int[] row : matrix)
        {
            for (int cell : row)
            {
                System.out.print(cell + " ");
            }

            System.out.println();
        }
    }

    private static void printSequence(char[] sequence)
    {
        String[] seq = new String[sequence.length];

        for (int i = 0; i < seq.length; i++)
        {
            seq[i] = "" + sequence[i];
        }

        System.out.println("<" + String.join(", ", seq) + ">");
    }

    private static void printSequence(int[] sequence)
    {
        String[] seq = new String[sequence.length];

        for (int i = 0; i < seq.length; i++)
        {
            seq[i] = "" + sequence[i];
        }

        System.out.println("<" + String.join(", ", seq) + ">");
    }
}
