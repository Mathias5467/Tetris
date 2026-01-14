# 🎮 Tetris Project

## 📝 1. Program Description
This program is an imitation of the game **Tetris**.

* **Game start**: When the game is initialized, the game board is displayed along with a window asking whether the player wants to start the game.
* **Scoring and score**:
  * After confirming the start, the current score is set to 0 and the maximum score is loaded from a text file.
  * If the player has not played the game before, the initial value of the maximum score is 0.
  * The score is displayed using a **seven-segment display**, inspired by exercises by doc. Ing. Ján Janech, PhD.
  * For each removed full row, the score is increased by the number of removed rows multiplied by 15 points.
  * The maximum achieved score is continuously updated and written to a text file after the game ends.
* **Game mechanics**:
  * Below the score, the next shape is displayed for better strategy planning.
  * Shapes consist of 4 squares and move downward at regular time intervals.
  * When a shape reaches the bottom boundary or another shape, it stops at its current position.
  * If the shapes form a full row (a continuous line of squares), the row is removed and the squares above it move down.
* **End of the game**: The game ends when a new shape can no longer be placed on the board. A window is then displayed showing the achieved score and offering the option to start a new game.
* **Goal**: The purpose of the game is to achieve the highest possible score.

---

## 🕹️ 2. User Guide

### 🖱️ Initialization and Confirmation
* During initialization, the player decides and confirms with the cursor whether they want to start a new game.
* After the game ends, the player confirms interest in a new game in the same way.

### ⌨️ Controls During the Game
* **⬅️ / ➡️ Left / Right arrow keys**: Move the shape horizontally within the game board.
* **⬆️ Up arrow key**: Rotate the shape while it is falling.
* **⬇️ Down arrow key**: Speed up the downward movement of the shape if the game feels too slow.


