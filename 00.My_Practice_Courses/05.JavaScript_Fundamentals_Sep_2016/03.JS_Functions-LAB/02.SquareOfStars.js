function printSquare(rows = 5) {
    for (let row = 0; row <rows; row++) {
        let result = '';
        for (let col = 0; col <rows; col++) {
            result += ' *';
        }
        console.log(result);
    }
}

printSquare();