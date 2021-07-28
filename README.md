# Java Game Box: The Legend of Zelda

## Objective

The objective of this project is to develop Java Game Box: Legend of Zelda a simplified
version of the game of the century Legend of Zelda. This project will be worked in teams of
two students. Your partner must be different for each of the three projects. You
should start by studying the application’s architecture in the base code and learn how to
efficiently modify the project to comply with the minimal requirements. For full credit your
team must develop these new features reusing as little code as possible, avoiding code
redundancy and duplication, following the project’s structure and making the least changes
possible. 

## Minimal Requirements
Your application must comply with the following requirements:

• Phase 1 (WarmUp): Implement additional minor features.
- Change the speed that the transitions between map areas Hint: if(movingMap)
- Add music when the game starts
- Make the cave in the first area look like this: (excluding the top bar with health and
the other things)

• Phase 2: Debugging Commands:
- Implement the keyboard button ‘H’ that gives Link (main character) one extra life.
(implement life)
- Implement the keyboard button ‘B’ that in the mapmaker (Press M in the main
menu) moves the selected tile to be the tile at the halfway point of the tileset.
- Implement the keyboard button ‘R’ selects a random tile from the list to be the one
selected
- Implement the keyboard button ‘Shift + R’ selects a random tile set and tile from
the tilesets available to be selected.
*Will make more sense once you run the game.

• Phase 3: Implement additional Features:
- Implement the sword at the cave to be “picked up” once walked on. Once Link
has the sword, pressing “Enter” will attack in front of him (must have
animation. (Hint: animation has a bool called ‘end’, Link has interactBounds
that might help)
- Implement an enemy and add him to the start Area, this enemy must move
aimlessly through the area without going into the walls. The enemy will die if
hit by the sword when Link is attacking.
- Implement a new Tile in the mapmaker (press M in the main menu) with a
feature, (like the teleport tile) this feature must be implemented in a recursive
way and will do as follows:

  - The tile will move the player one tile over in a specified, and obvious,
direction
  - If another tile is placed in the position the player was to be placed, it
will now be moved an extra tile over in a specified, and obvious,
direction, dictated by this newfound tile.

### Creative Directions (Optional Ideas):
Each team’s Java Game Box: Legend Of Zelda must be personalized, which means it must be
different from each other’s. Be as creative as possible. Remember that this project will
become part of your student project portfolio. 

