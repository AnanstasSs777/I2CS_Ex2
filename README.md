2D Map Java Project
This project implements a 2D raster map in Java, providing a framework for working with grids, drawing, scaling, and pathfinding. 
It is designed for simulations or any application requiring a 2D integer matrix as a map features 2D Map Representation. 
The Map class represents a 2D grid of integers (int[w][h]) and supports creating maps of any size, initializing them with a value, or copying from an existing array.
Pixel Operations
Get and set pixel values.
Check if a pixel is inside the map boundaries.
Support for custom Pixel2D objects to access coordinates easily.
Drawing Functions
Draw lines, rectangles, and circles on the map.
Flood fill algorithm to fill connected regions with a specific value.
Matrix Operations
Addition of two maps of the same dimensions.
Multiplication by a scalar.
Rescaling the map with different proportions.
Pathfinding
BFS-based shortest path computation avoiding obstacles.
Compute all distances from a starting point while considering obstacles.
Supports cyclic maps (edges wrap around).
Utility Methods
Check equality of maps.
Get map dimensions (width and height).
Deep copy of the map.
Project Structure
Map.java – main class implementing the 2D map and all operations.
Map2D.java – interface defining map operations.
Pixel2D.java – interface/class for representing 2D coordinates.
Index2D.java – concrete implementation of Pixel2D.
MainGUI.java – GUI class for displaying and interacting with maps.
Other helper classes (e.g., StdDraw) for visualization.
