# Social Networks Notes

## Module 1

### Video 1: Introduction to Social Networks

50 dots, connect puzzle

---

### Video 2: Answer to the Puzzle

Solution to the network puzzle where even if we divide it into two different classes, that is 25-25 each, even if one person has a friendship with the other class, the whole mesh becomes one and not two. Out of the probable 625-625 friendships, there will be one or two instances where there are existing friends, so that will make the mesh one and hence the gossip is spread.

---

### Video 3: Introduction to Python - 1

```python
import random

random.randrange(1, 10)  # generates a random number each time it is executed.
random.random()          # generates a random number each time it is executed between 0-1.
```

Use **Tab** key after dot to check for the function availability of that particular import.

For list, we have:

- `list.reverse`
- `list.remove`
- `list.append`
- `list.sort`

The command used to clear the screen is `clear`.

`!` starts for outside command, `vi` starts for the editor name, and the file name starts for the whatever file we have to open it in.

Is reload function for the file?

We execute a function in the terminal directly by importing the function and then writing or we can say that then importing it as `file name.function name` to change once editing, edit it, reload it using the reload function.

---

### Video 4: Introduction to Python - 2

Dictionary:

Order of elements in dictionary is not important. It is a key-value pair.

`dict = {"a": 1, "b": 2, "c": 3}` Del dictionary name deletes the whole dictionary. `del dict`

- `dict.keys`
- `dict.values`
- `dict.items`
- `dict.get`
- `dict.pop`
- `dict.clear`
- `dict.update`

import matplotlib.pyplot as plt

- `plt.plot`
- `plt.xlabel`
- `plt.ylabel`
- `plt.title`
- `plt.show`
- `plt.close`
- `plt.legend`
- `plt.grid`
- `plt.scatter`

import numpy as np

- `np.array`
- `np.zeros`
- `np.ones`
- `np.full`
- `np.random`
- `np.linspace`
- `np.eye`
- `np.dot`

### Video 5: Introduction to NetworkX - 1

The NetworkX is a package for the creation, manipulation, and study of the structure, dynamics, and functions of complex networks.

```python
import networkx as nx

g = nx.Graph()

g.add_node(1)
g.add_node(2)
g.add_node(3)
g.add_node(4)
g.add_node(5)

# prints the list of nodes
print(g.nodes())

g.add_edge(1, 2)
g.add_edge(2, 3)
g.add_edge(3, 4)
g.add_edge(4, 5)
g.add_edge(5, 1)

# prints the list of edges
print(g.edges())

nx.complete_graph(10)
nx.path_graph(10)
nx.cycle_graph(10)
G = nx.gnp_random_graph(10, 0.5)
```

### Video 6: Introduction to NetworkX - 2

Modeling Road Network of India's Cities

```python
import networkx as nx
import matplotlib.pyplot as plt
import random

G = nx.Graph() # undirected graph
# G = nx.DiGraph() # directed graph

city_set = ['Delhi', 'Kolkatta', 'Bangalore', 'Jaipur', 'Chennai']

for each_city in city_set:
    G.add_node(each_city)

nx.draw(G)
plt.show()

# Generating a list of costs from 100 to 2000
cost = []
value = 100

while value <= 2000:
    cost.append(value)
    value = value + 100

print(cost)

# Adding 16 random edges to the network
while(G.number_of_edges() < 16):
    c1 = random.choice(list(G.nodes()))
    c2 = random.choice(list(G.nodes()))
    if c1 != c2 and G.has_edge(c1, c2) == 0:
        w = random.choice(cost)
        G.add_edge(c1, c2, weight=w)

pos = nx.circular_layout(G)

nx.draw(G)
nx.draw_networkx_labels(G, pos)
plt.show()
```
