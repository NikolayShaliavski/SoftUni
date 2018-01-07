function modifyAverage(num) {
    num = num.toString();
    let success = average(num);
    while (!success) {
        num += '9';
        success = average(num);
    }
    console.log(num);

    function average(sum) {
        let res = 0;
        for (let i = 0; i < sum.length; i++) {
            let digit = sum[i];
            res += Number(digit);
        }
        if (res / sum.length <= 5) {
            return false;
        }
        return true;
    }
}

modifyAverage(101);