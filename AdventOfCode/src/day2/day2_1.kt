package day2

import java.io.File
import java.io.InputStream

var filename="input"

fun main(args: Array<String>) {
    val inputStream: InputStream = File("src/day2/" + filename).inputStream()

    val inputString = inputStream.bufferedReader().use { it.readText() }
    print(createChecksum(inputString))
}

fun createChecksum(file: String): Int {
    val rows = file.split("\r\n")
    var checksum = 0

    for(row in rows) {
        val rowAsNumbers = convertRow(row)

        val difference = differenceInRow(rowAsNumbers)

        checksum += difference
    }

    return checksum
}

fun convertRow(row: String): IntArray {
    var numbers: MutableList<Int> = mutableListOf()

    val columns = row.split("\t") // split on tabs to separate columns

    for(col in columns) {
        val intVal = col.toInt()
        numbers.add(intVal)
    }

    return numbers.toIntArray()
}

fun differenceInRow(numbers: IntArray): Int {
    var max = Int.MIN_VALUE
    var min = Int.MAX_VALUE

    for(num in numbers) {
        if(num > max) {
            max = num
        }
        if(num < min) {
            min = num
        }
    }

    return max - min
}