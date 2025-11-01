// Program: UART Communication
// Filename: uart_communication.ino

char data;  // Variable to store incoming data

void setup() {
  Serial.begin(9600);      // Initialize UART communication
  Serial.println("UART Communication Started");
}

void loop() {
  if (Serial.available() > 0) {  // Check if data is available
    data = Serial.read();        // Read the data
    Serial.print("Received: ");
    Serial.println(data);        // Echo it back

    // Optional LED feedback (for demo)
    if (data == '1') {
      digitalWrite(13, HIGH);
      Serial.println("LED ON");
    } else if (data == '0') {
      digitalWrite(13, LOW);
      Serial.println("LED OFF");
    }
  }
}