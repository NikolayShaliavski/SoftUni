using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace ShopHierarchy.Models
{
    public class Item
    {
        public int Id { get; set; }

        [Required]
        [MaxLength(50)]
        public string Name { get; set; }

        public decimal Price { get; set; }

        public List<OrdersItems> ItemOrders { get; set; } = new List<OrdersItems>();

        public List<Review> Reviews { get; set; } = new List<Review>();
    }
}
