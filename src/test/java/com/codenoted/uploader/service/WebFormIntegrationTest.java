package com.codenoted.uploader.service;

import org.junit.*;
import org.junit.experimental.categories.Category;
import static net.sourceforge.jwebunit.junit.JWebUnit.*;

import com.codenoted.test.markers.IntegrationTest;

/**
 * Integration test to ensure basic validation occurs.
 * 
 * @author andreas
 * 
 */
@Category(IntegrationTest.class)
public class WebFormIntegrationTest {

  @Before
  public void setUp() {
    setBaseUrl("http://127.0.0.1:8080/uploader");
    getTestContext().setResourceBundleName("messages");
  }

  @Test
  public void testThatImageNotBeenAdded() throws Exception {
    beginAt("/image/data");
    setTextField("alt", "alt");
    setTextField("caption", "caption");
    submit();
    assertKeyPresent("imageBinary.required");
  }

  @Test
  public void testThatFileNameUsedForCaptionAlt() {
    beginAt("/image/data");
    checkCheckbox("altCaptionEqualToFilename");
    submit();
    assertKeyPresent("imageBinary.required");
    assertKeyNotPresent("alt.required");
    assertKeyNotPresent("caption.required");

  }

}//
