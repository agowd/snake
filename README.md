# Snake Game (Java Swing)

## Overview
This is a classic Snake game implemented in Java using the Swing API. The player controls a snake that moves around the screen, eating apples to grow while avoiding collisions with the walls and itself. The game includes different types of apples with unique effects, a save/load feature, and structured game logic using object-oriented programming principles.

## Features
- **Smooth Snake Movement**: The snake moves seamlessly across the board, growing upon consuming apples.
- **Multiple Apple Types**: Red, Yellow, and Purple apples have different effects on the snake’s growth.
- **File I/O for Save/Load**: The game state can be saved and loaded using Java’s file handling capabilities.
- **JUnit Testing**: Core game functionalities are tested using JUnit.
- **Object-Oriented Design**: Implements key OOP principles such as inheritance and encapsulation.

## Game Mechanics
- The snake moves in four possible directions (up, down, left, right).
- Red apples grow the snake by 8 squares.
- Yellow apples grow the snake by 4 squares.
- Purple apples shrink the snake by 1 square.
- The game ends if the snake collides with itself or the walls.

## Implementation Details
This project follows an object-oriented design pattern with well-structured classes:

### Core Classes
- **`GameCourt.java`**: Manages game logic, rendering, and win/lose conditions.
- **`Snake.java`**: Represents the snake as a linked list of `Square` objects and handles movement.
- **`Apple.java`**: Abstract class representing apples; extended by `RedApple`, `YellowApple`, and `PurpleApple`.
- **`Square.java`**: Represents individual segments of the snake.
- **`GameObj.java`**: Abstract class defining attributes like position and movement for game objects.
- **`FileLineIterator.java`**: Handles reading saved game data for file I/O.
- **`RunSnake.java`**: Initializes the game UI and starts the game loop.
- **`GameTest.java`**: Contains JUnit tests for validating core functionality.

## Controls
- **Arrow Keys**: Move the snake in the corresponding direction.
- **'S' Key**: Save the current game state.
- **'L' Key**: Load the last saved game state.
- **'R' Key**: Restart the game.

## Setup & Execution
### Requirements
- Java 8 or later

### Running the Game
1. Clone this repository:
   ```sh
   git clone https://github.com/yourusername/snake-game-java.git
   cd snake-game-java
   ```
2. Compile the game:
   ```sh
   javac *.java
   ```
3. Run the game:
   ```sh
   java RunSnake
   ```

## Future Improvements
- Implementing difficulty levels.
- Adding sound effects and animations.
- Enabling multiplayer mode.

## External Resources
- Java Swing Documentation: [https://docs.oracle.com/javase/tutorial/uiswing/](https://docs.oracle.com/javase/tutorial/uiswing/)
- Java File I/O Documentation: [https://docs.oracle.com/javase/8/docs/api/java/io/package-summary.html](https://docs.oracle.com/javase/8/docs/api/java/io/package-summary.html)

## Author
Aditya Gowd (agowd)

