package com.calvin.unicodefonts;

public class Font {
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String applyFont(String inputString, String fontData) throws IndexOutOfBoundsException {
        int countPerCharacter = fontData.length() / 52;

        if (countPerCharacter == 0) { throw new IndexOutOfBoundsException("font data at zero"); }

        String result = "";

        for (int i = 0; i < inputString.length(); i++) {
          int letterIdx = LETTERS.indexOf(inputString.substring(i, i+1));

          if (letterIdx == -1 ) {
              result += inputString.substring(i, i+1);
          }
          else {
              String convertedLetter = fontData.substring(letterIdx*countPerCharacter, (letterIdx+1)*countPerCharacter);

              result += convertedLetter;
          }
        }

        return result;
    }


}
