using System;
using System.Collections.Generic;
using System.Linq;

class ReverseNumbersWithStack
{
    static void Main()
    {
        var nums = Console.ReadLine().Split().Select(int.Parse).ToArray();
        Stack<int> numsStack  = new Stack<int>();

        for (int i = 0; i < nums.Length; i++)
        {
            numsStack.Push(nums[i]);
        }

        while (numsStack.Count > 0)
        {
            Console.WriteLine(numsStack.Pop());
        }
    }
}
