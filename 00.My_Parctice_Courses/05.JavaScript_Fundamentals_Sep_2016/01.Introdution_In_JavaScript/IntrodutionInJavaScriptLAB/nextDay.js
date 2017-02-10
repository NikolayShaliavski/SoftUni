function addDay(input) {
    var year = Number(input[0]);
    var month = Number(input[1]) - 1;
    var day = Number(input[2]);

    var date = new Date(year, month, day);
    date.setDate(date.getDate() + 1);

    console.log(date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate());
}

addDay(['1951', '12', '24']);
