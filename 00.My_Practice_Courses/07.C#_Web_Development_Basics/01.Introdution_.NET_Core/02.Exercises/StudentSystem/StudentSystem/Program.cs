using Microsoft.EntityFrameworkCore;
using StudentSystem.Data;
using StudentSystemData.DatabaseContext;
using System;
using System.Linq;

namespace StudentSystem
{
    class Program
    {
        static void Main(string[] args)
        {
            using (StudentSystemDbContext db = new StudentSystemDbContext())
            {
                //db.Database.Migrate();
                //Console.WriteLine("Database ready...");

                DataSeeder seeder = new DataSeeder();

                // Add Courses and Resources
                //seeder.AddCoursesAndResources(db);

                // Add Students
                //seeder.AddStudents(db);

                //Add Homeworks
                //seeder.AddHomeworks(db);

                //PrintStudentsWithHomeworks(db);
                PrintCoursesAndResources(db);
            }
        }
        private static void PrintCoursesAndResources(StudentSystemDbContext db)
        {
            var result = db.Courses
                .OrderBy(c => c.StartDate)
                .ThenByDescending(c => c.EndDate)
                .Select(c => new
                {
                    c.Name,
                    c.Description,
                    c.Resources
                })
                .ToList();
            foreach (var course in result)
            {
                Console.WriteLine($"{course.Name} - {course.Description}");
                foreach (var res in course.Resources)
                {
                    Console.WriteLine($"{res.Name} - {res.ResourceType} - {res.Url}");
                }
            }
        }
        private static void PrintStudentsWithHomeworks(StudentSystemDbContext db)
        {
            var result = db.Students
                .Select(s => new
                {
                    s.Name,
                    Homeworks = s.Homeworks
                        .Select(h => new
                        {
                            h.Content,
                            h.ContentType
                        })
                        .ToList()
                })
                .ToList();

            foreach (var student in result)
            {
                Console.WriteLine(student.Name);
                foreach (var homework in student.Homeworks)
                {
                    Console.WriteLine($"---{homework.ContentType}, {homework.Content}");
                }
            }
        }
    }
}
