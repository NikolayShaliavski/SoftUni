using Microsoft.EntityFrameworkCore;
using SocialNetworkData.DatabaseContext;
using SocialNetworkData.Helpers;
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
        public void AddTags(SocialNetworkDbContext db, int count)
        {
            for (int i = 1; i <= count; i++)
            {
                string tagValue = TagTransformer.Transform($"my- - album   -tag_  {i}");
                Tag tag = new Tag
                {
                    TagValue = tagValue
                };

                db.Tags.Add(tag);
                db.SaveChanges();
            }
        }
        public void AddTagsToALbums(SocialNetworkDbContext db)
        {
            var albumIds = db.ALbums
                .Select(a => a.Id)
                .ToList();
            foreach (var tag in db.Tags)
            {
                int albumsCount = Random.Next(5, albumIds.Count);
                for (int j = 0; j < albumsCount; j++)
                {
                    int albumId = albumIds[Random.Next(0, albumIds.Count)];
                    if (tag.ALbums.Any(a => a.ALbumId == albumId))
                    {
                        j--;
                        continue;
                    }
                    tag.ALbums.Add(new AlbumTag { ALbumId = albumId, TagId = tag.Id });
                }
            }
            db.SaveChanges();
        }
        // Not working
        public void ShareAlbums(SocialNetworkDbContext db)
        {
            var users = db.Users
                .Include(u => u.Albums)
                .ToList();

            foreach (var user in users)
            {
                // No albums to share
                if (user.Albums.Count == 0)
                {
                    continue;
                }
                // Every user will share his albums with at least one other user
                int shareCount = Random.Next(1, db.Users.Count());
                for (int i = 0; i < shareCount; i++)
                {
                    int shareUserId = users[Random.Next(0, users.Count)].Id;
                    // Cannot share albums with myself
                    if (shareUserId == user.Id)
                    {
                        i--;
                        continue;
                    }
                    // Outer user has already shared his albums with this one - search for another
                    if (db.UserAlbums.Any(ua => ua.UserId == shareUserId) &&
                        db.UserAlbums.Any(ua => ua.AlbumId == user.Albums.First().Id))
                    {
                        i--;
                        continue;
                    }
                    foreach (var album in user.Albums)
                    {
                        UserAlbum ua = new UserAlbum
                        {
                            UserId = shareUserId,
                            AlbumId = album.Id
                        };
                        db.UserAlbums.Add(ua);
                    }
                }
                db.SaveChanges();
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
