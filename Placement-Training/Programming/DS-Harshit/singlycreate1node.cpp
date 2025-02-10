#include <iostream>
using namespace std;

class Node{
public:
	int data;
	Node *next;
	Node(int value){
		data = value;
		next = NULL;
	}
};

Node *create(Node *head, int value){
	if (head == NULL)
		head = new Node(value);
	else{
		Node *temp = new Node(value);
		temp->next = head;
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
	if (head == NULL)
		head = new Node(value);
	else{
		Node *temp = new Node(value);
		temp->next = head;
		head = temp;
	}
	return head;
}

Node *insert_at_end(Node *head, int value){
	if (head == NULL)
		head = new Node(value);
	else{
		Node *temp1 = new Node(value);
		Node *temp = head;
		while (temp->next != NULL)
			temp = temp->next;
		temp->next = temp1;
	}
	return head;
}

Node *insert_after(Node *head, int value, int pos){
	Node *temp1 = new Node(value);
	Node *temp = head;
	for (int i = 0; i < pos - 1; i++)
		temp = temp->next;
	temp1->next = temp->next;
	temp->next = temp1;
	return head;
}

void delete_at_end(Node *head){
	if (head == NULL)
		cout << "NO HEAD FOUND";
	Node *temp = head;
	while (temp->next != NULL && temp->next->next != NULL)
		temp = temp->next;
	temp->next = NULL;
}

Node *delete_at_start(Node *head){
	if (head == NULL)
		cout << "NO HEAD FOUND";
	Node *temp = head;
	head = temp->next;
	return head;
}

Node *delete_at_position(Node *head, int pos){
	if (head == NULL){
		cout << "List is empty.\n";
		return head;
	}
	if (pos == 0){
		Node *temp = head;
		head = head->next;
		delete temp;
		return head;
	}
	Node *temp = head;
	for (int i = 0; temp != NULL && i < pos - 1; i++)
		temp = temp->next;

	if (temp == NULL || temp->next == NULL){
		cout << "Invalid position.\n";
		return head;
	}

	Node *temp1 = temp->next;
	temp->next = temp1->next;
	delete temp1;
	return head;
}

int main(){
	Node *head = NULL;
	for (int i = 1; i <= 5; i++)
		head = create(head, i);
	head = insert_at_start(head, 6);
	head = insert_at_end(head, 0);
	head = insert_after(head, 10, 0);
	delete_at_end(head);
	head = delete_at_start(head);
	head = delete_at_position(head, 3);
	print(head);
}
