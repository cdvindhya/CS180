import java.io.*;

/**
 * Project 4
 *
 * @author Vindhya Banda, bandav
 * @version November 13, 2019
 */

public abstract class CommonFile {
    private boolean canOpen;
    private boolean canPlay;
    private File file;
    private long fileSizeInBytes;
    private String programToOpenIn;

    public CommonFile() {
        canOpen = false;
        canPlay = false;
        programToOpenIn = null;
        //fileSizeInBytes = this.getFile().length();
    }

    public CommonFile(boolean canOpen, boolean canPlay, String programToOpenIn) {
        this.canOpen = canOpen;
        this.canPlay = canPlay;
        this.programToOpenIn = programToOpenIn;
        //fileSizeInBytes = this.getFile().length();
    }

    public boolean getOpenable() {
        return canOpen;
    }

    public boolean getPlayable() {
        return canPlay;
    }

    public String getOpensWith() {
        return programToOpenIn;
    }

    public void setCanOpen(boolean open) {
        this.canOpen = open;
    }

    public void setCanPlay(boolean play) {
        this.canPlay = play;
    }

    public void setProgramToOpenIn(String program) {
        this.programToOpenIn = program;
    }

    public void setFileSizeInBytes(long fileSizeInBytes) {
        this.fileSizeInBytes = fileSizeInBytes;
    }

    public String getExtension() {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex >= 0) {
            //return fileName.substring(dotIndex + 1);
            return fileName.substring(dotIndex);
        } else {
            return "";
        }
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public long getFileSizeInBytes() {
        return fileSizeInBytes;
    }

    public abstract String getFileType();

    @Override
    public String toString() {
        return "CommonFile[" + getFileSizeInBytes() + ", " + getExtension() + "]";
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
        if (o instanceof CommonFile
                && ((CommonFile) o).getOpenable() == this.getOpenable()
                && ((CommonFile) o).getPlayable() == this.getPlayable()
                && ((CommonFile) o).getOpensWith().equals(this.getOpensWith())) {
            eql = true;
        }
        return eql;
    }

}
