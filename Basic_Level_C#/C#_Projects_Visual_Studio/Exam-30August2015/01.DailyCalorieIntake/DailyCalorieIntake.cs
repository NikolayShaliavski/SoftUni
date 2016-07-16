using System;

class DailyCalorieIntake
{
    static void Main()
    {
        double weight = double.Parse(Console.ReadLine()) / 2.2;
        double height = double.Parse(Console.ReadLine()) * 2.54;
        int age = int.Parse(Console.ReadLine());
        char gender = char.Parse(Console.ReadLine());
        int workouts = int.Parse(Console.ReadLine());

        double metabolicRate = 0;
        long dailyCalorie = 0;

        if (gender == 'm')
        {
            metabolicRate = 66.5 + (13.75 * weight) + (5.003 * height) - (6.755 * age);
        }
        else
        {
            metabolicRate = 655 + (9.563 * weight) +(1.85 * height) - (4.676 * age);
        }

        if (workouts <= 0)
        {
            dailyCalorie = (long)Math.Floor(metabolicRate * 1.2);
        }
        else if (workouts >= 1 && workouts <= 3)
        {
            dailyCalorie = (long)Math.Floor(metabolicRate * 1.375);
        }
        else if (workouts >= 4 && workouts <= 6)
        {
            dailyCalorie = (long)Math.Floor(metabolicRate * 1.55);
        }
        else if (workouts >= 7 && workouts <= 9)
        {
            dailyCalorie = (long)Math.Floor(metabolicRate * 1.725);
        }
        else
        {
            dailyCalorie = (long)Math.Floor(metabolicRate * 1.9);
        }

        Console.WriteLine(dailyCalorie);
    }
}
