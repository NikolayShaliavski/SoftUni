using StudentSystemData.Models.Enums;
using System.ComponentModel.DataAnnotations;

namespace StudentSystemData.Models
{
    public class Resource
    {
        public int Id { get; set; }

        [Required]
        public string Name { get; set; }

        [Required]
        public string Url { get; set; }

        public ResourceType ResourceType { get; set; }

        public int CourseId { get; set; }

        public Course Course { get; set; }
    }
}
