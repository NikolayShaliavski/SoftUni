using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class ArrayMatcher
{
    static void Main()
    {
        string userInput = Console.ReadLine();

        string[] inputarray = userInput.Split('\\');

        char[] firstInput = inputarray[0].ToCharArray();
        char[] secondInput = inputarray[1].ToCharArray();
        Array.Sort(firstInput);
        Array.Sort(secondInput);
        string userCommand = inputarray[2].ToString();

        List<char> listOfSortedChars = new List<char>();

        switch (userCommand)
        {
            case "join":
                for (int i = 0; i < firstInput.Length; i++)
                {
                    for (int j = 0; j < secondInput.Length; j++)
                    {
                        if (firstInput[i] == secondInput[j])
                        {
                            listOfSortedChars.Add(firstInput[i]);
                        }
                    }
                }
                break;
            case "right exclude":
                for (int i = 0; i < firstInput.Length; i++)
                {
                    if (!secondInput.Contains(firstInput[i]))
                    {
                        listOfSortedChars.Add(firstInput[i]);
                    }
                }
                break;
            case "left exclude":
                for (int i = 0; i < secondInput.Length; i++)
                {
                    if (!firstInput.Contains(secondInput[i]))
                    {
                        listOfSortedChars.Add(secondInput[i]);
                    }
                }
                    break;
            default:
                break;
        }
        for (int i = 0; i < listOfSortedChars.Count; i++)
        {
            Console.Write(listOfSortedChars[i]);
        }
        Console.WriteLine();
    }
}

