function sumAndVAT (numbers) {

    var length = numbers.length;
    var sum = 0;
    for (var i = 0; i < length; i++) {
        sum += Number(numbers[i]);
    }
    var vat = sum * 0.2;
    console.log('sum = ' + sum);
    console.log('VAT = ' + vat);
    console.log('total = ' + (sum + vat).toFixed(5));
}

sumAndVAT(['3.12', '5', '18', '19.24', '1953.2262', '0.001564', '1.1445']);
