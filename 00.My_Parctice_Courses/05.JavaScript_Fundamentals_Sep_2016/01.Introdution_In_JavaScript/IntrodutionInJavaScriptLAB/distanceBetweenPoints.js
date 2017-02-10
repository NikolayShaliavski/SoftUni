function calculateDistance(input) {

    var x1 = Number(input[0]);
    var y1 = Number(input[1]);
    var x2 = Number(input[2]);
    var y2 = Number(input[3]);

    var pointOne = {
        x: x1,
        y : y1
    };
    var pointTwo = {
        x: x2,
        y: y2
    };

    var xDist = pointOne.x - pointTwo.x;
    var yDist = pointOne.y - pointTwo.y;

    var distance = Math.sqrt(xDist * xDist + yDist * yDist);
    console.log(distance);
}

calculateDistance(['2.34', '15.66', '-13.55', '-2.9985']);