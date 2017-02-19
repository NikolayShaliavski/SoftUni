function oddOrEven(input) {
    "use strict";
    let number = Number(input[0]);
    let module = number % 2;

    if (module === 0) {
        console.log("even");
    } else if (module === 1 || module === -1) {
        console.log("odd");
    } else {
        console.log("invalid");
    }
}

oddOrEven(['-3']);
