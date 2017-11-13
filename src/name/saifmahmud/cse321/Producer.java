package name.saifmahmud.cse321;

public class Producer extends ProducerConsumer
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
                empty.acquire();
                lock.acquire();

                items.add(new Item(this));

                lock.release();
                full.release();

                sleep((long) (Math.random() * 2000));
            }
            catch (InterruptedException e) { e.printStackTrace(); break; }
        }
    }
}
