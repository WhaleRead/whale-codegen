package com.whaleread.codegen.sample.novel_admin;

/**
 * Created by Dolphin on 2018/4/9
 */
public class ShardingUtils {
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
}
