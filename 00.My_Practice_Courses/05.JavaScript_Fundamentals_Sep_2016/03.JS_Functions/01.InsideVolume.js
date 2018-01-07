function insideVolume(input) {

    let x1 = 10;
    let x2 = 50;
    let y1 = 20;
    let y2 = 80;
    let z1 = 14;
    let z2 = 50;

    for (let i = 0; i < input.length; i += 3) {
        let x = input[i];
        let y = input[i + 1];
        let z = input[i + 2];

        let res = isInside(x, y, z);
        if (res) {
            console.log('inside')
        } else {
            console.log('outside');
        }
    }

    function isInside(x, y, z) {
        if (x >= x1 && x <= x2 &&
            y >= y1 && y <= y2 &&
            z >= z1 && z <= z2) {
            return true;
        }
        return false;
    }
}

insideVolume([8, 20, 22]);