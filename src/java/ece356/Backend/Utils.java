/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ece356.Backend;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kentma
 */
public class Utils {

    public static final String ATTR_DBC = "dbcon";
    public static final String ATTR_CREDENTIALS = "credentials";

    public static final String P_NAME = "name";
    public static final String P_EXCEPTION = "exception";

    public static final String URL_ERROR = "/error.jsp";

    public static void showErrorPage(ServletContext context, Exception e, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(P_EXCEPTION, e);
        context.getRequestDispatcher(URL_ERROR).forward(request, response);
    }
}
