function TreeConstructor(strArr) {
    // 1. Create two objects to store relationships.

    // parentMap will store an array of children for each parent.
    // Example: { "2": ["1", "7"], "4": ["2"] }
    const parentMap = {};

    // childMap will store the parent for each child.
    // This helps us instantly check if a child already has a parent.
    // Example: { "1": "2", "2": "4", "7": "2" }
    const childMap = {};

    // 2. Loop through each (child, parent) pair string.
    for (const pairStr of strArr) {

        // Parse the string: e.g., "(1,2)" -> ["1", "2"]
        const [child, parent] = pairStr.replace(/[()]/g, '').split(',');

        // 3. Check Rule 1: A child can only have one parent.
        if (childMap[child]) {
            // If this child is already in the childMap, it means it
            // already has a parent. This is a violation.
            return "false";
        } else {
            // This is the first time we've seen this child.
            // Record its parent.
            childMap[child] = parent;
        }

        // 4. Check Rule 2: A parent can have at most two children.

        // If this parent isn't in the map yet, initialize it
        // with an empty array.
        if (!parentMap[parent]) {
            parentMap[parent] = [];
        }

        // Add the current child to this parent's list.
        parentMap[parent].push(child);

        // After adding, check if this parent now has more than 2 children.
        if (parentMap[parent].length > 2) {
            // This is a violation of the binary tree rule.
            return "false";
        }
    }

    // 5. If the loop completes without finding any violations...
    // ...it means all pairs can form a valid binary tree.
    return "true";
}

// --- Keep this function call here ---
// Example from your image:
const testInput = ["(1,2)", "(2,4)", "(5,7)", "(7,2)", "(9,5)"];
console.log(TreeConstructor(testInput)); // Output: true

// Example of an invalid tree (child 1 has two parents)
const testInputInvalid1 = ["(1,2)", "(1,3)"];
console.log(TreeConstructor(testInputInvalid1)); // Output: false

// Example of an invalid tree (parent 2 has three children)
const testInputInvalid2 = ["(1,2)", "(3,2)", "(4,2)"];
console.log(TreeConstructor(testInputInvalid2)); // Output: false