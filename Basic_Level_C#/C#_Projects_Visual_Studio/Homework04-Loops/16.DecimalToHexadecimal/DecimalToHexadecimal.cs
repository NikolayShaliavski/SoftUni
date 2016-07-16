using System;
using System.Text;

class DecimalToHexadecimal
{
    static void Main()
    {
        long input = long.Parse(Console.ReadLine());
        long digit = 0;
        StringBuilder hexa = new StringBuilder();

        do
        {
            digit = input % 16;
            if (digit >= 10)
            {
                switch (digit)
                {
                    case 10:
                        hexa.Append("A");
                        break;
                    case 11:
                        hexa.Append("B");
                        break;
                    case 12:
                        hexa.Append("C");
                        break;
                    case 13:
                        hexa.Append("D");
                        break;
                    case 14:
                        hexa.Append("E");
                        break;
                    case 15:
                        hexa.Append("F");
                        break;
                    default:
                        break;
                }
            }
            else
            {
                hexa.Append(digit);
            }
            input /= 16;
        } while (input >= 16);

        if (input >= 10)
        {
            switch (input)
            {
                case 10:
                    hexa.Append("A");
                    break;
                case 11:
                    hexa.Append("B");
                    break;
                case 12:
                    hexa.Append("C");
                    break;
                case 13:
                    hexa.Append("D");
                    break;
                case 14:
                    hexa.Append("E");
                    break;
                case 15:
                    hexa.Append("F");
                    break;
                default:
                    break;
            }
        }
        else
        {
            hexa.Append(input);
        }

        char[] hexaArray = hexa.ToString().ToCharArray();
        Array.Reverse(hexaArray);
        hexa = hexa.Clear();

        foreach (char h in hexaArray)
        {
            hexa.Append(h);
        }
        Console.WriteLine(hexa);
    }
}