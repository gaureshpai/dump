// Experiment No: 02
// Develop a program to deploy an intrusion detection system
// using an ultrasound and sound sensor.

#include "Arduino.h"

const int buzzer = 11;
const int trigPin = 7;
const int echoPin = 8;
const int ledPin = 10;
long duration;
int distance;

void setup()
{
    pinMode(buzzer, OUTPUT);
    pinMode(ledPin, OUTPUT);
    pinMode(trigPin, OUTPUT);
    pinMode(echoPin, INPUT);
    Serial.begin(9600);
}

void loop()
{
    // Trigger the ultrasonic sensor
    digitalWrite(trigPin, LOW);
    delayMicroseconds(2);
    digitalWrite(trigPin, HIGH);
    delayMicroseconds(10);
    digitalWrite(trigPin, LOW);

    // Read the echo from the sensor
    duration = pulseIn(echoPin, HIGH);

    // Calculate the distance in cm
    distance = (duration * 0.034) / 2;

    // Print the distance to the Serial Monitor
    Serial.print("Distance: ");
    Serial.print(distance);
    Serial.println(" cm");

    // Check if an object is within 20 cm
    if (distance < 20)
    {
        digitalWrite(ledPin, HIGH);
        tone(buzzer, 1000);
    }
    else
    {
        digitalWrite(ledPin, LOW);
        noTone(buzzer);
    }
    delay(100);
}