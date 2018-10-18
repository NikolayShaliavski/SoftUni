using Microsoft.EntityFrameworkCore;
using StudentSystemData.Models;

namespace StudentSystemData.DatabaseContext
{
    public class StudentSystemDbContext : DbContext
    {
        public DbSet<Student> Students { get; set; }

        public DbSet<Course> Courses { get; set; }

        public DbSet<Homework> Homeworks { get; set; }

        public DbSet<Resource> Resources { get; set; }

        public DbSet<License> Licenses { get; set; }

        protected override void OnModelCreating(ModelBuilder builder)
        {
            #region Students -> Courses many-to-many
            // Register StudentCourse composite key
            builder
                .Entity<StudentCourse>()
                .HasKey(sc => new { sc.StudentId, sc.CourseId });

            // Students -> Courses relation
            builder
                .Entity<StudentCourse>()
                .HasOne(sc => sc.Student)
                .WithMany(s => s.StudentCourses)
                .HasForeignKey(sc => sc.StudentId);

            // Courses -> Students relation
            builder
                .Entity<StudentCourse>()
                .HasOne(sc => sc.Course)
                .WithMany(c => c.CourseStudents)
                .HasForeignKey(sc => sc.CourseId);
            #endregion

            // Course -> Resources one-to-many relation
            builder
                .Entity<Resource>()
                .HasOne(r => r.Course)
                .WithMany(c => c.Resources)
                .HasForeignKey(r => r.CourseId);

            // Course -> Homeworks one-to-many relation
            builder
                .Entity<Homework>()
                .HasOne(h => h.Course)
                .WithMany(c => c.Homeworks)
                .HasForeignKey(h => h.CourseId);

            // Student -> Homeworks one-to-many relation
            builder
                .Entity<Homework>()
                .HasOne(h => h.Student)
                .WithMany(s => s.Homeworks)
                .HasForeignKey(h => h.StudentId);

            // Resource -> Licenses one-to-many relation
            builder
                .Entity<License>()
                .HasOne(l => l.Resource)
                .WithMany(r => r.Licenses)
                .HasForeignKey(l => l.ResourceId);

            base.OnModelCreating(builder);
        }

        protected override void OnConfiguring(DbContextOptionsBuilder builder)
        {
            builder.UseSqlServer("Server=.;Database=StudentSystem;Integrated Security=True;");
            base.OnConfiguring(builder);
        }
    }
}
