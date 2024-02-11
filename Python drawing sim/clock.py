from turtle import Turtle, Screen
import datetime

# get the current time and convert to the hand's angles
wn = Screen()
wn.title("Clock")
wn.bgcolor("saddlebrown")
wn.setup(width=1900, height=1000)
currentDT = datetime.datetime.now()

# output current time
currentHour = currentDT.hour
if currentHour > 12:
    currentHour = currentHour - 12
currentMinute = currentDT.minute
if currentMinute < 10:
    print("Time logged in at - " + str(currentHour) + ":0" + str(currentMinute))
else:
    print("Time logged in at - " + str(currentHour) + ":" + str(currentMinute))

# outside circle
circle = Turtle()
circle.penup()
circle.pencolor("black")
circle.speed(0)
circle.hideturtle()
circle.goto(0, -370)
circle.pendown()
circle.fillcolor("gold")
circle.begin_fill()
circle.circle(380)
circle.end_fill()

# outside outside circle
circle = Turtle()
circle.penup()
circle.pencolor("black")
circle.speed(0)
circle.pensize(35)
circle.hideturtle()
circle.goto(0, -390)
circle.pendown()
circle.fillcolor("gold")
circle.begin_fill()
circle.circle(400)
circle.end_fill()

# hour hand
hourHand = Turtle()
hourHand.shape("arrow")
hourHand.color("black")
hourHand.speed(10)
hourHand.shapesize(stretch_wid=0.4, stretch_len=18)

# minute hand
minuteHand = Turtle()
minuteHand.shape("arrow")
minuteHand.color("black")
minuteHand.speed(10)
minuteHand.shapesize(stretch_wid=0.4, stretch_len=26)

# second hand
secondHand = Turtle()
secondHand.shape("arrow")
secondHand.color("dark red")
secondHand.speed(10)
secondHand.shapesize(stretch_wid=0.4, stretch_len=36)

# inside circle
insideCircle = Turtle()
insideCircle.shape("circle")
insideCircle.color("black")
insideCircle.shapesize(stretch_wid=1.5, stretch_len=1.5)

# numbers with pen
pen = Turtle()
pen.speed(0)
pen.color("black")
pen.penup()
pen.hideturtle()
pen.goto(0, 300)
pen.write("12", align="center", font=("Courier", 50, "normal"))
pen.penup()
pen.hideturtle()
pen.goto(340, -30)
pen.write("3", align="center", font=("Courier", 50, "normal"))
pen.penup()
pen.hideturtle()
pen.goto(0, -370)
pen.write("6", align="center", font=("Courier", 50, "normal"))
pen.penup()
pen.hideturtle()
pen.goto(-340, -30)
pen.write("9", align="center", font=("Courier", 50, "normal"))
pen.penup()
pen.hideturtle()
pen.goto(170, 260)
pen.write("1", align="center", font=("Courier", 50, "normal"))
pen.penup()
pen.hideturtle()
pen.goto(-160, 260)
pen.write("11", align="center", font=("Courier", 50, "normal"))
pen.penup()
pen.hideturtle()
pen.goto(300, 140)
pen.write("2", align="center", font=("Courier", 50, "normal"))
pen.penup()
pen.hideturtle()
pen.goto(-280, 140)
pen.write("10", align="center", font=("Courier", 50, "normal"))
pen.penup()
pen.hideturtle()
pen.goto(300, -200)
pen.write("4", align="center", font=("Courier", 50, "normal"))
pen.penup()
pen.hideturtle()
pen.goto(-300, -200)
pen.write("8", align="center", font=("Courier", 50, "normal"))
pen.penup()
pen.hideturtle()
pen.goto(170, -325)
pen.write("5", align="center", font=("Courier", 50, "normal"))
pen.penup()
pen.hideturtle()
pen.goto(-170, -325)
pen.write("7", align="center", font=("Courier", 50, "normal"))

# moving hour hand
def moveHourHand():
   currentHourInternal = datetime.datetime.now().hour
   degree = (currentHourInternal - 15) * -30
   currentMinuteInternal = datetime.datetime.now().minute
   degree = degree + -0.5 * currentMinuteInternal
   hourHand.setheading(degree)
   wn.ontimer(moveHourHand, 60000)


# moving minute hand
def moveMinuteHand():
    currentMinuteInternal = datetime.datetime.now().minute
    degree = (currentMinuteInternal - 15) * -6
    currentSecondInternal = datetime.datetime.now().second
    degree = degree + (-currentSecondInternal * 0.1)
    minuteHand.setheading(degree)
    wn.ontimer(moveMinuteHand, 1000)

# moving second hand
def moveSecondHand():
    currentSecondInternal = datetime.datetime.now().second
    degree = (currentSecondInternal - 15) * -6
    secondHand.setheading(degree)
    wn.ontimer(moveSecondHand, 1000)

# on timer infinite loop
wn.ontimer(moveHourHand, 1)
wn.ontimer(moveMinuteHand, 1)
wn.ontimer(moveSecondHand, 1)

wn.exitonclick()