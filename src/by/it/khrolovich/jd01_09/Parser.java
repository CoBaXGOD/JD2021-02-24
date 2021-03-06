package by.it.khrolovich.jd01_09;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    @SuppressWarnings("ConstantConditions")
    Var evaluate(String expression) {
        expression = expression.replaceAll("\\s+", "");//ту да же положим, теперь переменная на новое значение указывает
        String[] parts = expression.split(Patterns.OPERATION, 2);//пока только две части
        //можно так
        /*Pattern p = Pattern.compile(Patterns.OPERATION);
        String[] parts1 = p.split(p.toString());*/
        Var leftVar = VarCreator.build(parts[0]);
        if (parts.length < 2) {
            return leftVar;
        }
        Var rightVar = VarCreator.build(parts[1]);
        Pattern patternOperation = Pattern.compile(Patterns.OPERATION);
        Matcher matcherOperation = patternOperation.matcher(expression);
        if (matcherOperation.find()) {
            String operation = matcherOperation.group();
            switch (operation) {
                case "+":
                    return leftVar.add(rightVar);
                case "-":
                    return leftVar.sub(rightVar);
                case "*":
                    return leftVar.mul(rightVar);
                case "/":
                    return leftVar.div(rightVar);

            }
        }
        return null;//stub
    }
}
