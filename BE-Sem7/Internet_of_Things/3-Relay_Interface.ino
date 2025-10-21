// Experiment No: 03
// Develop a program to interface a relay with an Arduino board.

#include "Arduino.h"

const int relayPin = 2;

void setup() {
  pinMode(relayPin, OUTPUT);
}

void loop() {
  digitalWrite(relayPin, HIGH); // Turn the relay ON
  delay(2000);
  digitalWrite(relayPin, LOW);  // Turn the relay OFF
  delay(2000);
}