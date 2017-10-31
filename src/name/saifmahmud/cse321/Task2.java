package name.saifmahmud.cse321;

public class Task2
{
    private static int i = 1;

    public static void main(String[] args) throws InterruptedException
    {
        Thread f = new Thread();
        Thread s = new Thread();

        f.setName("Thread #1");
        s.setName("Thread #2");

        f.start();
        s.start();

        f.join();
        s.join();
    }

    public static int i()
    {
        return i++;
    }

    private static class Thread extends java.lang.Thread
    {
        private final static Object lock = new Object();

        @Override
        public void run()
        {
            synchronized (lock)
            {
                while (true)
                {
                    if (Task2.i > 30)
                    {
                        lock.notify();
                        break;
                    }

                    System.out.println('\n' + getName() + '\n');

                    for (int i = 0; i < 10; i++)
                    {
                        System.out.println(Task2.i());
                    }

                    try
                    {
                        lock.notify();
                        lock.wait();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
