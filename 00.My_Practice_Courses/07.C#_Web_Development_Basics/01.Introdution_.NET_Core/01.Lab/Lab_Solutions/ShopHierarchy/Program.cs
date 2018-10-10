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

                PopulateSalesmen(db);
                ReadCommands(db);
                PrintSalesmenWithCustomers(db);
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
                        string[] customerTokens = tokens[1].Split(';');
                        string name = customerTokens[0];
                        Customer customer = new Customer() { Name = name };
                        db.Customers.Add(customer);
                        int salesmanId = int.Parse(customerTokens[1]);
                        Salesman salesman = db.Salesmen
                            .FirstOrDefault(s => s.Id == salesmanId);
                        salesman.Customers.Add(customer);
                        break;
                }

                line = Console.ReadLine();
            }
            db.SaveChanges();
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
    }
}
