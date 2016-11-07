package sg.edu.nus.iss.javaee.epod.web;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.javaee.epod.business.PodBean;
/**
 *
 * @author linby
 */
@WebServlet("/callback")
public class CallBackServlet extends HttpServlet{
    @EJB private PodBean podBean;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String podId =  req.getParameter("podId");
        String ackId = req.getParameter("ackId");
        podBean.updateAckId(Integer.valueOf(podId), ackId);
    }
}
