package com.tcsr.framework.common.utils;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 16位长度雪花ID生成器
 * 生成Long类型的16位ID，基于当前时间戳生成
 * @author tangzhong
 * @since  2025-08-27 15:16
 */
public class SnowflakeIdUtils {

    /**
     * 起始时间戳 (2025-01-01)
     */
    private final static long START_TIMESTAMP = 1735689600000L;

    /**
     * 各部分位数配置
     */
    private final static long MACHINE_BIT = 10;     // 机器ID位数
    private final static long SEQUENCE_BIT = 12;    // 序列号位数

    /**
     * 各部分最大值
     */
    private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    /**
     * 左移位数
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long TIMESTAMP_LEFT = SEQUENCE_BIT + MACHINE_BIT;

    /**
     * 机器ID
     */
    private final long machineId;

    /**
     * 序列号
     */
    private long sequence = 0L;

    /**
     * 上一次时间戳
     */
    private long lastTimestamp = -1L;

    /**
     * 构造函数
     * @param machineId 机器ID (0-63)
     */
    public SnowflakeIdUtils(long machineId) {
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId must be between 0 and " + MAX_MACHINE_NUM);
        }
        this.machineId = machineId;
    }

    /**
     * 生成下一个16位长度的雪花ID
     * @return Long类型的16位ID
     */
    public synchronized Long nextId() {
        long currentTimestamp = System.currentTimeMillis();

        // 时钟回退检测
        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id");
        }

        // 同一毫秒内序列号递增
        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                currentTimestamp = getNextMill();
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = currentTimestamp;

        // 生成16位ID
        long id = ((currentTimestamp - START_TIMESTAMP) << TIMESTAMP_LEFT) |
                (machineId << MACHINE_LEFT) |
                sequence;

        // 确保ID为16位长度
        String idStr = String.valueOf(id);
        if (idStr.length() > 16) {
            // 截取后16位
            idStr = idStr.substring(idStr.length() - 16);
        } else if (idStr.length() < 16) {
            // 前面补0到16位
            idStr = String.format("%016d", id);
        }

        return Long.parseLong(idStr);
    }

    /**
     * 等待下一毫秒
     * @return 下一毫秒时间戳
     */
    private long getNextMill() {
        long mill = System.currentTimeMillis();
        while (mill <= lastTimestamp) {
            mill = System.currentTimeMillis();
        }
        return mill;
    }

    /**
     * 获取默认实例
     * @return SnowflakeIdGenerator16实例
     */
    public static SnowflakeIdUtils getInstance() {
        return SnowflakeIdGenerator16Holder.INSTANCE;
    }

    /**
     * 单例持有者
     */
    private static class SnowflakeIdGenerator16Holder {
        private static final SnowflakeIdUtils INSTANCE = new SnowflakeIdUtils(getMachineNum());
    }

    /**
     * 获取机器编号
     * @return 机器编号
     */
    private static long getMachineNum() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(address);
            if (network == null) {
                return ThreadLocalRandom.current().nextLong(MAX_MACHINE_NUM + 1);
            }
            byte[] mac = network.getHardwareAddress();
            if (mac == null) {
                return ThreadLocalRandom.current().nextLong(MAX_MACHINE_NUM + 1);
            }
            long machineId = ((mac[0] & 0x0FL) << 2) | ((mac[1] & 0xC0L) >> 6);
            return machineId & MAX_MACHINE_NUM;
        } catch (Exception e) {
            return getProcessId() & MAX_MACHINE_NUM;
        }
    }

    /**
     * 获取进程ID
     * @return 进程ID
     */
    private static long getProcessId() {
        try {
            String processName = ManagementFactory.getRuntimeMXBean().getName();
            return Long.parseLong(processName.split("@")[0]) % (MAX_MACHINE_NUM + 1);
        } catch (Exception e) {
            return ThreadLocalRandom.current().nextLong(MAX_MACHINE_NUM + 1);
        }
    }
}