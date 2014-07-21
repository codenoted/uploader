package com.codenoted.uploader.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FileUtils;

import com.codenoted.uploader.model.Image;

public class ImageGalleryService implements GalleryService {


  // Default location for images uploaded to the gallery. Recommend changing this value, otherwise images will be
  // removed on a webapp redeploys.

  Map<String, Image> ImageLibarry = new ConcurrentHashMap<String, Image>();

  // We also hold the image reference within a list, for when users want to view
  // all images via the services (xml,etc) services. instead of iterating over the hashmap each time.(which would be
  // slower)

  List<Image> fastAccessImageList = new ArrayList<Image>();

 
  private GalleryLocationResource galleryResourceLocation;

  public ImageGalleryService() {

  }

  @Override
  public void setGalleryLocationResource(GalleryLocationResource galleryResourceLocation) {
    this.galleryResourceLocation = galleryResourceLocation;
  }

  @Override
  public GalleryLocationResource getGalleryLocationResource() {
    return this.galleryResourceLocation;
  }

  @Override
  public void uploadImage(Image image, byte[] bytes) {

    try {
      persistImageToDisk(image, bytes);
      ImageLibarry.put(image.getId(), image);

    } catch (IOException e) {
      throw new ImageUploaderException(e);
    }

  }//

  private void persistImageToDisk(Image image, byte[] bytes) throws IOException {
    FileUtils.writeByteArrayToFile(new File(this.galleryResourceLocation.getPath(), image.getFilename()), bytes);
  }//



  @Override
  public Image getImage(String filename) {
    return ImageLibarry.get(filename);
  }


}// ;~
