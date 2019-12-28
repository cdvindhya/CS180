import java.io.*;

/**
 * Project 4
 *
 * @author Vindhya Banda, bandav
 * @version November 13, 2019
 */

public class VideoFile extends CommonFile {

    private long videoLengthInSeconds;

    public VideoFile(File video, long videoLength) throws InvalidVideoFileException {
        super(true, true, "Quicktime Player");
        if (videoLength > 0) {
            this.videoLengthInSeconds = videoLength;
            super.setFile(video);
            super.setFileSizeInBytes(video.length());
        } else {
            throw new InvalidVideoFileException();
        }
    }

    public String getFileType() {
        return "Video";
    }

    public String getVideoLength() {
        long minutes = videoLengthInSeconds / 60;
        long secondsLeft = videoLengthInSeconds - minutes * 60;
        return minutes + " minutes and " + secondsLeft + " seconds";
    }

    @Override
    public String toString() {
        return "VideoFile[" + this.getFileType()
                + ", " + this.getFileSizeInBytes()
                + ", " + this.getFile().getName()
                + ", " + this.getVideoLength() + "]";
    }

    public boolean equals(Object o) {
        boolean a = false;
        if (o == null) {
            return false;
        }
        if (this == o) {
            a = true;
        }
        if (this.getClass() == o.getClass()) {
            a = true;
        }
        if (this.getOpenable() == ((VideoFile) o).getOpenable()
                && this.getPlayable() == ((VideoFile) o).getPlayable()
                && this.getVideoLength().equals(((VideoFile) o).getVideoLength())
                && this.getFileSizeInBytes() == ((VideoFile) o).getFileSizeInBytes()
                && this.getOpensWith().equals(((VideoFile) o).getOpensWith())
                && this.getFile() == ((VideoFile) o).getFile()) {
            a = true;
        }

        return a;

    }

}
