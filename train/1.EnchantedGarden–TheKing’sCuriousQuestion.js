/*
Question:
In a faraway kingdom, there was a magical garden with n enchanted flowers, each initially blooming with 1 petal. 
The king, eager to see the garden thrive, cast a growth spell that activated every night at midnight. 
Under this spell, each flower absorbed the sum of petals of all flowers to its left, including itself. 
This process repeated for k consecutive nights. 
After this period, the king grew curious: "How many petals does the last flower have now?"
Given n (the number of flowers) and k (the number of nights the spell was cast), determine the number of petals on the last flower (i.e., flower at position n) after k nights.
 */

// m = number of flowers, n = number of nights
var solution = function (m, n) {
    let flowers = new Array(m).fill(1);
    
    for (let night = 0; night < n; night++) {
        let prefixSum = 0;
        for (let i = 0; i < m; i++) {
            prefixSum += flowers[i];
            flowers[i] = prefixSum;
        }
    }
    return flowers[m - 1];
};
