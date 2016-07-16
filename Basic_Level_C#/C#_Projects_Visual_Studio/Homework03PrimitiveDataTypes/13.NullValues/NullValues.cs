using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class NullValues
{
    static void Main()
    {
        int? a = null;
        double? d = null;

        Console.WriteLine(a);
        Console.WriteLine(d);

        int? a1 = int.MinValue;
        double? d1 = (double.MaxValue);

        Console.WriteLine(a1);
        Console.WriteLine(d1);
    }
}

