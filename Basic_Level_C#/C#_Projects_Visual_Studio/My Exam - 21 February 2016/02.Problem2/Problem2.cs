using System;

class Problem2
{
    static void Main()
    {
        int questions = int.Parse(Console.ReadLine());
        char[] answers = new char[questions];

        for (int i = 0; i < questions; i++)
        {
            uint answer = uint.Parse(Console.ReadLine()) % 4;
            answers[i] = (char)(answer + 97);
        }

        int answerA = 0;
        int answerB = 0;
        int answerC = 0;
        int answerD = 0;

        for (int i = 0; i < answers.Length; i++)
        {
            Console.Write(answers[i] + " ");

            switch (answers[i])
            {
                case 'a':
                    answerA++;
                    break;
                case 'b':
                    answerB++;
                    break;
                case 'c':
                    answerC++;
                    break;
                case 'd':
                    answerD++;
                    break;
                default:
                    break;
            }           
        }
        Console.WriteLine();
        Console.WriteLine("Answer A: " + answerA);
        Console.WriteLine("Answer B: " + answerB);
        Console.WriteLine("Answer C: " + answerC);
        Console.WriteLine("Answer D: " + answerD);
    }
}
