const int rSensor = 5;
const int lSensor = 4;

const int startPin = 3;
const int leftOutPin = 5;
const int rightOutPin = 6;

int leftReading;
int rightReading;

bool leftTriggered = false;
bool rightTriggered = false;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
  rightReading = analogRead(rSensor);
  leftReading = analogRead(lSensor);

//  Serial.print("R:");
//  Serial.print(rightReading);
//  Serial.print(" L:");
//  Serial.println(leftReading);

  if (leftTriggered && rightTriggered) {
    if (rightReading > 600) {
      digitalWrite(rightOutPin, HIGH);
      digitalWrite(leftOutPin, LOW);
//      Serial.print("R:");
//      Serial.println(rightReading);
    } else if (leftReading > 600) {
      digitalWrite(rightOutPin, LOW);
      digitalWrite(leftOutPin, HIGH);
//      Serial.print("L:");
//      Serial.println(leftReading);
    }
  } else if (rightReading > 600) {
    rightTriggered = true;
//    Serial.println("Right Triggered");
  } else if (leftReading > 600) {
    leftTriggered = true;
//    Serial.println("Left Triggered");
  }
}
