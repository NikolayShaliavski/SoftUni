using ManyToManyRelation.Models;
using System;
using System.Collections.Generic;
using System.Linq;

namespace ManyToManyRelation
{
    class Program
    {
        static void Main(string[] args)
        {
            using (StudentsDbContext db = new StudentsDbContext())
            {
                db.Database.EnsureDeleted();
                db.Database.EnsureCreated();

                SeedDatabase(db);

                PrintAllCoursesWithStudents(db);
            }
        }

        private static void PrintAllCoursesWithStudents(StudentsDbContext db)
        {
            var courses = db.Courses
                .Select(c => new
                {
                    CourseName = c.Name,
                    Students = db.Students
                        .Select(s => new
                        {
                            StudentName = s.Name
                        })
                        .ToList()
                })
                .ToList();

            foreach (var course in courses)
            {
                Console.WriteLine(course.CourseName);
                foreach (var student in course.Students)
                {
                    Console.WriteLine($"---{student.StudentName}");
                }
            }
        }

        private static void SeedDatabase(StudentsDbContext db)
        {
            List<Course> courses = new List<Course>(5);
            for (int i = 1; i <= 5; i++)
            {
                Course course = new Course() { Name = $"Course_{i}" };
                courses.Add(course);

                db.Courses.Add(course);
            }
            List<Student> students = new List<Student>(5);
            for (int i = 1; i <= 5; i++)
            {
                Student student = new Student() { Name = $"Student_{i}" };
                students.Add(student);

                db.Students.Add(student);
            }

            foreach (var course in courses)
            {
                foreach (var student in students)
                {
                    StudentsCourses sc = new StudentsCourses() { Course = course, Student = student };
                    course.StudentsCourses.Add(sc);
                }
            }

            db.SaveChanges();
        }
    }
}
