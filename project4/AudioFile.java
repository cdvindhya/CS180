import java.io.*;

/**
 * Project 4
 *
 * @author Vindhya Banda, bandav
 * @version November 13, 2019
 */

public class AudioFile extends CommonFile {

    private int songLength;

    public AudioFile(File audio, int songLengthInSeconds) throws InvalidAudioFileException {
        super(true, true, "iTunes");
        super.setFile(audio);
        super.setFileSizeInBytes(audio.length());
        if (songLengthInSeconds > 0) {
            this.songLength = songLengthInSeconds;
        } else {
            throw new InvalidAudioFileException();
        }
    }

    public String getFileType() {
        return "Audio";
    }

    public String getSongLength() {
        int minutes = songLength / 60;
        int secondsLeft = songLength - minutes * 60;
        return minutes + " minutes and " + secondsLeft + " seconds";
    }

    @Override
    public String toString() {
        return "AudioFile[" + getFileType()
                + ", " + getFileSizeInBytes()
                + ", " + getFile().getName()
                + ", " + getSongLength() + "]";
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
        if (o instanceof AudioFile
                && ((AudioFile) o).getOpenable() == this.getOpenable()
                && ((AudioFile) o).getPlayable() == this.getPlayable()
                && ((AudioFile) o).getSongLength().equals(this.getSongLength())
                && ((AudioFile) o).getFileSizeInBytes() == this.getFileSizeInBytes()
                && ((AudioFile) o).getOpensWith().equals(this.getOpensWith())
                && ((AudioFile) o).getFile() == this.getFile()) {
            eql = true;
        }

        return eql;

    }
}
