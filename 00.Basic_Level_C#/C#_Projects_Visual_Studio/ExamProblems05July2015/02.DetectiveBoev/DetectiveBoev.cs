using System;
using System.Text;

class DetectiveBoev
{
    static void Main()
    {
        string word = Console.ReadLine();
        string message = Console.ReadLine();
        int maskNumber = 0;
        int mask = 0;

        for (int i = 0; i < word.Length; i++)
        {
            maskNumber += word[i];
        }

        string maskInString = maskNumber.ToString();

        for (int i = 0; i < maskInString.Length; i++)
        {
            mask += int.Parse(maskInString[i].ToString());
        }
        if (mask > 9)
        {
            maskNumber = mask;
            mask = 0;
            maskInString = maskNumber.ToString();
            for (int i = 0; i < maskInString.Length; i++)
            {
                mask += int.Parse(maskInString[i].ToString());
            }
        }

        byte[] messageArray = Encoding.ASCII.GetBytes(message);

        for (int i = 0; i < messageArray.Length; i++)
        {
            if (messageArray[i] % mask == 0)
            {
                messageArray[i] += (byte)mask;
            }
            else
            {
                messageArray[i] -= (byte)mask;
            }
        }
        
        Array.Reverse(messageArray);
        StringBuilder output = new StringBuilder();

        foreach (int i in messageArray)
        {
            output.Append((char)i);
        }
        Console.WriteLine(output);
    }
}
