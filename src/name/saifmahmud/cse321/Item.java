package name.saifmahmud.cse321;

class Item
{
    private static int uuid = 0;

    private int producer;
    private int id = ++uuid;

    Item(Producer producer)
    {
        this.producer = producer.id;
        produced();
    }

    void produced()
    {
        System.out.println(
                String.format(
                        "Producer #%d has produced item #%05d.",
                        producer, id
                )
        );
    }

    void consumed(Consumer consumer)
    {
        System.err.println(
                String.format(
                        "Consumer #%d has consumed item #%05d produced by producer #%d.",
                        consumer.id, id, producer
                )
        );
    }
}
