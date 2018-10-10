using System.Collections.Generic;

namespace ShopHierarchy.Models
{
    public class Salesman
    {
        public int Id { get; set; }

        public string Name { get; set; }

        public List<Customer> Customers { get; set; } = new List<Customer>();
    }
}
