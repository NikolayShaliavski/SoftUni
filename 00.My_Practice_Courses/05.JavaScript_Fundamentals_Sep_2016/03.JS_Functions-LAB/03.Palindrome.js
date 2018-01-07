/*function isPalindrome(word) {
    let arr = word.split('');
    arr.reverse();
    let reversed = arr.join('');
    if (word === reversed) {
        return true;
    }
    return false;
}

console.log(isPalindrome('racecar'));*/

((word) => word.split('').reverse().join('') === word ? console.log(true) : console.log(false))('racecar');
