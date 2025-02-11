#include <iostream>
using namespace std;

class Node {
public:
    int data;
    Node* next;

    Node(int value) {
        data = value;
        next = NULL;
    }
};

// Function to create a new node at the start of the list
Node* create(Node* head, int value) {
    Node* temp = new Node(value);
    temp->next = head;
    return temp;
}

// Function to print the linked list
void print(Node* head) {
    while (head != NULL) {
        cout << head->data << "\t";
        head = head->next;
    }
    cout << "\n";
}

// Insert a node at the beginning
Node* insert_at_start(Node* head, int value) {
    return create(head, value);
}

// Insert a node at the end
Node* insert_at_end(Node* head, int value) {
    if (head == NULL) {
        return new Node(value);
    }
    Node* temp = head;
    while (temp->next != NULL) {
        temp = temp->next;
    }
    temp->next = new Node(value);
    return head;
}

// Insert a node after a given position (0-based index)
Node* insert_after(Node* head, int value, int pos) {
    Node* temp = head;
    for (int i = 0; temp != NULL && i < pos; i++) {
        temp = temp->next;
    }

    if (temp == NULL) {
        cout << "Invalid position.\n";
        return head;
    }

    Node* newNode = new Node(value);
    newNode->next = temp->next;
    temp->next = newNode;
    return head;
}

// Delete a node at the end
Node* delete_at_end(Node* head) {
    if (head == NULL) {
        cout << "List is empty.\n";
        return NULL;
    }
    if (head->next == NULL) {  // Only one node in the list
        delete head;
        return NULL;
    }

    Node* temp = head;
    while (temp->next->next != NULL) {
        temp = temp->next;
    }

    delete temp->next;
    temp->next = NULL;
    return head;
}

// Delete a node at the start
Node* delete_at_start(Node* head) {
    if (head == NULL) {
        cout << "List is empty.\n";
        return NULL;
    }
    Node* temp = head;
    head = head->next;
    delete temp;
    return head;
}

// Delete a node at a specific position (0-based index)
Node* delete_at_position(Node* head, int pos) {
    if (head == NULL) {
        cout << "List is empty.\n";
        return head;
    }
    if (pos == 0) {
        return delete_at_start(head);
    }

    Node* temp = head;
    for (int i = 0; temp != NULL && i < pos - 1; i++) {
        temp = temp->next;
    }

    if (temp == NULL || temp->next == NULL) {
        cout << "Invalid position.\n";
        return head;
    }

    Node* nodeToDelete = temp->next;
    temp->next = nodeToDelete->next;
    delete nodeToDelete;
    return head;
}

// Reverse the linked list
Node* Reverse(Node* head) {
    if (head == NULL) {
        cout << "No head exists\n";
        return head;
    }
    if (head->next == NULL) {
        cout << "Only one Node exists\n";
        return head;
    }

    Node* prev = NULL;
    Node* cur = head;
    Node* fut;

    while (cur != NULL) {
        fut = cur->next;
        cur->next = prev;
        prev = cur;
        cur = fut;
    }

    return prev;
}

// Find the middle node of the linked list using two-pointer technique
void find_the_middle(Node* head) {
    if (head == NULL) {
        cout << "List is empty.\n";
        return;
    }

    Node* slow = head;
    Node* fast = head;

    while (fast != NULL && fast->next != NULL) {
        slow = slow->next;
        fast = fast->next->next;
    }

    cout << "Middle element: " << slow->data << endl;
}

// Main function
int main() {
    Node* head = NULL;

    // Creating a linked list with values 1 to 6
    for (int i = 1; i <= 6; i++) {
        head = create(head, i);
    }
    print(head);

    // Inserting at the start
    head = insert_at_start(head, 6);
    print(head);

    // Inserting at the end
    head = insert_at_end(head, 0);
    print(head);

    // Inserting after position 0 (i.e., second node)
    head = insert_after(head, 10, 0);
    print(head);

    // Deleting the last node
    head = delete_at_end(head);
    print(head);

    // Deleting the first node
    head = delete_at_start(head);
    print(head);

    // Deleting at position 3
    head = delete_at_position(head, 3);
    print(head);

    // Reversing the linked list
    head = Reverse(head);
    print(head);

    // Finding the middle element
    find_the_middle(head);

    return 0;
}