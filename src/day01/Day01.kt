package day01

import readInput
import kotlin.math.abs

fun main() {
    fun getLeftAndRight(input: List<String>): Pair<MutableList<Int>, MutableList<Int>> {
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()
        input.map {
            val nums = it.split("   ")
            left.add(nums[0].toInt())
            right.add(nums[1].toInt())
        }
        return Pair(left, right)
    }

    fun part1(input: List<String>): Int {
        val (left, right) = getLeftAndRight(input)
        left.sort()
        right.sort()

        var sum = 0
        for (i in left.zip(right)){
            sum += abs(i.first - i.second)
        }

        return sum
    }
    fun part2(input: List<String>): Int {
        val (left, right) = getLeftAndRight(input)

        var sum = 0
        for (l in left){
            val c = right.count { it == l }
            sum += l * c
        }

        return sum
    }
    
    val testInput = readInput("Day01_test")
    val input = readInput("Day01")

    check(part1(testInput) == 11)
    println(part1(input))

    check(part2(testInput) == 31)
    println(part2(input))
}
