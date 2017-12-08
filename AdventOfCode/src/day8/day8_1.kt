package day8

import java.io.File
import java.io.InputStream

val filename = "input"

var registerMap = HashMap<String, Int>()

fun main(args: Array<String>) {
    val inputStream: InputStream = File("src/day8/" + filename).inputStream()

    val inputString = inputStream.bufferedReader().use { it.readText() }

    val instructions = inputString.split("\n")

    println("Largest amount: " + findLargestRegister(instructions))
}

fun findLargestRegister(instr: List<String>): Int {
    val instructions = instr.map { convertStringToInstruction(it) }

    populateRegisters(instructions)

    var highestEver = 0

    for(instruction in instructions) {
        val registerAmount = registerMap[instruction.condReg]!!
        val performOp = when(instruction.condition) {
            Condition.LESSEQ -> registerAmount <= instruction.conditionAmount
            Condition.LESS -> registerAmount < instruction.conditionAmount
            Condition.GREAT -> registerAmount > instruction.conditionAmount
            Condition.GREATEQ -> registerAmount >= instruction.conditionAmount
            Condition.EQ -> registerAmount == instruction.conditionAmount
            Condition.NEQ -> registerAmount != instruction.conditionAmount
        }

        if(performOp) {
            val oldAmount = registerMap[instruction.register]!!
            val newValue = when(instruction.op) {
                Operation.ADD -> oldAmount + instruction.amount
                Operation.SUB -> oldAmount - instruction.amount
            }
            registerMap.put(instruction.register, newValue)
        }

        val highest = registerMap.values.max()!!
        if(highest > highestEver) {
            highestEver = highest
        }
    }

    println("Highest value seen: " + highestEver)
    return registerMap.values.max()!!
}

fun populateRegisters(instructions: List<Instruction>) {
    instructions.forEach {
        registerMap.put(it.register, 0)
    }
}

fun convertStringToInstruction(instrution: String): Instruction {
    val data = instrution.split(" ")

    val register = data[0]
    var op = Operation.ADD
    if (data[1] == "dec") {
        op = Operation.SUB
    }
    val amount = data[2].toInt()

    val conditionRegister = data[4]
    var condition = Condition.LESS
    val cond = data[5]
    when (cond) {
        "<" -> condition = Condition.LESS
        ">" -> condition = Condition.GREAT
        "==" -> condition = Condition.EQ
        "!=" -> condition = Condition.NEQ
        ">=" -> condition = Condition.GREATEQ
        "<=" -> condition = Condition.LESSEQ
    }
    val conditionAmount = data[6].toInt()

    return Instruction(register, op, amount, conditionRegister, condition, conditionAmount)
}

data class Instruction(val register: String,
                       val op: Operation,
                       val amount: Int,
                       val condReg: String,
                       val condition: Condition,
                       val conditionAmount: Int)

enum class Operation {
    ADD, SUB
}

enum class Condition {
    LESS, GREAT, LESSEQ, GREATEQ, EQ, NEQ
}