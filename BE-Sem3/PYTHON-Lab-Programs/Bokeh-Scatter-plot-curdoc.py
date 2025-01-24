
import numpy as np
from bokeh.plotting import figure, output_file, show # Curdoc Functions
from bokeh.layouts import layout

x = np.random.random(50) * 10
y = np.random.random(50) * 200

# instantiating the figure object 
graph = figure(title="Simple Scatter Plot", x_axis_label="x", y_axis_label="y") 

graph.width = 1280
graph.height = 720

circle = graph.circle(x, y, legend_label="Random Points", color="yellow", size=12)

glyph = circle.glyph
glyph.size = 20
glyph.fill_color = "red"

# displaying the model 
show(graph)
