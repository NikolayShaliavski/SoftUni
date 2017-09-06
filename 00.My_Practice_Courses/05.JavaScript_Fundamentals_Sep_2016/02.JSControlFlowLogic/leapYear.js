function leapYear(yearStr) {
    var year = parseInt(yearStr);
    var result = "";
    if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
        result = "yes";
    } else {
        result = "no";
    }
    console.log(result);
}

leapYear('2000')
