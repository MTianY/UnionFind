/**
 * 基于 rank 优化: 路径分裂: 使路径上的每个节点都指向其祖父节点.(parent 的 parent)
 */

public class UnionFind_QU_Rank_PathSpliting extends UnionFind_QuickUnion_Rank{
    public UnionFind_QU_Rank_PathSpliting(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);

        // v 和其父节点相等时结束循环
        while (v != parents[v]) {
            // 存 v 的父节点, 下面会改变 v 的父节点指向
            int p = parents[v];
            // 将 v 的父节点变成它的祖父节点.
            parents[v] = parents[parents[v]];
            // 将之前 v 的父节点重新赋值给 v, 再次进入循环, 保证每一个元素都能指向其祖父节点
            v = p;
        }
        return v;
    }
}
