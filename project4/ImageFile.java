import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Project 4
 *
 * @author Vindhya Banda, bandav
 * @version November 13, 2019
 */

public class ImageFile extends CommonFile {

    private int imageHeight;
    private int imageWidth;
    private boolean isAnimated;

    public ImageFile(File image) throws IOException, InvalidImageFileException {
        super(true, false, "Preview");
        super.setFile(image);
        super.setFileSizeInBytes(image.length());
        BufferedImage bufferedImage = ImageIO.read(image);
        if (bufferedImage.getWidth() > 0 && bufferedImage.getHeight() > 0) {
            this.imageWidth = bufferedImage.getWidth();
            this.imageHeight = bufferedImage.getHeight();
        } else {
            throw new InvalidImageFileException();
        }
        String imageName = image.getName();
        String extension = "";
        int dotIndex = imageName.lastIndexOf(".");
        if (dotIndex >= 0) {
            extension = imageName.substring(dotIndex + 1);
        }
        if (extension.equals("gif")) {
            isAnimated = true;
        }
    }

    public String getFileType() {
        return "Image";
    }

    public int getImageHeight() {
        return this.imageHeight;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public boolean animated() {
        return (this.getExtension().equals("gif"));
    }

    @Override
    public String toString() {
        return "ImageFile[" + getFileType()
                + ", " + getFileSizeInBytes()
                + ", " + this.getFile().getName()
                + ", " + (this.imageHeight + "x" + this.imageWidth)
                + ", " + this.animated() + "]";
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
        if (o instanceof ImageFile
                && ((ImageFile) o).getOpenable() == this.getOpenable()
                && ((ImageFile) o).getPlayable() == this.getPlayable()
                && ((ImageFile) o).animated() == this.animated()
                && ((ImageFile) o).getFileSizeInBytes() == this.getFileSizeInBytes()
                && ((ImageFile) o).imageHeight == this.imageHeight
                && ((ImageFile) o).imageWidth == this.imageWidth
                && ((ImageFile) o).getOpensWith().equals(this.getOpensWith())
                && ((ImageFile) o).getFile() == this.getFile()) {
            eql = true;
        }
        return eql;
    }

}
