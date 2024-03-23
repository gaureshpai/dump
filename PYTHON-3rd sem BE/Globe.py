#Importing the required libraries
import plotly.graph_objects as go

# Orthographic Projection
fig_ortho = go.Figure(go.Scattergeo())
fig_ortho.update_geos(projection_type="orthographic")
fig_ortho.update_layout(height=300, margin={"r":0,"t":0,"l":0,"b":0})

# Natural Earth Projection
fig_natural_earth = go.Figure(go.Scattergeo())
fig_natural_earth.update_geos(projection_type="natural earth")
fig_natural_earth.update_layout(height=300, margin={"r":0,"t":0,"l":0,"b":0})

# Asia Map
fig_asia = go.Figure(go.Scattergeo())
fig_asia.update_geos(visible=False, resolution=50, scope="asia",showcountries=True, countrycolor="Black",showsubunits=True, subunitcolor="Blue")
fig_asia.update_layout(height=300, margin={"r":0,"t":0,"l":0,"b":0})

# Displaying the maps
fig_ortho.show()
fig_natural_earth.show()
fig_asia.show()
