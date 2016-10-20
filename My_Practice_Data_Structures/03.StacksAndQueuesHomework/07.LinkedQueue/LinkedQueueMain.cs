using System;

class LinkedQueueMain
{
    static void Main(string[] args)
    {
        LinkedQueue<int> testQueue = new LinkedQueue<int>();

        testQueue.Enqueue(1);
        testQueue.Enqueue(2);
        testQueue.Enqueue(3);
        testQueue.Enqueue(4);
        testQueue.Enqueue(5);

        //testQueue.Dequeue();
        //testQueue.Dequeue();

        PrintQueue(testQueue);
    }

    private static void PrintQueue(LinkedQueue<int> testQueue)
    {
        foreach (var num in testQueue)
        {
            Console.Write(num + " ");
        }
        Console.WriteLine();
    }
}

