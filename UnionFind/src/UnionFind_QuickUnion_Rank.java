/**
 * QuickUnion , 基于 Rank 的优化
 */
public class UnionFind_QuickUnion_Rank extends UnionFind_QuickUnion {

    private int[] ranks;

    public UnionFind_QuickUnion_Rank(int capacity) {
        super(capacity);

        ranks = new int[capacity];
        for (int i = 0; i < ranks.length; i++) {
            ranks[i] = 1;
        }
    }

    @Override
    public void union(int v1, int v2) {
        int parent1 = find(v1);
        int parent2 = find(v2);
        if (parent1 == parent2) return;

        if (ranks[parent1] < ranks[parent2]) {
            // 父节点 2 嫁接到父节点 1 上
            parents[parent1] = parent2;
        } else if (ranks[parent1] > ranks[parent2]) {
            parents[parent2] = parent1;
        } else {
            parents[parent1] = parent2;
            ranks[parent2] += 1;
        }
    }
}
