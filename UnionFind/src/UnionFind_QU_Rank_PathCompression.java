/**
 * 基于 Quick Union - rank 优化, 采用路径压缩方法 (Path Compression)
 */

public class UnionFind_QU_Rank_PathCompression extends UnionFind_QuickUnion_Rank {
    public UnionFind_QU_Rank_PathCompression(int capacity) {
        super(capacity);
    }

    /**
     * 将元素 v 的父节点指向根节点
     * @param v 待查找元素
     * @return
     */
    @Override
    public int find(int v) {
        rangeCheck(v);

        // 比较元素和其父节点元素, 相等了就结束循环, 不找了
        if (parents[v] != v) {
            parents[v] = find(parents[v]);
        }

        // v 与其父节点相等
        return parents[v];
    }
}
