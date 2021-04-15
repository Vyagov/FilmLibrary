package project.components;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import project.model.binding.ActorBindingModel;

@Component
public class ActorImageValidator implements Validator {
    public static final String PNG_TYPE = "image/png";
    public static final String JPEG_TYPE = "image/jpeg";
    public static final String GIF_TYPE = "image/gif";
    public static final String BMP_TYPE = "image/bmp";
    public static final long ONE_MB_IN_BYTES = 1048576;

    @Override
    public boolean supports(Class<?> aClass) {
        return ActorBindingModel.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ActorBindingModel actorBindingModel = (ActorBindingModel) obj;

        MultipartFile image = actorBindingModel.getImage();

        if (image.isEmpty()) {
            errors.rejectValue("image", "NoFile", "Upload file required!");
        } else if (!PNG_TYPE.equalsIgnoreCase(image.getContentType())
                && !BMP_TYPE.equalsIgnoreCase(image.getContentType())
                && !JPEG_TYPE.equalsIgnoreCase(image.getContentType())
                && !GIF_TYPE.equalsIgnoreCase(image.getContentType())) {
            errors.rejectValue("image", "NotAllowed","Allowed only files - .jpeg,.jpg,.gif,.bmp,.png!");
        } else if (image.getSize() > ONE_MB_IN_BYTES) {
            errors.rejectValue("image", "FileTooLarge","Uploaded file exceeded 1MB!");
        }
    }
}
