using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AgeAfterTenYears
{
    class AgeAfterTenYears
    {
        static void Main()
        {
            DateTime birthdayDate = DateTime.Parse(Console.ReadLine());
            DateTime dateNow = DateTime.Now;
            int myAgeNow1 = (dateNow.Year - birthdayDate.Year);
            int myAgeNow2 = (dateNow.Year - (birthdayDate.Year + 1));
            if (birthdayDate.DayOfYear < dateNow.DayOfYear)
            {
                Console.WriteLine("Now: " + myAgeNow1);
                Console.WriteLine("After 10 years: " + (myAgeNow1 + 10));
            }
            else
            {
                Console.WriteLine("Now: " + myAgeNow2);
                Console.WriteLine("After 10 years: " + (myAgeNow2 + 10));
            }
        }
    }
}
