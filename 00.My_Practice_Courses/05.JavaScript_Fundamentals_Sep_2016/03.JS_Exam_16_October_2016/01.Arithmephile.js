function arithmephile(input) {

    var result = Number.MIN_SAFE_INTEGER;
    for (var i = 0; i < input.length; i++) {
        var number = Number(input[i]);
        var currResult = 1;
        if (number > 0 && number < 10) {
            var start = i + 1;
            var end = start + number;
            if (end <= input.length) {
                for (var x = start; x < end; x++) {
                    if (x < input.length) {
                        currResult *= Number(input[x]);
                    }
                }
            }
            if (currResult > result) {
                result = currResult;
            }
        }
    }
    console.log(result);
}

arithmephile(['1', '-200', '3', '-3', '-100', '-35']);
