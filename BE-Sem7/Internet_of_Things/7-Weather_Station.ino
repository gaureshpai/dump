// Experiment No: 07
// Develop a program to demonstrate weather station readings using Arduino.

// This code requires the "DHT sensor library" by Adafruit.
#include "DHT.h"
#include "Arduino.h"

#define DHTPIN 2
#define DHTTYPE DHT11

DHT dht(DHTPIN, DHTTYPE);

void setup() {
  Serial.begin(9600);
  dht.begin();
}

void loop() {
  // Reading temperature or humidity takes about 250 milliseconds!
  float h = dht.readHumidity();
  // Read temperature as Celsius (the default)
  float t = dht.readTemperature();

  // Check if any reads failed and exit early (to try again).
  if (isnan(h) || isnan(t)) {
    Serial.println("Failed to read from DHT sensor!");
    return;
  }

  // Print the results to the Serial Monitor
  Serial.print("Humidity: " + String(h) + "%");
  Serial.println("  Temperature: " + String(t) + "°C");

  delay(2000);
}