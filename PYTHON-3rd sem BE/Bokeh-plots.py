from bokeh.plotting import figure, show
from bokeh.layouts import row
import numpy as np

# Define the data
x = np.arange(0, 6, 0.1)
y1 = np.sin(x)
y2 = np.cos(x)
y3 = np.tan(x)

# Create figures and lines
graph1 = figure()
graph1.line(x, y1)

graph2 = figure()
graph2.line(x, y2)

graph3 = figure()
graph3.line(x, y3)

# Display the plots
show(row(children=[graph1, graph2, graph3], sizing_mode="scale_width"))
