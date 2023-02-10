public class UnionFind_QuickFind extends UnionFind {

    public UnionFind_QuickFind(int capacity) {
        super(capacity);
    }

    /**
     * 查找 v 所属的集合 (根节点)
     * @param v 待查找元素
     * @return v 所属的集合(根节点)
     */
    @Override
    public int find(int v) {
        rangeCheck(v);
        return parents[v];
    }

    /**
     * 合并 v1 与 v2 到一个集合
     * @param v1 元素 v1
     * @param v2 元素 v2
     */
    @Override
    public void union(int v1, int v2) {

        // 找到 v1 的所属集合, 父节点
        int parent_v1 = find(v1);
        // 找到 v2 的所属集合, 父节点
        int parent_v2 = find(v2);
        // 同一个集合内, 不需要合并, 直接 return
        if (parent_v1 == parent_v2) return;

        for (int i = 0; i < parents.length; i++) {
            // 如果 i 位置元素和 v1 在同一个集合内
            if (parents[i] == parent_v1) {

                /**
                 * 那么把 v2 覆盖到 v1 的集合内, 都和 v2 一样.
                 * 比如序列:
                 * 0 1 2 3 4 5 6 7
                 * 1 1 2 1 5 6 6 6
                 *
                 * union(1, 2); 把 1 和 2 放到一个集合内, 那么让 0 和 1 的父节点都变为 2的父节点. 如下:
                 * 0 1 2 3 4 5 6 7
                 * 2 2 2 1 5 6 6 6
                 */

                parents[i] = parent_v2;
            }
        }

    }

}
