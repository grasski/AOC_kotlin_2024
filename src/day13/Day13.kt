package day13

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val lines = input.map { it.split("\n") }.toMutableList()
        lines.removeAll { it[0].isEmpty() }
        val blocks = lines.chunked(3)

        var sum = 0
        blocks.forEach { block ->
            val A = "X[+]\\d+|Y[+]\\d+".toRegex().findAll(block[0][0]).map { it.value.takeLast(it.value.length-2).toInt() }.toList()
            val B = "X[+]\\d+|Y[+]\\d+".toRegex().findAll(block[1][0]).map { it.value.takeLast(it.value.length-2).toInt() }.toList()
            val prize = "X[=]\\d+|Y[=]\\d+".toRegex().findAll(block[2][0]).map { it.value.takeLast(it.value.length-2).toInt() }.toList()

            val clickA = (prize[0]*B[1] - prize[1]*B[0]) / (A[0]*B[1] - A[1]*B[0]).toFloat()
            val clickB = -(prize[0]*A[1] - prize[1]*A[0]) / (A[0]*B[1] - A[1]*B[0]).toFloat()
            if (!(clickA > 100 || clickB > 100 || clickA < 0 || clickB < 0 || clickA.rem(1)>0 || clickB.rem(1)>0)) {
                sum += (clickA.toInt() * 3 + clickB.toInt() * 1)
            }
        }
        return sum
    }
    
    fun part2(input: List<String>): Long {
        val lines = input.map { it.split("\n") }.toMutableList()
        lines.removeAll { it[0].isEmpty() }
        val blocks = lines.chunked(3)

        var sum:Long = 0
        blocks.forEach { block ->
            val A = "X[+]\\d+|Y[+]\\d+".toRegex().findAll(block[0][0]).map { it.value.takeLast(it.value.length-2).toInt() }.toList()
            val B = "X[+]\\d+|Y[+]\\d+".toRegex().findAll(block[1][0]).map { it.value.takeLast(it.value.length-2).toInt() }.toList()
            val prize = "X[=]\\d+|Y[=]\\d+".toRegex().findAll(block[2][0]).map { it.value.takeLast(it.value.length-2).toLong() }.toMutableList()
            prize[0] += 10000000000000
            prize[1] += 10000000000000

            val clickA = (prize[0]*B[1] - prize[1]*B[0]) / (A[0]*B[1] - A[1]*B[0]).toDouble()
            val clickB = -(prize[0]*A[1] - prize[1]*A[0]) / (A[0]*B[1] - A[1]*B[0]).toDouble()
            if (!(clickA < 0 || clickB < 0 || clickA.rem(1)>0 || clickB.rem(1)>0)) {
                sum += (clickA.toLong() * 3 + clickB.toLong() * 1)
            }
        }
        return sum
    }
    
    val testInput = readInput("Day13_test")
    val input = readInput("Day13")

    check(part1(testInput) == 480)
    println(part1(input))

//    check(part2(testInput) == 0)
    println(part2(input))
}
