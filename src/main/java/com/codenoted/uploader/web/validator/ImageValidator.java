package com.codenoted.uploader.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.codenoted.uploader.model.Image;

public class ImageValidator implements Validator {

  @Override
  public void validate(Object obj, Errors errors) {

    Image img = (Image) obj;

    if (!img.isAltCaptionEqualToFilename()) {
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "caption", "caption.required");
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, "alt", "alt.required");
    }

  }//

  @Override
  public boolean supports(Class clazz) {
    return Image.class.isAssignableFrom(clazz);
  }

}// ;~
