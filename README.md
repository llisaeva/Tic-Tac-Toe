

# Tic-Tac-Toe

<p align = "right">
  <img src="https://github.com/llisaeva/Tic-Tac-Toe/blob/master/demo_images/TicTacToe-Gameplay.gif" align ="right" alt="animated" width= "400"/>
</p>

A __Tic-Tac-Toe game__ where the user can play with a friend,
with a bot or watch 2 bots play against each other.

The bot moves strategically, its opponent can either lose or draw.

## Tools used
- [Java 14](https://www.oracle.com/java/technologies/javase-downloads.html)
- [JavaFX 14](https://openjfx.io/)
- [Scene Builder](https://gluonhq.com/products/scene-builder/)
- [Gradle Build Tool](https://gradle.org/)
- [Eclipse IDE](https://www.eclipse.org/eclipseide/)
- [Adobe Illustrator](https://www.adobe.com/products/illustrator.html)

## Table of Contents
1. [Game Window](#game-window)
2. [Settings Window](#settings-window)
3. [How the Game Works](#how-the-game-works)
4. [Download and Run](#download-and-run)

## Game Window
The game starts in __Player vs Player mode__. The top corners show the names and scores for Player X and Player O.
You can __change the color themes__ by clicking on the swatches on the bottom left corner.

<p align = "right">
  <img src="https://github.com/llisaeva/Tic-Tac-Toe/blob/master/demo_images/TicTacToe-Options.gif" align ="left" alt="animated" width= "250"/>
</p>

## Settings Window
__Select one of 3 game modes:__
- __Player vs Player (the default mode)__ - have full control over placing the marks on the board.
- __Player vs Master__ – play against an unbeatable bot. Choose the mark that the bot will play with. The __?__ option will assign a random mark for Master on each replay.
- __Master vs Master__ – watch two bots play.

You can also click on the player names to change them. <br><br><br><br>

## How the Game Works
<p align = "right">
  <img src="https://github.com/llisaeva/Tic-Tac-Toe/blob/master/demo_images/TicTacToeExplanation.png" align ="left" width= "300"/>
</p>

Behind the scenes, each cell in the grid has an associated __code__ (shown in image). __3 matching digits__ from different codes identify a winning condition.
__Master__ uses these codes to understand the state of the board. <br>
The __optimal tic-tac-toe strategy__ used to create Master’s move decisions is described [here](https://en.wikipedia.org/wiki/Tic-tac-toe).

## Download and Run
__Prerequisites:__ 
JRE - [click here](https://docs.oracle.com/goldengate/1212/gg-winux/GDRAD/java.htm#BGBFJHAB) for installation steps.
Make sure to set the JAVA_HOME system variable.

Download and unzip the project.

__Run Command:__

```bash
    ..\Tic-Tac-Toe-master> ./gradlew run
```
