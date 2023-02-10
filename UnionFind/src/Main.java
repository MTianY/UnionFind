public class Main {
    public static void main(String[] args) {

        UnionFind uf = new UnionFind_QuickUnion(12);
        uf.union(0,1);
        uf.union(0,3);
        uf.union(0,4);
        uf.union(2,3);
        uf.union(2,5);
        uf.union(1,7);
        uf.union(3,8);

        System.out.println(uf.isSame(1,8));

        for (int i = 0; i < uf.parents.length; i++) {
            System.out.print(uf.parents[i] + "_");
        }


    }
}