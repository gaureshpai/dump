import matplotlib.pyplot as plt
import numpy as np

# Simple Bar Plot
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]

plt.bar(x, y)
plt.show()

# Bar Plot with Labels and Title
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]

plt.xlabel("Language")
plt.ylabel("Number")
plt.title("WSCUBE")
plt.bar(x, y)
plt.show()

# Bar Plot with Font Size
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]

plt.xlabel("Language", fontsize=20)
plt.ylabel("Number", fontsize=20)
plt.title("WSCUBE", fontsize=20)
plt.bar(x, y)
plt.show()

# Bar Plot with Width
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]

plt.xlabel("Language", fontsize=20)
plt.ylabel("Number", fontsize=20)
plt.title("WSCUBE", fontsize=20)
plt.bar(x, y, width=0.4)
plt.show()

# Bar Plot with Color
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]

plt.xlabel("Language", fontsize=20)
plt.ylabel("Number", fontsize=20)
plt.title("WSCUBE", fontsize=20)
plt.bar(x, y, width=0.4, color="y")
plt.show()

# Bar Plot with Different Colors
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]

plt.xlabel("Language", fontsize=20)
plt.ylabel("Number", fontsize=20)
plt.title("WSCUBE", fontsize=20)
colors = ["y", "b", "m", "g"]
plt.bar(x, y, width=0.4, color=colors)
plt.show()

# Bar Plot with Align (Edge)
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]

plt.xlabel("Language", fontsize=20)
plt.ylabel("Number", fontsize=20)
plt.title("WSCUBE", fontsize=20)
colors = ["y", "b", "m", "g"]
plt.bar(x, y, width=0.4, color=colors, align="edge")
plt.show()

# Bar Plot with Align (Center)
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]
colors = ["y", "b", "m", "g"]

plt.xlabel("Language", fontsize=20)
plt.ylabel("Number", fontsize=20)
plt.title("WSCUBE", fontsize=20)

plt.bar(x, y, width=0.4, color=colors, align="center")
plt.show()

# Bar Plot with Edge Color
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]
colors = ["y", "b", "m", "g"]

plt.xlabel("Language", fontsize=20)
plt.ylabel("Number", fontsize=20)
plt.title("WSCUBE", fontsize=20)

plt.bar(x, y, width=0.4, color=colors, align="center", edgecolor="r")
plt.show()

# Bar Plot with Line Width
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]
colors = ["y", "b", "m", "g"]

plt.xlabel("Language", fontsize=20)
plt.ylabel("Number", fontsize=20)
plt.title("WSCUBE", fontsize=20)

plt.bar(x, y, width=0.4, color=colors, align="center", edgecolor="r", linewidth=10)
plt.show()

# Bar Plot with Line Style (:)
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]
colors = ["y", "b", "m", "g"]

plt.xlabel("Language", fontsize=20)
plt.ylabel("Number", fontsize=20)
plt.title("WSCUBE", fontsize=20)

plt.bar(x, y, width=0.4, color=colors, align="center", edgecolor="r", linewidth=10, linestyle=":")
plt.show()

# Bar Plot with Color Dim (Alpha)
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]
colors = ["y", "b", "m", "g"]

plt.xlabel("Language", fontsize=20)
plt.ylabel("Number", fontsize=20)
plt.title("WSCUBE", fontsize=20)

plt.bar(x, y, width=0.4, color=colors, align="center", edgecolor="r", linewidth=10, linestyle=":", alpha=0.4)
plt.show()

# Bar Plot with Label Legend
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]
colors = ["y", "b", "m", "g"]

plt.xlabel("Language", fontsize=20)
plt.ylabel("Number", fontsize=20)
plt.title("WSCUBE", fontsize=20)

plt.bar(x, y, width=0.4, color=colors, align="center", edgecolor="r", label="Popularity")
plt.legend()
plt.show()

# Multiple Label Legends
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]

plt.xlabel("Language", fontsize=20)
plt.ylabel("Number", fontsize=20)
plt.title("WSCUBE", fontsize=20)

plt.bar(x, y, width=0.4, color="r", label="Popularity")
plt.bar(x, y, width=0.4, color="b", label="Popularity1")
plt.legend()
plt.show()

# Multiple Bars with Same Color (Overlapping)
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]
z = [20, 30, 40, 50]

plt.xlabel("Language", fontsize=20)
plt.ylabel("Number", fontsize=20)
plt.title("WSCUBE", fontsize=20)

plt.bar(x, y, width=0.4, color="r", label="Popularity")
plt.bar(x, z, width=0.4, color="r", label="Popularity1", bottom=y)
plt.legend()
plt.show()

# Multiple Bars with Different Colors (Overlapping)
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]
z = [20, 30, 40, 50]

plt.xlabel("Language", fontsize=20)
plt.ylabel("Number", fontsize=20)
plt.title("WSCUBE", fontsize=20)

plt.bar(x, y, width=0.4, color="r", label="Popularity")
plt.bar(x, z, width=0.4, color="y", label="Popularity1", bottom=y)
plt.legend()
plt.show()

# Reduced Width
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]
z = [20, 30, 40, 50]

plt.xlabel("Language", fontsize=20)
plt.ylabel("Number", fontsize=20)
plt.title("WSCUBE", fontsize=20)
width = 0.2

plt.bar(x, y, width, color="r", label="Popularity")
plt.bar(x, z, width, color="y", label="Popularity1", bottom=y)
plt.legend()
plt.show()

# Arranging in Numbers
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]
z = [20, 30, 40, 50]
p = np.arange(len(x))

plt.xlabel("Language", fontsize=20)
plt.ylabel("Number", fontsize=20)
plt.title("WSCUBE", fontsize=20)
width = 0.2

plt.bar(p, y, width, color="r", label="Popularity")
plt.bar(p + width, z, width, color="y", label="Popularity1")
plt.xticks(p + width / 2, x)
plt.legend()
plt.show()

# Side-by-Side Bars
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]
z = [20, 30, 40, 50]
width = 0.2
p = np.arange(len(x))
p1 = [j + width for j in p]

plt.xlabel("Language", fontsize=20)
plt.ylabel("Number", fontsize=20)
plt.title("WSCUBE", fontsize=20)

plt.bar(p, y, width, color="r", label="Popularity")
plt.bar(p1, z, width, color="y", label="Popularity1")
plt.legend()
plt.show()

# Changing X-axis Position
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]
z = [20, 30, 40, 50]
width = 0.2
p = np.arange(len(x))
p1 = [j + width for j in p]

plt.xlabel("Language", fontsize=20)
plt.ylabel("Number", fontsize=20)
plt.title("WSCUBE", fontsize=20)

plt.bar(p, y, width, color="r", label="Popularity")
plt.bar(p1, z, width, color="y", label="Popularity1")
plt.xticks(p + width, x)
plt.legend()
plt.show()

# X-axis Name at Center
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]
z = [20, 30, 40, 50]
width = 0.2
p = np.arange(len(x))
p1 = [j + width for j in p]

plt.xlabel("Language", fontsize=20)
plt.ylabel("Number", fontsize=20)
plt.title("WSCUBE", fontsize=20)

plt.bar(p, y, width, color="r", label="Popularity")
plt.bar(p1, z, width, color="y", label="Popularity1")
plt.xticks(p + width / 2, x)
plt.legend()
plt.show()

# Rotation of X-axis Labels
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]
z = [20, 30, 40, 50]
width = 0.2
p = np.arange(len(x))
p1 = [j + width for j in p]

plt.xlabel("Language", fontsize=20)
plt.ylabel("Number", fontsize=20)
plt.title("WSCUBE", fontsize=20)

plt.bar(p, y, width, color="r", label="Popularity")
plt.bar(p1, z, width, color="y", label="Popularity1")
plt.xticks(p + width / 2, x, rotation=45)
plt.legend()
plt.show()

# Horizontal and Vertical Bar
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]
z = [20, 30, 40, 50]

plt.xlabel("Language", fontsize=20)
plt.ylabel("Number", fontsize=20)
plt.title("WSCUBE", fontsize=20)

plt.barh(x, y, color="r", label="Popularity")
plt.bar(x, z, color="y", label="Popularity1")
plt.legend()
plt.show()

# Horizontal Bar
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]
z = [20, 30, 40, 50]

plt.xlabel("Language", fontsize=20)
plt.ylabel("Number", fontsize=20)
plt.title("WSCUBE", fontsize=20)

plt.barh(x, y, color="r", label="Popularity")
plt.barh(x, z, color="y", label="Popularity1")
plt.legend()
plt.show()

# Reduced Size of Horizontal Bar
x = ["Python", "C", "C++", "Java"]
y = [85, 70, 60, 82]
z = [20, 30, 40, 50]
width = 0.2
p = np.arange(len(x))
p1 = [j + width for j in p]

plt.xlabel("Language", fontsize=20)
plt.ylabel("Number", fontsize=20)
plt.title("WSCUBE", fontsize=20)

plt.barh(p, y, width, color="r", label="Popularity")
plt.barh(p1, z, width, color="y", label="Popularity1")
plt.legend()
plt.show()
