using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using SocialNetworkData.Models;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Drawing;
using System.Linq;

namespace SocialNetworkData.DatabaseContext
{
    public class SocialNetworkDbContext : DbContext
    {
        public DbSet<User> Users { get; set; }

        public DbSet<FriendShip> FriendShips { get; set; }

        public DbSet<Album> ALbums { get; set; }

        public DbSet<Picture> Pictures { get; set; }

        protected override void OnModelCreating(ModelBuilder builder)
        {
            builder
                .Entity<FriendShip>()
                .HasKey(f => new { f.FromUserId, f.ToUserId });

            builder
                .Entity<User>()
                .HasMany(u => u.FromFriends)
                .WithOne(f => f.FromUser)
                .HasForeignKey(f => f.FromUserId);

            builder
                .Entity<User>()
                .HasMany(u => u.ToFriends)
                .WithOne(f => f.ToUser)
                .HasForeignKey(f => f.ToUserId);

            builder
                .Entity<Album>()
                .Property(a => a.BackgroundColor)
                .HasConversion(
                    c => c.ToString(),
                    c => (KnownColor)Enum.Parse(typeof(KnownColor), c));

            builder
                .Entity<AlbumPicture>()
                .HasKey(ap => new { ap.AlbumId, ap.PictureId });

            builder
                .Entity<Album>()
                .HasMany(a => a.Pictures)
                .WithOne(ap => ap.Album)
                .HasForeignKey(ap => ap.AlbumId);

            builder
                .Entity<Picture>()
                .HasMany(p => p.Albums)
                .WithOne(ap => ap.Picture)
                .HasForeignKey(ap => ap.PictureId);

            builder
                .Entity<Album>()
                .HasOne(a => a.Owner)
                .WithMany(u => u.Albums)
                .HasForeignKey(a => a.UserId);
        }
        protected override void OnConfiguring(DbContextOptionsBuilder builder)
        {
            builder.UseSqlServer("Server=.;Database=SocialNetwork;Integrated Security=True;");
            base.OnConfiguring(builder);
        }

        public override int SaveChanges(bool acceptAllChangesOnSuccess)
        {
            var serviceProvider = this.GetService<IServiceProvider>();
            var items = new Dictionary<object, object>();

            foreach (var entry in this.ChangeTracker.Entries().Where(e => (e.State == EntityState.Added) || (e.State == EntityState.Modified)))
            {
                var entity = entry.Entity;
                var context = new ValidationContext(entity, serviceProvider, items);
                var results = new List<ValidationResult>();

                if (Validator.TryValidateObject(entity, context, results, true) == false)
                {
                    foreach (var result in results)
                    {
                        if (result != ValidationResult.Success)
                        {
                            throw new ValidationException(result.ErrorMessage);
                        }
                    }
                }
            }

            return base.SaveChanges(acceptAllChangesOnSuccess);
        }
    }
}
