﻿using System.Collections.Generic;

namespace ShopHierarchy.Models
{
    public class Order
    {
        public int Id { get; set; }

        public int CustomerId { get; set; }

        public Customer Customer { get; set; }

        public List<OrdersItems> OrderItems { get; set; } = new List<OrdersItems>();
    }
}
