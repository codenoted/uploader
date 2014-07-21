package com.codenoted.uploader.service;

import static org.junit.Assert.*;

import java.io.IOException;
import static com.codenoted.uploader.service.GalleryLocationResource.FILE_SEPARATOR;

import org.junit.*;

public class GalleryLocationResourceTest {

  GalleryLocationResource resource;
  String basePath;
  String child;

  @Before
  public void setUp() throws Exception {
    basePath = FILE_SEPARATOR + "opt" + FILE_SEPARATOR + "java";
    child = "resources" + FILE_SEPARATOR + "img";
    resource = new GalleryLocationResource(basePath, child);
  }

  @After
  public void tearDown() {
  }//

  @Test
  public void basePathShouldMatch() throws Exception {
    assertEquals(basePath, resource.getBasePath());
  }//

  @Test
  public void childPathShouldMatch() throws Exception {
    assertEquals(child, resource.getChild());
  }//

  @Test(expected = IOException.class)
  public void addParentAndChildTest() throws IOException {
    final String child = FILE_SEPARATOR + "resources";
    new GalleryLocationResource(basePath, child);
    fail();
  }//

}// ;~
