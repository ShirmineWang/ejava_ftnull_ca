package sg.edu.nus.iss.javaee.epod.web;

import java.io.IOException;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author linby
 */
@WebServlet("/callback")
public class CallBackServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String podId =  req.getParameter("podId");
        String ackId = req.getParameter("ackId");
        //TODO update pod database
        System.out.println("----> suspending request");
    }
}
