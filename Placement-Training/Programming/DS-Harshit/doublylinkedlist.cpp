//Doubly linked list operations

#include <iostream>
using namespace std;

class Node{
public:
	int data;
	Node *before;
	Node *next;
	Node(int value){
		data = value;
		before = NULL;
		next = NULL;
	}
};

Node *create(Node *head, int value){
	if (head == NULL)
		head = new Node(value);
	else{
		Node *temp = new Node(value);
		temp->next = head;
		head->before = temp;
		head = temp;
	}
	return head;
}

void print(Node *head){
	while (head != NULL){
		cout << head->data << "\t";
		head = head->next;
	}
	cout << "\n";
}

Node *insert_at_start(Node *head, int value){
	Node *temp = new Node(value);
	if (head != NULL){
		temp->next = head;
		head->before = temp;
	}
	head = temp;
	return head;
}

Node *insert_at_end(Node *head, int value){
	Node *temp1 = new Node(value);
	if (head == NULL)
		return temp1;
	
	Node *temp = head;
	while (temp->next != NULL)
		temp = temp->next;
	temp->next = temp1;
	temp1->before = temp;
	return head;
}

Node *insert_after(Node *head, int value, int pos){
	Node *temp1 = new Node(value);
	Node *temp = head;
	for (int i = 0; temp != NULL && i < pos - 1; i++)
		temp = temp->next;
	if (temp == NULL){
		cout << "Position out of range\n";
		return head;
	}
	temp1->next = temp->next;
	if (temp->next != NULL)
		temp->next->before = temp1;
	temp->next = temp1;
	temp1->before = temp;
	return head;
}

void delete_at_end(Node *&head){
	if (head == NULL){
		cout << "NO HEAD FOUND\n";
		return;
	}
	if (head->next == NULL){
		delete head;
		head = NULL;
		return;
	}
	Node *temp = head;
	while (temp->next->next != NULL)
		temp = temp->next;
	delete temp->next;
	temp->next = NULL;
}

Node *delete_at_start(Node *head){
	if (head == NULL){
		cout << "NO HEAD FOUND\n";
		return NULL;
	}
	Node *temp = head;
	head = temp->next;
	if (head)
		head->before = NULL;
	delete temp;
	return head;
}

Node *delete_at_position(Node *head, int pos){
	if (head == NULL){
		cout << "List is empty.\n";
		return head;
	}
	if (pos == 0)
		return delete_at_start(head);

	Node *temp = head;
	for (int i = 0; temp != NULL && i < pos - 1; i++)
		temp = temp->next;

	if (temp == NULL || temp->next == NULL){
		cout << "Invalid position.\n";
		return head;
	}

	Node *temp1 = temp->next;
	temp->next = temp1->next;
	if (temp1->next != NULL)
		temp1->next->before = temp;
	delete temp1;
	return head;
}

Node* Reverse(Node* head){
	if (head == NULL || head->next == NULL)
		return head;
	
	Node* prev = NULL;
	Node* cur = head;
	while (cur != NULL){
		prev = cur->before;
		cur->before = cur->next;
		cur->next = prev;
		cur = cur->before;
	}
	if (prev != NULL)
		head = prev->before;
	return head;
}

void find_the_middle(Node* head){
	if (head == NULL){
		cout << "List is empty.\n";
		return;
	}
	Node* slow = head;
	Node* fast = head;
	while (fast != NULL && fast->next != NULL){
		slow = slow->next;
		fast = fast->next->next;
	}
	cout << "Middle element: " << slow->data << endl;
}

int main(){	
	Node *head = NULL;
	for (int i = 1; i <= 5; i++)
		head = create(head, i);
	print(head);
	head = insert_at_start(head, 6);
	print(head);
	head = insert_at_end(head, 0);
	print(head);
	head = insert_after(head, 10, 4);
	print(head);
	delete_at_end(head);
	print(head);
	head = delete_at_start(head);
	print(head);
	head = delete_at_position(head, 3);
	print(head);
	head = Reverse(head);
	print(head);
	find_the_middle(head);
}