# Room-Swap-Problem
Submitted solution to the ECM competition: http://www.ecmselection.co.uk/articles/ecm-brainbuster-42-office-move
Our solution gave the correct answer.

We noted that there are only 6 possible moves possible. From the initial position, we can make the moves:
ACD (called CL meaning clockwise left)
D (called UD meaning up-down)
BED (called AR meaning anticlockwise right).
The only other position (which is the exact same as the initial position, but with D in the empty room), we can make the moves:
CAD (called AL meaning anticlockwise left)
D (called UD meaning up-down)
EBD (called CR meaning clockwise right).
We keep this notation throughout the programming, and call these the available moves.

The programme works by trialling every possible move available below a known bound which produces a non-optimal solution.
