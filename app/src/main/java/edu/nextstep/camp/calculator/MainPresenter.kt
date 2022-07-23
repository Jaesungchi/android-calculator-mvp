package edu.nextstep.camp.calculator

import edu.nextstep.camp.calculator.domain.Calculator
import edu.nextstep.camp.calculator.domain.Expression
import edu.nextstep.camp.calculator.domain.History
import edu.nextstep.camp.calculator.domain.Operator

/**
 * Created by link.js on 2022. 07. 20..
 */
class MainPresenter(
    private val view: MainContract.View,
    private val calculator: Calculator = Calculator(),
    private var expression: Expression = Expression.EMPTY,
    private val histories: MutableList<History> = mutableListOf()
) : MainContract.Presenter {
    override fun enterNumber(number: Int) {
        expression += number

        view.showExpression(expression.toString())
    }

    override fun enterOperator(operator: Operator) {
        expression += operator

        view.showExpression(expression.toString())
    }

    override fun removeLast() {
        expression = expression.removeLast()

        view.showExpression(expression.toString())
    }

    override fun calculate() {
        val result = calculator.calculate(expression.toString())
        if (result == null) {
            view.showInCompleteExpressionMessage()
            return
        }
        histories.add(History(expression.toString(), result))
        expression = Expression.EMPTY + result

        view.showExpression(expression.toString())
    }

    override fun loadHistory() {
        view.loadHistories(histories)
    }

    override fun clickHistory(isShown: Boolean) {
        if (isShown) {
            view.hideHistory()
        } else {
            view.showHistory()
        }
    }
}