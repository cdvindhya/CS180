import java.io.*;

/**
 * Project 4
 *
 * @author Vindhya Banda, bandav
 * @version November 13, 2019
 */

public class UnknownFile extends CommonFile {

    public UnknownFile(File unknownFile) throws InvalidFileException {
        super(false, false, "Unknown Program");
        super.setFile(unknownFile);
        super.setFileSizeInBytes(unknownFile.length());
        if (!(unknownFile.getName().lastIndexOf(".") == unknownFile.getName().indexOf("."))) {
            throw new InvalidFileException();
        }
    }

    public String getFileType() {
        return "Unknown";
    }

    @Override
    public String toString() {
        return "UnknownFile[" + this.getFileType()
                + ", " + this.getFileSizeInBytes()
                + ", " + this.getFile().getName() + "]";
    }

    public boolean equals(Object o) {
        boolean b = false;
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            b = false;
        }
        if (o == this) {
            b = true;
        }
        if (o instanceof UnknownFile
                && this.getOpenable() == ((UnknownFile) o).getOpenable()
                && this.getPlayable() == ((UnknownFile) o).getPlayable()
                && this.getFileSizeInBytes() == ((UnknownFile) o).getFileSizeInBytes()
                && this.getOpensWith().equals(((UnknownFile) o).getOpensWith())
                && this.getFile() == ((UnknownFile) o).getFile()) {
            b = true;
        }
        return b;
    }

}
