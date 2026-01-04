# Flappy Bird Game

A Java implementation of the classic Flappy Bird game with enhanced graphics and smooth gameplay.

## Features

- **Enhanced Graphics**
  - Gradient sky background
  - Animated scrolling clouds with parallax effect
  - Detailed bird character with wings, eye, and beak
  - 3D-style pipes with gradients and highlights
  - Textured ground with grass

- **Smooth Gameplay**
  - Physics-based bird movement with gravity
  - Collision detection
  - Score tracking
  - Game over and restart functionality

- **Controls**
  - **SPACE** - Start game / Jump / Restart after game over

## Project Structure

```
2d game/
├── README.md
└── src/
    ├── Main.java          # Entry point
    ├── FlappyBird.java    # Main game logic and rendering
    ├── Bird.java          # Bird character class
    ├── Pipe.java          # Obstacle pipe class
    └── Cloud.java         # Background cloud class
```

## How to Compile and Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher

### Compile
From the project root directory (`2d game/`):
```bash
javac src/*.java
```

### Run
```bash
java -cp src Main
```

## Gameplay

1. Press **SPACE** to start the game
2. Press **SPACE** to make the bird jump
3. Navigate through the pipes without hitting them
4. Each pipe you pass increases your score by 1
5. Game ends if you hit a pipe, the ground, or the ceiling
6. Press **SPACE** to restart after game over

## Game Mechanics

- **Gravity**: 0.6 pixels per frame²
- **Jump Strength**: -8 pixels per frame
- **Pipe Speed**: 3 pixels per frame
- **Pipe Gap**: 150 pixels
- **Bird Size**: 30x30 pixels
- **Window Size**: 800x600 pixels

## Credits

Built with Java Swing for graphics and game loop management.
