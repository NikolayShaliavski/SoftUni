﻿using StudentSystemData.Models.Enums;
using System;
using System.ComponentModel.DataAnnotations;

namespace StudentSystemData.Models
{
    public class Homework
    {
        public int Id { get; set; }

        [Required]
        public string Content { get; set; }

        public DateTime SubmissionDate { get; set; }

        public ContentType ContentType { get; set; }

        public int CourseId { get; set; }

        public Course Course { get; set; }

        public int StudentId { get; set; }

        public Student Student { get; set; }
    }
}
