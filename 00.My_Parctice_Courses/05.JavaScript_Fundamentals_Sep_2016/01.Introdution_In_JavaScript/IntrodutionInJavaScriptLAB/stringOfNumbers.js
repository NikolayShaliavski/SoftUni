function concatenateNumbers(input) {
    var n = Number(input[0]);
    var concatenatedString = "";

    for (var i = 1; i <= n; i++) {
        concatenatedString += i;
    }
    console.log(concatenatedString);
}

concatenateNumbers(['11']);
