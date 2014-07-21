package com.codenoted.uploader.service;

import com.codenoted.uploader.model.Image;


/**
 * The Gallery allows for images to be added and viewed.
 * 
 * @author andreas bester
 * 
 */
public interface GalleryService {

  /**
   * Adds/Update and image to Image Gallery. Images with the same filename will be overwritten.
   * 
   * @param image
   * @return true if successful
   */
  void uploadImage(Image image, byte[] bytes);

  /**
   * Returns and Image based on unique filename value.
   * 
   * @param filename
   * @return
   */
  Image getImage(String filename);
 
  /**
   * Provide location resources that determines where the file is saved.
   * 
   * @param gallaryLocationResource
   */
  void setGalleryLocationResource(GalleryLocationResource gallaryLocationResource);

 
  GalleryLocationResource getGalleryLocationResource();

}// ;~
