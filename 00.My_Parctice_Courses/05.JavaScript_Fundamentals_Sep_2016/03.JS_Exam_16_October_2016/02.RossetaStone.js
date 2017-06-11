function rossetaStone(stone) {
    var rows = Number(stone[0]);
    var template = new Array(rows);
    for(var i = 1; i <= rows; i++) {
        var row = stone[i].split(' ');
        template[i - 1] = new Array(row.length);
        for (var x = 0; x < row.length; x ++) {
            template[i - 1][x] = Number(row[x]);
        }
    }
    var matrix = [];
    for(var i = rows + 1; i < stone.length; i++) {
        var messageRow = stone[i].split(' ');
        var cell = i - (rows + 1);
        matrix[cell] = new Array(messageRow.length);
        for (var x = 0; x < messageRow.length; x++) {
            matrix[cell][x] = Number(messageRow[x]);
        }
    }
    var tempRows = template.length;
    var tempCols = template[0].length;
    for(var row = 0; row < matrix.length; row++) {
        for (var col = 0; col < matrix[row].length; col++) {
            matrix[row][col] =
                (matrix[row][col] + template[row % tempRows][col % tempCols]) % 27;
        }
    }
    var decriptedMessage = '';
    for(var row = 0; row < matrix.length; row++) {
        for(var col = 0; col < matrix[row].length; col++) {
            if (matrix[row][col] == 0) {
                decriptedMessage += ' ';
            } else {
                decriptedMessage += String.fromCharCode(matrix[row][col] + 64);
            }
        }
    }
    console.log(decriptedMessage);
}

rossetaStone(['2',
    '59 36',
    '82 52',
    '4 18 25 19 8',
    '4 2 8 2 18',
    '23 14 22 0 22',
    '2 17 13 19 20',
    '0 9 0 22 22'
]);