# WheelOfJeopardy

Open Android Studio and select **Import project (Eclipse ADT, Gradle, etc.)** to load the project into your workspace.

## Background

This game is a computer version of a combination of two TV game programs: Wheel of
Fortune and Jeopardy!. If you don’t know how to play either of these games, your first
assignment is to spend a few hours watching these shows on TV. Usually these two
programs are aired back to back in the 7:00pm – 8:00pm hour. Check your local listings
for details. The following is a description of the rules for The Wheel of Jeopardy (WoJ)
game.

Game Equipment: In the user interface to the WoJ game there will be a wheel and a
question board. These are analogous to the wheel and game board in the TV programs

Game Rules: The players take turns spinning the wheel and answering questions. When
it is your turn, you will spin the wheel, and do whatever it says on the sector of the wheel.

The wheel has the following 12 sectors, randomly distributed:

* One sector for each of the six categories on the Jeopardy! board. When the player
spins one of these sectors, he or she must answer the next question in that category.
The questions are answered in the order of increasing point value. If the player
answers correctly, he or she is awarded the corresponding points and gets to spin
again. If incorrect, the corresponding points are subtracted from the player’s score,
and the player loses his turn. (Negative scores are possible.) If all of the questions in
the selected category have been answered, the player must spin again.
* One “lose turn” sector.
* One “free turn” sector. When this sector comes up, the player gets a token for a free
turn later in the game. The token could be used if the player loses his turn by
spinning a “lose turn” or answering a question incorrectly in a future turn. If this
happens, the player could redeem the token and would get to spin the wheel again.
The number of tokens is unlimited.
* One “bankrupt” sector. When this sector comes up, the player loses all of his or her
points for the current round. The player loses his turn, and can’t use a token for a
second chance.
* One “player’s choice” sector. When this sector comes up, the player gets to choose
which category to answer.
* One “opponents’ choice” sector. When this sector comes up, the player’s opponents
get to select the category.
* One “spin again” sector. When this sector comes up, the player must spin again.

The game is played in two rounds. In the second round, the point values are doubled
from the first round (as in Jeopardy!). At the end of the game, the scores for the two
rounds are added together. The player with the largest total score is the winner. In each
round there are a maximum of 50 spins of the wheel. A counter should be displayed
indicating the number of spins remaining in the round. A round is over if either all of the
questions have been answered or if the spin count goes to zero.

In Jeopardy!, the questions are actually in the form of answers, and the answers should be
worded as questions. (However, for this program, you may word the questions in the
form of questions rather than answers, if you wish. ) There should be a time limit on
answering a question. If the time expires, no points are subtracted from the player’s
score, but he loses his turn. If he has a token, he can redeem it for another spin. There is
no “buzzing in” in this game.

**Options**: The following options are nice to have, but not absolutely required. You can
increase the value of your program by implementing these options.

* There should be a convenient way for someone to edit the set of questions & answers,
as well as the categories. Instructions for doing this should be provided in the
documentation. This would be useful for, say, a high school teacher to provide drill
and practice for students.
* You need to come up with a way to signal to the program that a question has been
answered correctly. Spend some time thinking of various options and evaluating
them based on ease of use by the players and ease of implementation in the software.
* It would be nice to allow multimedia questions (e.g., videos, pictures, sound).
* Animation of the wheel would be nice to have.
* It would be nice to have “Jeopardy” and “Double Jeopardy” squares, as in the TV
game. The wagering rules would be the same as on TV.

Here are the official websites of the two TV game shows:
http://www.wheeloffortune.com/
http://www.jeopardy.com/
