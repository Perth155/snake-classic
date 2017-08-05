# Snake Classic
A Java implementation of the classic game of [Snake](https://en.wikipedia.org/wiki/Snake_(video_game)), with a GUI written using Swing framework. This game is based on Snake featured in Nokia 6110 in 1997.

![snakes](https://i.imgur.com/GVEHqRH.png)

## Description
- Move the snake's head towards an item to eat it to gain a point.
- Snake grows if item is eaten, making it progressively harder to control it.
- Controls : W,A,S,D keys to change direction.

## Prerequisites
- Java (JDK 8)
- Apache Ant

## Compile and Run
To compile all Java files and create a single JAR file (to easily run the application):
```
ant compile
```
Running the application-
```
ant run
# Alternatively
java -jar Snake.jar
```

## TODO
- [X] Allow player to restart game.
- [X] Add sounds.
- [X] Fix scaling issues across different screen resolutions. Allow fullscreen.
- [ ] Menu screen with difficulty levels (speed) on start. Add collidable walls as an option.
- [ ] Use compressed audio formats instead of wav.

## Resources
- Font : [VT323](https://fonts.google.com/specimen/VT323?selection.family=VT323)
- Audio Files : [successful](https://freesound.org/people/Kastenfrosch/sounds/162473/), [Ping Bing](https://freesound.org/people/GameAudio/sounds/220212/), [Boot Sound](https://freesound.org/people/GameAudio/sounds/220207/)
