# BoxJumpGame

## Project Overview  
BoxJumpGame is a simple side-scrolling Java game where you control a box that continuously moves forward and must jump over randomly spaced obstacles. The game uses Java Swing for the interface and implements smooth jumping, obstacle movement, and score tracking. Your score increases every second you survive and with every successful jump. 

---

## Technologies  
- Java (tested on Java 8+)  
- Java Swing for UI and event handling  
- Basic file I/O for storing high scores

---

## Features  
- Continuous side-scrolling gameplay with obstacles of varying spacing  
- Jump mechanic controlled via **spacebar** or mouse click  
- Collision detection ending the game when the player hits an obstacle  
- Score system that increments over time and with jumps  
- Persistent high score saved in a text file  
- Colorful, randomly colored obstacles for visual variety  
- Restart option after game over

---

## Gameplay  
- The player controls a square that jumps over obstacles automatically scrolling from right to left.  
- Obstacles appear at random intervals between 175 to 350 pixels apart.  
- The score increases by 1 every time an obstacle passes and by 2 every successful jump.  
- The game ends if the square collides with an obstacle, displaying the current score and asking to restart or quit.  
- High scores are saved in a file called `highscores.txt` and displayed during gameplay.

---

## Notes  
- This game was developed as a 2nd-year university project.  
- The game window is fixed size (800x320) and uses a simple background image.  
- The jumping animation is controlled by a `Timer` firing every 16 ms for smooth motion.

---

## Future Improvements  
- Add sound effects and background music  
- More varied obstacle types and levels of difficulty  
- Mobile or web-based port  
- Enhanced graphics and animations  

---

## License  
This project is licensed under the MIT License.

