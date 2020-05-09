package chat;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

@WebServlet(name = "ChatSubmitServlet", urlPatterns = "/ChatSubmitServlet")
public class ChatSubmitServlet extends HttpServlet {
    private final static Logger log = Logger.getLogger(ChatSubmitServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String chatName = URLDecoder.decode(request.getParameter("chatName"), "UTF-8");
        String chatContent = URLDecoder.decode(request.getParameter("chatContent"), "UTF-8");

        if(chatName == null || chatName.equals("") || chatContent == null || chatContent.equals("")) {
            response.getWriter().write("0");
        }
        else {
            response.getWriter().write(new ChatDAO().submit(chatName, chatContent) + "");
        }

        // log 테스트용
        //log.addAppender(new ConsoleAppender(new PatternLayout()));
        //log.fatal("severe log");
        //log.warn("warning log");
        //log.info("info log");

        //System.out.println("helloworld");
    }
}
