/*
Question:
You are given: n
n: the size of an array of integers, the array list_of_numbers.

Task:
Determine if the product of all the integers in the array is even or odd.
If the product of all the integers is even, return the sum of all the integers. 
However, if any of the integers is 0, return double the sum.
If the product of all the integers is odd, return the product of all the integers. 
However, if any of the integers is 3, return one more than the product.
Note: 0 is considered even.

Example 1
Input:
n = 4
list_of_numbers = 1 2 3 4
Output: 10
*/

function calculateRunningTotal(n, list_of_numbers) {
    let isZero = false, isThree = false;
    let prod = 1, sum = 0;
    for (let i = 0; i < n; i++) {
        if (list_of_numbers[i] == 0 && isZero == false) isZero = true;
        if (list_of_numbers[i] == 3 && isThree == false) isThree = true;
        prod = prod * list_of_numbers[i];
        sum = sum + list_of_numbers[i];
    }
    if (prod % 2 == 0) {
        return sum;
    } else if (prod == 0 && isZero == true) return 2 * sum;
    else if (prod % 2 == 1 && isThree == true) {
        return prod + 1;
    } else {
        return prod
    }
}

const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

rl.question('', (n) => {
    rl.question('', (inputList) => {
        const list_of_numbers = inputList.split(' ').map(Number);
        const result = calculateRunningTotal(n, list_of_numbers);
        console.log(result);
        rl.close();
    });
});
