using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class LinkedListMain
{
    static void Main()
    {
        LinkedList<int> testList = new LinkedList<int>();

        for (int i = 1; i <= 10; i++)
        {
            testList.AddLast(i);
        }
        //testList.AddLast(1);
        //testList.AddLast(2);
        //testList.AddLast(3);
        //testList.AddLast(4);
        //testList.AddLast(5);
        //testList.AddLast(5);
        //testList.AddLast(5);
        //testList.AddLast(3);
        //testList.AddFirst(5);
        //testList.Remove(testList.Count - 1);
        int res = testList.FirstIndexOf(10);
        Console.WriteLine(res);
        PrintList(testList);
    }

    private static void PrintList(LinkedList<int> testList)
    {
        foreach (var element in testList)
        {
            Console.Write(element + " ");
        }
        Console.WriteLine();
    }
}
