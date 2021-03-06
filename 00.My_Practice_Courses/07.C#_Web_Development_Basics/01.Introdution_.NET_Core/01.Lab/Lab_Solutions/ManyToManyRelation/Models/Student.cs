﻿using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace ManyToManyRelation.Models
{
    public class Student
    {
        public int Id { get; set; }

        [Required]
        [MaxLength(50)]
        public string Name { get; set; }

        public List<StudentsCourses> StudentsCourses { get; set; } = new List<StudentsCourses>();
    }
}
