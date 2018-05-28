package com.whaleread.codegen.sample.novel_admin;

/**
 * @author Dolphin
 */
public class ShardingUtils {
    /**
     * 计算分表节点
     *
     * @param shardingValue 分表字段值
     * @param tableCount    分表数
     * @return 分表节点
     */
    public static int node(long shardingValue, int tableCount) {
        return (int) (shardingValue % tableCount);
    }

    /**
     * 计算分表后缀，小于100
     *
     * @param shardingValue 分表字段值
     * @param tableCount    分表数
     * @return 分表后缀
     */
    public static String nodeSuffix(long shardingValue, int tableCount) {
        if (tableCount == 1) {
            return "";
        }
        long node = shardingValue % tableCount;
        return node < 10 ? ("_0" + node) : ("_" + node);
    }

    /**
     * 主要特点分表后缀
     *
     * @param node 节点索引
     * @return 分表后缀
     */
    public static String nodeSuffix(int node) {
        return node < 10 ? ("_0" + node) : ("_" + node);
    }
}