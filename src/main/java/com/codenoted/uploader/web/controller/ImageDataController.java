package com.codenoted.uploader.web.controller;

import static com.codenoted.uploader.Globals.getAllowedImageExtensions;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.codenoted.uploader.model.Image;
import com.codenoted.uploader.service.GalleryService;
import com.codenoted.uploader.service.ImageUploaderException;

@Controller
@RequestMapping("/data")
public class ImageDataController {

  @Autowired
  ServletContext context;

  @Autowired
  @Qualifier("imageGalleryService")
  private GalleryService gallaryService;

  @Autowired
  @Qualifier("imageValidator")
  private Validator validator;

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.setDisallowedFields("imageBinary");
    binder.setValidator(validator);
  }

  @RequestMapping(method = RequestMethod.GET)
  public String loadImageForm(Model model) {
    model.addAttribute("formImage", new Image());
    return "data/upload";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String submitImageForm(@Valid @ModelAttribute("formImage") final Image formImage, BindingResult bindingResult,
      Model model, @RequestParam(value = "imageBinary", required = false) MultipartFile imageBinary) {

    // check that the user added an image binary
    if (imageBinary == null || imageBinary.getSize() < 1 || imageBinary.getOriginalFilename().isEmpty()) {
      bindingResult.reject("imageBinary.required");
    }

    final String ext = FilenameUtils.getExtension(imageBinary.getOriginalFilename());
    if (getAllowedImageExtensions().indexOf(ext.toLowerCase()) < 0) {
      bindingResult.reject("imageBinary.types");
    }

    if (bindingResult.hasErrors()) {
      return "data/upload";
    }

    // Construct image using the builder
    final Image image = new Image.ImageBuilder(imageBinary.getOriginalFilename(), formImage.getAlt(),
        formImage.getCaption()).setAltCaptionEqualToFilename(formImage.isAltCaptionEqualToFilename()).build();

    try {
      gallaryService.uploadImage(image, imageBinary.getBytes());
    } catch (IOException e) {
      throw new ImageUploaderException(e);
    }

    model.addAttribute("formImage", image);
    model.addAttribute("success", "yes");
    model.addAttribute("imgPath", gallaryService.getGalleryLocationResource().getChild());

    return "data/upload";

  }

}// :~
