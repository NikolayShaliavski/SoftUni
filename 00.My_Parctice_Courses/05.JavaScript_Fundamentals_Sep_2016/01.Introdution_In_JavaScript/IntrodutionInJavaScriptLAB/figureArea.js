function calculateArea(input) {
    var w = Number(input[0]);
    var h = Number(input[1]);
    var W = Number(input[2]);
    var H = Number(input[3]);

    var areasSum = (w * h) + (W * H);

    var minWidth = Math.min(w, W);
    var minHeight = Math.min(h, H);

    areasSum -= minWidth * minHeight;
    console.log(areasSum);
}

calculateArea(['13', '2', '5', '8']);
