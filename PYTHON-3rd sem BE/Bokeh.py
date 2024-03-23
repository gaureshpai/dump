from bokeh.plotting import figure, show
from bokeh.layouts import layout
from bokeh.models import Div, Spinner, TextInput
import numpy as np

# Generate random data
x = np.random.random(50)
y = np.random.random(50)

# Create the plot
graph = figure()
points = graph.circle(x, y)

# Define the widgets
div = Div(text="<p>Select Size</p>")
spinner = Spinner(title="Circle Size", low=0, high=20, step=1, value=points.glyph.size, width=200)
spinner.js_link("value", points.glyph, "size")

textinput = TextInput(value=points.glyph.fill_color, width=200)
textinput.js_link("value", points.glyph, "fill_color")

# Create the layout
layout = layout([[div, spinner], [textinput], [graph]])

# Show the plot
show(layout)
