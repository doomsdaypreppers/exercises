public class CommonAncestor {

    public static String findCommmonAncestor(String[] commitHashes, String[][] parentHashes, String commitHash1, String commitHash2){
        return null;
    }

    public static void main(String[] args) {

        String [] commits = new String [] {"G","F","E","D","C","B","A"};
        String [][] parents = new String [][] {{"F","D"},{"E"},{"B"},{"C"},{"B"},{"A"},{null}};

        String commit1 = "G";
        String commit2 = "F";

        String commmonAncestor = findCommmonAncestor(commits, parents, commit1, commit2);

    }

}
