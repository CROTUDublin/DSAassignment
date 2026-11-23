Maze Generator and Solver
Cian Reeves O'Shea - B00164770

My attempt at the Maze generator and solver assignment for DSA.

Assignment was built using IntelliJ. To run from the command line, java MazeFrame to see the maze and animation, java MazeTest to run tests as long as you are in the correct folder src. Both will ask for user input with MazeFrame requiring the number of rows and columns. MazeTest will display a menu you can view to select options. 
Input accepts 1-100, anything else will print "Not from 1-100, using default 10" using 10 as default value based on lecture examples.

Approach
The main line of thinking I had for this assignments design was to reuse, adapt or be inspired by code examples and logic from labwork, lectures and video demos. I've been guilty in the past of drifting too far from lecturer/course work style of approach which has caused unneeded trouble. While this arguably isn't the most creative of choices overall it made the development a lot more straightforward than I imagine it would have been, the only time I deliberately go against this is in using getters and setters for Cell instead of public fields, but even this follows whats found in Node and TreeNode from the labs (ie c.getTop() instead of c.top)
Not using Dijkstra as per the briefs recommendation to use BFS. The video demos ended up proving more useful than I even expected, especially the DijkstrasSPA demo which have structure and logic to many elements of the assignment.

Added extra colours also to the animation stage by splitting unvisited into not carved (grey) and carved (white) and actively explored into being worked on now (red) and has already been explored (blue), keeping final shortest path as forest green. Based on the brief stating unvisited grey and standard white, added the blue to actively explored for further clarification on where the maze is at.

Maze theory
for my own head to comprehend, but I thought it could work in the ReadME.
The maze is a grid of rooms that has walls, building here is knocking the walls down while the solver is walking through the right gaps.
Building using DFS with stack means randomly walking from room to room knocking walls as you go, when you hit a dead end you backtrack using your stack until you can go somewhere new again, done when back at the start with nowhere left to go and every room is connected by one route
Solving with BFS and a queue is first come first served so it ripples out across the maze. First ripple to get the exit its the shortest way giving the path
The way the animation works is redrawing after every step and colouring based on whats happened 

Classes
Reused completely from lab work
!One exception, changed MAX_QUEUE so big mazes fit -> 
ListInterface.java
ListArrayBased.java
ListException.java
ListIndexOutOfBoundsException.java
Node.java
StackInterface.java
StackReferenceBased.java
StackException.java
QueueInterface.java
QueueArrayBased.java
QueueException.java

New classes for assignment
Cell.java
MazeFrame.java
MazeCanvas.java
MazeTest.java

Methods by class
MazeFrame
MazeFrame constructor - build N x M grid of any size, based on lecture MazeFrame and MazeCanvas
2. initializeGrid - Fill with closed cells, from lecturer MazeFrame
3. pause - redraw and wait, for animation. Try/catch from my lab 3 and repaint/sleep as standard Swing syntax
4. tearDownAllWalls - not part of my final maze, from SPA demo
5. knockDownWall - carve a passage through, based on checks in Dijkstra demo
6. getUnvisitedNeighbours - find neighbours to carve but keep edges clean, based on SPA demo neighbour checks
7. generateMazeRandomiseDFS - with backtracking using a stack, based on my lab 3 stack work and logic from lecture 6 iterative DFS
8. solveMazeBFS - from start to exit using parent pointers and queue, using lecture 6 logic and my lab 4 queue work, structure based on the Dijkstra demo but no weights
9.displaySP - rebuild the winner path using the parent pointers, based on lecturers displays but also marking with truePath
10. readSize - user input for building the maze without crashing, based almost entirely on my lab 3 TestStack
11. main - read size and show the maze generation animation and solve, based on the main method seen in the SPA demo

MazeCanvas
MazeCanvas constructor - panel for maze grid, exact same as lecture MazeCanvas
drawCell - colours the cells based on their state then draws walls, from lecture drawCell and fillRect and I also used the colours found in HouseCanvas example
paintComponent - To redraw the whole grid every step, based on lecture paint method but the row/col swap was fixed and sizing changed


MazeTest
main - Tests for the maze, structure based on my lab 3 TestStack for do/whiles etc
displayMenu - shows the testing options, exact structure as the lab displayMenus
 runTest - build and solve several times and print each time, based on the test drivers from my labwork and the loop found in testTreeNode example.
allCarved - makes sure each cell was visited, based on the loops from the markUnvisited example
borderIntact - make sure edges are handled cleanly, best approach was taking the tearDownAllWalls lecture example and reversing it
totalPassages - ensures perfect maze using rows x cols -1 passages, formula from lecture 7 and once again using tearDownAllWalls loops
7. pathThroughOpenWalls - runs start to finish without walking through walls, based on the SPA demo neighbour checks.


Cell
1.Cell constructor - track your walls and if visited, based on Cell example from demo, vertexNum the same
2. Getters/Setters - walls and state stored privately, my one main change but still styled on lab node and treeNode.



Development
The actual approach to the assignment was as followed, I ended up not having as much time as I thought I would have due to other projects and work but I just had to push each stage forward and work in longer sessions which I believe has worked hopefully as I've hit brief requirements.

After design idea, imported lab work for reuse, increasing queue size for the maze
Set up the grid and drew on screen
Added the knock down walls method for cells
Used DFS approach to building the maze generator
Used BFS approach for the solver and showed path
Animation and colours added
User input for mazes and the test class
Final tidy and format

