using Microsoft.EntityFrameworkCore;
using StudentSystemData.DatabaseContext;
using System;

namespace StudentSystem
{
    class Program
    {
        static void Main(string[] args)
        {
            using (StudentSystemDbContext db = new StudentSystemDbContext())
            {
                db.Database.Migrate();

                Console.WriteLine("Database ready...");
            }
        }
    }
}
