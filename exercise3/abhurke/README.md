
A -> B -> C -> D -> G -> <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| -> E -> F ->|

The merge base (common ancestor) for a git tree can be identified, given 2 commit hashes - 

* One of the commit hash is a common ancestor, case both are in the same branch.
* Walk back (parents) up from the commit hashes till a common hash is encountered, case 2 different branches.  