

    /**
     * CS5001 - Object Oriented Modelling, Design and Programming A Precital 1 -
     * Aligning Text
     * @param args Here is paramater....... -----------------------------CHANGE --------------------
     * @author Student id number: 200028225
     */

    import java.util.*;
    public class AlignText {

        public static void addSpaces(StringBuffer text , int leftover, int maximumLineLength){
            if(leftover > 0 && leftover < maximumLineLength ){
                String emptySpace = " " ;
                text.append(emptySpace.repeat(leftover));
            }
        }

    //add space will add the spac anad below on will have different aligment type and do its task : withh be
        public static String leftAlignment(String input, int maximumLineLength, String alignment) {
            String[] tokens = input.split("\\s+");
            StringBuffer result = new StringBuffer(input.length());
            //Total words length per line
            int lineCapacity = 0;
            for(int i = 0 ; i < tokens.length ; i++){
                String eachWord = tokens[i] + ' ';
                int eachWordLength = eachWord.length();
                int leftover = 0;
                if( ( lineCapacity + eachWordLength ) > maximumLineLength){
                    leftover = maximumLineLength - lineCapacity;

                    addSpaces(result, leftover, maximumLineLength);

                    result.append("\n");

                    System.out.println("Leftover: "+leftover);
                    //reseting the line capacity to 0. in order to start from next line
                    lineCapacity = 0;
                    leftover = 0;
                }
                result.append(eachWord);
                lineCapacity += eachWord.length();
            }
            return result.toString();
        }


            public static String rightAlignment(String input, int maximumLineLength, String alignment) {
                String[] tokens = input.split("\\s+");
                StringBuffer result = new StringBuffer(input.length());
                //Total words length per line
                int lineCapacity = 0;
                ArrayList<String> wordsPerLine = new ArrayList<String>(200);

                for(int i = 0 ; i < tokens.length ; i++){
                    String eachWord = tokens[i];
                    int eachWordLength = eachWord.length();

                    if ((lineCapacity + wordsPerLine.size() - 1 + eachWordLength) < maximumLineLength) {
                        wordsPerLine.add(eachWord);
                        lineCapacity += eachWordLength; //6 + wordsPerLine.length() - 1
                    }
                    else{
                        addSpaces(result, (maximumLineLength - (lineCapacity + wordsPerLine.size() - 1) ),maximumLineLength);
                        for(int j = 0 ; j < wordsPerLine.size() ; j++){
                            result.append(wordsPerLine.get(j));
                            if(wordsPerLine.size() - 1 == j){
                                result.append('\n');
                            }
                            else{
                                result.append(" ");
                            }
                        }
                        lineCapacity = eachWordLength;
                        wordsPerLine.clear();
                        wordsPerLine.add(eachWord);
                     }
                }

         addSpaces(result, (maximumLineLength - (lineCapacity + wordsPerLine.size() - 1) ),maximumLineLength);

                        for(int j = 0 ; j < wordsPerLine.size() ; j++){
                            result.append(wordsPerLine.get(j));
                            if(wordsPerLine.size() - 1 != j){

                                result.append(" ");
                            }
                        }

                return result.toString();
            }

        public static void main(String[] args) {
            try{
                int setLineLength = Integer.parseInt(args[1]);
                String[] paragraphs = FileUtil.readFile(args[0]);
                String alignment = "L";
                if(args.length == 3) { alignment = args[2]; }

                if(args.length > 1 ){
                     for(int i = 0; i < paragraphs.length ; i++){
                          System.out.println(rightAlignment(paragraphs[i], setLineLength, alignment));
                     }
                }
                else{
                    System.out.println("usage: java AlignText file_name linelength");
                }
            }
            catch(Exception e ){
                System.out.print("Error: " + e);
            }
            finally{
                System.exit(0);
            }
        }
    }
