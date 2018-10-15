using Microsoft.EntityFrameworkCore;
using StudentSystemData.DatabaseContext;
using StudentSystemData.Models;
using StudentSystemData.Models.Enums;
using System;
using System.Linq;

namespace StudentSystem.Data
{
    public class DataSeeder
    {
        private static readonly Random random = new Random();

        private string[] names = new string[] { "Nikolay", "Maria", "Ivan", "Atanas", "Desislava",
                                            "Nikolay2", "Maria2", "Ivan2", "Atanas2", "Desislava2"};

        public void AddCoursesAndResources(StudentSystemDbContext db)
        {
            // Resources
            Resource cSharpPresentation = new Resource()
            {
                Name = "C# Introduction to .NET Core presentation",
                ResourceType = ResourceType.Presentation,
                Url = "https://Introduction_to_.NET_Core/presentation"
            };
            Resource cSharpVideo = new Resource()
            {
                Name = "C# Introduction to .NET Core video",
                ResourceType = ResourceType.Video,
                Url = "https://Introduction_to_.NET_Core/video"
            };
            Resource cSharpLab = new Resource()
            {
                Name = "C# Introduction to .NET Core Lab exercise",
                ResourceType = ResourceType.Document,
                Url = "https://Introduction_to_.NET_Core/lab"
            };
            db.Resources.Add(cSharpPresentation);
            db.Resources.Add(cSharpVideo);
            db.Resources.Add(cSharpLab);

            Resource javaPresentation = new Resource()
            {
                Name = "Java Web Fundamentals Introduction presentation",
                ResourceType = ResourceType.Presentation,
                Url = "https://Java_Web_Fundamentals/presentation"
            };
            Resource javaVideo = new Resource()
            {
                Name = "Java Web Fundamentals Introduction video",
                ResourceType = ResourceType.Video,
                Url = "https://Java_Web_Fundamentals/video"
            };
            Resource javaLab = new Resource()
            {
                Name = "Java Web Fundamentals Introduction Lab exercise",
                ResourceType = ResourceType.Document,
                Url = "https://Java_Web_Fundamentals/lab"
            };
            db.Resources.Add(javaPresentation);
            db.Resources.Add(javaVideo);
            db.Resources.Add(javaLab);

            // Courses
            for (int i = 1; i <= 2; i++)
            {
                Course cSharpCourse = new Course()
                {
                    Name = "C# Web-" + i,
                    StartDate = DateTime.Now.AddMonths(-i),
                    EndDate = DateTime.Now.AddMonths(1 - i).AddDays(1),
                    Price = 120.00m + (i * 25),
                    Description = "C# Web Development course"
                };
                cSharpCourse.Resources.Add(cSharpPresentation);
                cSharpCourse.Resources.Add(cSharpVideo);
                cSharpCourse.Resources.Add(cSharpLab);
                db.Courses.Add(cSharpCourse);

                Course javaCourse = new Course()
                {
                    Name = "Java Web-" + i,
                    StartDate = DateTime.Now.AddMonths(-i),
                    EndDate = DateTime.Now.AddDays(1 - i).AddDays(15),
                    Price = 135.00m + (i * 25),
                    Description = "Java Web Development course"
                };
                javaCourse.Resources.Add(javaPresentation);
                javaCourse.Resources.Add(javaVideo);
                javaCourse.Resources.Add(javaLab);
                db.Courses.Add(javaCourse);
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
                int coursesEnrolled = random.Next(1, 5);
                for (int j = 0; j < coursesEnrolled; j++)
                {
                    int courseId = random.Next(1, 5);
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

                int homeworksCount = random.Next(2, 6);
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
                    StudentCourse sc = student.StudentCourses[random.Next(0, student.StudentCourses.Count)];
                    homework.CourseId = sc.CourseId;

                    db.Homeworks.Add(homework);
                }
            }
            db.SaveChanges();
        }
    }
}
