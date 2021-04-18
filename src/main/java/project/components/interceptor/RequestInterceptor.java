package project.components.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import project.components.WriteInFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
public class RequestInterceptor implements HandlerInterceptor {
    public static final String FILE_LOG_PATH = getBaseFilePathString() + "fileLogRequestInterceptor.txt";
    //private final Logger logger;
    private final WriteInFile writeInFile;

    public RequestInterceptor(WriteInFile writeInFile) {
        this.writeInFile = writeInFile;
      //  this.logger = LoggerFactory.getLogger(RequestInterceptor.class);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String msg = request.getRemoteAddr() + " accessed resource " + request.getRequestURI() + " @ " + getCurrentTime();
//        this.logger.info(msg); //for review!
        this.writeInFile.writeInLogFile(msg, FILE_LOG_PATH);

        return true;
    }

    private String getCurrentTime() {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        return formatter.format(calendar.getTime());
    }

    private static String getBaseFilePathString() {
        return System.getProperty("user.dir")
                + File.separator + "src"
                + File.separator + "main"
                + File.separator + "resources"
                + File.separator + "static"
                + File.separator + "files"
                + File.separator;
    }
}
