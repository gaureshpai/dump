// Program: Moisture Sensor Wet/Dry Detection
// Filename: moisture_sensor.ino

int moistureValue;
float moisture_percentage;

void setup() {
  pinMode(6, OUTPUT);
  pinMode(7, OUTPUT);
  Serial.begin(9600);
}

void loop() {
  moistureValue = analogRead(A0);
  moisture_percentage = (moistureValue / 539.00) * 100;

  if (moisture_percentage <= 100) {
    digitalWrite(6, HIGH);
    digitalWrite(7, LOW);
  } else {
    digitalWrite(6, LOW);
    digitalWrite(7, HIGH);
  }

  Serial.print("\nMoisture Value: ");
  Serial.print(moisture_percentage);
  Serial.println(" %");

  delay(1000);
}