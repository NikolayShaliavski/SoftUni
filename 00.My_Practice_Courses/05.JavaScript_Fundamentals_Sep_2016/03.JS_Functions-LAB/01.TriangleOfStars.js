function printTriangle(rows) {
    for (let row = 1; row <= rows; row++) {
        console.log('*'.repeat(row));
    }
    for (let row = rows - 1; row > 0; row--) {
        console.log('*'.repeat(row));
    }
}

printTriangle(4);