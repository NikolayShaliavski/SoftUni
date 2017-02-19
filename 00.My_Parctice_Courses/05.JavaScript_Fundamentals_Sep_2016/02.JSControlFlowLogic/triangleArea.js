function triangleArea(sides) {
    var a = Number(sides[0]);
    var b = Number(sides[1]);
    var c = Number(sides[2]);

    var s = (a + b + c) / 2;

    var area = Math.sqrt(s * (s - a) * (s - b) * (s - c));

    console.log(area);
}

triangleArea(['2', '3.5', '4']);
