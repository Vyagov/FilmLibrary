package project.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class FileExceptionHandler {
    @ExceptionHandler(value = MultipartException.class)
    public ModelAndView handleFileUploadException(MultipartException mpex, HttpServletRequest request) {

        ModelAndView modelAndVew = new ModelAndView("error");
        modelAndVew.addObject("errorMessage", mpex.getMessage());
        return modelAndVew;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleException(Exception ex, HttpServletRequest request) {

        ModelAndView modelAndVew = new ModelAndView("error");
        modelAndVew.addObject("errorMessage", ex.getMessage());
        return modelAndVew;
    }
}
