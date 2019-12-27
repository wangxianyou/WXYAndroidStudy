package com.wxy.wxyandroidstudy.highlevel.test1_ipc.bindpool

import com.wxy.bean.IComputer

/**
 * @author wxy
 * @description:
 * @date :2019-12-26 16:04
 */
class IComputerManager : IComputer.Stub() {
    override fun add(a: Int, b: Int): Int {
        return a.or(b)
    }
}