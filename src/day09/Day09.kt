package day09

import readInput

fun main() {
    fun part1(input: List<String>): Long {
        val list = mutableListOf<Pair<Int, Int>>()
        input.joinToString("").chunked(1).forEachIndexed { index, char ->
            val number = char.toInt()
            if (index % 2 == 0){
                list.add(Pair(index/2, number))
            } else{
                if (number != 0){
                    list.add(Pair(-1, number))
                }
            }
        }

        while (list.count { it.first == -1 } > 0){
            val spaceIndex = list.indexOfFirst { it.first == -1 }
            val spaceSize = list[spaceIndex].second

            val lastBlock = list.last()
            if (lastBlock.second <= spaceSize){
                if (spaceSize - lastBlock.second == 0){
                    list[spaceIndex] = Pair(lastBlock.first, lastBlock.second)
                } else{
                    list[spaceIndex] = Pair(-1, spaceSize-lastBlock.second)
                    list.add(spaceIndex, Pair(lastBlock.first, lastBlock.second))
                }

                list.removeLast()
            } else{
                list[spaceIndex] = Pair(lastBlock.first, spaceSize)
                list[list.size-1] = Pair(lastBlock.first, lastBlock.second - spaceSize)
            }
        }
        var sum:Long = 0
        var index = 0
        list.forEach { pair ->
            for (i in index until index + pair.second){
                sum += pair.first * i
                index ++
            }
        }
        return sum
    }

    fun part2(input: List<String>): Long {
        val list = mutableListOf<Pair<Int, Int>>()
        input.joinToString("").chunked(1).forEachIndexed { index, char ->
            val number = char.toInt()
            if (index % 2 == 0){
                list.add(Pair(index/2, number))
            } else{
                if (number != 0){
                    list.add(Pair(-1, number))
                }
            }
        }

        var listIndex = list.size-1
        while (listIndex > 0){
            var lastBlock = list[listIndex]
            while (lastBlock.first == -1){
                listIndex --
                lastBlock = list[listIndex]
            }

            val spaceIndex = list.indexOfFirst { it.first == -1 && it.second >= lastBlock.second }
            if (spaceIndex == -1 || spaceIndex > listIndex){
                listIndex --
                continue
            }
            val spaceSize = list[spaceIndex].second
            if (lastBlock.second <= spaceSize){
                if (spaceSize - lastBlock.second == 0){
                    list[spaceIndex] = Pair(lastBlock.first, lastBlock.second)
                } else{
                    list[spaceIndex] = Pair(-1, spaceSize-lastBlock.second)
                    list.add(spaceIndex, Pair(lastBlock.first, lastBlock.second))

                    listIndex++

                }
                list[listIndex] = Pair(-1, lastBlock.second)
            }
            listIndex--
        }

        var sum:Long = 0
        var index = 0
        list.forEach { pair ->
            if (pair.first == -1){
                index += pair.second
                return@forEach
            }
            for (i in index until index + pair.second){
                sum += pair.first * i
                index ++
            }
        }
        return sum
    }
    
    val testInput = readInput("Day09_test")
    val input = readInput("Day09")

    check(part1(testInput) == 1928L)
    println(part1(input))

    check(part2(testInput) == 2858L)
    println(part2(input))
}
