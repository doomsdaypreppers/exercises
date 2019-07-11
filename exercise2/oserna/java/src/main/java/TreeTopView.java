
import java.util.*;
import java.util.stream.Collectors;

/*
Example 1: the top view of the below binary tree is 4 2 1 3 7

             1
           /  \
         2      3
       /  \    / \
      4    5  6   7

Example 2: the top view of the below binary tree is B A C F:

      A
    /   \
   B     C
     \
      D
       \
        E
         \
          F
 */
public class TreeTopView<T> {

    public Node<T> root;

    public List<Node<T>> topView() {

        //root tuple and first top node
        Tuple<Node<T>, Integer, Integer> rootTuple = new Tuple<>(root, 0, 0);

        //data structure to keep the highest tuples per width
        SortedMap<Integer, Tuple<Node<T>,Integer,Integer>> topTuples = new TreeMap<>(Integer::compare);

        topTuples.put(0, rootTuple);

        //data structure to help traverse the tree DFS in order
        Deque<Tuple<Node<T>, Integer, Integer>> tuples = new ArrayDeque<>();

        tuples.add(rootTuple);

        //traverse the tree looking for the min and max width related to a fictitious central axis
        int min = 0;
        int max = 0;
        int currentWidth = 0;
        int currentDepth = 0;

        while (!tuples.isEmpty()) {

            Tuple<Node<T>, Integer, Integer> tuple = tuples.peekFirst();
            currentWidth = tuple.getY();
            currentDepth = tuple.getZ();

            if (currentWidth < min) min = currentWidth;

            if (currentWidth > max) max = currentWidth;

            Node<T> left = tuple.getX().left;
            if (left != null && !left.visited) {
                tuples.addFirst(new Tuple<>(left, --currentWidth, ++currentDepth));
                continue;
            }

            tuple.getX().visited = true;

            Tuple<Node<T>, Integer, Integer> tupleToRemove = tuples.removeFirst();

            if (!topTuples.containsKey(tupleToRemove.getY())){
                topTuples.put(tupleToRemove.getY(), tupleToRemove);
            } else {
                if(topTuples.get(tupleToRemove.getY()).getZ() > tupleToRemove.getZ()) {
                    topTuples.put(tupleToRemove.getY(), tupleToRemove);
                }
            }

            Node<T> right = tuple.getX().right;
            if (right != null && !right.visited) {
                tuples.addFirst(new Tuple<>(right, ++currentWidth, ++currentDepth));
                continue;
            }
        }

        return topTuples.values().stream()
                .map(Tuple::getX)
                .collect(Collectors.toList());
    }

    //helper class that aggregates a node with its position related to a fictitious central axis
    public static final class Tuple<X,Y,Z> {

        private final X x;
        private final Y y;
        private final Z z;

        public Tuple(X x, Y y, Z z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public X getX() {return x;}

        public Y getY() {return y; }

        public Z getZ() {return z;}
    }

    public static final class Node<T> {
        T value;
        boolean visited;
        Node left, right;

        public Node(T value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {

        TreeTopView<String> tree = new TreeTopView();

        tree.root = new Node("A");

        tree.root.left = new Node("B");
        tree.root.right = new Node("C");

        tree.root.left.right = new Node("D");

        tree.root.left.right.right = new Node("E");

        tree.root.left.right.right.right = new Node("F");

        for (Node<String> stringNode : tree.topView()) {
            System.out.print(" "+stringNode.value);
        }

    }


}
