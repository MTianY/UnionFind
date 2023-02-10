# 并查集 Union Find

假设 n 个村庄, 有些村庄之间有连接的路,有些村庄之间没有连接的路.

设计一个数据结构, 能够快速执行 2 个操作

- 查询 2 个村庄之间是否有连接的路
- 连接 2 个村庄

用数组,链表,平衡二叉树,集合的话:

- 查询,连接的时间复杂度都是: O(n)

但是用并查集的话, 其查询,连接的均摊时间复杂度都是 O(α(n)), α(n) < 5.

`并查集非常适合解决这类"连接"相关的问题.`

## 并查集核心操作 

并查集也叫做不相交集合.

并查集有 2 个核心操作:

- 查找(Find): 查找元素所在的集合(不是特指 Set 这种数据结构, 是广义上数据的集合)
- 合并(Union): 将两个元素所在的集合合并为一个集合

### 有 2 种常见实现思路:

Quick Find

- 查找(Find)的时间复杂度: O(1)
- 合并(Union)的时间复杂度: O(n)

Quick Union

- 查找(Find)的时间复杂度: O(logn), 可以优化至 O(α(n)), α(n)<5
- 合并(Union)的时间复杂度: O(logn), 可以优化至 O(α(n)), α(n)<5

### 并查集存储数据

假设并查集处理的数据都是整型, 那么可以用整型数组来存储数据.


| 索引 | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 集合 | 1 | 1 | 2 | 1 | 5 | 6 | 6 | 6 |

可以看出:
- 0 1 3 这三个属于一个集合
- 2 属于一个集合
- 4 属于一个集合
- 5,6,7 属于一个集合

可以把集合那里看做索引的父节点. 那么索引 0 的父节点是 1. 索引 1 的父节点 1, 索引 3 的父节点是 1.


所以, 在初始时, 每个元素各自属于一个单元素集合.

#### Quick Find 实现思路

- 初始化数组. 数组存放对应索引元素. 每个元素各自属于一个单元素集合

```java
parents = new int[capacity];

// 父节点初始化为索引值, 每个元素各自属于一个单元素集合.
for (int i = 0; i < parents.length; i++) {
  parents[i] = i;
}
```

- 定义查找元素 v 所属集合的接口 (找元素 v 的根节点)

```java
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
```

- 合并 v1 和 v2 到一个集合

这里合并, 是把 v1 的所有元素的父节点,都改为 v2 的父节点. 他们的根结点都相同.

```java
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
```


#### Quick Union 实现思路

- 初始化数组. 数组存放对应索引元素. 每个元素各自属于一个单元素集合

```java
parents = new int[capacity];

// 父节点初始化为索引值, 每个元素各自属于一个单元素集合.
for (int i = 0; i < parents.length; i++) {
  parents[i] = i;
}
```

- 查找根节点

```java
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
```

- 合并节点

和 quick find 不同, quick find 是把所有 v1 元素的父节点, 都变为 v2 元素的父节点.

这里只处理根节点, 把 v1 的根节点变为 v2 的根结点. 如果 v1 还有父节点, 那么找到其根节点, 改为 v2 的根结点.

```java
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
```

##### Quick Union 优化

在 union 过程中, 可能出现树不平衡的情况, 甚至退化成链表.

有 2 种常见优化方案:

- 基于 size 优化
    - 元素少的树, 嫁接到元素多的树    
- `基于 rank 优化`
    - 矮的树, 嫁接到 高的树 








