=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: agowd
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. Collections: I chose to use a LinkedList of Square objects to represent the snake.
     This is a useful abstraction as a snake quite literally looks like linked squares. In addition,
     the desired functionality is that each square is added right after the previous one was added. The
     LinkedList allows for this function. This is useful as we can then iterate down the list in order to
     draw the snake, move the snake, etc. not having to worry about the order in which this action is being
     completed since the LinkedList is ordered.

     In the feedback from the game proposal, I intended on having a 2D array of GameObj objects and also
     create some novel linked structure for the snake. I received the feedback that this may be too complicated
     and I should refactor; I wholeheartedly agreed. Thus, the LinkedList of Square objects seemed like a natural
     and useful reconciliation between the two ideas and allowed for effective, easy implementation.

  2. File I/O: File I/O was used to save the game state. Java's file I/O libraries are robust and allow
     for useful functionality in writing and reading since the recorded game state follows a similar format
     each time the game is saved. I am able to record each Square's positions and velocities as well as each apple's
     positions.

  3. JUnit Testing: The JUnit tests are used to test aspects of the game model. The proper instantiation of the
     snake is tested. The size of the snake is tested. The methods to grow the snake are tested, ensured proper
     size increase of the snake. Unit testing allows for testing each aspect of functionality separately in
     specific cases.

  4. Inheritance/Subtyping: There are three different Apples that a snake can eat. Red, Yellow, and Purple.
     In the Apple abstract class, and additional method, grow(), was defined that takes in the Snake object
     that ate the apple. In each class, the grow() method is defined differently. RedApple.grow() will grow the
     snake by 8 squares, YellowApple.grow() will grow the snake by 4, and PurpleApple.grow() will shrink the snake
     by 1. This allows

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

Apple.java - abstract class that extends GameObj. abstract method grow() added. Apples are eaten
             by the snake to change in size
RedApple.java - subtype of Apple class. if the snake's head intersects this apple, the snake grows by 8
YellowApple.java - subtype of Apple class. if the snake's head intersects this apple, the snake grows by 4
PurpleApple.java - subtype of Apple class. if the snake's head intersects this apple, the snake shrinks by 1
Direction.java - enum to indicate direction that the head of the snake is moving (used in GameObj.java)
FileLineIterator.java - reads the save.txt file line by line. each line contains the information necessary to
                        instantiate a new object (Square, Apple). Used in File I/O (GameCourt.load()) to load
                        saved game
GameCourt.java - contains all the game logic. instantiates each object used in the game and contains those
                 interactions. displays the court (paintComponent(), repaint()). defines win/lose conditions
GameObj.java - abstract class that is a blueprint/scaffolding for all objects used in the game (Apples, Squares).
               Defines how each object moves, has critical attributes like position, velocity, and keeps
               track of the court width and height.
RunSnake.java - implements the Runnable interface. RunSnake.run() runs the game. Creates the GameCourt object,
                all necessary JFrames (instructions window, gamecourt), and defines useful buttons to save/load game
                or reset game.
Snake.java - this is the snake object that the user controls. extends GameObj. defines how the snake moves,
             and boundary conditions for losing (intersecting itself, or hitting wall). Snake is
             represented as a LinkedList of Squares. The head and tail of the snake is kept track.
Square.java - this is the building block of the snake. extends GameObj. stores a color and size and is drawn as that
              color with specified size.
Game.java - initializes a Runnable object to run the game
GameTest.java - JUnit testing to test game functionality (snake initialization, snake intersecting with apples, grow)

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

The most difficult part of the assignment was the snake movement. However, the LinkedList structure made this
easy as I was able to simply temporarily store each Square's position and velocities and pass it to the next Square.
The GameObj abstract class and Square class implementations helped significantly.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

There is good separation of functionality and encapsulation. One thing to consider is separation of concern
with regards to grow(). Although the snake is the one that grows, the behavior is defined in the Apple subclasses.
This is to be able to differentiate easily between changing the size based on which apple is eaten.

========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.

Java Swing javadocs
Java BufferedWriter javadocs