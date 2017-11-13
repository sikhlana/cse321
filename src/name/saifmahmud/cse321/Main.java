package name.saifmahmud.cse321;


public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        for (int i = 5; i >= 0; i--)
        {
            Thread thread = new Producer();
            thread.start();

            Thread.sleep((long) (Math.random() * 1000));

            thread = new Consumer();
            thread.start();

            Thread.sleep((long) (Math.random() * 1000));
        }
    }
}
