using Microsoft.EntityFrameworkCore;
using SocialNetworkData.DatabaseContext;
using SocialNetworkData.Models;
using System;
using System.Collections.Generic;
using System.Linq;

namespace SocialNetwork
{
    class Program
    {
        static void Main(string[] args)
        {
            using (SocialNetworkDbContext db = new SocialNetworkDbContext())
            {
                //db.Database.Migrate();
                //Console.WriteLine("Database ready...");

                DataSeeder seeder = new DataSeeder();
                //seeder.AddUsers(db, 50);
                //seeder.AddUserFriends(db);
                //seeder.DeleteUsers(db, 25);
                //seeder.AddPictures(db, 200);
                //seeder.AddAlbums(db, 100);
                //seeder.PutPicturesInAlbums(db);
                //seeder.AddTags(db, 200);
                //seeder.AddTagsToALbums(db);


                //ListUsersWithCountOfFriends(db);
                //ListActiveUserswithMoreThan15Friends(db);
                //ListAlbumsWithOwnerAndpicturesCount(db);
                //ListPicturesInMoreThanTwoAlbums(db);
                //ListAlbumsByUserId(db, 1);
                //ListAlbumsByGivenTag(db, "#my-cool-album-tag_1");
                ListUsersWithAlbumsWithMoreThanThreeTags(db);
            }
        }

        private static void ListUsersWithAlbumsWithMoreThanThreeTags(SocialNetworkDbContext db)
        {
            var users = db.Users
                .Where(u => u.Albums
                    .Any(a => a.Tags.Count > 3))
                .Select(u => new
                {
                    u.Name,
                    Albums = u.Albums
                        .Where(a => a.Tags.Count > 3)
                        .Select(a => new
                        {
                            a.Name,
                            Tags = db.Tags
                                .Where(t => a.Tags
                                    .Any(at => at.TagId == t.Id))
                                .Select(t => t.TagValue)
                                .ToList()
                        })
                        .ToList()
                })
                .OrderByDescending(u => u.Albums.Count)
                .ThenByDescending(u => u.Albums.Sum(a => a.Tags.Count))
                .ThenBy(u => u.Name)
                .ToList();

            foreach (var u in users)
            {
                Console.WriteLine($"{u.Name}");
                foreach (var a in u.Albums)
                {
                    Console.WriteLine($"   {a.Name}");
                    foreach (var t in a.Tags)
                    {
                        Console.WriteLine($"      {t}");
                    }
                }
            }
        }
        private static void ListAlbumsByGivenTag(SocialNetworkDbContext db, string tag)
        {
            var albums = db.ALbums
                .Where(a => a.Tags
                    .Any(t => t.Tag.TagValue == tag))
                .OrderByDescending(a => a.Tags.Count)
                .Select(a => new
                {
                    a.Name,
                    Owner = a.Owner.Name
                })
                .OrderBy(a => a.Name)
                .ToList();

            foreach (var a in albums)
            {
                Console.WriteLine($"{a.Name} - {a.Owner}");
            }

            Console.WriteLine($"Total count: {albums.Count}");
        }
        private static void ListAlbumsByUserId(SocialNetworkDbContext db, int userId)
        {
            var albums = db.ALbums
                .Where(a => a.UserId == userId)
                .Select(a => new
                {
                    a.Name,
                    Owner = a.Owner.Name,
                    Pictures = a.IsPublic ? db.Pictures
                                                .Where(p => a.Pictures
                                                    .Any(ap => ap.PictureId == p.Id))
                                                .Select(p => new
                                                {
                                                    p.Title,
                                                    p.Path
                                                })
                                                .ToList() : null
                })
                .OrderBy(a => a.Name)
                .ToList();

            foreach (var a in albums)
            {
                Console.WriteLine($"{a.Name} - {a.Owner}");
                // Album is public
                if (a.Pictures != null)
                {
                    foreach (var p in a.Pictures)
                    {
                        Console.WriteLine($"   {p.Title} - {p.Path}");
                    }
                }
                else
                {
                    Console.WriteLine("   Private content!");
                }
            }
        }
        private static void ListPicturesInMoreThanTwoAlbums(SocialNetworkDbContext db)
        {
            var pictures = db.Pictures
                .Where(p => p.Albums.Count > 2)
                .Select(p => new
                {
                    p.Title,
                    OwnerNames = db.Users
                        .Where(u => u.Albums
                            .Any(a => p.Albums
                                .Any(ap => ap.AlbumId == a.Id)))
                        .Select(u => u.Name)
                        .ToList(),
                    AlbumNames = db.ALbums
                        .Where(a => p.Albums
                            .Any(ap => ap.AlbumId == a.Id))
                        .Select(a => a.Name)
                        .ToList()
                })
                .ToList();

            foreach (var pic in pictures)
            {
                Console.WriteLine($"{pic.Title}");
                Console.WriteLine("Albums:");
                foreach (var album in pic.AlbumNames)
                {
                    Console.WriteLine($"--- {album}");
                }
                Console.WriteLine("Album owners:");
                foreach (var owner in pic.OwnerNames)
                {
                    Console.WriteLine($"--- {owner}");
                }
            }
        }
        private static void ListAlbumsWithOwnerAndpicturesCount(SocialNetworkDbContext db)
        {
            var albums = db.ALbums
                .Select(a => new
                {
                    a.Name,
                    Owner = a.Owner.Name,
                    PicturesCount = a.Pictures.Count
                })
                .OrderByDescending(a => a.PicturesCount)
                .ThenBy(a => a.Owner)
                .ToList();

            foreach (var album in albums)
            {
                Console.WriteLine($"{album.Name} - {album.Owner} - {album.PicturesCount}");
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
