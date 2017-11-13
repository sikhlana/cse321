package name.saifmahmud.cse321;

public class Consumer extends ProducerConsumer
{
    private static int uuid = 0;

    int id = ++uuid;

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                full.acquire();
                lock.acquire();

                items.remove().consumed(this);

                lock.release();
                empty.release();

                sleep((long) (Math.random() * 2000));
            }
            catch (InterruptedException e) { e.printStackTrace(); break; }
        }
    }
}
