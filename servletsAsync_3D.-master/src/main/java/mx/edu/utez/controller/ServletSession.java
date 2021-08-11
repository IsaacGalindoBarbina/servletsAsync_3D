package mx.edu.utez.controller;

import mx.edu.utez.model.user.BeanUser;
import mx.edu.utez.model.user.DaoUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletSession", urlPatterns = {"/login", "/logout"})
public class ServletSession extends HttpServlet {
    /**
     * Cierra la sesion del Usuario logeado
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Obteniendo la sesion
        HttpSession session = request.getSession();
        //cerrando la sesion
        session.setAttribute("session", null);
        session.invalidate();
        //redirigiendo al home
        request.getRequestDispatcher("/").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        BeanUser beanUser = new BeanUser();
        DaoUser daoUser = new DaoUser();
        beanUser.setEmail(request.getParameter("email"));
        beanUser.setPassword(request.getParameter("password"));
        boolean flag = daoUser.createSession(
                beanUser.getEmail(),
                beanUser.getPassword()
        );

        if(flag){
            session.setAttribute("session",beanUser);
            request.getRequestDispatcher("views/Inicio.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/").forward(request, response);
        }


    }
}
