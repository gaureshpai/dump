import numpy as np
from bokeh.plotting import figure, output_file, show 

# Line Graph
x = np.arange(0, 10, 1)
y1 = x ** 2
y2 = x ** 3
y3 = x ** 4

# instantiating the figure object 
graph = figure(title="Bokeh Line Graph", x_axis_label="x", y_axis_label="y") 

graph.line(x, y1, legend_label="Quadratic Function", line_width=2, color="red")
graph.line(x, y2, legend_label="Cubic Function", line_width=2, color="green")
graph.line(x, y3, legend_label="Quadratic Function", line_width=2, color="blue")

# displaying the model 
show(graph)

