public class WordSearcher {

    public static void main(String[] args) {
        if (args.length > 1) {
            String wordLibrary = args[0];
            String puzzle = args[1];

            //String wordLibrary = "TEST,SAMPLE,OUTPUT,NEW";
            //String puzzle = "XXNEWXXTUPTUOXXEXLXPXMXAXSXXTXEXSXXTXX";
            //String wordLibrary = "HELLO,YOINK,LARGE";
            //String puzzle = "TIERJTOIERJOJHELLOHJUYERTOJERJOITERYOINKJTOREOJIGRELARG";

            /*
             * Makes string into an array storing the words to be searched for
             */

            String[] wordArr = wordLibrary.split(","); // no spaces after comments
            String result = "";

            /*
             * Print out a forward printed word
             */

            for (String word : wordArr) {
                if (puzzle.contains(word)) {
                    //System.out.println(word);
                    if (result == "") {
                        if (!result.contains(word)) {
                            result = word;
                        }
                    } else {
                        if (!result.contains(word)) {
                            result = result + "," + word;
                        }
                    }
                }
            }

            /*
             * Print out a reverse printed word
             */

            for (String word : wordArr) {
                String reverse = "";
                for (int i = word.length() - 1; i >= 0; i--) {
                    reverse = reverse + word.charAt(i);
                }
                if (puzzle.contains(reverse)) {
                    //System.out.println(word);
                    if (result == "") {
                        if (!result.contains(word)) {
                            result = word;
                        }
                    } else {
                        if (!result.contains(word)) {
                            result = result + "," + word;
                        }
                    }
                }
            }

            /*
             * Print out a forward dispersed word
             */
            for (String word : wordArr) {
                String fdString = "";
                if (!puzzle.contains(word)) {
                    //for (int j = 0; j < word.length(); ) {
                    int j = 0;
                    for (int i = 0; i < puzzle.length(); i++) {
                        if (word.charAt(j) == puzzle.charAt(i)) {
                            fdString = fdString + puzzle.charAt(i);
                            j++;
                            if (j == word.length()) {
                                i = puzzle.length();
                            }
                        }
                    }
                    //}
                    if (fdString.contains(word)) {
                        //System.out.println(word);
                        if (result == "") {
                            if (!result.contains(word)) {
                                result = word;
                            }
                        } else {
                            if (!result.contains(word)) {
                                result = result + "," + word;
                            }
                        }
                    }
                }
            }

            /*
             * Print out a reverse dispersed word // correct, TEST should not appear twice
             */
            for (String word : wordArr) {
                String rev = "";
                for (int i = word.length() - 1; i >= 0; i--) {
                    rev = rev + word.charAt(i);
                }
                String rdString = "";
                if (!puzzle.contains(rev)) {
                    //for (int j = 0; j < word.length(); ) {
                    int j = 0;
                    for (int i = 0; i < puzzle.length(); i++) {
                        if (rev.charAt(j) == puzzle.charAt(i)) {
                            rdString = rdString + puzzle.charAt(i);
                            j++;
                            if (j == rev.length()) {
                                i = puzzle.length();
                            }
                        }
                    }
                    //}
                    if (rdString.contains(rev)) {
                        //System.out.println(word);
                        if (result == "") {
                            if (!result.contains(word)) {
                                result = word;
                            }
                        } else {
                            if (!result.contains(word)) {
                                result = result + "," + word;
                            }
                        }
                    }
                }
            }

            // loops end here

            String[] resultArr = result.split(","); // no spaces after comments
            for (int k = 0; k < resultArr.length; k++) {
                System.out.println(resultArr[k]);
            }

        }
    }
}