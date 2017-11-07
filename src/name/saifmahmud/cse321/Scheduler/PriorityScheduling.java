package name.saifmahmud.cse321.Scheduler;

import name.saifmahmud.cse321.Process;

import java.util.ArrayList;
import java.util.Queue;

public class PriorityScheduling extends Abstract
{
    public PriorityScheduling(Queue<Process> processes)
    {
        super(processes);
    }

    @Override
    public void run() throws InterruptedException
    {
        int total = getTotalProcessTime();

        for (int i = 0; i <= total; i++)
        {
            ArrayList<Process> toRemove = new ArrayList<>();

            for (Process p : processes)
            {
                if (p.burst == 0)
                {
                    p.end = i;

                    finished.add(p);
                    toRemove.add(p);

                    continue;
                }

                if (p.start == -1)
                {
                    p.start = i;
                }

                for (Process pp : processes)
                {
                    if (pp.equals(p) || toRemove.contains(pp))
                    {
                        continue;
                    }

                    pp.wait++;
                }

                p.burst--;
                break;
            }

            processes.removeAll(toRemove);
        }

        printProcessTimings();
    }
}
