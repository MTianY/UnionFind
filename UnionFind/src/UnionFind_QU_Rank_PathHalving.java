/**
 * 基于 rank 优化 , 路径减半: 使路径上每隔一个节点就指向其祖父节点.
 */

public class UnionFind_QU_Rank_PathHalving extends UnionFind_QuickUnion_Rank {
    public UnionFind_QU_Rank_PathHalving(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        while (v != parents[v]) {
            parents[v] = parents[parents[v]];
            v = parents[v];
        }
        return v;
    }
}
