import matplotlib.pyplot as plt

# Simple Scatter Plot
Day = [1, 2, 3, 4, 5, 6, 7]
Numbers = [2, 3, 1, 4, 5, 3, 6]

plt.scatter(Day, Numbers)
plt.show()

# Scatter Plot with Title
x = [1, 2, 3, 4, 5, 6, 7]
y = [2, 3, 1, 4, 5, 3, 6]

plt.scatter(x, y)
plt.title("Scatter Plot")
plt.show()

# Scatter Plot with Title and Font Size
x = [1, 2, 3, 4, 5, 6, 7]
y = [2, 3, 1, 4, 5, 3, 6]

plt.scatter(x, y)
plt.title("Scatter Plot", fontsize=20)
plt.show()

# Scatter Plot with Title, Labels, and Font Size
Day = [1, 2, 3, 4, 5, 6, 7]
Numbers = [2, 3, 1, 4, 5, 3, 6]

plt.scatter(Day, Numbers)
plt.title("Scatter Plot", fontsize=20)
plt.xlabel("Day")
plt.ylabel("Numbers")
plt.show()

# Scatter Plot with Title, Labels, and Font Size for X and Y Axes
Day = [1, 2, 3, 4, 5, 6, 7]
Numbers = [2, 3, 1, 4, 5, 3, 6]

plt.scatter(Day, Numbers)
plt.title("Scatter Plot", fontsize=20)
plt.xlabel("Day", fontsize=20)
plt.ylabel("Numbers", fontsize=20)
plt.show()

# Scatter Plot with Color (Red)
Day = [1, 2, 3, 4, 5, 6, 7]
Numbers = [2, 3, 1, 4, 5, 3, 6]

plt.scatter(Day, Numbers, color="r")
plt.title("Scatter Plot", fontsize=20)
plt.xlabel("Day", fontsize=20)
plt.ylabel("Numbers", fontsize=20)
plt.show()

# Scatter Plot with Different Colors
Day = [1, 2, 3, 4, 5, 6, 7]
Numbers = [2, 3, 1, 4, 5, 3, 6]

colors = ["r", "y", "g", "b", "r", "g", "r"]
plt.scatter(Day, Numbers, c=colors)
plt.title("Scatter Plot", fontsize=20)
plt.xlabel("Day", fontsize=20)
plt.ylabel("Numbers", fontsize=20)
plt.show()

# Scatter Plot with Dot Size
Day = [1, 2, 3, 4, 5, 6, 7]
Numbers = [2, 3, 1, 4, 5, 3, 6]

colors = ["r", "y", "g", "b", "r", "g", "r"]
sizes = [400, 200, 400, 300, 200, 100, 600]

plt.scatter(Day, Numbers, c=colors, s=sizes)
plt.title("Scatter Plot", fontsize=20)
plt.xlabel("Day", fontsize=20)
plt.ylabel("Numbers", fontsize=20)
plt.show()

# Scatter Plot with Transparency (Dim)
Day = [1, 2, 3, 4, 5, 6, 7]
Numbers = [2, 3, 1, 4, 5, 3, 6]
colors = ["r", "y", "g", "b", "r", "g", "r"]
sizes = [400, 200, 400, 300, 200, 100, 600]

plt.scatter(Day, Numbers, c=colors, s=sizes, alpha=0.2)
plt.title("Scatter Plot", fontsize=20)
plt.xlabel("Day", fontsize=20)
plt.ylabel("Numbers", fontsize=20)
plt.show()

# Scatter Plot with Marker (*)
Day = [1, 2, 3, 4, 5, 6, 7]
Numbers = [2, 3, 1, 4, 5, 3, 6]
colors = ["r", "y", "g", "b", "r", "g", "r"]
sizes = [400, 200, 400, 300, 200, 100, 600]

plt.scatter(Day, Numbers, c=colors, s=sizes, alpha=0.2, marker="*")
plt.title("Scatter Plot", fontsize=20)
plt.xlabel("Day", fontsize=20)
plt.ylabel("Numbers", fontsize=20)
plt.show()

# Scatter Plot with Marker Edge Color
Day = [1, 2, 3, 4, 5, 6, 7]
Numbers = [2, 3, 1, 4, 5, 3, 6]
colors = ["r", "y", "g", "b", "r", "g", "r"]
sizes = [400, 200, 400, 300, 200, 100, 600]

plt.scatter(Day, Numbers, c=colors, s=sizes, marker="*", edgecolor="g")
plt.title("Scatter Plot", fontsize=20)
plt.xlabel("Day", fontsize=20)
plt.ylabel("Numbers", fontsize=20)
plt.show()

# Scatter Plot with Line Width
Day = [1, 2, 3, 4, 5, 6, 7]
Numbers = [2, 3, 1, 4, 5, 3, 6]
colors = ["r", "y", "g", "b", "r", "g", "r"]
sizes = [400, 200, 400, 300, 200, 100, 600]

plt.scatter(Day, Numbers, c=colors, s=sizes, marker="*", edgecolor="g", linewidth=2)
plt.title("Scatter Plot", fontsize=20)
plt.xlabel("Day", fontsize=20)
plt.ylabel("Numbers", fontsize=20)
plt.show()

# Scatter Plot with Color Map (Viridis)
Day = [1, 2, 3, 4, 5, 6, 7]
Numbers = [2, 3, 1, 4, 5, 3, 6]
colors = [10, 49, 30, 29, 56, 20, 30]
sizes = [400, 200, 400, 300, 200, 100, 600]

plt.scatter(Day, Numbers, c=colors, s=sizes, cmap="viridis")
plt.title("Scatter Plot", fontsize=20)
plt.xlabel("Day", fontsize=20)
plt.ylabel("Numbers", fontsize=20)
plt.show()

# Scatter Plot with Color Bar
Day = [1, 2, 3, 4, 5, 6, 7]
Numbers = [2, 3, 1, 4, 5, 3, 6]
colors = [10, 49, 30, 29, 56, 20, 30]
sizes = [400, 200, 400, 300, 200, 100, 600]

plt.scatter(Day, Numbers, c=colors, s=sizes, cmap="viridis")
plt.colorbar()
plt.title("Scatter Plot", fontsize=20)
plt.xlabel("Day", fontsize=20)
plt.ylabel("Numbers", fontsize=20)
plt.show()

# Scatter Plot with Different Color Bar (BrBG)
Day = [1, 2, 3, 4, 5, 6, 7]
Numbers = [2, 3, 1, 4, 5, 3, 6]
colors = [10, 49, 30, 29, 56, 20, 30]
sizes = [400, 200, 400, 300, 200, 100, 600]

plt.scatter(Day, Numbers, c=colors, s=sizes, cmap="BrBG")
plt.colorbar()
plt.title("Scatter Plot", fontsize=20)
plt.xlabel("Day", fontsize=20)
plt.ylabel("Numbers", fontsize=20)
plt.show()

# Scatter Plot with Transparency (Dim/Blurr)
Day = [1, 2, 3, 4, 5, 6, 7]
Numbers = [2, 3, 1, 4, 5, 3, 6]
colors = [10, 49, 30, 29, 56, 20, 30]
sizes = [400, 200, 400, 300, 200, 100, 600]

plt.scatter(Day, Numbers, c=colors, s=sizes, cmap="viridis", alpha=0.2)
plt.colorbar()
plt.title("Scatter Plot", fontsize=20)
plt.xlabel("Day", fontsize=20)
plt.ylabel("Numbers", fontsize=20)
plt.show()

# Scatter Plot with Color Bar Name
Day = [1, 2, 3, 4, 5, 6, 7]
Numbers = [2, 3, 1, 4, 5, 3, 6]
colors = [10, 49, 30, 29, 56, 20, 30]
sizes = [400, 200, 400, 300, 200, 100, 600]

plt.scatter(Day, Numbers, c=colors, s=sizes, cmap="viridis", alpha=0.2)
t = plt.colorbar()
t.set_label("ColorBar")
plt.title("Scatter Plot", fontsize=20)
plt.xlabel("Day", fontsize=20)
plt.ylabel("Numbers", fontsize=20)
plt.show()

# Scatter Plot with Color Bar Font Size
Day = [1, 2, 3, 4, 5, 6, 7]
Numbers = [2, 3, 1, 4, 5, 3, 6]
colors = [10, 49, 30, 29, 56, 20, 30]
sizes = [400, 200, 400, 300, 200, 100, 600]

plt.scatter(Day, Numbers, c=colors, s=sizes, cmap="viridis", alpha=0.2)
t = plt.colorbar()
t.set_label("ColorBar", fontsize=30)
plt.title("Scatter Plot", fontsize=20)
plt.xlabel("Day", fontsize=20)
plt.ylabel("Numbers", fontsize=20)
plt.show()

# Scatter Plot with Different Numbers in a Single Graph
Day = [1, 2, 3, 4, 5, 6, 7]
Numbers = [2, 3, 1, 4, 5, 3, 6]
Numbers2 = [4, 5, 6, 7, 8, 9, 7]
colors = [10, 49, 30, 29, 56, 20, 30]
sizes = [400, 200, 400, 300, 200, 100, 600]

plt.scatter(Day, Numbers, c=colors, s=sizes)
plt.scatter(Day, Numbers2, color="r", s=sizes)
t = plt.colorbar()
t.set_label("ColorBar", fontsize=30)
plt.title("Scatter Plot", fontsize=20)
plt.xlabel("Day", fontsize=20)
plt.ylabel("Numbers", fontsize=20)
plt.show()
