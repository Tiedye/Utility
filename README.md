# Utility
This is a utility library that contains the functionality required by the simple game I am creating.

*THIS IS A VERY INCOMPLETE LIBRARY*

### Functions
* Math
  - Vectors
    - 2D
      - Contains the most indepth functionality for doubles and floats
      - Contains untility operations for integers
    - 3D
      - Less developed than the 2D variant
  - Rays
    - Simply two vectors with some aditional functionality, only 2D supported ATM
  - Matrices
    - Very simple functionality
    - Only supports 3 by 3 matrices
  - Convex Bounding boxes
    - AABB
      - Intersections, tranlations, etc. are supported
    - Circles
    - Arbitrary Convex Hulls
    - Supports finding the overlap between any two bounding boxes
    - Supports fast AABB overlap checking for these types
* Rendering
  - Java2D
    - Line, Arrow, and Polygon rendering helper functions
* Grid
  - Special methods for accessing a cell in a 2D array using a 2D int vector
