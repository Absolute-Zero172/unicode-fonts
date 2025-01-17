package com.calvin.unicodefonts;

import java.util.Random;

public class Font {
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static int getRandom(int number, int variance) {
        int n = number;
        if (variance == 0) return n;

        Random r = new Random();
        n += r.nextInt(variance + 1) * (r.nextBoolean() ? -1 : 1);
        if (n < 0) n = 0;

        return n;

    }

    public static boolean isVowel(char letter) {
        return (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u' || letter == 'y'
                || letter == 'A' || letter == 'E' || letter == 'I' || letter == 'O' || letter == 'U' || letter == 'Y');
    }

    public static boolean isVowel(String letter) {
        return isVowel(letter.charAt(0));
    }

    public static String applyFont(String inputString, String fontData) throws IndexOutOfBoundsException {
        int countPerCharacter = fontData.length() / 52;

        if (countPerCharacter == 0) {
            throw new IndexOutOfBoundsException("font data at zero");
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < inputString.length(); i++) {
            int letterIdx = LETTERS.indexOf(inputString.substring(i, i + 1));

            if (letterIdx == -1) {
                result.append(inputString.charAt(i));
            } else {
                String convertedLetter = fontData.substring(letterIdx * countPerCharacter, (letterIdx + 1) * countPerCharacter);

                result.append(convertedLetter);
            }
        }

        return result.toString();
    }


    public static String scriptAlternate(String inputString, String superscript, String subscript) {
        int mode = 0;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < inputString.length(); i++) {
            String currentLetter = inputString.substring(i, i + 1);

            if (mode == 0) result.append(applyFont(currentLetter, subscript));
            else if (mode == 2) result.append(applyFont(currentLetter, superscript));
            else result.append(currentLetter);

            mode++;
            if (mode > 3) mode = 0;
        }

        return result.toString();
    }


    public static String addSpaces(String inputString, int number, int variance) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < inputString.length(); i++) {
            result.append(inputString.charAt(i));

            int spaceCount = getRandom(number, variance);

            for (int j = 0; j < spaceCount; j++) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static String addPeriods(String inputString) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < inputString.length(); i++) {
            String current = inputString.substring(i, i + 1);

            if (current.equals(" ") && i != 0 && inputString.charAt(i - 1) != ' ') {
                result.append(". ");
            } else if (!current.equals(" ") && i != 0 && inputString.charAt(i - 1) == ' ') {
                result.append(current.toUpperCase());
            } else {
                result.append(current);
            }
        }

        return result + ".";
    }

    public static String addSparkles(String inputString, int number, int variance) {
        Random r = new Random();

        String sparkle = "✨";

        StringBuilder result = new StringBuilder();

        int count = getRandom(number, variance);

        for (int i = 0; i < count; i++) {
            result.append(sparkle);
        }
        result.append(" ").append(inputString).append(" ");

        count = number;
        if (variance > 0) {
            count += r.nextInt(variance) * (r.nextBoolean() ? -1 : 1);
            if (count < 0) count = 0;
        }

        for (int i = 0; i < count; i++) {
            result.append(sparkle);
        }

        return result.toString();
    }

    public static String addLetters(String inputString, int number, int variance) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < inputString.length(); i++) {
            String letter = inputString.substring(i, i + 1);

            if (!letter.equals(" ")) {
                for (int j = 0; j < getRandom(number, variance); j++) {
                    if (j == 0) output.append(letter);
                    else output.append(letter.toLowerCase());
                }
            } else {
                output.append(letter);
            }
        }

        return output.toString();
    }

    public static String capitals(String inputString, int number, int variance) {
        StringBuilder output = new StringBuilder();

        int idx = 0;
        int counter = 0;
        boolean capital = false;

        while (idx < inputString.length()) {
            if (counter == 0) {
                counter = getRandom(number, variance);
                capital = !capital;
            }

            String letter = inputString.substring(idx, idx + 1);

            output.append(capital ? letter.toUpperCase() : letter.toLowerCase());
            counter--;
            idx++;

        }

        return output.toString();
    }

    public static String uwu(String inputString) {
        String output = inputString;

        output = output.replace("r", "w");
        output = output.replace("R", "W");


        StringBuilder finalOutput = new StringBuilder(output.substring(0, 1));

        for (int i = 1; i < output.length(); i++) {
            String letter = output.substring(i, i + 1);
            String previous = output.substring(i - 1, i);

            if (!isVowel(letter)) {
                finalOutput.append(letter);
                continue;
            }
            if (isVowel(previous)) {
                finalOutput.append(letter);
                continue;
            }
            if (previous.equals(" ")) {
                finalOutput.append(letter);
                continue;
            }
            if (previous.equalsIgnoreCase("w")) {
                finalOutput.append(letter);
                continue;
            }

            finalOutput.append("w");
            finalOutput.append(letter);
        }

        return finalOutput.toString();
    }
}
