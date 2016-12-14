using System;

class ReversedListMain
{
    static void Main()
    {
        ReversedList<int> testList = new ReversedList<int>();

        for (int i = 1; i <= 20; i++)
        {
            testList.Add(i);
        }

        int res = testList.Remove(testList.Count - 1);
        Console.WriteLine(res);
        PrintList(testList);
    }

    static void PrintList(ReversedList<int> list)
    {
        foreach (var num in list)
        {
            Console.Write(num + " ");
        }
        Console.WriteLine();
    }
}

