Here is my attempt to build a java program that learns with experience . SO far, I have trained my Java program to an “Intermediate” level. With more practice set, it should be possible to achieve “Expert” level.

Approach :  Designed my program on “Analytical Learning” principle ,i.e. to learn from prior experience.
How it works:  
1.	Cells in the “tic-tac-toe”  board are sequenced from 1 to 9.. this helps cell selection.
2.	User will be playing against the computer. First move (X) will be made by the program and then user’s turn (0) will come. 
3.	Every move of User and of the program is recorded. 
	a.	Ex: if program selects the centre piece and then User have selected top left cell, then the sequence recorded will be {5,1}.
4.	If Program wins, it records the sequence of moves in a property file -> “winning.properties”
5.	If it is a draw then the sequence of moves are written in “draw.properties” file.
6.	If User win, then it performs two steps:
	a.	Record the sequence in “loss.properties” file.
	b.	Re-arrange the sequence order which defeated the program. That way, you get a winning sequence out of the given sequence.
7.	While playing, program is designed to check the above mentioned properties file for a suitable combination before making move. 
	a.	If not suitable move is found then program makes a random selection and records it accordingly.

For more details, i have attached a pdf file that explains it little more..