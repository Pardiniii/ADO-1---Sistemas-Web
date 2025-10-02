package br.com.carstore.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/create-car")
public class CreateCarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String carName = req.getParameter("car-name");
        if (carName == null) carName = "";
        carName = carName.trim();

        req.getSession(true).setAttribute("carName", carName);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write("{\"carName\":\"" + jsonEscape(carName) + "\"}");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        HttpSession s = req.getSession(false);
        String carName = (s != null && s.getAttribute("carName") != null)
                ? s.getAttribute("carName").toString()
                : "";

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write("{\"carName\":\"" + jsonEscape(carName) + "\"}");
    }

    private String jsonEscape(String s) {
        if (s == null) return "";
        return s.replace("\\","\\\\").replace("\"","\\\"")
                .replace("\b","\\b").replace("\f","\\f")
                .replace("\n","\\n").replace("\r","\\r")
                .replace("\t","\\t");
    }
}