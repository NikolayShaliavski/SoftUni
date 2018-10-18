using StudentSystemData.DatabaseContext;
using StudentSystemData.Models;
using StudentSystemData.Models.Enums;
using System;
using System.Linq;

namespace StudentSystem.Data
{
    public class DataSeeder
    {
        private static readonly Random Random = new Random();

        private string[] names = new string[] { "Nikolay", "Maria", "Ivan", "Atanas", "Desislava",
                                            "Nikolay2", "Maria2", "Ivan2", "Atanas2", "Desislava2"};

        public void AddCoursesAndResources(StudentSystemDbContext db)
        {
            // Courses
            for (int i = 1; i <= 10; i++)
            {
                int monthsToEnd = i;
                if (i % 2 == 0)
                {
                    monthsToEnd *= -1;
                }
                Course course = new Course()
                {
                    Name = "Course_" + i,
                    StartDate = DateTime.Now.AddMonths(-i),
                    EndDate = DateTime.Now.AddMonths(monthsToEnd).AddDays(30),
                    Price = 120.00m + (i * 25),
                    Description = $"Course_{i}_decsription"
                };
                // Resources
                int resourcesCount = Random.Next(3, 6);
                for (int j = 1; j <= resourcesCount; j++)
                {
                    ResourceType type = ResourceType.Other;
                    if (j % 4 == 0)
                    {
                        type = ResourceType.Video;
                    }
                    else if (j % 3 == 0)
                    {
                        type = ResourceType.Presentation;
                    }
                    else if (j % 2 == 0)
                    {
                        type = ResourceType.Document;
                    }
                    Resource res = new Resource
                    {
                        Name = $"Resource_{j}_Course_{i}",
                        ResourceType = type,
                        Url = $"Resource_{j}_URL",
                        Course = course
                    };
                    db.Resources.Add(res);
                }
                db.Courses.Add(course);
            }
            db.SaveChanges();
        }

        public void AddStudents(StudentSystemDbContext db)
        {
            for (int i = 0; i < 10; i++)
            {
                Student student = new Student()
                {
                    Name = this.names[i],
                    RegistrationDate = DateTime.Now.AddDays(i * 10),
                    Birthday = DateTime.Now.AddYears(-(20 + i)),
                    PhoneNumber = "0888 00 00 0" + i
                };
                int coursesEnrolled = Random.Next(1, 5);
                for (int j = 0; j < coursesEnrolled; j++)
                {
                    int courseId = Random.Next(1, 11);
                    // Student already enrolled in this course
                    if (student.StudentCourses.Any(sc => sc.CourseId == courseId))
                    {
                        j--;
                        continue;
                    }
                    student.StudentCourses.Add(new StudentCourse() { CourseId = courseId });
                }
                db.Students.Add(student);
            }
            db.SaveChanges();
        }

        public void AddHomeworks(StudentSystemDbContext db)
        {
            for (int i = 1; i <= 10; i++)
            {
                int studentId = i;
                var student = db.Students
                    .Where(s => s.Id == studentId)
                    .Select(sc => new
                    {
                        sc.StudentCourses
                    })
                    .FirstOrDefault();

                int homeworksCount = Random.Next(2, 6);
                for (int j = 1; j <= homeworksCount; j++)
                {
                    ContentType type;
                    if (j % 2 == 0)
                    {
                        type = ContentType.Application;
                    }
                    else if (j % 3 == 0)
                    {
                        type = ContentType.Zip;
                    }
                    else
                    {
                        type = ContentType.Pdf;
                    }
                    Homework homework = new Homework()
                    {
                        ContentType = type,
                        Content = $"{this.names[studentId - 1]}-Homework-{j}",
                        StudentId = studentId,
                        SubmissionDate = DateTime.Now.AddDays(- i)
                    };
                    StudentCourse sc = student.StudentCourses[Random.Next(0, student.StudentCourses.Count)];
                    homework.CourseId = sc.CourseId;

                    db.Homeworks.Add(homework);
                }
            }
            db.SaveChanges();
        }

        public void AddLicenses(StudentSystemDbContext db)
        {
            // Obtain all Resources in database
            var resourceIds = db.Resources
                .Select(r => r.Id)
                .ToList();

            for (int i = 1; i <= 100; i++)
            {
                int resourceId = resourceIds[Random.Next(0, resourceIds.Count)];

                License lic = new License
                {
                    Name = $"License_{i}",
                    ResourceId = resourceId
                };

                db.Licenses.Add(lic);
            }

            db.SaveChanges();
        }
    }
}
