using Microsoft.EntityFrameworkCore;
using SocialNetworkData.DatabaseContext;
using SocialNetworkData.Models;
using System;
using System.Linq;

namespace SocialNetwork
{
    class Program
    {
        static void Main(string[] args)
        {
            using (SocialNetworkDbContext db = new SocialNetworkDbContext())
            {
                db.Database.Migrate();
                Console.WriteLine("Database ready...");

                DataSeeder seeder = new DataSeeder();
                //seeder.AddUsers(db, 20);
                //seeder.AddUserFriends(db);
                //seeder.DeleteUsers(db, 10);

                ListUsersWithCountOfFriends(db);

                ListActiveUserswithMoreThan15Friends(db);
            }
        }
        private static void ListActiveUserswithMoreThan15Friends(SocialNetworkDbContext db)
        {
            var users = db.Users
                .Where(u => !u.IsDeleted
                             && (u.FromFriends.Count + u.ToFriends.Count) > 15)
                .OrderBy(u => u.RegisteredOn)
                .ThenByDescending(u => u.FromFriends.Count + u.ToFriends.Count)
                .Select(u => new
                {
                    u.Name,
                    FriendsCount = u.FromFriends.Count + u.ToFriends.Count,
                    RegitsrationPeriod = DateTime.Now.Subtract(u.RegisteredOn).TotalDays
                })
                .ToList();

            foreach (var u in users)
            {
                Console.WriteLine($"{u.Name} - Friends: {u.FriendsCount} - Regitration period: {u.RegitsrationPeriod}");
            }
        }
        private static void ListUsersWithCountOfFriends(SocialNetworkDbContext db)
        {
            var users = db.Users
                .Select(u => new
                {
                    u.Name,
                    NumberOfFriends = u.FromFriends.Count + u.ToFriends.Count,
                    Status = u.IsDeleted ? "Inactive" : "Active"
                })
                .OrderByDescending(u => u.NumberOfFriends)
                .ThenBy(u => u.Name)
                .ToList();

            foreach (var user in users)
            {
                Console.WriteLine($"{user.Name} {user.NumberOfFriends} {user.Status}");
            }
        }
    }
}
