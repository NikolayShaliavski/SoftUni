using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class StringsAndObjects
{
    static void Main()
    {
         string firstString = "Hello";
            string secondString = "World";
            object objectString = (firstString + " " + secondString);
            string fullstring = (string)objectString;
            Console.WriteLine(fullstring);
    }
}
