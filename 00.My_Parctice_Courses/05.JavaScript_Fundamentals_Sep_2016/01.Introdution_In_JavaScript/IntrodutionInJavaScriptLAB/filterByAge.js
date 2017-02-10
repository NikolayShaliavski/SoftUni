function filterByAge(input) {
    var minAge = Number(input[0]);

    var firstName = input[1];
    var firstAge = Number(input[2]);
    var secondName = input[3];
    var secondAge = Number(input[4]);

    var personOne = {
        name: firstName,
        age: firstAge,
        personInfo: function() {
            return "{ name: '" + this.name + "', age: " + this.age + " }";
        }
    };

    var personTwo = {
        name: secondName,
        age: secondAge,
        personInfo: function() {
            return "{ name: '" + this.name + "', age: " + this.age + " }";
        }
    };

    if (personOne.age >= minAge) {
        console.log(personOne.personInfo());
    }
    if (personTwo.age >= minAge) {
        console.log(personTwo.personInfo());
    }
}

filterByAge(['12', 'Ivan', '15', 'Asen', '9']);
