package tasks.first;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class FirstTaskSolution implements FirstTask {
    @Override
    public String breadthFirst(boolean[][] adjacencyMatrix, int startIndex) {

        if(adjacencyMatrix == null)
            return null;

        ArrayList<Integer> result = new ArrayList<>();

        ArrayDeque<Integer> stringBroadside = new ArrayDeque<>();
        stringBroadside.addFirst(startIndex);
        result.add(startIndex);

        do {
            for (int i = 0; i < adjacencyMatrix.length; i++) {

                if ((stringBroadside.peekFirst()!=null)
                        && (adjacencyMatrix[stringBroadside.peekFirst()][i]) && !result.contains(i)) {
                    result.add(i);
                    stringBroadside.addLast(i);
                }
            }
            System.out.println(stringBroadside.pollFirst());

        }while (!stringBroadside.isEmpty());

        return result.toString();
    }

    @Override
    public Boolean validateBrackets(String s) {

        if (s == null)
            return true;

        ArrayDeque<String> parentheses = new ArrayDeque<>();

        int leftInd = 0;

        while (leftInd != s.length()){

            String openingPar;
            switch (s.charAt(leftInd)){
                case 40:
                    parentheses.addFirst("(");
                    leftInd++;
                    break;

                case 91:
                    parentheses.addFirst("[");
                    leftInd++;
                    break;

                case 123:
                    parentheses.addFirst("{");
                    leftInd++;
                    break;

                case 41:
                    if (parentheses.pollFirst()!="(")
                        throw new IllegalArgumentException("Wrong closing )");
                    leftInd++;
                    break;

                case 93:
                    if (parentheses.pollFirst()!="[")
                        throw new IllegalArgumentException("Wrong closing ]");
                    leftInd++;
                    break;

                case 125:
                    if (parentheses.pollFirst()!="{")
                        throw new IllegalArgumentException("Wrong closing }");
                    leftInd++;
                    break;

                default:
                    leftInd++;
            }
        }

        if (parentheses.size()==0)
            return true;
        else throw new IllegalArgumentException("Not enough closing parentheses for " + parentheses);

    }

    @Override
    public Long polishCalculation(String s) {

        ArrayDeque<Integer> numbers = new ArrayDeque<>();

        int n = s.split(" ").length;

        int i = 0;
        while ((n>2) && (s.split(" ")[i].charAt(0) > 48) && (i < n)) {

            // проверка на количество пробелов
            if (s.split(" ")[i].charAt(0)==32)
                throw new IllegalStateException(s.split(" ")[i]);

            numbers.addFirst(Integer.parseInt(s.split(" ")[i]));
            i++;
        }

        int size = numbers.size();
        if (numbers.size()>1) {
            long result = numbers.pollFirst();

            try {
                while (size < s.split(" ").length) {

                    //проверка на количество знаков подряд
                    if (s.split(" ")[size].length() > 1)
                        throw new IllegalStateException(s.split(" ")[size]);

                    switch (s.split(" ")[size].charAt(0)) {
                        case 42: //*
                            result *= numbers.pollFirst();
                            break;
                        case 43: //+
                            result += numbers.pollFirst();
                            break;
                        case 45: //-
                            result -= numbers.pollFirst();
                            break;
                        case 47: // /
                            result /= numbers.pollFirst();
                            break;
                        default:
                            // прверка на сам знак
                            throw new IllegalStateException(s.split(" ")[size]);
                    }
                    size++;
                }
            }
            catch (StringIndexOutOfBoundsException e){
                System.out.println(e.getMessage());
                throw new StringIndexOutOfBoundsException(" Incorrect input ");
            }
            return result;
        }
        // проверка на общее количество знаков
        else throw new IllegalStateException("Number of actions doesn't match the number of digits");

    }
}
