package name.saifmahmud.cse321;

import javafx.collections.transformation.SortedList;
import name.saifmahmud.cse321.Scheduler.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

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

        /*--- Priority Scheduling ---*/
        priority();
        System.out.println();

        /*--- Round Robin ---*/
        rr();
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
        PriorityQueue<Process> processes = new PriorityQueue<>(Comparator.comparingInt(p -> p.burst));

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

    static void priority() throws FileNotFoundException, InterruptedException
    {
        System.out.println("Priority Scheduling:");

        FileInputStream file = new FileInputStream("priority.txt");
        Scanner scan = new Scanner(file);

        int count = scan.nextInt();
        PriorityQueue<Process> processes = new PriorityQueue<>(Comparator.comparingInt(p -> p.priority));

        for (int i = 0; i < count; i++)
        {
            Process process = new Process();

            process.id = i + 1;
            process.burst = scan.nextInt();
            process.priority = scan.nextInt();

            processes.add(process);
        }

        PriorityScheduling scheduler = new PriorityScheduling(processes);
        scheduler.run();
    }

    static void rr() throws FileNotFoundException, InterruptedException
    {
        System.out.println("Round Robin:");

        FileInputStream file = new FileInputStream("rr.txt");
        Scanner scan = new Scanner(file);

        int count = scan.nextInt();
        Queue<Process> processes = new LinkedList<>();

        for (int i = 0; i < count; i++)
        {
            Process process = new Process();

            process.id = i + 1;
            process.burst = scan.nextInt();

            processes.add(process);
        }

        RoundRobin scheduler = new RoundRobin(processes);
        scheduler.run();
    }
}
