package name.saifmahmud.cse321.Scheduler;

import name.saifmahmud.cse321.Process;

import java.util.Queue;

public class RoundRobin extends Abstract
{
    public static final int QUANTUM = 4;

    public RoundRobin(Queue<Process> processes)
    {
        super(processes);
    }

    @Override
    public void run() throws InterruptedException
    {
        int time = 0;

        while (!processes.isEmpty())
        {
            Process p = processes.remove();

            for (int i = 0; i < QUANTUM; i++)
            {
                if (p.burst == 0)
                {
                    break;
                }

                if (p.start == -1)
                {
                    p.start = time;
                }

                for (Process pp : processes)
                {
                    if (pp.equals(p))
                    {
                        continue;
                    }

                    pp.wait++;
                }

                p.burst--;
                time++;
            }

            if (p.burst > 0)
            {
                processes.add(p);
            }
            else
            {
                p.end = time;
                finished.add(p);
            }
        }

        printProcessTimings();
    }
}
