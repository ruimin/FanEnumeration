FanEnumeration
==============

rigidity theory-fan enumeration

Open a terminal window

use the cd command to navigate to the correct directory under "/FindingAllPerfectMatchings/bin"
then input "java GenerateKFans" with the string paramter.

The string parameter should be formatted as

"k;number_of_vertex;{(vertex1,vertex2,edge_num),....(vertex1,vertex2,edge_num)};{(tiedown_vertex,tiedown_pebble_no_on_the vertex),...}"


An example is

"2;3;{(0,1,0),(1,2,1),(1,2,2),(0,2,3)};{(0,0),(0,1)}"

In this string, we have k=2, three vertices. There is Edge 0 between vertex 0 and 1 represented by "{0,1,0}";Edge 1 betwwen vertex 1 and 2 represented by "{1,2,1}" ...etc.




In the terminal window, first navigate tothe directory "BasicBarJointKFan/FindingAllPerfectMatchings/bin"

Then type in

java GenerateKFans "2;3;{(0,1,0),(1,2,1),(1,2,2),(0,2,3)};{(0,0),(0,1)}" 


========================
I am working on a version of the a+b fan enumeration-stay tuned!

