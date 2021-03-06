package by.it.khrolovich.jd01_06;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskA2 {
    private static String[] words = {};
    private static int[] counts = {};

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[а-яА-ЯёЁ]+");
        Matcher matcher = pattern.matcher(Poem.text);
        while (matcher.find()) {
            String word = matcher.group();
            processOneWord(word);
        }
        for (int i = 0; i < words.length; i++) {
            System.out.printf("%s=%d\n", words[i], counts[i]);
        }
    }

    private static void processOneWord(String word) {
        boolean newWord = true;
        for (int i = 0, wordsLenght = words.length; i < wordsLenght; i++) {
            String CurrentWord = words[i];
            if (CurrentWord.equals(word)) {
                newWord = false;
                counts[i]++;
            }
        }
        //Если новое слово, то добавляем в массив, увеличиваем размер массива
        if (newWord) {
            final int newLenght = words.length + 1;
            words = Arrays.copyOf(words, newLenght);//новое место в памяти
            counts = Arrays.copyOf(counts, newLenght);
            words[words.length - 1] = word;
            counts[counts.length - 1] = 1;
        }

    }
}
