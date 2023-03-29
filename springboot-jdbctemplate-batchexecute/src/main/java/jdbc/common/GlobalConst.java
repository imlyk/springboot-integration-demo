package jdbc.common;

/**
 * @ClassName: GlobalConst
 * @Author: lequal
 * @Date: 2022/11/15
 * @Description: 常量池
 */
public interface GlobalConst {

    /**
     * 雪花算法
     */
    interface SnowFlake {

        /** 机器id */
        long WORKER_ID = 1L;

        /** 数据中心id */
        long DATA_CENTER_ID = 1L;
    }
}
