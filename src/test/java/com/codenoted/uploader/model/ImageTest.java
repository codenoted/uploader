package com.codenoted.uploader.model;

import static org.junit.Assert.*;

import org.apache.commons.io.FilenameUtils;
import org.junit.*;

public class ImageTest {

  private Image image;
  private String filename;
  private String alt;
  private String caption;
  private boolean altAndCaptionEqualsFilename;

  @Before
  public void setUp() {
    filename = "test.png";
    alt = "its so great";
    caption = "my best photo yet";
    altAndCaptionEqualsFilename = false;
    image = new Image.ImageBuilder(filename, alt, caption).build();

  }

  @Test
  public void createSimpleImageTest() throws Exception {
    assertEquals(filename, image.getFilename());
    assertEquals(alt, image.getAlt());
    assertEquals(caption, image.getCaption());
    assertFalse(image.isAltCaptionEqualToFilename());
  }//

  @Test
  public void filenameEqualsToAlt() throws Exception {
    altAndCaptionEqualsFilename = true;
    filename = "change.png";
    image = new Image.ImageBuilder(filename, alt, caption).setAltCaptionEqualToFilename(altAndCaptionEqualsFilename)
        .build();
    assertEquals(FilenameUtils.getBaseName(filename), image.getAlt());
  }//

  @Test
  public void filenameEqualToCaption() throws Exception {
    filename = "fav.jpeg";
    altAndCaptionEqualsFilename = true;
    image = new Image.ImageBuilder(filename, alt, caption).setAltCaptionEqualToFilename(altAndCaptionEqualsFilename)
        .build();

    assertEquals(FilenameUtils.getBaseName(filename), image.getCaption());
  }//

  @After
  public void tearDown() {
    image = null;

  }

}//
