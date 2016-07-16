using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class ASCIITable
{
    static void Main()
    {
        Console.OutputEncoding = System.Text.Encoding.UTF8;

        for (int i = 0; i < 255; i++)
        {
            Console.Write((char)i);
        }
    }
}
