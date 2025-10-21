// Experiment No: 05
// Develop a water level depth detection system using an ultrasonic sensor.

#include "Arduino.h"

#define TRIG_PIN 9
#define ECHO_PIN 10 // Corrected from the image which had a typo defining TRIG_PIN twice
const int tankHeight = 30;

void setup() {
  Serial.begin(9600);
  pinMode(TRIG_PIN, OUTPUT);
  pinMode(ECHO_PIN, INPUT);
}

void loop() {
  long duration;
  float distance;

  // Trigger the ultrasonic sensor
  digitalWrite(TRIG_PIN, LOW);
  delayMicroseconds(2);
  digitalWrite(TRIG_PIN, HIGH);
  delayMicroseconds(10);
  digitalWrite(TRIG_PIN, LOW);

  // Read the echo from the sensor
  duration = pulseIn(ECHO_PIN, HIGH);
  
  // Calculate the distance in cm
  distance = duration * 0.034 / 2;

  if (distance > tankHeight || distance <= 0) {
    Serial.println("Invalid reading. Sensor may be misaligned or tank is empty.");
  } else {
    float emptySpace = distance;
    float filledWater = tankHeight - distance;

    Serial.print("Distance to water surface: ");
    Serial.print(distance);
    Serial.println(" cm");

    Serial.print("Filled water level: ");
    Serial.print(filledWater);
    Serial.println(" cm");

    Serial.print("Empty space above water: ");
    Serial.print(emptySpace);
    Serial.println(" cm");
  }

  Serial.println("--------------------");
  delay(1000);
}