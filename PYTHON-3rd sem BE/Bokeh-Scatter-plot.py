import numpy as np
from bokeh.plotting import figure, output_file, show 

# Scatter Plot
x = np.random.random(50) * 10
y = np.random.random(50) * 200

# instantiating the figure object 
graph = figure(title="Simple Scatter Plot", x_axis_label="x", y_axis_label="y") 

graph.circle(x, y, legend_label="Random Points", color="yellow", size=12)
 
show(graph)
