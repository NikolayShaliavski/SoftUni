using System;
using System.Collections.Generic;

class CalculateArithmeticExpression
{
    static Dictionary<string, int> precedences = new Dictionary<string, int>();
    static Queue<string> output = new Queue<string>();
    static Stack<string> operators = new Stack<string>();

    static void Main(string[] args)
    {
        InitPrecedences();
        string[] expression = Console.ReadLine().Split(' ');

        for (int i = 0; i < expression.Length; i++)
        {
            string token = expression[i];

            if (!precedences.ContainsKey(token))
            {
                output.Enqueue(token);
            }
            else if (token == "(")
            {
                operators.Push(token);
            }
            else if (token == ")")
            {
                string currOperator = operators.Pop();
                while (currOperator != "(")
                {
                    output.Enqueue(currOperator);
                    currOperator = operators.Pop();
                }
            }
            else if (operators.Count > 0)
            {
                if (precedences[token] > precedences[operators.Peek()])
                {
                    operators.Push(token);
                }
                else
                {
                    output.Enqueue(operators.Pop());
                    operators.Push(token);
                }
            }
            else
            {
                operators.Push(token);
            }
        }
        while (operators.Count > 0)
        {
            output.Enqueue(operators.Pop());
        }
        Stack<double> numbers = new Stack<double>();
        while (output.Count > 0)
        {
            string token = output.Dequeue();
            if (!precedences.ContainsKey(token))
            {
                try
                {
                    numbers.Push(double.Parse(token));
                }
                catch (FormatException)
                {
                    Console.WriteLine("error");
                    return;
                }
                
            }
            else
            {
                double num2 = numbers.Pop();
                double num1 = numbers.Pop();
                double result = CalculateSubExpression(num1, num2, token);
                numbers.Push(result);
            }
        }
        Console.WriteLine(numbers.Pop());

    }

    private static double CalculateSubExpression(double num1, double num2, string token)
    {
        switch (token)
        {
            case "-":
                return num1 - num2;
            case "+":
                return num1 + num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
        }
        throw new InvalidOperationException("error");
    }

    private static void InitPrecedences()
    {
        precedences["("] = 0;
        precedences[")"] = 0;
        precedences["-"] = 1;
        precedences["+"] = 1;
        precedences["/"] = 2;
        precedences["*"] = 2;
        precedences["^"] = 3;
    }
}
