package name.saifmahmud.cse321.Scheduler;

import name.saifmahmud.cse321.Process;

import java.util.Queue;

public class FirstComeFirstServe extends Abstract
{
    public FirstComeFirstServe(Queue<Process> processes)
    {
        super(processes);
    }

    @Override
    public void run() throws InterruptedException
    {
        printHeading();
        int total = getTotalProcessTime();

        for (int i = 0; i <= total; i++)
        {
            Process p = processes.peek();

            if (p.burst == 0)
            {
                p.end = i;
                finished.add(p);

                processes.remove();
            }

            if (processes.isEmpty())
            {
                continue;
            }

            p = processes.peek();

            if (p.start == -1)
            {
                p.start = i;
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
        }

        printProcessTimings();
    }
}
