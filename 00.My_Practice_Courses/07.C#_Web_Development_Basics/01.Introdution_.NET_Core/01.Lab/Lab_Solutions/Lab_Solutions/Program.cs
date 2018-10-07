using Employees.Models;
using System;
using System.Linq;

namespace Employees
{
    class Program
    {
        static void Main(string[] args)
        {
            using (EmployeesDbContext db = new EmployeesDbContext())
            {
                db.Database.EnsureDeleted();
                db.Database.EnsureCreated();

                SeedDatabase(db);

                PrintDepartments(db);
            }
        }

        private static void PrintDepartments(EmployeesDbContext db)
        {
            var departments = db
                .Departments
                .Select(d => new
                {
                    DepartmentName = d.Name,
                    Managers = db.Employees
                        .Where(e => e.Employees.Count > 0)
                        .Select(e => new
                        {
                            ManagerName = e.Name,
                            Employees = e.Employees
                        })
                })
                .ToList();

            foreach (var dep in departments)
            {
                Console.WriteLine($"{dep.DepartmentName}");
                foreach (var manager in dep.Managers)
                {
                    Console.WriteLine($"--- Manager: {manager.ManagerName}");
                    foreach (var employee in manager.Employees)
                    {
                        Console.WriteLine($"------ Employee: {employee.Name}");
                    }
                }
            }
        }

        private static void SeedDatabase(EmployeesDbContext db)
        {
            // Departments
            Department salesDep = new Department() { Name = "Sales" };
            db.Departments.Add(salesDep);

            // Managers
            Employee bigBoss = new Employee() { Name = "The big boss" };
            Employee littleBoss = new Employee() { Name = "The little boss" };
            bigBoss.Department = salesDep;
            salesDep.Employees.Add(littleBoss);
            db.Employees.Add(bigBoss);
            db.Employees.Add(littleBoss);

            db.SaveChanges();

            // Employees
            for (int i = 1; i < 10; i++)
            {
                Employee em = new Employee() { Name = $"Employee-{i}" };
                int managerId = GetRandom(1, 3);
                Department dep = db
                    .Departments
                    .FirstOrDefault(d => d.Name == "Sales");
                dep.Employees.Add(em);

                Employee manager = db
                    .Employees
                    .FirstOrDefault(m => m.Id == managerId);
                manager.Employees.Add(em);

                db.Employees.Add(em);
            }
            db.SaveChanges();
        }

        private static int GetRandom(int from, int to)
        {
            int num = 0;
            Random random = new Random();
            num = random.Next(from, to);
            return num;
        }
    }
}
