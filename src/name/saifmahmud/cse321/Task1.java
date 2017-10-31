package name.saifmahmud.cse321;

public class Task1
{
    public static void main(String[] args) throws InterruptedException
    {
        Thread[] threads = new Thread[5];

        for (int i = 0; i < threads.length; i++)
        {
            threads[i] = new Thread();
            threads[i].setName(String.format("Thread #%d", i + 1));
            threads[i].setPriority((int) Math.ceil(Math.random() * 9));
        }

        for (Thread t : threads)
        {
            t.start();
        }

        for (Thread t : threads)
        {
            t.join();

            for (int i = 0; i < threads.length; i++)
            {
                System.out.printf("Thread #%d is %s.\n", i + 1, threads[i].isAlive() ? "alive" : "dead");
            }

            System.out.println();
        }
    }

    private static class Thread extends java.lang.Thread
    {
        @Override
        public void run()
        {
            try
            {
                System.out.println(getName());
                sleep(1000L);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
