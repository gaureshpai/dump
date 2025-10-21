// Experiment No: 06
// Develop a program to deploy a smart street light system using an LDR sensor.

#include "Arduino.h"

void setup() {
  pinMode(2, OUTPUT); // Pin 2 is for the LED/light
  Serial.begin(9600);
}

void loop() {
  // Read LDR value from analog pin A0 (0-1023)
  int ldrValue = analogRead(A0);
  Serial.println(ldrValue);

  // Map the LDR value to LED brightness
  // Note: The image maps light to ON (255) and dark to OFF (0).
  // A typical street light would be the reverse: map(ldrValue, 33, 1023, 255, 0);
  int ledBrightness = map(ldrValue, 33, 1023, 0, 255);

  // Control LED brightness using analogWrite (PWM)
  analogWrite(2, ledBrightness);

  delay(500);
}