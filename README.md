                     2D Map Java Project
**This project implements a 2D raster map in Java, providing a framework for working with grids, drawing, scaling, and pathfinding. It is designed for simulations or any application requiring a 2D integer matrix as a map.**

**❆ Features ❆** 

*2D Map Representation*

The Map class represents a 2D grid of integers (int[w][h]) and supports creating maps of any size, initializing them with a value, or copying from an existing array.

**❆ Pixel Operations ❆**
1. Get and set pixel values.
2. Check if a pixel is inside the map boundaries.
3. Support for custom Pixel2D objects to access coordinates easily.
4. Drawing Functions
5. Draw lines, rectangles, and circles on the map.
6. Flood fill algorithm to fill connected regions with a specific value.
   
**❆ Matrix Operations ❆**
1. Addition of two maps of the same dimensions.
2. Multiplication by a scalar.
3. Rescaling the map with different proportions.

**❆ Pathfinding ❆**
1. BFS-based shortest path computation avoiding obstacles.
2. Compute all distances from a starting point while considering obstacles.
3. Supports cyclic maps (edges wrap around).
4. Utility Methods
5. Check equality of maps.
6. Get map dimensions (width and height).
7. Deep copy of the map.

**❆ Project Structure ❆**
1. Map.java – main class implementing the 2D map and all operations.
2. Map2D.java – interface defining map operations.
3. Pixel2D.java – interface/class for representing 2D coordinates.
4. Index2D.java – concrete implementation of Pixel2D.
5. MainGUI.java – GUI class for displaying and interacting with maps.
6. Other helper classes (e.g., StdDraw) for visualization.

*❆ You can use your own main code, but here is mine one for example, that draws 
a heart. You can see result on the picture. ❆*

**Good luck!**
<img width="508" height="514" alt="heartEx2" src="https://github.com/user-attachments/assets/9451f6fe-5da7-45b0-adb8-1c06d695175a" />

