using System;
using System.Text;
using System.Collections.Generic;

class EncryptedMatrix
{
    static void Main()
    {
        string message = Console.ReadLine();
        char direction = char.Parse(Console.ReadLine());

        byte[] messageArray = Encoding.ASCII.GetBytes(message);

        for (int i = 0; i < messageArray.Length; i++)
        {
            messageArray[i] = (byte)(messageArray[i] % 10);
        }

        int[] encryptedArray = new int[messageArray.Length];

        for (int i = 0; i < messageArray.Length; i++)
        {
            if (messageArray[i] == 0 || (messageArray[i] % 2 == 0))
            {
                encryptedArray[i] = messageArray[i] * messageArray[i];
            }
            else if (i == 0)
            {
                encryptedArray[i] += (byte)(messageArray[i] + messageArray[i + 1]);
            }
            else if (i == (messageArray.Length - 1))
            {
                encryptedArray[i] = (messageArray[i]) + (messageArray[i - 1]);
            }
            else if (i > 0 && i < (messageArray.Length - 1))
            {
                encryptedArray[i] = (byte)(messageArray[i] + messageArray[i + 1] + messageArray[i - 1]);
            }
        }

        List<int> matrixList = new List<int>();

        for (int i = 0; i < encryptedArray.Length; i++)
        {
            if (encryptedArray[i] > 9)
            {
                matrixList.Add(encryptedArray[i] / 10);
                matrixList.Add(encryptedArray[i] % 10);
            }
            else
            {
                matrixList.Add(encryptedArray[i]);
            }
        }

        int[,] matrix = new int[matrixList.Count, matrixList.Count];

        if (direction == '\\')
        {
            for (int row = 0; row < matrix.GetLength(0); row++)
            {
                for (int col = row; col <= row; col++)
                {
                    matrix[row, col] = matrixList[row];
                }
            }
        }
        else
        {
            for (int row = matrix.GetLength(0) - 1; row >= 0; row--)
            {
                for (int col = matrix.GetLength(1) - row - 1; col <= matrix.GetLength(1) - row - 1; col++)
                {
                    matrix[row, col] = matrixList[col];
                }
            }
        }

        for (int row = 0; row < matrix.GetLength(0); row++)
        {
            for (int col = 0; col < matrix.GetLength(1); col++)
            {
                Console.Write(matrix[row, col] + " ");
            }
            Console.WriteLine();
        }
    }
}
