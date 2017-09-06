function circleArea(radius) {
    radius = parseInt(radius);

    var area = radius * radius * Math.PI;

    console.log(area);
    var fixed = area.toFixed(2);
    console.log(fixed);
}

circleArea(5);
