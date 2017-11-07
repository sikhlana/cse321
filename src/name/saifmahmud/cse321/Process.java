package name.saifmahmud.cse321;

public class Process
{
    public int id;
    public int arrival = 0;
    public int start = -1;
    public int end;
    public int wait;
    public int burst;
    public int priority = 1;

    public String toString()
    {
        return String.format("ID: %d; Arrival: %d; Burst: %d", id, arrival, burst);
    }
}
