package com.codenoted.uploader.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.io.FilenameUtils;

/**
 * Image object
 * 
 * @author andreas
 * 
 */
@XmlRootElement(name = "image")
@XmlType(propOrder = { "id", "filename", "alt", "caption", "altCaptionEqualToFilename" })
public class Image {

  private String id;
  private String filename;
  private String alt;
  private String caption;
  private boolean altCaptionEqualToFilename;

  public Image() {

  }

  public Image(ImageBuilder builder) {
    this.id = builder.id;
    this.filename = builder.filename;
    this.alt = builder.alt;
    this.caption = builder.caption;
    this.altCaptionEqualToFilename = builder.altCaptionEqualToFilename;
  }

  @XmlElement
  public String getFilename() {
    return this.filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  @XmlElement
  public String getAlt() {
    return this.alt;
  }

  public void setAlt(String alt) {
    this.alt = alt;
  }

  @XmlElement
  public String getCaption() {
    return this.caption;
  }

  @XmlElement
  public String getId() {
    return this.id;
  }
  public void setId(String id){
    this.id=id;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public boolean isAltCaptionEqualToFilename() {
    return this.altCaptionEqualToFilename;
  }

  public void setAltCaptionEqualToFilename(boolean isAltCaptionEqualToFilename) {
    this.altCaptionEqualToFilename = isAltCaptionEqualToFilename;
  }

  /**
   * Builder class enables you to create an Image.
   * 
   */
  public static class ImageBuilder {
    private final String id;
    private final String filename;
    private String alt;
    private String caption;
    private boolean altCaptionEqualToFilename;

    public ImageBuilder(String filename, String alt, String caption) {
      this.id = (FilenameUtils.removeExtension(filename)).toLowerCase();
      this.filename = filename;
      this.alt = alt;
      this.caption = caption;
      this.altCaptionEqualToFilename = false;
    }

    /**
     * Set 'alt' and 'caption' value equal to the filename without the extension.
     * 
     * @return ImageBuilder
     */
    public ImageBuilder setAltCaptionEqualToFilename(boolean isAltCaptionEqualToFilename) {
      if (isAltCaptionEqualToFilename) {
        final String baseName = FilenameUtils.removeExtension(this.filename);
        this.alt = baseName;
        this.caption = baseName;
      }
      this.altCaptionEqualToFilename = isAltCaptionEqualToFilename;
      return this;
    }

    public Image build() {
      return new Image(this);
    }
  }

  
  
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Image [id=").append(id).append(", filename=").append(filename).append(", alt=").append(alt)
        .append(", caption=").append(caption).append(", altCaptionEqualToFilename=").append(altCaptionEqualToFilename)
        .append("]");
    return builder.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((alt == null) ? 0 : alt.hashCode());
    result = prime * result + (altCaptionEqualToFilename ? 1231 : 1237);
    result = prime * result + ((caption == null) ? 0 : caption.hashCode());
    result = prime * result + ((filename == null) ? 0 : filename.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Image other = (Image) obj;
    if (alt == null) {
      if (other.alt != null)
        return false;
    } else if (!alt.equals(other.alt))
      return false;
    if (altCaptionEqualToFilename != other.altCaptionEqualToFilename)
      return false;
    if (caption == null) {
      if (other.caption != null)
        return false;
    } else if (!caption.equals(other.caption))
      return false;
    if (filename == null) {
      if (other.filename != null)
        return false;
    } else if (!filename.equals(other.filename))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}// ;~
