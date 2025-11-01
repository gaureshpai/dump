// Program: 4x4 Keypad Interfacing
// Filename: keypad_interfacing.ino

#include <Keypad.h>

// Define the number of rows and columns
const byte ROWS = 4; 
const byte COLS = 4; 

// Define the symbols on the buttons of the keypad
char keys[ROWS][COLS] = {
  {'1','2','3','A'},
  {'4','5','6','B'},
  {'7','8','9','C'},
  {'*','0','#','D'}
};

// Connect to Arduino pins
byte rowPins[ROWS] = {9, 8, 7, 6};
byte colPins[COLS] = {5, 4, 3, 2};

// Create keypad object
Keypad keypad = Keypad(makeKeymap(keys), rowPins, colPins, ROWS, COLS);

void setup() {
  Serial.begin(9600);
}

void loop() {
  char key = keypad.getKey();  // Get the pressed key
  
  if (key) {                   // If a key is pressed
    Serial.print("Key Pressed: ");
    Serial.println(key);
  }
}