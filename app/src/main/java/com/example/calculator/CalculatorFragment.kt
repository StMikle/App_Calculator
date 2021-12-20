package com.example.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.calculator.databinding.FragmentCalculatorBinding
import com.example.homework.kotlin.calculator.calculatorExpression.CalculateExpression

class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
//            Не заработало(((
//            val clickListener = object : View.OnClickListener {
//                override fun onClick(view: View) {
//                    when (view.id) {
//                        R.id.zero_button -> {
//                            textInputAndResult.append(zeroButton.text)
//                        }
//                        R.id.one_button -> {
//                            textInputAndResult.append(oneButton.text)
//                        }
//                        R.id.two_button -> {
//                            textInputAndResult.append(twoButton.text)
//                        }
//                        R.id.three_button -> {
//                            textInputAndResult.append(threeButton.text)
//                        }
//                        R.id.four_button -> {
//                            textInputAndResult.append(fourButton.text)
//                        }
//                        R.id.fife_button -> {
//                            textInputAndResult.append(fifeButton.text)
//                        }
//                        R.id.six_button -> {
//                            textInputAndResult.append(sixButton.text)
//                        }
//                        R.id.seven_button -> {
//                            textInputAndResult.append(sevenButton.text)
//                        }
//                        R.id.eight_button -> {
//                            textInputAndResult.append(eightButton.text)
//                        }
//                        R.id.nine_button -> {
//                            textInputAndResult.append(nineButton.text)
//                        }
//                    }
//                }
//            }
            zeroButton.setOnClickListener { changeTextInCalc("0") }
            oneButton.setOnClickListener { changeTextInCalc("1") }
            twoButton.setOnClickListener { changeTextInCalc("2") }
            threeButton.setOnClickListener { changeTextInCalc("3") }
            fourButton.setOnClickListener { changeTextInCalc("4") }
            fifeButton.setOnClickListener { changeTextInCalc("5") }
            sixButton.setOnClickListener { changeTextInCalc("6") }
            sevenButton.setOnClickListener { changeTextInCalc("7") }
            eightButton.setOnClickListener { changeTextInCalc("8") }
            nineButton.setOnClickListener { changeTextInCalc("9") }
            clearButton.setOnClickListener { clear() }
            plusMinusButton.setOnClickListener { changeSing() }
            bracketButton.setOnClickListener { changeTextInExpression("(") }
            backBracketButton.setOnClickListener { changeTextInExpression(")") }
            divisionButton.setOnClickListener { operatorHandler("/") }
            multiplyButton.setOnClickListener { operatorHandler("*") }
            minusButton.setOnClickListener { operatorHandler("-") }
            plusButton.setOnClickListener { operatorHandler("+") }
            pointButton.setOnClickListener { pointHandler(".") }
            resultButton.setOnClickListener { calculationResult() }
        }
    }

    // Обработка получившегося выражения
    private fun calculationResult() {
        with(binding) {
            when {
                textExpression.text.contains('=') -> {
                    Toast.makeText(context, "Введите новое выражение", Toast.LENGTH_SHORT).show()
                }
                else -> changeTextInExpression("${textInputAndResult.text} =")
            }
            val calculator = CalculateExpression()
            val result: String
            val expressionWithoutSpace = textExpression.text
                .replace(Regex("="), "")
                .replace(Regex("\\s+"), "")

            try {
                result = calculator.calculateExpressionWithBrackets(expressionWithoutSpace)
                when {
                    result.length > 9 -> textInputAndResult.text = calculator.format(result.toBigDecimal(),4)
                    else -> textInputAndResult.text = result
                }
            } catch (e: UnsupportedOperationException) {
                Toast.makeText(context, "Неправильный ввод", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Меняет текст в text_input_and_result TextView
    private fun changeTextInCalc(string: String) {
        with(binding) {
            when {
                textInputAndResult.text.length >= 10 -> {
                    Toast.makeText(context, "Лимит цифр", Toast.LENGTH_SHORT).show()
                }
                textInputAndResult.text.toString() == "0" -> {
                    textInputAndResult.text = string
                    clearButton.text = "C"
                }
                else -> textInputAndResult.append(string)
            }
        }
    }

    // Обрабатывает нажатие на кнопку с точкой
    private fun pointHandler(string: String) {
        with(binding) {
            when {
                textInputAndResult.text.toString() == "0" -> {
                    Toast.makeText(context, "Введите цифру", Toast.LENGTH_SHORT).show()
                }
                textInputAndResult.text.toString().contains('.') -> {
                    Toast.makeText(context, "Уже есть точка", Toast.LENGTH_SHORT).show()
                }
                else -> changeTextInCalc(string)
            }
        }
    }

    // Обрабатывает нажатие на математические операторы
    private fun operatorHandler(string: CharSequence) {
        with(binding) {
            if (textExpression.text.toString() == "0") textExpression.text = ""
            changeTextInExpression("${textInputAndResult.text} $string ")
            textInputAndResult.text = "0"
        }
    }

    // Меняет текст в text_expression TextView
    private fun changeTextInExpression(string: String) {
        with(binding) {
            when {
                textExpression.text.toString() == "0" -> textExpression.text = string
                textExpression.text.toString().contains('=') -> {
                    textExpression.text = string
                }
                else -> textExpression.append(string)
            }
        }
    }

    // Меняет знак
    private fun changeSing() {
//        with(binding) {
//            val textFromResult = textInputAndResult.text
//            when {
//                textFromResult[0] == '-' -> textFromResult.replace("-".toRegex(), "")
//                    .also { textInputAndResult.text = it }
//                else -> textInputAndResult.text = "-$textFromResult"
//            }
//        }
        Toast.makeText(context,  "Эта функци доступна по премиум подписке :)", Toast.LENGTH_SHORT).show()
    }

    //Очищает поля
    private fun clear() {
        with(binding) {
            when {
                textInputAndResult.text.toString() == "0" -> textExpression.text = "0"
                else -> {
                    clearButton.text = "AC"
                    textInputAndResult.text = "0"
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}