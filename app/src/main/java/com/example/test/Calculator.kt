package com.example.test
import android.util.Log
import java.util.Stack

class Calculator(_org_exp : String) {

    var org_exp: String
    var postfix_exp: String

    init {
        org_exp = _org_exp
        org_exp.replace(" ", "")
        postfix_exp = ""
    }

    private fun get_weight(oprt : Char): Int {
        when (oprt) {
            '*', '/' -> return 9
            '+', '-' -> return 7
            '(' -> return 5
            else -> return -1
        }
    }

    private fun convert_to_postfix() {
        var exp_list = ArrayList<String>()
        var oprt_stack = Stack<Char>()
        var numTemp = ""

        for (ch in org_exp) {
            if (ch.isDigit() || ch == '.') {
                numTemp += ch.toString()
            } else {
                exp_list.add(numTemp)
                numTemp = ""

                if (ch == '(' || oprt_stack.empty()) {
                    oprt_stack.push(ch)
                } else if (ch == ')') {
                    var op = oprt_stack.pop()

                    while (op != '(') {
                        exp_list.add(op.toString())
                        op = oprt_stack.pop()
                    }
                } else if (get_weight(ch) > get_weight(oprt_stack.peek())) {
                    oprt_stack.push(ch)
                } else {
                    while (! oprt_stack.empty()
                        && (get_weight(ch) <= get_weight(oprt_stack.peek()))) {
                        exp_list.add(oprt_stack.pop().toString())
                    }
                    oprt_stack.push(ch)
                }
            }
        }

        if (! numTemp.isEmpty()) {
            exp_list.add(numTemp)
        }

        while (! oprt_stack.empty()) {
            exp_list.add(oprt_stack.pop().toString())
        }

        Log.d("dgb", "exp_list: " + exp_list)
        postfix_exp = exp_list.joinToString(" ")
    }

    fun get_postfix_exp(): String {
        if (postfix_exp.isEmpty())
            convert_to_postfix()

        return postfix_exp
    }

    private fun calc_two_oprd(_oprd1: String, _oprd2: String, oprt: Char): Double {
        var oprd1 = _oprd1.toDouble()
        var oprd2 = _oprd2.toDouble()

        when (oprt) {
            '+' -> return oprd1 + oprd2
            '-' -> return oprd1 - oprd2
            '*' -> return oprd1 * oprd2
            '/' -> return oprd1 / oprd2
            else -> throw Exception()
        }
    }

    fun calculate(): String {
        var oprd_stack = Stack<String>()
        var numTemp = ""

        Log.d("dgb", "postfix_exp: " + postfix_exp)
        // e.g) postfix_exp == "30 60 +"
        for (ch in postfix_exp) {
            if (ch.isDigit() || ch == '.') {
                numTemp += ch.toString()
            } else {
                if (ch == ' ') {
                    if (numTemp.isNotEmpty()) {
                        oprd_stack.push(numTemp)
                        numTemp = ""
                    }
                } else {
                    Log.d("dgb", "initial oprd_stack: " + oprd_stack.toString())
                    val oprd2 = oprd_stack.pop()
                    val oprd1 = oprd_stack.pop()
                    Log.d("dgb", "oprd2: " + oprd2 + "\toprd1: " + oprd1)
                    Log.d("dgb", "before oprd_stack: " + oprd_stack.toString())
                    oprd_stack.push(calc_two_oprd(oprd1, oprd2, ch).toString())
                    Log.d("dgb", "after oprd_stack: " + oprd_stack.toString())
                }
            }
        }

        return oprd_stack.pop()
    }
}