# Connect-Four-Game

# What is it?

![alt text](https://www.gophersport.com/cmsstatic/img/245/G-11048-PopularBoardGames-ce-2-clean.jpg?medium)

Basically, all you need to know, is that your goal is to connect 4 checkers of color. You can connect them diagonally, vertically or horizontaly.
However, your opponent might do that too, so don't let him get to his goal.

When you choose a column to insert your checker to, the checker will drop to the last vacant row. Try to create a pattern that always wins, a trap pattern or whatever you wish, just win the game, please.

# How to run the game

I added two types of this game, pvp and pve.

Just in case, PVP- Player versus Player, i.e. two real humans. PVE- Player versus Enviroment, in this case PVE will be against an "AI", which is not really an AI, but still a computer that can potentially win you.

# PVP 
To play pvp all you need is to create a new class, add main method, and then add these lines:

ConnectFour cf=new ConnectFour();

cf.start();

After that, you are good to go.

# PVE
To play pve all you need is to create a new class, add main method, and then add these lines:

ConnectFour cf=new ConnectFour();

cf.startAI();

After that, you are good to go.
