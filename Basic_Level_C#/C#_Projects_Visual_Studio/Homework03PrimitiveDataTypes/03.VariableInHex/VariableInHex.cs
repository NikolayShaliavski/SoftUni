using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class VariableInHex
{
    static void Main()
    {
        int numberInDecimal = 254;
        string numberInHexadecimal = numberInDecimal.ToString("X");
        Console.WriteLine(numberInHexadecimal);
    }
}
