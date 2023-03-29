package jdbc.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import jdbc.common.GlobalConst;

public class IDKit {

    private final static Snowflake SNOW_FLAKE = IdUtil.getSnowflake(GlobalConst.SnowFlake.WORKER_ID, GlobalConst.SnowFlake.DATA_CENTER_ID);


    /**
     * @return long
     * @Author: lequal
     * @Description 生成唯一id
     * @Date 2022/11/15 11:48
     */

    public static long nextId() {
        return SNOW_FLAKE.nextId();
    }
}