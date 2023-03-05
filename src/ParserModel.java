import java.util.ArrayList;
import java.util.List;

public class ParserModel {


    public String parserEquation(String expression){
        List<Lexeme> lexemes = lexAnalyze(expression);
        LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemes);
        return Integer.toString(expr(lexemeBuffer));
    }

    /**
     * Принимаем выражение, вытаскиваем из него лексемы и помещаем в List (лексический анализатор)
     */

    public static List<Lexeme> lexAnalyze(String expText){
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        int pos = 0;
        while (pos < expText.length()){
            char c = expText.charAt(pos);
            switch (c){
                case '(':
                    lexemes.add(new Lexeme(LexemeType.LEFT_BRACKET, c));
                    pos++;
                    continue;
                case ')':
                    lexemes.add(new Lexeme(LexemeType.RIGHT_BRACKET, c));
                    pos++;
                    continue;
                case '+':
                    lexemes.add(new Lexeme(LexemeType.OP_SUM, c));
                    pos++;
                    continue;
                case '-':
                    lexemes.add(new Lexeme(LexemeType.OP_SUB, c));
                    pos++;
                    continue;
                case '*':
                    lexemes.add(new Lexeme(LexemeType.OP_MULT, c));
                    pos++;
                    continue;
                case '/':
                    lexemes.add(new Lexeme(LexemeType.OP_DIV, c));
                    pos++;
                    continue;
                default:
                    if (c <= '9' && c>= '0') {
                        StringBuilder sb = new StringBuilder();
                        do{
                            sb.append(c);
                            pos++;
                            if (pos >= expText.length()){
                                break;
                            }
                            c = expText.charAt(pos);
                        } while (c <= '9' && c>= '0');
                        lexemes.add(new Lexeme(LexemeType.NUMBER, sb.toString()));
                    }
                    else {
                        if (c != ' ') {
                            throw new RuntimeException("Найден неверный символ: " + c);
                        }
                        pos++;
                    }
            }
        }
        lexemes.add(new Lexeme(LexemeType.EOF, ""));
        return lexemes;
    }

    /**
     * Проверяем, что выражение не пустое
     */

    public static int expr (LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        if (lexeme.type == LexemeType.EOF) {
            return 0;
        }
        else {
            lexemes.back();
            return plusminus(lexemes);
        }
    }

    /**
     * Считаем сложение / вычитание
     */

    public static int plusminus (LexemeBuffer lexemes) {
        int value = multdiv(lexemes);
        while (true){
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type){
                case OP_SUM:
                    value += multdiv(lexemes);
                    break;
                case OP_SUB:
                    value -= multdiv(lexemes);
                    break;
                default:
                    lexemes.back();
                    return value;
            }
        }
    }

    /**
     * Считаем умножение / деление
     */
    public static int multdiv (LexemeBuffer lexemes) {
        int value = factor(lexemes);
        while (true){
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type){
                case OP_MULT:
                    value *= factor(lexemes);
                    break;
                case OP_DIV:
                    if (value != 0){
                        value /= factor(lexemes);
                        break;
                    }
                    else {
                        throw new RuntimeException("На 0 делить нельзя, крошка");
                    }
                default:
                    lexemes.back();
                    return value;
            }
        }
    }

    /**
     * Выявляем число (или выражение в скобках)
     */
    public static int factor (LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        switch (lexeme.type){
            case NUMBER:
                return Integer.parseInt(lexeme.value);
            case LEFT_BRACKET:
                int value = expr(lexemes);
                lexeme = lexemes.next();
                if(lexeme.type != LexemeType.RIGHT_BRACKET) {
                    throw new RuntimeException("Ошибка: обнаружена незакрытая скобка");
                }
                return value;
            default:
                throw new RuntimeException("Обнаружена ошибка");
        }
    }
}


// НИЖЕ: ПАРСЕР ВЫЧИСЛЕНИЯ ПРОСТОГО ВЫРАЖЕНИЯ (ОДНО ДЕЙСТВИЕ)


//    public String parserEquation(String expression) {
//        double result = 0;
//        String[] pars = expression.split(" ");
//        for (int i = 1; i < pars.length; i++) {
//            if (pars[i].equals("*")) {
//                result = Integer.parseInt(pars[i - 1]) * Integer.parseInt(pars[i + 1]);
//                break;
//            } else if (pars[i].equals("/")) {
//                if (Integer.parseInt(pars[i + 1]) != 0){
//                    result = Integer.parseInt(pars[i - 1]) / Integer.parseInt(pars[i + 1]);
//                }
//                else {
//                    return "На 0 делить нельзя!";
//                }
//                break;
//            } else if (pars[i].equals("+")) {
//                result = Integer.parseInt(pars[i - 1]) + Integer.parseInt(pars[i + 1]);
//                break;
//            } else if (pars[i].equals("-")) {
//                result = Integer.parseInt(pars[i - 1]) - Integer.parseInt(pars[i + 1]);
//                break;
//            } else {
//                return "Введено некорректное выражение!";
//            }
//        }
//        return Double.toString(result);
//    }
//}


