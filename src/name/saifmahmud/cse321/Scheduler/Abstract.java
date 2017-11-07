package name.saifmahmud.cse321.Scheduler;

import name.saifmahmud.cse321.Process;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public abstract class Abstract
{
    protected Queue<Process> processes;
    protected PriorityQueue<Process> finished = new PriorityQueue<>(Comparator.comparingInt(p -> p.id));

    public Abstract(Queue<Process> processes)
    {
        this.processes = processes;
    }

    abstract public void run() throws InterruptedException;

    void printHeading()
    {
        System.out.println("Id    Time    WaitT    StartT    EndT");
        System.out.println("-------------------------------------");
    }

    void printProcessTimings()
    {
        printHeading();

        while (!finished.isEmpty())
        {
            printProcess(finished.remove());
        }
    }

    void printProcess(Process p)
    {
        System.out.printf(
                "% 2d % 7d % 8d % 9d % 7d\n",
                p.id, p.end - p.start, p.wait, p.start, p.end
        );
    }

    int getTotalProcessTime()
    {
        int total = 0;

        for (Process p : processes)
        {
            total += p.burst;
        }

        return total;
    }
}
