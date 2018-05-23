package by.epam.task3.interpreter;

public class TerminalExpressionMultiply implements AbstractExpression {
    @Override
    public void interpret(Context context) {
        context.push(context.pop() * context.pop());
    }
}
