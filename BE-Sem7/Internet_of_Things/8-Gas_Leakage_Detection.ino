// Program: Gas Leakage Detection
// Filename: gas_leakage_detector.ino

int gas_pin = A0;
int buzzer_pin = 2;

void setup() {
  Serial.begin(9600);
  pinMode(buzzer_pin, OUTPUT);
  pinMode(gas_pin, INPUT);
}

void loop() {
  float sensorValue = analogRead(gas_pin);

  if (sensorValue >= 100) {
    tone(buzzer_pin, 1000);
    Serial.print(sensorValue);
    Serial.println(" SMOKE DETECTED!");
  } else {
    noTone(buzzer_pin);
    Serial.println("No Smoke Detected");
    Serial.print("Sensor Value: ");
    Serial.println(sensorValue);
  }

  delay(1000);
}