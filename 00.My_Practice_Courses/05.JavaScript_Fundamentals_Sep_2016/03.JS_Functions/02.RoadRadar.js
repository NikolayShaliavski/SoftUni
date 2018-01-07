function radar(input) {
    let motorwaySpeed = 130;
    let interstateSpeed = 90;
    let citySpeed = 50;
    let residentalSpeed = 20;

    let speed = Number(input[0]);
    let area = input[1];

    console.log(rideInLimits(speed, area));

    function rideInLimits(speed, area) {
        let difference = 0;
        switch (area) {
            case 'motorway':
                difference = speed - motorwaySpeed;
                break;
            case 'interstate':
                difference = speed - interstateSpeed;
                break;
            case 'city':
                difference = speed - citySpeed;
                break;
            case 'residential':
                difference = speed - residentalSpeed;
                break;
        }
        if (difference <= 0) {
            return '';
        }
        if (difference <= 20) {
            return 'speeding';
        }
        if (difference <= 40) {
            return 'excessive speeding';
        }
        return 'reckless driving';
    }
}

radar([[21, 'residential']]);