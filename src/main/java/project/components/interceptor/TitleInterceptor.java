package project.components.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import project.annotation.Title;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TitleInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) {
        String title = "Film Library";
        String addToTitleStr = "";
        if (handler instanceof HandlerMethod) {
            Title addToTitle = ((HandlerMethod) handler).getMethodAnnotation(Title.class);
            addToTitleStr = addToTitle != null ? " - " + addToTitle.name() : "";
        }
        if (modelAndView == null) {
            modelAndView = new ModelAndView();
        }
        modelAndView.addObject("title", title + addToTitleStr);
    }
}
