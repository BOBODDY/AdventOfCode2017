package day5

import java.io.File
import java.io.InputStream

fun main(args: Array<String>) {
    val inputStream: InputStream = File("src/day5/" + filename).inputStream()

    val inputString = inputStream.bufferedReader().use { it.readText() }
    val lines = inputString.split("\n")

    var jumps: MutableList<Int> = mutableListOf()

    lines.forEach { jumps.add(it.toInt()) }

    print(countJumps2(jumps))
}

fun countJumps2(instructions: MutableList<Int>): Int {
    var count = 0
    val size = instructions.size
    var nextJump = 0
    var i = 0

    while(nextJump in 0..(size - 1)) {
        nextJump += instructions[i]
        if(instructions[i] >= 3) {
            instructions[i] -= 1
        } else {
            instructions[i] += 1
        }
        i = nextJump
        count++
    }

    return count
}