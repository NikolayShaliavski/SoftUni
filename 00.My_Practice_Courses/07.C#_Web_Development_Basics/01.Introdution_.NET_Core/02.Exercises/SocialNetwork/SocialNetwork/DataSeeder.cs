using SocialNetworkData.DatabaseContext;
using SocialNetworkData.Models;
using System;
using System.Drawing;
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
        public void AddPictures(SocialNetworkDbContext db, int count)
        {
            for (int i = 1; i <= count; i++)
            {
                Picture pic = new Picture
                {
                    Title = $"Picture_{i}.jpg",
                    Caption = $"Pic_{i}_caption",
                    Path = $@"D:\Photos\Picture_{i}.jpg"
                };
                db.Pictures.Add(pic);
                db.SaveChanges();
            }
            
        }
        public void AddAlbums(SocialNetworkDbContext db, int count)
        {
            Array colors = Enum.GetValues(typeof(KnownColor));

            var userIds = db.Users
                .Select(u => u.Id)
                .ToList();

            for (int i = 1; i <= count; i++)
            {
                KnownColor color = (KnownColor)colors.GetValue(Random.Next(0, colors.Length));
                Album album = new Album
                {
                    Name = $"Album_{i}",
                    BackgroundColor = color,
                    IsPublic = i % 2 == 0,
                    UserId = userIds[Random.Next(0, userIds.Count)]
                };
                db.ALbums.Add(album);
                db.SaveChanges();
            }
            
        }
        public void PutPicturesInAlbums(SocialNetworkDbContext db)
        {
            foreach (var album in db.ALbums)
            {
                int picturesInAlbum = Random.Next(0, 11);
                for (int i = 0; i < picturesInAlbum; i++)
                {
                    int picId = Random.Next(1, db.Pictures.Count());

                    if (album.Pictures.Any(p => p.PictureId == picId))
                    {
                        i--;
                        continue;
                    }
                    album.Pictures.Add(new AlbumPicture { AlbumId = album.Id, PictureId = picId });
                }
            }
            db.SaveChanges();
        }
        

        private void DeleteUsers(SocialNetworkDbContext db, int count)
        {
            for (int i = 0; i < count; i++)
            {
                int userId = Random.Next(1, db.Users.Count());
                User user = db.Users.FirstOrDefault(u => u.Id == userId);
                if (user.IsDeleted)
                {
                    i--;
                }
                user.IsDeleted = true;
            }
            db.SaveChanges();
        }
    }
}
