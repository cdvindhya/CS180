import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Project 4
 *
 * @author Vindhya Banda, bandav
 * @version November 13, 2019
 */
public class FileManipulator {

    private static Scanner scanner;
    private static CommonFile cf;
    private static final ArrayList<String> VIDEO_PROPERTY_CHOICES = new ArrayList<>(Arrays.asList("" +
            "Openable", "Playable", "Opens With", "File Size", "Extension", "File Type", "Video Length"));
    private static final ArrayList<String> IMAGE_PROPERTY_CHOICES = new ArrayList<>(Arrays.asList("" +
                    "Openable", "Playable", "Opens With", "File Size", "Extension", "File Type", "Image Height",
            "Image Width", "Animated"));
    private static final ArrayList<String> TEXT_PROPERTY_CHOICES = new ArrayList<>(Arrays.asList("" +
                    "Openable", "Playable", "Opens With", "File Size", "Extension", "File Type", "Line Count",
            "Occurrences"));
    private static final ArrayList<String> AUDIO_PROPERTY_CHOICES = new ArrayList<>(Arrays.asList("" +
            "Openable", "Playable", "Opens With", "File Size", "Extension", "File Type", "Song Length"));
    private static final ArrayList<String> UNKNOWN_PROPERTY_CHOICES = new ArrayList<>(Arrays.asList("" +
            "Openable", "Playable", "Opens With", "File Size", "Extension", "File Type"));

    public static void main(String[] args) throws IOException {

        String fileName;
        long videoLength;
        int songLength;
        scanner = new Scanner(System.in);
        System.out.println("\nWhat is the name of the file you would like to obtain details of?\n" +
                "Make sure you include the getExtension of the filename.");
        fileName = scanner.nextLine();
        File file = new File("Files/" + fileName);
        if (file.exists()) {
            if (fileName.contains(".mp4")) {

                System.out.println("What is the length, in seconds, of the video file?");
                videoLength = scanner.nextLong();
                scanner.nextLine();
                try {
                    cf = new VideoFile(new File("Files/" + fileName), videoLength);
                    videoChoice();
                } catch (InvalidVideoFileException e) {
                    e.printStackTrace();
                }

            } else if (fileName.contains(".png") || fileName.contains(".gif")) {
                try {
                    cf = new ImageFile(new File("Files/" + fileName));
                    imageChoice();
                } catch (InvalidImageFileException e) {
                    e.printStackTrace();
                }

            } else if (fileName.contains(".txt") || fileName.contains(".rtf")) {
                try {
                    cf = new TextFile(new File("Files/" + fileName));
                    textChoice();
                } catch (InvalidTextFileException e) {
                    e.printStackTrace();
                }
            } else if (fileName.contains(".mp3") || fileName.contains(".flac") | fileName.contains(".wav")) {
                System.out.println("What is the length, in seconds, of the audio file?");
                songLength = scanner.nextInt();
                scanner.nextLine();
                try {
                    cf = new AudioFile(new File("Files/" + fileName), songLength);
                    audioChoice();
                } catch (InvalidAudioFileException e) {
                    e.printStackTrace();
                }

            } else {
                if (fileName.contains(".")) {
                    System.out.println("Because the file format is not recognized, " +
                            "file property retrieval will be limited.");
                    try {
                        cf = new UnknownFile(new File("Files/" + fileName));
                        unknownChoice();
                    } catch (InvalidFileException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("The file does not exist!");
        }
    }

    private static void printArrayListStrings(ArrayList<String> list) {
        System.out.println();
        for (String s : list) {
            System.out.println(s);
        }
    }

    private static void videoChoice() {
        String choice;
        if (cf instanceof VideoFile) {
            System.out.println("Choose a choice from the list below.");
            printArrayListStrings(VIDEO_PROPERTY_CHOICES);
            choice = scanner.nextLine();
            if (choice.equals(VIDEO_PROPERTY_CHOICES.get(0))) {
                if (cf.getOpenable()) {
                    System.out.println("The file is openable.");
                } else {
                    System.out.println("The file is not openable.");
                }
            } else if (choice.equals(VIDEO_PROPERTY_CHOICES.get(1))) {
                if (cf.getPlayable()) {
                    System.out.println("The file is playable.");
                } else {
                    System.out.println("The file is not playable.");
                }
            } else if (choice.equals(VIDEO_PROPERTY_CHOICES.get(2))) {
                System.out.println("The file opens with the program " + cf.getOpensWith() + ".");
            } else if (choice.equals(VIDEO_PROPERTY_CHOICES.get(3))) {
                System.out.println("The file is made up of " + cf.getFileSizeInBytes() + " bytes.");
            } else if (choice.equals(VIDEO_PROPERTY_CHOICES.get(4))) {
                System.out.println("The file has an extension of " + cf.getExtension() + ".");
            } else if (choice.equals(VIDEO_PROPERTY_CHOICES.get(5))) {
                System.out.println("The file is a " + cf.getFileType() + " file.");
            } else if (choice.equals(VIDEO_PROPERTY_CHOICES.get(6))) {
                System.out.println("The file is " + ((VideoFile) cf).getVideoLength() + " long.");
            } else {
                System.out.println("Invalid Command. Try again.");
            }
        }
    }

    private static void imageChoice() {
        String choice;
        if (cf instanceof ImageFile) {
            System.out.println("Choose a choice from the list below.");
            printArrayListStrings(IMAGE_PROPERTY_CHOICES);
            choice = scanner.nextLine();
            if (choice.equals(IMAGE_PROPERTY_CHOICES.get(0))) {
                if (cf.getOpenable()) {
                    System.out.println("The file is openable.");
                } else {
                    System.out.println("The file is not openable.");
                }
            } else if (choice.equals(IMAGE_PROPERTY_CHOICES.get(1))) {
                if (cf.getPlayable()) {
                    System.out.println("The file is playable.");
                } else {
                    System.out.println("The file is not playable.");
                }
            } else if (choice.equals(IMAGE_PROPERTY_CHOICES.get(2))) {
                System.out.println("The file opens with the program " + cf.getOpensWith() + ".");
            } else if (choice.equals(IMAGE_PROPERTY_CHOICES.get(3))) {
                System.out.println("The file is made up of " + cf.getFileSizeInBytes() + " bytes.");
            } else if (choice.equals(IMAGE_PROPERTY_CHOICES.get(4))) {
                System.out.println("The file has an extension of " + cf.getExtension() + ".");
            } else if (choice.equals(IMAGE_PROPERTY_CHOICES.get(5))) {
                System.out.println("The file is a " + cf.getFileType() + " file.");
            } else if (choice.equals(IMAGE_PROPERTY_CHOICES.get(6))) {
                System.out.println("The file is " + ((ImageFile) cf).getImageHeight() + " pixels tall.");
            } else if (choice.equals(IMAGE_PROPERTY_CHOICES.get(7))) {
                System.out.println("The file is " + ((ImageFile) cf).getImageWidth() + " pixels wide.");
            } else if (choice.equals(IMAGE_PROPERTY_CHOICES.get(8))) {
                if (((ImageFile) cf).animated()) {
                    System.out.println("The file is animated.");
                } else {
                    System.out.println("The file is not animated.");
                }
            } else {
                System.out.println("Invalid Command. Try again.");
            }
        }
    }

    private static void textChoice() throws IOException {
        String choice;
        if (cf instanceof TextFile) {
            System.out.print("Choose a choice from the list below.");
            printArrayListStrings(TEXT_PROPERTY_CHOICES);
            choice = scanner.nextLine();
            if (choice.equals(TEXT_PROPERTY_CHOICES.get(0))) {
                if (cf.getOpenable()) {
                    System.out.println("The file is openable.");
                } else {
                    System.out.println("The file is not openable.");
                }
            } else if (choice.equals(TEXT_PROPERTY_CHOICES.get(1))) {
                if (cf.getPlayable()) {
                    System.out.println("The file is playable.");
                } else {
                    System.out.println("The file is not playable.");
                }
            } else if (choice.equals(TEXT_PROPERTY_CHOICES.get(2))) {
                System.out.println("The file opens with the program " + cf.getOpensWith() + ".");
            } else if (choice.equals(TEXT_PROPERTY_CHOICES.get(3))) {
                System.out.println("The file is made up of " + cf.getFileSizeInBytes() + " bytes.");
            } else if (choice.equals(TEXT_PROPERTY_CHOICES.get(4))) {
                System.out.println("The file has an extension of " + cf.getExtension() + ".");
            } else if (choice.equals(TEXT_PROPERTY_CHOICES.get(5))) {
                System.out.println("The file is a " + cf.getFileType() + " file.");
            } else if (choice.equals(TEXT_PROPERTY_CHOICES.get(6))) {
                System.out.println("The file has " + ((TextFile) cf).getLines() + " lines in it.");
            } else if (choice.equals(TEXT_PROPERTY_CHOICES.get(7))) {
                System.out.println("What word would you like to search for in the file?");
                String toSearch = scanner.nextLine();
                System.out.println("The file has " + ((TextFile) cf).getOccurrences(toSearch) + " occurrences " +
                        "of the word " + toSearch + ".");
            } else {
                System.out.println("Invalid Command. Try again.");
            }
        }
    }

    private static void audioChoice() {
        String choice;
        if (cf instanceof AudioFile) {
            System.out.println("Choose a choice from the list below.");
            printArrayListStrings(AUDIO_PROPERTY_CHOICES);
            choice = scanner.nextLine();
            if (choice.equals(AUDIO_PROPERTY_CHOICES.get(0))) {
                if (cf.getOpenable()) {
                    System.out.println("The file is openable.");
                } else {
                    System.out.println("The file is not openable.");
                }
            } else if (choice.equals(AUDIO_PROPERTY_CHOICES.get(1))) {
                if (cf.getPlayable()) {
                    System.out.println("The file is playable.");
                } else {
                    System.out.println("The file is not playable.");
                }
            } else if (choice.equals(AUDIO_PROPERTY_CHOICES.get(2))) {
                System.out.println("The file opens with the program " + cf.getOpensWith() + ".");
            } else if (choice.equals(AUDIO_PROPERTY_CHOICES.get(3))) {
                System.out.println("The file is made up of " + cf.getFileSizeInBytes() + " bytes.");
            } else if (choice.equals(AUDIO_PROPERTY_CHOICES.get(4))) {
                System.out.println("The file has an extension of " + cf.getExtension() + ".");
            } else if (choice.equals(AUDIO_PROPERTY_CHOICES.get(5))) {
                System.out.println("The file is a " + cf.getFileType() + " file.");
            } else if (choice.equals(AUDIO_PROPERTY_CHOICES.get(6))) {
                System.out.println("The file is " + ((AudioFile) cf).getSongLength() + " long.");
            } else {
                System.out.println("Invalid Command. Try again.");
            }
        }
    }

    private static void unknownChoice() {
        String choice;
        if (cf instanceof UnknownFile) {
            System.out.println("\nChoose a choice from the list below.");
            printArrayListStrings(UNKNOWN_PROPERTY_CHOICES);
            choice = scanner.nextLine();
            if (choice.equals(UNKNOWN_PROPERTY_CHOICES.get(0))) {
                if (cf.getOpenable()) {
                    System.out.println("The file is openable.");
                } else {
                    System.out.println("The file is not openable.");
                }
            } else if (choice.equals(UNKNOWN_PROPERTY_CHOICES.get(1))) {
                if (cf.getPlayable()) {
                    System.out.println("The file is playable.");
                } else {
                    System.out.println("The file is not playable.");
                }
            } else if (choice.equals(UNKNOWN_PROPERTY_CHOICES.get(2))) {
                System.out.println("The file opens with the program " + cf.getOpensWith() + ".");
            } else if (choice.equals(UNKNOWN_PROPERTY_CHOICES.get(3))) {
                System.out.println("The file is made up of " + cf.getFileSizeInBytes() + " bytes.");
            } else if (choice.equals(UNKNOWN_PROPERTY_CHOICES.get(4))) {
                System.out.println("The file has an extension of " + cf.getExtension() + ".");
            } else if (choice.equals(UNKNOWN_PROPERTY_CHOICES.get(5))) {
                System.out.println("The file is a " + cf.getFileType() + " file.");
            } else {
                System.out.println("Invalid Command. Try again.");
            }
        }
    }
}