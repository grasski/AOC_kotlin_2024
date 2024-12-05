package day05

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val rules = input.filter { it.contains("|") }.map { it.split("|") }.toList()
        val pages = input.filter { it.contains(",") }.map { it.split(",") }.toList()

        var sum = 0
        pages.forEach { page ->
            val mutList = mutableListOf<Int>()
            for (pIndex in 0..page.size-2){
                val p1 = page[pIndex]
                val p2 = page[pIndex+1]

                val r = rules.filter { it[0] == p1 }
                if (r.any { it[1] == p2 }){
                    mutList.add(p1.toInt())
                    if (pIndex >= page.size-2){
                        mutList.add(p2.toInt())
                    }
                    continue
                } else{
                    mutList.clear()
                    break
                }
            }
            if (mutList.isNotEmpty()){
                sum += mutList[mutList.size/2]
            }
        }
        return sum
    }
    fun part2(input: List<String>): Int {
        val rules = input.filter { it.contains("|") }.map { it.split("|") }.toList()
        val pages = input.filter { it.contains(",") }.map { it.split(",") }.toList()

        var sum = 0
        pages.forEach { page ->
            val mutList = mutableListOf<Int>()
            for (pIndex in 0..page.size-2){
                val p1 = page[pIndex]
                val p2 = page[pIndex+1]

                val r = rules.filter { it[0] == p1 }
                if (r.any { it[1] == p2 }){
                    continue
                } else{
                    mutList.addAll(page.map { it.toInt() })
                    break
                }
            }

            if (mutList.isNotEmpty()){
                for (uIndex in 0..mutList.size-2){
                    var p1 = mutList[uIndex]
                    var p2 = mutList[uIndex+1]

                    var r = rules.filter { it[0].toInt() == p1 }

                    var i = uIndex
                    while (r.none { it[1].toInt() == p2 }){
                        mutList[i] = p2
                        mutList[i+1] = p1

                        if (i > 0){
                            i -= 1
                        }
                        p1 = mutList[i]
                        p2 = mutList[i+1]

                        r = rules.filter { it[0].toInt() == p1 }
                    }
                }
                sum += mutList[mutList.size/2]
            }
        }
        return sum
    }
    
    val testInput = readInput("Day05_test")
    val input = readInput("Day05")

    check(part1(testInput) == 143)
    println(part1(input))

    check(part2(testInput) == 123)
    println(part2(input))
}
