package day6

import java.io.File
import java.io.InputStream

val filename = "input"
var pastStates: MutableList<State> = mutableListOf()

fun main(args: Array<String>) {
    val inputStream: InputStream = File("src/day6/" + filename).inputStream()

    val inputString = inputStream.bufferedReader().use { it.readText() }

    val memoryBanks = inputString.split("\t").map { it.toInt() }

    println("Total cycles: " + countRedistributions(memoryBanks))
}

fun countRedistributions(banks: List<Int>): Int {
    var count = 0

    var state = banks.toIntArray()
    val initialState = State(convertToString(state), 0)
    pastStates.add(initialState) // Add initial state

    val size = state.size

    while(true) {
        val max = state.max()
        val index = state.indexOf(max!!)
        var i = (index + 1) % size

        state[index] = 0

        count++

        for(j in max downTo 1) {
            state[i] = state[i] + 1
            i = (i + 1) % size
        }

        val stateString = convertToString(state)
        val newState = State(stateString, count)
        if(pastStates.contains(newState)) {
            val firstStateIndex = pastStates.indexOf(newState)
            val firstState = pastStates[firstStateIndex]
            println("Cycles between states: " + (newState.cycle - firstState.cycle))
            break
        }
        pastStates.add(newState)
    }

    return count
}

fun convertToString(state: IntArray): String {
    var str = ""

    for(bank in state) {
        str += bank.toString() + ","
    }

    return str
}

data class State(val state: String, val cycle: Int) {
    override fun equals(other: Any?): Boolean {
        return if(other is State) {
            other.state == this.state
        } else {
            false
        }
    }
}