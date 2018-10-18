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

                //DataSeeder seeder = new DataSeeder();

                // Add Courses and Resources
                //seeder.AddCoursesAndResources(db);

                // Add Students
                //seeder.AddStudents(db);

                //Add Homeworks
                //seeder.AddHomeworks(db);

                // Add Licenses
                //seeder.AddLicenses(db);

                // Task 1
                //PrintStudentsWithHomeworks(db);

                // Task 2
                //PrintCoursesAndResources(db);

                // Task 3
                //PrintCoursesWithmoreThanFiveResources(db);

                // Task 4
                //PrintActiveCoursesOnGivenDate(db, DateTime.Now);

                // Task 5
                //PrintStudentsAndCoursesTotal(db);

                // Task 6
                //PrintCoursesWithResourcesAndLicenses(db);

                // Task 7
                PrintStudentsWithCoursesAndResourcesAndLicenses(db);
            }
        }
        // Task 1
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
        // Task 2
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
        // Task 3
        private static void PrintCoursesWithmoreThanFiveResources(StudentSystemDbContext db)
        {
            var courses = db.Courses
                .Where(c => c.Resources.Count >= 5)
                .OrderByDescending(c => c.Resources.Count)
                .ThenByDescending(c => c.StartDate)
                .Select(c => new
                {
                    c.Name,
                    ResourcesCount = c.Resources.Count
                })
                .ToList();

            foreach (var course in courses)
            {
                Console.WriteLine($"{course.Name} - {course.ResourcesCount}");
            }
        }
        // Task 4
        private static void PrintActiveCoursesOnGivenDate(StudentSystemDbContext db, DateTime date)
        {
            var courses = db.Courses
                .Where(c => c.EndDate >= date)
                .Select(c => new
                {
                    c.Name,
                    c.StartDate,
                    c.EndDate,
                    DurationInDays = c.EndDate.Subtract(c.StartDate).TotalDays,
                    StudentsCount = c.CourseStudents.Count
                })
                .OrderByDescending(c => c.StudentsCount)
                .ThenByDescending(c => c.DurationInDays)
                .ToList();

            foreach (var c in courses)
            {
                Console.WriteLine($"{c.Name}");
                Console.WriteLine($"Start date: {c.StartDate}");
                Console.WriteLine($"End date: {c.EndDate}");
                Console.WriteLine($"Duration: {c.DurationInDays}");
                Console.WriteLine($"Students: {c.StudentsCount}");
                Console.WriteLine(new string('-', 30));
            }
        }
        // Task 5
        private static void PrintStudentsAndCoursesTotal(StudentSystemDbContext db)
        {
            var students = db.Students
                .Select(s => new
                {
                    s.Name,
                    NumberOfCourses = s.StudentCourses.Count,
                    TotalPrice = s.StudentCourses.Sum(sc => sc.Course.Price),
                    AveragePrice = s.StudentCourses.Average(sc => sc.Course.Price)
                })
                .OrderByDescending(s => s.TotalPrice)
                .ThenByDescending(s => s.NumberOfCourses)
                .ThenBy(s => s.Name)
                .ToList();

            foreach (var s in students)
            {
                Console.WriteLine($"{s.Name}");
                Console.WriteLine($"Number of courses: {s.NumberOfCourses}");
                Console.WriteLine($"Total price: {s.TotalPrice}");
                Console.WriteLine($"Average price: {s.AveragePrice}");
                Console.WriteLine(new string('-', 20));
            }
        }
        // Task 6
        private static void PrintCoursesWithResourcesAndLicenses(StudentSystemDbContext db)
        {
            var courses = db.Courses
                .Select(c => new
                {
                    c.Name,
                    Resources = c.Resources
                        .Select(r => new
                        {
                            r.Name,
                            Licenses = r.Licenses
                                .Select(l => new
                                {
                                    l.Name
                                })
                                .ToList()
                        })
                        .OrderByDescending(r => r.Licenses.Count)
                        .ThenBy(r => r.Name)
                        .ToList()
                })
                .OrderByDescending(c => c.Resources.Count)
                .ThenBy(c => c.Name)
                .ToList();

            foreach (var c in courses)
            {
                Console.WriteLine(c.Name);
                foreach (var r in c.Resources)
                {
                    Console.WriteLine($"---{r.Name}");
                    foreach (var l in r.Licenses)
                    {
                        Console.WriteLine($"------{l.Name}");
                    }
                }
                Console.WriteLine(new string('-',  30));
            }
        }
        // Task 7
        private static void PrintStudentsWithCoursesAndResourcesAndLicenses(StudentSystemDbContext db)
        {
            var students = db.Students
                .Select(s => new
                {
                    s.Name,
                    CoursesCount = s.StudentCourses.Count,
                    ResourcesCount = s.StudentCourses
                        .Sum(sc => sc.Course.Resources.Count),
                    LicensesCount = s.StudentCourses
                        .Sum(sc => sc.Course.Resources
                            .Sum(r => r.Licenses.Count))
                })
                .OrderByDescending(s => s.CoursesCount)
                .ThenByDescending(s => s.ResourcesCount)
                .ThenBy(s => s.Name)
                .ToList();

            foreach (var s in students)
            {
                Console.WriteLine($"{s.Name} Courses: {s.CoursesCount} Resources: {s.ResourcesCount} Licenses: {s.LicensesCount}");
            }
        }
    }
}
