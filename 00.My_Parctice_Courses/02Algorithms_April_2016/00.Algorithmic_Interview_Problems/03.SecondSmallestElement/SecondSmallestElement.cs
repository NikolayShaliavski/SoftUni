using System;

namespace _03.SecondSmallestElement
{
    class SecondSmallestElement
    {
        static void Main(string[] args)
        {
            //algorithm with O(n) complexity -> only one iteration throw the elements
            int[] nums = { 1, 2, 3, 4, 5 };

            int smallest = int.MaxValue;
            int secondSmallest = int.MaxValue;

            for (int i = 0; i < nums.Length; i++)
            {
                //check for smaller element
                if (nums[i] < smallest)
                {
                    secondSmallest = smallest;//if we found smaller element secondSmallest became previuous smallest
                    smallest = nums[i];//save smallest element
                }
                else if (nums[i] < secondSmallest)//if we cannot found smaller element, secondSmallest is the smallest element 
                {                                 //from the rest part of the array
                    secondSmallest = nums[i];
                }
            }

            Console.WriteLine(secondSmallest);
        }
    }
}
