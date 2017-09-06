function letterOccurencies(input) {
    var word = input[0];
    var letter = input[1];
    var count = 0;

    for (var i = 0; i < word.length; i++) {
        if (word.charAt(i) === letter) {
            count++;
        }
    }
    console.log(count);
}

letterOccurencies(['hello', 'l'])
