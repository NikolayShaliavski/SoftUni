function primeChecker(input) {
    "use strict";
    let num = parseInt(input[0]);
    if (num <= 0 || num === 1) {
        return false;
    }
    for (let i = 2; i <= Math.sqrt(num); i++) {
        let module = num % i;
        if (module == 0) {
            return false;
        }
    }
    return true;
}

console.log(primeChecker(['0']));
