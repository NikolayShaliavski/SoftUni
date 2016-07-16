using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NumberSequence
{
    class NumberSequence
    {
        static void Main()
        {
            for (int i = 2; i <= 10; i++)
            {
                if (i % 2 ==0)
                {
                    Console.Write(i + ", ");
                }
                else
                {
                    Console.Write(-i + ", ");
                }
            }
            Console.WriteLine("-11");
        }
    }
}
