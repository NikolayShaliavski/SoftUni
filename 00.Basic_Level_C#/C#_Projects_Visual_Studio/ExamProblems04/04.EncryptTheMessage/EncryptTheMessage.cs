using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class EncryptingMessage
{
    static void Main()
    {
        string input = null;
        while (input != "START" && input != "start")
        {
            input = Console.ReadLine();
        }
        List<string> listOfMessages = new List<string>();
        input = Console.ReadLine();

        while (input != "END" && input != "end")
        {
            byte[] asciiArray = Encoding.ASCII.GetBytes(input);

            for (int i = 0; i < asciiArray.Length; i++)
            {
                if (asciiArray[i] >= 65 && asciiArray[i] <= 77)
                {
                    asciiArray[i] += 13;
                }
                else if (asciiArray[i] >= 78 && asciiArray[i] <= 90)
                {
                    asciiArray[i] -= 13;
                }
                else if (asciiArray[i] >= 97 && asciiArray[i] <= 109)
                {
                    asciiArray[i] += 13;
                }
                else if (asciiArray[i] >= 110 && asciiArray[i] <= 122)
                {
                    asciiArray[i] -= 13;
                }
                else if (asciiArray[i] == ' ')
                {
                    asciiArray[i] += 11;
                }
                else if (asciiArray[i] == ',')
                {
                    asciiArray[i] -= 7;
                }
                else if (asciiArray[i] == '.')
                {
                    asciiArray[i] -= 8;
                }
                else if (asciiArray[i] == '?')
                {
                    asciiArray[i] -= 28;
                }
                else if (asciiArray[i] == '!')
                {
                    asciiArray[i] += 3;
                }
            }
            Array.Reverse(asciiArray);
            string message = new string(Array.ConvertAll(asciiArray, x => (char)x));
            if (input != "")
            {
                listOfMessages.Add(message);
            }
            input = Console.ReadLine();
        }
        if (listOfMessages.Count == 0)
        {
            Console.WriteLine("No messages sent.");
        }
        else
        {
            Console.WriteLine("Total number of messages: " + listOfMessages.Count);
            foreach (string i in listOfMessages)
            {
                Console.WriteLine(i);
            }
        }
    }
}

