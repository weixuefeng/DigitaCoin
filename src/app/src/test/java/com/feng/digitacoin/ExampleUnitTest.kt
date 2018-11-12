package com.feng.digitacoin

import android.util.SparseArray
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun devideTest() {
        println(12 % 10)
    }

    @Test
    fun sparryTest() {
        val list = SparseArray<String>()
        for(i in 10 .. 20) {
            list.put(i, i.toString() + "haha")
        }
        println(list)
        for(i in 0 .. list.size()) {
            val s = list[i]
            print(list[i])
        }
    }
}
