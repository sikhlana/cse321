package name.saifmahmud.cse321;

import javafx.collections.transformation.SortedList;
import name.saifmahmud.cse321.Scheduler.Abstract;
import name.saifmahmud.cse321.Scheduler.FirstComeFirstServe;
import name.saifmahmud.cse321.Scheduler.ShortestJobFirst;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException, InterruptedException
    {
        /*--- First Come First Serve ---*/
        fcfs();
        System.out.println();

        /*--- Shortest Job First ---*/
        sjf();
        System.out.println();
    }

    static void fcfs() throws FileNotFoundException, InterruptedException
    {
        System.out.println("FCFS:");

        FileInputStream file = new FileInputStream("fcfs.txt");
        Scanner scan = new Scanner(file);

        int count = scan.nextInt();
        LinkedList<Process> processes = new LinkedList<>();

        for (int i = 0; i < count; i++)
        {
            Process process = new Process();

            process.id = i + 1;
            process.burst = scan.nextInt();

            processes.add(process);
        }

        FirstComeFirstServe scheduler = new FirstComeFirstServe(processes);
        scheduler.run();
    }

    static void sjf() throws FileNotFoundException, InterruptedException
    {
        System.out.println("SJF:");

        FileInputStream file = new FileInputStream("sjf.txt");
        Scanner scan = new Scanner(file);

        int count = scan.nextInt();
        PriorityQueue<Process> processes = new PriorityQueue<>(new Comparator<Process>() { public int compare(Process p1, Process p2) { return p1.burst - p2.burst; } });

        for (int i = 0; i < count; i++)
        {
            Process process = new Process();

            process.id = i + 1;
            process.arrival = scan.nextInt();
            process.burst = scan.nextInt();

            processes.add(process);
        }

        ShortestJobFirst scheduler = new ShortestJobFirst(processes);
        scheduler.run();
    }
}
