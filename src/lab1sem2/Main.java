package lab1sem2;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 *  Приемы работы с коллекциями
 */
public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
//        List<String> list = readFile("file.txt");
//        writeListToFile(list, "out.txt");
//        task2();
//        List<String> list = task4("file.txt");
//        for (String s : list) {
//            System.out.println(s);
//        }
        System.out.println(brackets("{(())[{()}]}"));
    }

    // 1. Ввести строки из файла, записать в список. Вывести строки в файл в обратном порядке.
    public List<String> readFile(String filename) {
        try (BufferedReader reader = Files.newBufferedReader(Path.of(filename))) {
            List<String> list = new ArrayList<>();
            String line;
            while ((line = reader.readLine())!=null) {
                list.add(line);
            }
            return list;
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    public void writeListToFile(List<String> list, String filename) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
// 1
//            for (int i = list.size()-1; i >=0 ; i--) {
//                out.println(list.get(i));
//            }
// 2
//            Collections.reverse(list);
//            for (String s : list) {
//                out.println(s);
//            }
// 3
            ListIterator<String> iterator = list.listIterator();
            while (iterator.hasNext()) iterator.next();
            while (iterator.hasPrevious()) {
                out.println(iterator.previous());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 2. Задать два стека, поменять информацию местами
    public void task2() {
        Deque<Integer> stack1 = new ArrayDeque<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0));
        Deque<Integer> stack2 = new ArrayDeque<>(Arrays.asList(2, 4, 6, 8, 0));

        swapStacks(stack1, stack2);

        System.out.println("stack1 : " + stack1);
        System.out.println("stack2 : " + stack2);
    }

    private void swapStacks(Deque<Integer> stack1, Deque<Integer> stack2) {
        Deque<Integer> temp1 = new ArrayDeque<>();
        while (!stack1.isEmpty()) {
            temp1.push(stack1.pop());
        }
        Deque<Integer> temp2 = new ArrayDeque<>();
        while (!stack2.isEmpty()) {
            temp2.push(stack2.pop());
        }
        while (!temp2.isEmpty()) {
            stack1.push(temp2.pop());
        }
        while (!temp1.isEmpty()) {
            stack2.push(temp1.pop());
        }
    }

    // 3. Не используя вспомогательных объектов, переставить отрицательные элементы данного списка в конец,
    //    а положительные — в начало списка.
    public void task3() {
        List<Integer> list = new ArrayList<>(List.of(-5, 10, 2, -4, 6, 11, -8, 5, 12));

    }

    // 4. Задан файл с текстом на английском языке. Выделить все различные слова.
    //   Слова, отличающиеся только регистром букв, считать одинаковыми.
    public List<String> task4(String filename) {
        Set<String> allWords = new TreeSet<>(String::compareToIgnoreCase);
        try (BufferedReader reader = Files.newBufferedReader(Path.of(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(" ");
                for (String s : split) {
                    if(!s.isBlank()) allWords.add(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(allWords);
    }

    // 5. Задана строка, состоящая из символов «(», «)», «[», «]», «{», «}». Проверить
    //    правильность расстановки скобок. Использовать стек.
    // {(())[{()}]}(((
    public boolean brackets(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(' : stack.push(')'); break;
                case '[' : stack.push(']'); break;
                case '{' : stack.push('}'); break;
                default:
                    if (stack.isEmpty()) return false;
                    char t = stack.pop();
                    if (c!=t) {
                        return false;
                    }
            }
        }
        return stack.isEmpty();
    }
}
