public class UnionFind_QuickUnion extends UnionFind {

    public UnionFind_QuickUnion(int capacity) {
        super(capacity);
    }

    /**
     * 查找元素 v 的根节点.
     * @param v 待查找元素
     * @return
     */
    @Override
    public int find(int v) {
        rangeCheck(v);

        // 不断找根节点, 如果 v == parents[v] 说明找到了, 退出循环
        while (v != parents[v]) {
            v = parents[v];
        }
        return v;
    }

    /**
     * 合并 v1 和 v2
     * @param v1 元素 v1
     * @param v2 元素 v2
     */
    @Override
    public void union(int v1, int v2) {
        int parent_v1 = find(v1);
        int parent_v2 = find(v2);
        if (parent_v1 == parent_v2) return;

        // 这里合并, 只处理根节点
        parents[parent_v1] = parents[parent_v2];
    }
}
