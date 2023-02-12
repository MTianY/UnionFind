/**
 * QuickUnion, 基于 size 的优化
 */

public class UnionFind_QuickUnion_Size extends UnionFind_QuickUnion {

    // 父节点数量数组
    private int[] sizes;

    public UnionFind_QuickUnion_Size(int capacity) {
        super(capacity);

        sizes = new int[capacity];
        for (int i = 0; i < sizes.length; i++) {
            // 初始时, 每个元素父节点数量都是 1
            sizes[i] = 1;
        }

    }

    @Override
    public void union(int v1, int v2) {
        int parent1 = find(v1);
        int parent2 = find(v2);
        if (parent1 == parent2) return;

        // parent1 小于 parent2 的话, parent1 则嫁接到 parent2 上.
        if (sizes[parent1] < sizes[parent2]) {
            parents[parent1] = parent2;
            sizes[parent2] += sizes[parent1];
        } else {
            parents[parent2] = parent1;
            sizes[parent1] += sizes[parent2];
        }
    }
}
