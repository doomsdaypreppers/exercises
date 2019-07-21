package tree;

import java.util.*;
import java.util.stream.IntStream;

public class CommonAncestor {

    public static String findCommmonAncestor(String[] commitHashes,
                                             String[][] parentHashes,
                                             String commitHash1,
                                             String commitHash2){

        //edge cases
        if (commitHash1 == null || commitHash2 == null) throw  new RuntimeException("Starting commits cannot be null!");

        if (commitHash1.equals(commitHash2)) throw  new RuntimeException("Starting commits cannot be the same!");

        //building the Map that works as the structure of the tree
        Map<String, String[]> commitToParents = new HashMap<>();

        IntStream.range(0, commitHashes.length).forEach(x -> commitToParents.put(commitHashes[x],parentHashes[x]));

        Set<String> commitsTraversed = new HashSet<>();

        Deque<String> commits = new ArrayDeque<>(){
            {add(commitHash1);}
            {add(commitHash2);}};

        while (!commits.isEmpty()) {

            String[] firstParents = commitToParents.get(commits.removeFirst());

            for (String firstParent : firstParents) {

                if (firstParent != null) {

                    if (!commitsTraversed.add(firstParent)) {
                        return firstParent;
                    }

                    commits.addLast(firstParent);
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {

        String [] commits = new String [] {"G","F","E","D","C","B","A"};
        String [][] parents = new String [][] {{"F","D"},{"E"},{"B"},{"C"},{"B"},{"A"},{null}};

        System.out.println("A - B -> " + CommonAncestor.findCommmonAncestor(commits, parents, "A", "B"));
        System.out.println("G - D -> " + CommonAncestor.findCommmonAncestor(commits, parents, "G", "D"));
        System.out.println("D - F -> " + CommonAncestor.findCommmonAncestor(commits, parents, "D", "F"));
        System.out.println("C - E -> " + CommonAncestor.findCommmonAncestor(commits, parents, "C", "E"));

    }

}
