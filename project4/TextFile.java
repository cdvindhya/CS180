import java.io.*;

/**
 * Project 4
 *
 * @author Vindhya Banda, bandav
 * @version November 13, 2019
 */

public class TextFile extends CommonFile {

    private long numberOfLines;

    public TextFile(File text) throws IOException, InvalidTextFileException {
        super(true, false, "TextEdit");
        super.setFile(text);
        super.setFileSizeInBytes(text.length());
        FileReader fr = new FileReader(text);
        BufferedReader br = new LineNumberReader(fr);
        long noOfLines = 0;
        while (br.readLine() != null) {
            noOfLines = noOfLines + 1;
        }
        br.close();
        if (noOfLines > 0) {
            this.numberOfLines = noOfLines;
        } else {
            throw new InvalidTextFileException();
        }
    }

    public String getFileType() {
        return "Text";
    }

    public long getLines() {
        return numberOfLines;
    }

    public long getOccurrences(String charSequence) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(this.getFile());
        BufferedReader br = new BufferedReader(fr);
        String line;
        long count = 0;
        String[] arr;
        while ((line = br.readLine()) != null) {
            arr = line.split(" ");
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].equals(charSequence)
                        || arr[i].equals(charSequence + ".")
                        || arr[i].equals(charSequence + ",")
                        || arr[i].equals(charSequence + "!")) {
                    count = count + 1;
                }
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return "TextFile[" + this.getFileType() + ", "
                + this.getFileSizeInBytes() + ", "
                + this.getFile().getName() + ", "
                + this.getLines() + "]";
    }

    public boolean equals(Object o) {
        boolean eql = false;
        if (o == null) {
            return false;
        }
        if (this == o) {
            eql = true;
        }
        if (this.getClass() == o.getClass()) {
            eql = true;
        }
        if (o instanceof TextFile
                && ((TextFile) o).getOpenable() == this.getOpenable()
                && ((TextFile) o).getPlayable() == this.getPlayable()
                && ((TextFile) o).getLines() == this.getLines()
                && ((TextFile) o).getFileSizeInBytes() == this.getFileSizeInBytes()
                && ((TextFile) o).getOpensWith().equals(this.getOpensWith())
                && ((TextFile) o).getFile() == this.getFile()) {
            eql = true;
        }
        return eql;
    }
}
