function calculate(a, b, op) {
    let sum = (a, b) => { return a + b };
    let subtract = (a, b) => { return a - b };
    let multiply = (a, b) => { return a * b };
    let divide = (a, b) => { return a / b };

    switch (op) {
        case '+': return (sum(a, b));
        case '-': return (subtract(a, b));
        case '*': return (multiply(a, b));
        case '/': return (divide(a, b));
    }
}

console.log(calculate(5, 5, '*'));