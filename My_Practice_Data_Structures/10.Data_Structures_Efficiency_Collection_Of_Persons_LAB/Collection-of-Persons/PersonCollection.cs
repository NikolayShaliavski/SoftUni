using System;
using System.Collections.Generic;
using Wintellect.PowerCollections;

public class PersonCollection : IPersonCollection
{
    private Dictionary<string, Person> peopleByEmail;

    private Dictionary<string, SortedSet<Person>> peopleByEmailDomain;

    private Dictionary<string, SortedSet<Person>> peopleByNameAndTown;

    private OrderedDictionary<int, SortedSet<Person>> peopleByAge;

    private Dictionary<string, OrderedDictionary<int, SortedSet<Person>>> peopleByTownAndAge;

    public PersonCollection()
    {
        this.peopleByEmail = new Dictionary<string, Person>();
        this.peopleByEmailDomain = new Dictionary<string, SortedSet<Person>>();
        this.peopleByNameAndTown = new Dictionary<string, SortedSet<Person>>();
        this.peopleByAge = new OrderedDictionary<int, SortedSet<Person>>();
        this.peopleByTownAndAge = new Dictionary<string, OrderedDictionary<int, SortedSet<Person>>>();
    }

    public bool AddPerson(string email, string name, int age, string town)
    {
        var person = this.FindPerson(email);
        if (person != null)
        {
            return false;
        }
        Person newPerson = new Person(email, name, age, town);
        
        this.peopleByEmail.Add(email, newPerson);

        string emailDomain = this.ExtractEmailDomain(email);
        this.peopleByEmailDomain.AppendValueToKey(emailDomain, newPerson);

        string nameAndTown = this.CombineNameAndTown(name, town);
        this.peopleByNameAndTown.AppendValueToKey(nameAndTown, newPerson);

        this.peopleByAge.AppendValueToKey(age, newPerson);

        this.peopleByTownAndAge.EnsureKeyExists(town);
        this.peopleByTownAndAge[town].AppendValueToKey(age, newPerson);

        return true;
    }

    public int Count
    {
        get
        {
            return this.peopleByEmail.Count;
        }
    }

    public Person FindPerson(string email)
    {
        Person person;
        var personExists = this.peopleByEmail.TryGetValue(email, out person);
        return person;
    }

    public bool DeletePerson(string email)
    {
        var person = this.FindPerson(email);
        if (person == null)
        {
            return false;
        }
        this.peopleByEmail.Remove(email);

        var emailDomain = this.ExtractEmailDomain(email);
        this.peopleByEmailDomain[emailDomain].Remove(person);

        var nameAndTown = this.CombineNameAndTown(person.Name, person.Town);
        this.peopleByNameAndTown[nameAndTown].Remove(person);

        this.peopleByAge[person.Age].Remove(person);

        this.peopleByTownAndAge[person.Town][person.Age].Remove(person);

        return true;
    }

    public IEnumerable<Person> FindPersons(string emailDomain)
    {
        return this.peopleByEmailDomain.GetValuesForKey(emailDomain);
    }

    public IEnumerable<Person> FindPersons(string name, string town)
    {
        var nameAndTown = this.CombineNameAndTown(name, town);
        return this.peopleByNameAndTown.GetValuesForKey(nameAndTown);
    }

    public IEnumerable<Person> FindPersons(int startAge, int endAge)
    {
        var peopleInRange =
            this.peopleByAge.Range(startAge, true, endAge, true);
        foreach (var personsByAge in peopleInRange)
        {
            foreach (var person in personsByAge.Value)
            {
                yield return person;
            }
        }
    }

    public IEnumerable<Person> FindPersons(
        int startAge, int endAge, string town)
    {
        if (!this.peopleByTownAndAge.ContainsKey(town))
        {
            yield break;
        }
        var peopleInRange =
            this.peopleByTownAndAge[town].Range(startAge, true, endAge, true);
        foreach (var personsByAge in peopleInRange)
        {
            foreach (var person in personsByAge.Value)
            {
                yield return person;
            }
        }
    }

    private string ExtractEmailDomain(string email)
    {
        var domain = email.Split('@')[1];
        return domain;
    }

    private string CombineNameAndTown(string name, string town)
    {
        const string separator = "|!|";
        return name + separator + town;
    }
}
