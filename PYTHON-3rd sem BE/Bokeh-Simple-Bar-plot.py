import numpy as np
from bokeh.plotting import figure, output_file, show

# Simple Bar Plot
x = np.arange(0, 5, 1)
y = np.random.random(5) * 200

# instantiating the figure object 
graph = figure(title="Simple Bar Plot", x_axis_label="x", y_axis_label="y") 

graph.vbar(x=x, top=y, width=0.5, bottom=0, color="red")
 
show(graph)
