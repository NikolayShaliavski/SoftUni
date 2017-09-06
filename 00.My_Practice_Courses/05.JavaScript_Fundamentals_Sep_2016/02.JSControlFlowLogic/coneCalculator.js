function coneCalculator(input) {
    let radius = parseFloat(input[0]);
    let height = parseFloat(input[1]);

    let volume = (Math.PI * Math.pow(radius, 2) * height) / 3;
    let slantHeight = Math.sqrt(Math.pow(radius, 2) + Math.pow(height, 2));
    let lateralSurface = Math.PI * radius * slantHeight;
    let baseSurface = Math.PI * Math.pow(radius, 2);
    let area = lateralSurface + baseSurface;

    console.log("volume = " + volume.toFixed(4));
    console.log("area = " + area.toFixed(4));
}

coneCalculator(['3', '5']);
