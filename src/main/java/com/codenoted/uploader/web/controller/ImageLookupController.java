package com.codenoted.uploader.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codenoted.uploader.model.Image;
import com.codenoted.uploader.service.GalleryService;

@Controller
@RequestMapping("/lookup")
public class ImageLookupController {

  @Autowired
  @Qualifier("imageGalleryService")
  private GalleryService gallaryService;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<String> getDefault() {
    return new ResponseEntity<String>("/{id} - provides name of image", HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public @ResponseBody
  Image getImage(@PathVariable String id) {
    Image img = gallaryService.getImage(id);
    return img;
  }

  

}// ;~
