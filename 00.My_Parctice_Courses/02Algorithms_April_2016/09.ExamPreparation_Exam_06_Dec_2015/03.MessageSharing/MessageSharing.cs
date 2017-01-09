using System;
using System.Collections.Generic;
using System.Linq;

class MessageSharing
{
    static Dictionary<string, List<string>> network;
    static Dictionary<string, bool> visited;
    static Dictionary<string, int> steps;
    static Queue<string> queue;

    static void Main(string[] args)
    {
        network = new Dictionary<string, List<string>>();
        visited = new Dictionary<string, bool>();
        steps = new Dictionary<string, int>();
        queue = new Queue<string>();

        ParseInput();
        var start = Console.ReadLine().Substring(6).Split(new[] { ',', ' ' }, StringSplitOptions.RemoveEmptyEntries);
        foreach (var startPerson in start)
        {
            queue.Enqueue(startPerson);
            steps[startPerson] = 0;
            visited[startPerson] = true;
        }

        while (queue.Count > 0)
        {
            var person = queue.Dequeue();
            foreach (var friend in network[person])
            {
                if (!visited[friend])
                {
                    queue.Enqueue(friend);
                    steps[friend] = steps[person] + 1;
                    visited[friend] = true;
                }
            }
        }
        var minStep = steps.Values.Min();
        if (minStep == -1)
        {
            List<string> notReached = new List<string>();
            foreach (var person in steps)
            {
                if (person.Value == minStep)
                {
                    notReached.Add(person.Key);
                }
            }
            notReached.Sort();
            Console.Write("Cannot reach: ");
            Console.WriteLine(string.Join(", ", notReached));
        }
        else
        {
            var lastStep = steps.Values.Max();
            List<string> lastVisited = new List<string>();
            foreach (var person in steps)
            {
                if (person.Value == lastStep)
                {
                    lastVisited.Add(person.Key);
                }
            }
            lastVisited.Sort();
            Console.WriteLine("All people reached in {0} steps", lastStep);
            Console.WriteLine("People at last step: {0}", string.Join(", ", lastVisited));
        }
    }

    private static void ParseInput()
    {
        var people = Console.ReadLine().Substring(7).Split(new[] { ',', ' ' }, StringSplitOptions.RemoveEmptyEntries);
        var connections = Console.ReadLine().Substring(12).Split(new[] { ',' }, StringSplitOptions.RemoveEmptyEntries);
        foreach (var person in people)
        {
            visited[person] = false;
            steps[person] = -1;
            network[person] = new List<string>();
        }
        foreach (var connection in connections)
        {
            var friendConnection = connection.Split(new[] { '-' }, StringSplitOptions.RemoveEmptyEntries);
            network[friendConnection[0].Trim()].Add(friendConnection[1].Trim());
            network[friendConnection[1].Trim()].Add(friendConnection[0].Trim());
        }
    }
}
