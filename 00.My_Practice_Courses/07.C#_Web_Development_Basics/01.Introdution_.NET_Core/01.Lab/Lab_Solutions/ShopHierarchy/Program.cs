using ShopHierarchy.Models;
using System;
using System.Linq;

namespace ShopHierarchy
{
    class Program
    {
        static void Main(string[] args)
        {
            using (ShopDbContext db = new ShopDbContext())
            {
                db.Database.EnsureDeleted();
                db.Database.EnsureCreated();

                Console.WriteLine("Database ready...");
                //db.Database.Migrate();

                PopulateSalesmen(db);
                PopulateItems(db);
                ReadCommands(db);

                int customerId = int.Parse(Console.ReadLine());

                //PrintSalesmenWithCustomers(db);
                //PrintCustomersWithOrdersAndReviews(db);
                //PrintCustomersWithOrdersAndItems(customerId, db);
                //PrintCustomersWithOrdersReviewsAndSalesman(customerId, db);
                PrintCustomerWithOrderWithMoreThanOneItem(customerId, db);
            }
        }
        private static void ReadCommands(ShopDbContext db)
        {
            string line = Console.ReadLine();
            while(line != "END")
            {
                string[] tokens = line.Split('-');
                string command = tokens[0];
                switch (command)
                {
                    case "register":
                        RegisterCustomer(tokens[1], db);
                        break;
                    case "order":
                        string[] orderTokens = tokens[1].Split(';');
                        CreateOrder(orderTokens, db);
                        break;
                    case "review":
                        string[] reviewTokens = tokens[1].Split(';');
                        CreateReview(reviewTokens, db);
                        break;
                }
                db.SaveChanges();
                line = Console.ReadLine();
            }
        }
        private static void CreateReview(string[] tokens, ShopDbContext db)
        {
            int customerId = int.Parse(tokens[0]);
            Review review = new Review();
            Customer customer = db
                .Customers
                .FirstOrDefault(c => c.Id == customerId);
            customer.Reviews.Add(review);
            int itemId = int.Parse(tokens[1]);
            Item item = db
                .Items
                .FirstOrDefault(it => it.Id == itemId);
            review.Item = item;

            db.Reviews.Add(review);
        }
        private static void CreateOrder(string[] tokens, ShopDbContext db)
        {
            int customerId = int.Parse(tokens[0]);
            Order order = new Order();
            Customer customer = db
                .Customers
                .FirstOrDefault(c => c.Id == customerId);
            customer.Orders.Add(order);
            for (int i = 1; i < tokens.Length; i++)
            {
                int itemId = int.Parse(tokens[i]);
                Item item = db
                    .Items
                    .FirstOrDefault(it => it.Id == itemId);
                OrdersItems orderItem = new OrdersItems() { Item = item, Order = order };
                order.OrderItems.Add(orderItem);
            }
            db.Orders.Add(order);
        }
        private static void RegisterCustomer(string line, ShopDbContext db)
        {
            string[] customerTokens = line.Split(';');
            string name = customerTokens[0];
            Customer customer = new Customer() { Name = name };
            db.Customers.Add(customer);
            int salesmanId = int.Parse(customerTokens[1]);
            Salesman salesman = db.Salesmen
                .FirstOrDefault(s => s.Id == salesmanId);
            salesman.Customers.Add(customer);
        }
        private static void PopulateSalesmen(ShopDbContext db)
        {
            string[] salesmenNames = Console.ReadLine().Split(';');
            foreach (var name in salesmenNames)
            {
                Salesman salesman = new Salesman() { Name = name };
                db.Salesmen.Add(salesman);
            }
            db.SaveChanges();
        }
        private static void PopulateItems(ShopDbContext db)
        {
            string line = Console.ReadLine();
            while (line != "END")
            {
                string[] tokens = line.Split(';');
                string itemName = tokens[0];
                decimal itemPrice = decimal.Parse(tokens[1]);
                Item item = new Item() { Name = itemName, Price = itemPrice };
                db.Items.Add(item);

                line = Console.ReadLine();
            }
            db.SaveChanges();
        }
        private static void PrintCustomersWithOrdersAndReviews(ShopDbContext db)
        {
            var customers = db
                .Customers.Select(c => new
                {
                    Name = c.Name,
                    Orders = c.Orders.Count,
                    Reviews = c.Reviews.Count
                })
                .OrderByDescending(c => c.Orders)
                .ThenByDescending(c => c.Reviews)
                .ToList();

            foreach (var customer in customers)
            {
                Console.WriteLine(customer.Name);
                Console.WriteLine($"Orders: {customer.Orders}");
                Console.WriteLine($"Reviews: {customer.Reviews}");
            }
        }
        private static void PrintSalesmenWithCustomers(ShopDbContext db)
        {
            var data = db.Salesmen
                .Select(s => new
                {
                    Name = s.Name,
                    Customers = s.Customers
                            .Select(c => new
                            {
                                Name = c.Name
                            })
                            .ToList()
                })
                .OrderByDescending(s => s.Customers.Count)
                .ThenBy(s => s.Name)
                .ToList();

            foreach (var salesman in data)
            {
                Console.WriteLine($"{salesman.Name} - {salesman.Customers.Count} customers");
                foreach (var customer in salesman.Customers)
                {
                    Console.WriteLine($"---{customer.Name}");
                }
            }
        }
        private static void PrintCustomersWithOrdersAndItems(int customerId, ShopDbContext db)
        {
            var data = db
                .Customers
                .Where(c => c.Id == customerId)
                .Select(c => new
                {
                    Orders = c.Orders
                              .Select(o => new
                              {
                                  Id = o.Id,
                                  Items = o.OrderItems.Count
                              })
                              .OrderBy(o => o.Id),
                    Reviews = c.Reviews.Count
                })
                .FirstOrDefault();

            foreach (var order in data.Orders)
            {
                Console.WriteLine($"order {order.Id}: {order.Items} items");
            }
            Console.WriteLine($"reviews: {data.Reviews}");
        }
        private static void PrintCustomersWithOrdersReviewsAndSalesman(int customerId, ShopDbContext db)
        {
            var customer = db
                .Customers
                .Where(c => c.Id == customerId)
                .Select(c => new
                {
                    c.Name,
                    Orders = c.Orders.Count,
                    Reviews = c.Reviews.Count,
                    Salesman = c.Salesman.Name
                })
                .FirstOrDefault();

            Console.WriteLine($"Customer: {customer.Name}");
            Console.WriteLine($"Orders count: {customer.Orders}");
            Console.WriteLine($"Reviews count: {customer.Reviews}");
            Console.WriteLine($"Salesman: {customer.Salesman}");
        }
        private static void PrintCustomerWithOrderWithMoreThanOneItem(int customerId, ShopDbContext db)
        {
            var customer = db
                .Customers
                .Where(c => c.Id == customerId)
                .Select(c => new
                {
                    Orders = c.Orders.Where(o => o.OrderItems.Count > 1).Count()
                })
                .FirstOrDefault();

            Console.WriteLine($"Orders: {customer.Orders}");
        }
    }
}
