package com.learning.java.multiThread;

/**
 * 宇宙能量系统
 * 能量守恒定律：能量不会凭空创生或消失，只会从一处转移到另一处
 */
public class EnergySystem {

    /**
     * 能量盒子
     */
    private final double[] energyBoxes;

    /**
     * 同步锁
     * 实现互斥
     *
     * Create an object named 'lock' specially to use as a Lock
     * e.g. final Object lock = new Object();
     */
    private final Byte[] lock = new Byte[0];

    /**
     * @param numberOfBoxes:       能量盒子的数量
     * @param initialEnergyPerBox: 每个能量盒子含有的初始能量值
     */
    public EnergySystem(int numberOfBoxes, double initialEnergyPerBox) {
        this.energyBoxes = new double[numberOfBoxes];
        for (int i = 0; i < energyBoxes.length; i++)
            energyBoxes[i] = initialEnergyPerBox;
    }

    /**
     * 能量的转移，从一个盒子到另一个盒子
     *
     * @param from:   能量源
     * @param to:     能量终点
     * @param amount: 能量值
     */
    public void transfer(int from, int to, double amount) {

        if (from < 0 || from >= getNumberOfBoxes() || to < 0 || to >= getNumberOfBoxes())
            return;
        if (from == to)
            return;
        if (amount > getTotalEnergies())
            return;

        /** 'synchronized' on object 'lock' means competing for the intrinsic lock of object 'lock' */
        synchronized (lock) {
            /**
             * 保证条件不满足时任务会被阻塞，而不是继续竞争CPU资源
             * */
            if (energyBoxes[from] < amount) {
                try {
                    /** wait on the condition queue of object 'lock' */
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            energyBoxes[from] -= amount;
            energyBoxes[to] += amount;
            System.out.printf("%s  从盒子[%d]转移到盒子[%d] %.2f单元能量。  能量总和：%.2f%n",
                    Thread.currentThread().getName(), from, to, amount, getTotalEnergies());

            /**
             * 唤醒所有在lock对象上等待的线程
             *
             * notify all other threads waiting on the condition queue of object 'lock'
             * and all the threads notified will compete for the lock of object 'lock'
             * and one of them will win and be runnable again
             * */
            lock.notifyAll();

        }

    }

    /**
     * 获取宇宙能量总和
     */
    public double getTotalEnergies() {
        double sum = 0;
        for (double amount : energyBoxes)
            sum += amount;
        return sum;
    }

    /**
     * 获取能量盒子数量
     */
    public int getNumberOfBoxes() {
        return energyBoxes.length;
    }

}
