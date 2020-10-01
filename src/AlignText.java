/* import Statements*/
import java.util.ArrayList;

/**
* Course: CS5001 - (Object Oriented Modelling, Design and Programming) Practical 1 - Aligntext
* AlignText programme implements a application which does left and right alignments
* for given text file. Output will be displayed on console.
* @author Student id number: 200028225
*/

public class AlignText {
    /**
     * This method adds the 'leftover' number of spaces to the text(per line)
     * @param text - The StringBuffer for the words to store the final output.
     * @param leftover - The number of free character space in each line after adding words
     * @param maximumLineLength - The desired length of the line for wrapping the text
     */
    public static void addSpaces(StringBuffer text, int leftover, int maximumLineLength) {
        if (leftover > 0 && leftover < maximumLineLength) {
            String emptySpace = " ";
            text.append(emptySpace.repeat(leftover));
        }
    }

    /**
     * This method calls the addSpaces() method based on different alignment types
     * @param result - the StringBuffer to store the final output
     * @param maximumLineLength - The desired length of the line for wrapping the text
     * @param alignmentType - The alignment type (given by user as command line argument) as string
     * @param lineCapacity - The capacity of the line to stores the words
     * @param wordsPerLine -  Total words per line stored in ArrayList
     * @return - returns the aligned rext
     */
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

    /**
     * This method does the calculation based on provided input and returns the alignmed text as a string
     * @param input - The each sentance from the user input text file
     * @param maximumLineLength - The desired length of the line for wrapping the text
     * @param alignmentType - The type of aligment as string
     * @return - retuns the output (given by user as command line argument) as string
     */
    public static String alignment(String input, int maximumLineLength, String alignmentType) {
         String[] tokens = input.split("\\s+");
         StringBuffer result = new StringBuffer(input.length()); // To store the final output
         int lineCapacity = 0;     //Total words length per line
         final int arrayLength = 200;
         ArrayList<String> wordsPerLine = new ArrayList<String>(arrayLength);  // To store the words per line

         for (int i = 0; i < tokens.length; i++) {
              String eachWord = tokens[i];
              int eachWordLength = eachWord.length();

              // condition to adds the maximum words to wordsPerLine(ArrayList) which can be stored in each line
              if ((lineCapacity + wordsPerLine.size() - 1 + eachWordLength) < maximumLineLength) {
                  wordsPerLine.add(eachWord);
                  lineCapacity += eachWordLength;
              }
              else {
                  // adds spaces per line
                  addSpacesToAlignmentType(result, maximumLineLength, alignmentType, lineCapacity, wordsPerLine);

                  // add words to the result from wordsPerLine ArrayList
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

    /**
     * This is the main method of the program
     * This method takes the input from command line
     * Calls the alignment() method and prints the results to the console
     * @param args - The list of all command line agruments provided by the user
     * @param args[0] - Text file name and location
     * @param args[1] - The length of the line for wrapping the text
     * @param args[2] - Alignment type (optional)
     * @return Nothing
     */
    public static void main(String[] args) {
         final int argsLength = 3;
         try {
              if (args.length < 2 || Integer.parseInt(args[1]) < 0) {
                   System.out.println("usage: java AlignText file_name line_length [align_mode]");
              }
              else {
                   int setLineLength = Integer.parseInt(args[1]);
                   String[] paragraphs = FileUtil.readFile(args[0]);

                   //setting the default alignment
                   String alignment = "L";
                   if (args.length == argsLength) {
                        alignment = args[2];
                   }

                   // loop to go throught the each sentance from textfile and prints the results to the console
                   for (int i = 0; i < paragraphs.length; i++) {
                        System.out.println(alignment(paragraphs[i], setLineLength, alignment));
                   }
              }
         }
         catch (Exception e) {
              System.out.print("usage: java AlignText file_name line_length [align_mode]");
         }
         finally {
             //exit the program
              System.exit(0);
         }
    }
}
