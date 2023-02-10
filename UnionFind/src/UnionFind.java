public abstract class UnionFind {

    protected int[] parents;

    public UnionFind(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }

        // 初始化数组
        parents = new int[capacity];

        // 父节点初始化为索引值, 每个元素各自属于一个单元素集合.
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    /**
     * 查找 v 所属的集合 (根节点)
     * @param v 待查找元素
     * @return v 所属的集合(根节点)
     */
    public abstract int find(int v);

    /**
     * 合并 v1 和 v2 到一个集合
     * @param v1 元素 v1
     * @param v2 元素 v2
     */
    public abstract void union(int v1, int v2);

    /**
     * 是否在同一个集合内
     * @param v1
     * @param v2
     * @return
     */
    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }

    protected void rangeCheck(int v) {
        if (v < 0 || v >= parents.length) {
            throw new IllegalArgumentException("v is out of bounds");
        }
    }

}
