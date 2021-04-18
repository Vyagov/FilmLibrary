package project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import project.components.interceptor.RequestInterceptor;
import project.components.interceptor.TitleInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final TitleInterceptor titleInterceptor;
    private final RequestInterceptor requestInterceptor;

    @Autowired
    public WebMvcConfig(TitleInterceptor titleInterceptor, RequestInterceptor requestInterceptor) {
        this.titleInterceptor = titleInterceptor;
        this.requestInterceptor = requestInterceptor;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/static/css/", "/static/images/", "/static/js/", "/static/files/")
                .setCachePeriod(0);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(titleInterceptor);
        registry.addInterceptor(requestInterceptor);
    }

}
