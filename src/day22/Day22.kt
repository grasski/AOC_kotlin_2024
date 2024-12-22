package day22

import readInput

fun main() {
    fun part1(input: List<String>): Long {
        var sum = 0L
        input.forEach { line ->
            var secret = line.toLong()
            for (i in 0..<2000){
                val r1: Long = secret*64L
                val m1: Long = r1 xor secret
                val p1: Long = m1 % 16777216

                val s2 = p1
                val r2 = (s2/32)
                val m2 = r2 xor s2
                val p2 = m2 % 16777216

                val s3 = p2
                val r3 = s3 * 2048
                val m3 = r3 xor s3
                val p3 = m3 % 16777216

                secret = p3
            }
            sum += secret
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val testInput = readInput("Day22_test")
    val input = readInput("Day22")

    check(part1(testInput) == 37327623L)
    println(part1(input))

//    check(part2(testInput) == 0)
//    println(part2(input))
}
