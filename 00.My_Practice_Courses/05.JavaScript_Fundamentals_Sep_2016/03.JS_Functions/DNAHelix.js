function printHeilx(rows) {
    for (let i = 0; i < rows; i++) {
        let reminder = i % 5;
        switch (reminder) {
            case 0:
                console.log('**AT**');
                break;
            case 1:
                console.log('*C--G*');
                break;
            case 2:
                console.log('T----T');
                break;
            case 3:
                console.log('*A--G*');
                break;
            case 4:
                console.log('**GG**');
                break;
        }
    }
}

printHeilx(10);