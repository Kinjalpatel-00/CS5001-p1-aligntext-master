/**
* CS5001 - Object Oriented Modelling, Design and Programming A Precital 1 -
* AlignText programe implements a application which does left and right alignments
* for given text file. Output will be displayed on console.
* @param args[0] - Text file name and location
* @param args[1] - The length of the line for wrapping the text
* @param args[2] - Alignment type (optional)
* @author Student id number: 200028225
*/

import java.util.ArrayList;

public class AlignText {
    public static void addSpaces(StringBuffer text, int leftover, int maximumLineLength) {
        if (leftover > 0 && leftover < maximumLineLength) {
            String emptySpace = " ";
            text.append(emptySpace.repeat(leftover));
        }
    }

    public static StringBuffer addSpacesToAlignmentType(StringBuffer result, int maximumLineLength, String alignmentType, int lineCapacity, ArrayList<String> wordsPerLine) {
        if (alignmentType.equals("L")) {
             addSpaces(result, 0, maximumLineLength);
        }
         else if (alignmentType.equals("R")) {
              addSpaces(result, (maximumLineLength - (lineCapacity + wordsPerLine.size() - 1)), maximumLineLength);
         }
         else if (alignmentType.equals("C")) {
              System.out.println("Have not been implemented the center alignment!");
         }
         else if (alignmentType.equals("B")) {
              System.out.println("Have not been implemented the speech bubble alignment!");
         }
         else {
              System.out.println("usage: java AlignText file_name line_length [align_mode]");
         }
         return result;
    }

    public static String alignment(String input, int maximumLineLength, String alignmentType) {
         String[] tokens = input.split("\\s+");
         StringBuffer result = new StringBuffer(input.length());
                //Total words length per line
         int lineCapacity = 0;
         ArrayList<String> wordsPerLine = new ArrayList<String>(200);

         for (int i = 0; i < tokens.length; i++) {
              String eachWord = tokens[i];
              int eachWordLength = eachWord.length();
              if ((lineCapacity + wordsPerLine.size() - 1 + eachWordLength) < maximumLineLength) {
                  wordsPerLine.add(eachWord);
                  lineCapacity += eachWordLength; //6 + wordsPerLine.length() - 1
              }
              else {
                  addSpacesToAlignmentType(result, maximumLineLength, alignmentType, lineCapacity, wordsPerLine);
                  for (int j = 0; j < wordsPerLine.size(); j++) {
                      result.append(wordsPerLine.get(j));
                          if (wordsPerLine.size() - 1 == j) {
                              result.append('\n');
                          }
                          else {
                              result.append(" ");
                          }
                  }
                  lineCapacity = eachWordLength;
                  wordsPerLine.clear();
                  wordsPerLine.add(eachWord);
                  }
              }
              addSpacesToAlignmentType(result, maximumLineLength, alignmentType, lineCapacity, wordsPerLine);
              for (int j = 0; j < wordsPerLine.size(); j++) {
                  result.append(wordsPerLine.get(j));
                  if (wordsPerLine.size() - 1 != j) {
                      result.append(" ");
                  }
              }
              return result.toString();
         }

    public static void main(String[] args) {
         try {
              if (args.length < 2 || Integer.parseInt(args[1]) < 0) {
                   System.out.println("usage: java AlignText file_name line_length [align_mode]");
              }
              else {
                   int setLineLength = Integer.parseInt(args[1]);
                   String[] paragraphs = FileUtil.readFile(args[0]);
                   String alignment = "L";
                   if (args.length == 3) {
                        alignment = args[2];
                   }
                   for (int i = 0; i < paragraphs.length; i++) {
                        System.out.println(alignment(paragraphs[i], setLineLength, alignment));
                   }
              }
         }
         catch (Exception e) {
              System.out.print("usage: java AlignText file_name line_length [align_mode]");
         }
         finally {
              System.exit(0);
         }
    }
}
