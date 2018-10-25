using SocialNetworkData.DatabaseContext;
using SocialNetworkData.Models;
using System;
using System.Linq;

namespace SocialNetwork
{
    public class DataSeeder
    {
        private static readonly Random Random = new Random();

        public void AddUsers(SocialNetworkDbContext db, int count)
        {
            for (int i = 1; i <= count; i++)
            {
                User user = new User
                {
                    Name = $"User_{i}",
                    Password = $"PaSwO&{i}rD",
                    Age = 20 + i,
                    Email = $"User_email_{i}@gmail.com",
                    RegisteredOn = DateTime.Now.AddDays(-(i * 3)),
                    LastTimeLoggedIn = DateTime.Now.AddDays(-i),
                    ProfilePicture = new byte[] { 1, 2, 3 }
                };

                db.Users.Add(user);
            }
            db.SaveChanges();
        }
        public void AddUserFriends(SocialNetworkDbContext db)
        {
            var userIds = db.Users
                .Select(u => u.Id)
                .ToList();

            foreach (var userId in userIds)
            {
                int friendsCount = Random.Next(5, 11);

                for (int i = 0; i < friendsCount; i++)
                {
                    int friendId = userIds[Random.Next(0, userIds.Count)];
                    if (friendId == userId)
                    {
                        i--;
                        continue;
                    }
                    if (db.FriendShips.Any(f =>
                                          (f.FromUserId == userId && f.ToUserId == friendId) ||
                                          (f.ToUserId == userId && f.FromUserId == friendId)))
                    {
                        i--;
                        continue;
                    }

                    db.FriendShips.Add(new FriendShip { FromUserId = userId, ToUserId = friendId });
                    db.SaveChanges();
                }
            }
        }
        public void DeleteUsers(SocialNetworkDbContext db, int count)
        {
            for (int i = 0; i < count; i++)
            {
                int userId = Random.Next(1, db.Users.Count());
                User user = db.Users.FirstOrDefault(u => u.Id == userId);
                user.IsDeleted = true;
            }
            db.SaveChanges();
        }
    }
}
