package com.example.test
import java.util.Stack
import kotlin.math.exp

class Calculator(_org_exp : String) {

    var org_exp: String
    var postfix_exp: String

    init {
        org_exp = _org_exp
        org_exp.replace(" ", "")
        postfix_exp = ""
    }


    fun get_weight(oprt : Char): Int {
        when (oprt) {
            '*', '/' -> return 9
            '+', '-' -> return 7
            '(' -> return 5
            else -> return -1
        }
    }

    fun convert_to_postfix(){
        var exp_list = ArrayList<Char>()
        var oprt_stack = Stack<Char>()

        for (ch in org_exp) {
            if (ch.isDigit()) {
                exp_list.plus(ch)
            } else {
                if (ch == '(' || oprt_stack.empty()) {
                    oprt_stack.push(ch)
                } else if (ch == ')') {
                    var op = oprt_stack.pop()

                    while (op != '(') {
                        exp_list.plus(op)
                        op = oprt_stack.pop()
                    }
                } else if (get_weight(ch) > get_weight(oprt_stack.peek())) {
                    oprt_stack.push(ch)
                } else {
                    while (! oprt_stack.empty()
                        && (get_weight(ch) <= get_weight(oprt_stack.peek()))) {
                        exp_list.plus(oprt_stack.pop())
                    }
                    oprt_stack.push(ch)
                }
            }
        }

        while (! oprt_stack.empty()) {
            exp_list.plus(oprt_stack.pop())
        }

        postfix_exp += exp_list
    }

    fun get_postfix_exp(): String {
        if (postfix_exp.isEmpty())
            convert_to_postfix()

        return postfix_exp
    }

    fun calc_two_oprd(oprd1: Int, oprd2: Int, oprt: Char): Int {
        when (oprt) {
            '+' -> return oprd1 + oprd2
            '-' -> return oprd1 - oprd2
            '*' -> return oprd1 * oprd2
            '/' -> return oprd1 / oprd2
            else -> throw Exception()
        }
    }

    fun calculate(): Int {
        var oprd_stack = Stack<Int>()

        for (ch in postfix_exp) {
            if (ch.isDigit()) {
                oprd_stack.push(ch as Int)
            } else {
                var oprd2 = oprd_stack.pop()
                var oprd1 = oprd_stack.pop()

                oprd_stack.push(calc_two_oprd(oprd1, oprd2, ch))
            }
        }

        return oprd_stack.pop()
    }
}