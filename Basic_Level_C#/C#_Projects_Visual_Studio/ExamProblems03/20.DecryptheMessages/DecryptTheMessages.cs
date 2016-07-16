using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class DecryptTheMessages
{
    static void Main()
    {
        while (true)
        {
            string input = Console.ReadLine();
            if (input == "START" || input == "start")
            {
                break;
            }
        }

        List<string> listOfMessages = new List<string>();
        for (int i = 0; i < 100; i++)
        {
            string command = Console.ReadLine();
            if (command == String.Empty || command.Contains(' '))
            {
                continue;                               
            }
            if (command == "END" || command == "end")
            {
                break;
            }
            char[] charArray = command.ToCharArray();
            for (int j = 0; j < charArray.Length; j++)
            {
                if (charArray[j] == '+')
                {
                    charArray[j] = ' ';
                }
                if (charArray[j] == '%')
                {
                    charArray[j] = ',';
                }
                if (charArray[j] == '&')
                {
                    charArray[j] = '.';
                }
                if (charArray[j] == '#')
                {
                    charArray[j] = '?';
                }
                if (charArray[j] == '$')
                {
                    charArray[j] = '!';
                }
            }
            int[] valueArray = new int[charArray.Length];
            for (int j = 0; j < charArray.Length; j++)
            {
                valueArray[j] = (int)charArray[j];    //creating character Array with ASCII values
            }

            for (int j = 0; j < valueArray.Length; j++)
            {
                if (valueArray[j] >= 65 && valueArray[j] <= 77)
                {
                    valueArray[j] += 13;
                }
                else if (valueArray[j] >= 78 && valueArray[j] <= 90)
                {
                    valueArray[j] -= 13;
                }
                else if (valueArray[j] >= 97 && valueArray[j] <= 109)
                {
                    valueArray[j] += 13;
                }
                else if (valueArray[j] >= 110 && valueArray[j] <= 122)
                {
                    valueArray[j] -= 13;
                }
            }
            for (int j = 0; j < valueArray.Length; j++)
            {
                charArray[j] = (char)valueArray[j];
            }
            Array.Reverse(charArray);
            command = new string(charArray);
            listOfMessages.Add(command);
        }
        if (listOfMessages.Count == 0)
        {
            Console.WriteLine("No message received.");
        }
        else
        {
            Console.WriteLine("Total number of messages: " + listOfMessages.Count);
            foreach (string s in listOfMessages)
            {
                Console.WriteLine(s);
            }
        }
    }
}
