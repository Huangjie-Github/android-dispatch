package cn.ajiehome.dispatch.memory.service.impl;

import cn.ajiehome.dispatch.memory.service.Fit;
import cn.ajiehome.dispatch.utils.MemoryUtils;

/**
 * @author Jie
 */
public class NextFit implements Fit {
    @Override
    public Integer distribution(Integer memorySize) {
        int index = -1;//符合条件的初始地址
        int count = 0;//符合区的长度
        for (int i = MemoryUtils.indexAll; i < MemoryUtils.MEMORY.length; i++) {
            Integer memoryByte = MemoryUtils.MEMORY[i];
            if (memoryByte == 0) {
                //存储首地址
                if (index == -1) {
                    index = i;
                }
                count++;
            } else {
                if (count >= memorySize) {
                    break;
                } else {
                    index = -1;
                    count = 0;
                }
            }
        }

        if (index != -1 && count >= memorySize) {
            MemoryUtils.occupyMemory(index, memorySize);
            MemoryUtils.indexAll = index;
        } else {
            index = -1;
            count = 0;
        }
        return index;

    }
}
