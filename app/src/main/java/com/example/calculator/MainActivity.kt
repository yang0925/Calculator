package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Numbers
        tv_one.setOnClickListener { appendOnExpression("1", true) }
        tv_two.setOnClickListener { appendOnExpression("2", true) }
        tv_three.setOnClickListener { appendOnExpression("3", true) }
        tv_four.setOnClickListener { appendOnExpression("4", true) }
        tv_five.setOnClickListener { appendOnExpression("5", true) }
        tv_six.setOnClickListener { appendOnExpression("6", true) }
        tv_seven.setOnClickListener { appendOnExpression("7", true) }
        tv_eight.setOnClickListener { appendOnExpression("8", true) }
        tv_nine.setOnClickListener { appendOnExpression("9", true) }
        tv_zero.setOnClickListener { appendOnExpression("0", true) }
        tv_dot.setOnClickListener { appendOnExpression(".", true) }

        //Operators
        tv_plus.setOnClickListener { appendOnExpression("+", false) }
        tv_minus.setOnClickListener { appendOnExpression("-", false) }
        tv_mul.setOnClickListener { appendOnExpression("*", false) }
        tv_divide.setOnClickListener { appendOnExpression("/", false) }
        tv_open.setOnClickListener { appendOnExpression("(", false) }
        tv_close.setOnClickListener { appendOnExpression(")", false) }

        tv_clear.setOnClickListener {
            tv_expression.text = ""
            tv_result.text = ""
        }

        img_back.setOnClickListener {
            val string = tv_expression.text.toString()
            if(string.isNotEmpty()) {
                tv_expression.text = string.substring(0,string.length-1)
            }
            tv_result.text = ""
        }

        tv_equals.setOnClickListener {
            try {
                val expression = ExpressionBuilder(tv_expression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()

                if(result == longResult.toDouble())
                    tv_result.text = longResult.toString()
                else
                    tv_result.text = result.toString()

            }catch (e: Exception) {

            }
        }
    }

    fun appendOnExpression(string: String, canClear: Boolean) {

        if (tv_result.text.isNotEmpty()) {
            tv_expression.text = ""
        }

        if(canClear) {
            tv_result.text = ""
            tv_expression.append(string)
        }else {
            tv_expression.append(tv_result.text)
            tv_expression.append(string)
            tv_result.text = ""
        }
    }
}