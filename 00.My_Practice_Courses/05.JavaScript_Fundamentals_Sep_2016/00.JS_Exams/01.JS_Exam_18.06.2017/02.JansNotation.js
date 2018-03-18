function calculate(input) {
    let stack = [];
    for (let i of input) {
        if (isNaN(parseInt(i))) {
            let x = stack.pop();
            let y = stack.pop();
            let res = 0;
            if (x == undefined || y == undefined) {
                console.log("Error: not enough operands!");
                return;
            }
            switch(i) {
                case '+':
                    res = x + y;
                    stack.push(res);
                    break;
                case '-':
                    res = y - x;
                    stack.push(res);
                    break;
                case '*':
                    res = x * y;
                    stack.push(res);
                    break;
                case '/':
                    res = y / x;
                    stack.push(res);
                    break;
            }
        } else {
            stack.push(i);
        }
    }
    if (stack.length != 1) {
        console.log("Error: too many operands!");
    } else {
        let res = stack.pop();
        console.log(res);
    }
}

calculate([-1, 1, '+', 101, '*', 18, '+', 3, '/']);