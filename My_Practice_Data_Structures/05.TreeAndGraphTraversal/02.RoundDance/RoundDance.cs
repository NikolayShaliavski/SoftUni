using System;
using System.Collections.Generic;
using System.Linq;

class RoundDance
{
    private static Dictionary<int, List<int>> friends;
    private static Dictionary<int, bool> visited;
    private static int maxDepth;

    static void Main(string[] args)
    {
        friends = new Dictionary<int, List<int>>();
        visited = new Dictionary<int, bool>();
        maxDepth = 0;

        var numberOfFriendships = int.Parse(Console.ReadLine());
        var leaderOfDance = int.Parse(Console.ReadLine());

        for (int i = 0; i < numberOfFriendships; i++)
        {
            var friendship = Console.ReadLine().Split(' ').Select(int.Parse).ToArray();
            var leftSide = friendship[0];
            var rightSide = friendship[1];

            if (!friends.ContainsKey(leftSide))
            {
                friends[leftSide] = new List<int>();
            }
            if (!friends.ContainsKey(rightSide))
            {
                friends[rightSide] = new List<int>();
            }
            friends[leftSide].Add(rightSide);
            friends[rightSide].Add(leftSide);

            visited[leftSide] = false;
            visited[rightSide] = false;
        }

        TraverseFriends(leaderOfDance, 1);

        Console.WriteLine(maxDepth);
    }

    private static void TraverseFriends(int currFriend, int depth)
    {
        if (!visited[currFriend])
        {
            visited[currFriend] = true;
            foreach (var friend in friends[currFriend])
            {
                TraverseFriends(friend, depth + 1);
            }
            if (maxDepth < depth)
            {
                maxDepth = depth;
            }
        }
    }
}
