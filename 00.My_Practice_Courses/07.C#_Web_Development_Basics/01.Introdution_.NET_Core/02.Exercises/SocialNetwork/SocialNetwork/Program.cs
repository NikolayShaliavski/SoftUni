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
                //db.Database.Migrate();
                //Console.WriteLine("Database ready...");

                //DataSeeder seeder = new DataSeeder();
                //seeder.AddUsers(db, 50);
                //seeder.AddUserFriends(db);
                //seeder.DeleteUsers(db, 25);
                //seeder.AddPictures(db, 200);
                //seeder.AddAlbums(db, 100);
                //seeder.PutPicturesInAlbums(db);



                //ListUsersWithCountOfFriends(db);
                //ListActiveUserswithMoreThan15Friends(db);
                //ListAlbumsWithOwnerAndpicturesCount(db);
                ListPicturesInMoreThanOneAlbums(db);
            }
        }

        private static void ListPicturesInMoreThanOneAlbums(SocialNetworkDbContext db)
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
