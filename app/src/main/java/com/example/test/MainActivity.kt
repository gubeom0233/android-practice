package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import java.lang.Double.parseDouble


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val display = findViewById(R.id.txt_input) as EditText
        val btn_clear = findViewById(R.id.btn_clear) as Button
        val btn_bracket = findViewById(R.id.btn_bracket) as Button
        val btn_percentage = findViewById(R.id.btn_percentage) as Button
        val btn_divide = findViewById(R.id.btn_divide) as Button
        val btn_multiply = findViewById(R.id.btn_multiply) as Button
        val btn_sub = findViewById(R.id.btn_sub) as Button
        val btn_add = findViewById(R.id.btn_add) as Button
        val btn_nun = findViewById(R.id.btn_nun) as Button
        val btn_change = findViewById(R.id.btn_change) as Button
        val btn_dot = findViewById(R.id.btn_dot) as Button
        val btn_1 = findViewById(R.id.btn_1) as Button
        val btn_2 = findViewById(R.id.btn_2) as Button
        val btn_3 = findViewById(R.id.btn_3) as Button
        val btn_4 = findViewById(R.id.btn_4) as Button
        val btn_5 = findViewById(R.id.btn_5) as Button
        val btn_6 = findViewById(R.id.btn_6) as Button
        val btn_7 = findViewById(R.id.btn_7) as Button
        val btn_8 = findViewById(R.id.btn_8) as Button
        val btn_9 = findViewById(R.id.btn_9) as Button
        val btn_zero = findViewById(R.id.btn_zero) as Button

        display.gravity = Gravity.CENTER
        btn_clear.setOnClickListener {
            display.setText("")
        }

        btn_bracket.setOnClickListener {
            var exists = display.text.toString()
            var newInput = '?'
            var bracketCount = 0

            if (exists.isNotEmpty()) {
                for (ch in exists) {
                    if (ch == '(')
                        bracketCount += 1
                    else if (ch == ')')
                        bracketCount -= 1
                }

                if (bracketCount > 0 && exists.last().isDigit()) {
                    newInput = ')'
                } else if (bracketCount > 0 && exists.last() == ')') {
                    newInput = ')'
                } else {
                    newInput = '('
                }

                if ((exists.last().isDigit() || exists.last() == ')') && newInput == '(')
                    display.setText(exists + '*' + newInput)
                else
                    display.setText(exists + newInput)
            } else {
                display.setText('('.toString())
            }
        }

        btn_percentage.setOnClickListener {
            try {
                var lastChar = parseDouble(display.text.last().toString())
                display.setText(display.text.toString() + btn_percentage.text.toString())
            } catch (e: NumberFormatException) {
                // Do nothing...
            } catch (e: NoSuchElementException) {
                // Do nothing...
            }
        }

        btn_divide.setOnClickListener {
            var exists = display.text.toString()

            if (exists.last().isDigit() || exists.last() == ')')
                display.setText(exists + btn_divide.text[0])
        }

        btn_multiply.setOnClickListener {
            var exists = display.text.toString()

            if (exists.last().isDigit() || exists.last() == ')')
                display.setText(exists + btn_multiply.text[0])
        }

        btn_sub.setOnClickListener {
            var exists = display.text.toString()

            if (exists.last().isDigit() || exists.last() == ')')
                display.setText(exists + btn_sub.text[0])
        }

        btn_add.setOnClickListener {
            var exists = display.text.toString()

            if (exists.last().isDigit() || exists.last() == ')')
                display.setText(exists + btn_add.text[0])
        }

        btn_nun.setOnClickListener {
            try {
                display.setText(this.calculate(display.text.toString()))
            } catch (e: NumberFormatException) {
                // Do nothing...
            } catch (e: NoSuchElementException) {
                // Do nothing...
            }
        }

        btn_change.setOnClickListener {

        }

        btn_dot.setOnClickListener {
            var exists = display.text.toString()

            if (exists.last().isDigit())
                display.setText(exists + btn_dot.text[0])
        }

        btn_1.setOnClickListener {
            var exists = display.text.toString()

            display.setText(exists + commonNumericInput(exists) + btn_1.text.toString())
        }
        btn_2.setOnClickListener {
            var exists = display.text.toString()

            display.setText(exists + commonNumericInput(exists) + btn_2.text.toString())
        }
        btn_3.setOnClickListener {
            var exists = display.text.toString()

            display.setText(exists + commonNumericInput(exists) + btn_3.text.toString())
        }
        btn_4.setOnClickListener {
            var exists = display.text.toString()

            display.setText(exists + commonNumericInput(exists) + btn_4.text.toString())
        }
        btn_5.setOnClickListener {
            var exists = display.text.toString()

            display.setText(exists + commonNumericInput(exists) + btn_5.text.toString())
        }
        btn_6.setOnClickListener {
            var exists = display.text.toString()

            display.setText(exists + commonNumericInput(exists) + btn_6.text.toString())
        }
        btn_7.setOnClickListener {
            var exists = display.text.toString()

            display.setText(exists + commonNumericInput(exists) + btn_7.text.toString())
        }
        btn_8.setOnClickListener {
            var exists = display.text.toString()

            display.setText(exists + commonNumericInput(exists) + btn_8.text.toString())
        }
        btn_9.setOnClickListener {
            var exists = display.text.toString()

            display.setText(exists + commonNumericInput(exists) + btn_9.text.toString())
        }
        btn_zero.setOnClickListener {
            var exists = display.text.toString()

            display.setText(exists + commonNumericInput(exists) + btn_zero.text.toString())
        }
    }

    private fun commonNumericInput(exists: String): Char {
        if (exists.isNotEmpty() && exists.last() == ')')
            return '*'

        return '\u0000'
    }

    // reference: https://wayhome25.github.io/cs/2017/04/18/cs-22/
    private fun calculate(exp: String): String {

        val calc = Calculator(exp)
        calc.get_postfix_exp()
        val ret = calc.calculate()

        return ret
    }
}
