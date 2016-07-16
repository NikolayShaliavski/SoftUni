using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class DreamItem
{
    static void Main()
    {
        string inputLine = Console.ReadLine();

        string[] inputArray = inputLine.Split('\\');

        string month = inputArray[0];
        decimal levaPerHour = Convert.ToDecimal(inputArray[1]);
        decimal hoursPerDay = Convert.ToDecimal(inputArray[2]);
        decimal priceforItem = Convert.ToDecimal(inputArray[3]);

        decimal workDays = 0;
        if (month == "Jan" || month == "March" || month == "May" || 
            month == "July" || month == "Aug" || month == "Oct" || month == "Dec")
        {
            workDays = (31 - 10);
        }
        else if (month == "Feb")
        {
            workDays = (28 - 10);
        }
        else if (month == "Apr" || month == "June" || month == "Sept" || month == "Nov")
        {
            workDays = (30 - 10);
        }
        decimal salary = (workDays * levaPerHour * hoursPerDay);

        if (salary > 700)
        {
            salary += salary / 10;
        }
        if (salary >= priceforItem)
        {
            Console.WriteLine("Money left = {0:F2} leva.", (salary - priceforItem));
        }
        else
        {
            Console.WriteLine("Not enough money. {0:F2} leva needed.", (priceforItem - salary));
        }
    }
}

