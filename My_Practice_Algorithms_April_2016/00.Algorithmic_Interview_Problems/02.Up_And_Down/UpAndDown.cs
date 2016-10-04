using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _02.Up_And_Down
{
    class UpAndDown
    {
        static void Main(string[] args)
        {

            int inputNumber = 499;

            int left = 1;
            int right = 1000;
            int counter = 0;

            int outputNumber = 0;

            while (left <= right)
            {
                counter++;
                int mid = (left + right) / 2;
                int result = GuessNum(mid, inputNumber);
                if (result == 0)
                {
                    outputNumber = mid;
                    break;
                }
                else if (result == -1)
                {
                    left = mid + 1;
                }
                else
                {
                    right = mid - 1;
                }
            }
            Console.WriteLine(outputNumber);
            Console.WriteLine(counter);
        }

        static int GuessNum(int numberToTest, int inputNumber)
        {
            if (inputNumber == numberToTest)
            {
                return 0;
            }
            else if (inputNumber > numberToTest)
            {
                return -1;
            }
            else
            {
                return 1;
            }
        }
    }
}
