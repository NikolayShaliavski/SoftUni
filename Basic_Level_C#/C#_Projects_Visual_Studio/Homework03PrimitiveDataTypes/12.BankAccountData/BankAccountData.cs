using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class BankAccountData
{
    static void Main()
    {
        Console.WriteLine("Bank Account");

        string firstName = "Nikolay";
        string secondName = "Yordanov";
        string lastName = "Shalyavski";

        Console.WriteLine("Holder name: " + firstName + " " + secondName + " " + lastName + ".");

        int balance = 520;

        Console.WriteLine("Balance: " + "{0:C2}", balance);

        string bankName = "First Investment Bank";

        Console.WriteLine("Bank name: " + bankName + ".");

        string iBaN = "STSA0123BG653954423";

        Console.WriteLine("IBAN: " + iBaN + ".");

        string firstCardNumber = "6548 0102 3654 9865";
        string secondCardNumber = "6598 3266 0015 2010";
        string thirdCardNumber = "8965 3624 7878 0745";

        Console.WriteLine("Credit card number: ");
        Console.WriteLine("1: " + firstCardNumber + ".");
        Console.WriteLine("2: " + secondCardNumber + ".");
        Console.WriteLine("3: " + thirdCardNumber + ".");
    }
}

