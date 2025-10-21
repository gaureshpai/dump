// Experiment No: 04
// Develop a program to control a DC motor with an Arduino board.

#include "Arduino.h"

int in1 = 8; // Note: Transcribed from "int1" in the image
int in2 = 7; // Note: Transcribed from "int2" in the image

void setup() {
  pinMode(in1, OUTPUT);
  pinMode(in2, OUTPUT);
  
  // Start with the motor off
  digitalWrite(in1, LOW);
  digitalWrite(in2, LOW);
}

void loop() {
  // Rotate in one direction for 2 seconds
  digitalWrite(in1, HIGH);
  digitalWrite(in2, LOW);
  delay(2000);

  // Rotate in the opposite direction for 2 seconds
  digitalWrite(in1, LOW);
  digitalWrite(in2, HIGH);
  delay(2000);

  // Stop the motor
  digitalWrite(in1, LOW);
  digitalWrite(in2, LOW);
  // No delay here as per the image, the loop will restart immediately
}