package com.calvin.unicodefonts;

import java.sql.PreparedStatement;
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

    public static String applyFont(String inputString, String fontData) throws IndexOutOfBoundsException {
        int countPerCharacter = fontData.length() / 52;

        if (countPerCharacter == 0) {
            throw new IndexOutOfBoundsException("font data at zero");
        }

        String result = "";

        for (int i = 0; i < inputString.length(); i++) {
            int letterIdx = LETTERS.indexOf(inputString.substring(i, i + 1));

            if (letterIdx == -1) {
                result += inputString.substring(i, i + 1);
            } else {
                String convertedLetter = fontData.substring(letterIdx * countPerCharacter, (letterIdx + 1) * countPerCharacter);

                result += convertedLetter;
            }
        }

        return result;
    }


    public static String scriptAlternate(String inputString, String superscript, String subscript) {
        int mode = 0;
        String result = "";

        for (int i = 0; i < inputString.length(); i++) {
            String currentLetter = inputString.substring(i, i + 1);

            if (mode == 0) result += applyFont(currentLetter, subscript);
            else if (mode == 2) result += applyFont(currentLetter, superscript);
            else result += currentLetter;

            mode++;
            if (mode > 3) mode = 0;
        }

        return result;
    }


    public static String addSpaces(String inputString, int number, int variance) {
        String result = "";
        Random r = new Random();

        for (int i = 0; i < inputString.length(); i++) {
            result += inputString.substring(i, i + 1);

            int spaceCount = getRandom(number, variance);

            for (int j = 0; j < spaceCount; j++) {
                result += " ";
            }
        }

        return result;
    }

    public static String addPeriods(String inputString) {
        String result = "";

        for (int i = 0; i < inputString.length(); i++) {
            String current = inputString.substring(i, i + 1);

            if (current.equals(" ") && i != 0 && !inputString.substring(i - 1, i).equals(" ")) {
                result += ". ";
            } else if (!current.equals(" ") && i != 0 && inputString.substring(i - 1, i).equals(" ")) {
                result += current.toUpperCase();
            } else {
                result += current;
            }
        }

        return result + ".";
    }

    public static String addSparkles(String inputString, int number, int variance) {
        Random r = new Random();

        String sparkle = "✨";

        String result = "";

        int count = getRandom(number, variance);

        for (int i = 0; i < count; i++) {
            result += sparkle;
        }
        result += " " + inputString + " ";

        count = number;
        if (variance > 0) {
            count += r.nextInt(variance) * (r.nextBoolean() ? -1 : 1);
            if (count < 0) count = 0;
        }

        for (int i = 0; i < count; i++) {
            result += sparkle;
        }

        return result;
    }
}
