package com.codenoted.uploader.service;

import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The gallery resource location provides the full path to the image gallery.
 * 
 * The location can be made up from two parts parent and child.
 * 
 * @author andreas
 * 
 */
public class GalleryLocationResource {

  public static final String FILE_SEPARATOR = System.getProperty("file.separator");
  private static final Logger LOG = LoggerFactory.getLogger(GalleryLocationResource.class.getName());

  private final String basePath;
  private final String child;
  private final String path;

  public GalleryLocationResource() {
    this.basePath = null;
    this.child = null;
    this.path = null;
  }

  /**
   * <p>
   * The first argument is the base path, the second is the path to concatenate.
   * 
   * <p>
   * <code>child</code> value is not allowed to start with <code>System.getProperty("path.separator")</code> Doing so
   * would remove basePath value from full path.
   * <p>
   * Valid Examples:
   * 
   * <pre>
   * /foo/ + bar          -->   /foo/bar
   * /foo + bar           -->   /foo/bar
   * </pre>
   * 
   * Invalid Example:
   * 
   * <pre>
   * /foo/ + /bar         -->   /bar
   * </pre>
   * 
   * @param basePath the base path to attach to, always treated as a path
   * @param child the path to attach to the base
   * 
   * @throws IOException in case of path not being configured properly. As it would leave the image gallery in an
   *           inconsistent state.
   * 
   */
  public GalleryLocationResource(String basePath, String child) throws IOException {

    
    if (basePath == null || child == null || child.startsWith(FILE_SEPARATOR) ) {
      throw new IOException("basePath:'"+basePath+ "' and child:'"+ child + "'value can not be null and child value can not start with '"
          + FILE_SEPARATOR + "', "  );
    }
    this.basePath = basePath;
    this.child = child;
    this.path = FilenameUtils.concat(basePath, child);
    LOG.debug("Setting repository location to:'{}'  (basePath:'{}', child:'{}')", path, basePath, child);
  }

  public String getPath() {
    return path;
  }

  public String getBasePath() {
    return this.basePath;
  }

  public String getChild() {
    return this.child;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("GalleryLocationResource [basePath=").append(basePath).append(", child=").append(child).append(", path=")
        .append(path).append("]");
    return sb.toString();
  }

}// ;~
