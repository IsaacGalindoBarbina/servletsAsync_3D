package mx.edu.utez.controller;

import com.google.gson.Gson;
import mx.edu.utez.model.category.BeanCategory;
import mx.edu.utez.model.game.BeanGame;
import mx.edu.utez.model.game.DaoGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig
@WebServlet(name = "ServletGame", urlPatterns ={"/ServletGame","/createGame"})
public class ServletGame extends HttpServlet {
    private Map map = new HashMap();
    final private Logger CONSOLE = LoggerFactory.getLogger(ServletGame.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("session") != null){
           // request.setAttribute("listGames", new DaoGame().findAll());
           // request.getRequestDispatcher("views/game/games.jsp").forward(request,response);
            map.put("listGames", new DaoGame().findAll());
            write(response, map);
        }else{
            request.getRequestDispatcher("views/Inicio.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    String action = request.getParameter("action");
        BeanGame beanGame =new BeanGame();
        BeanCategory beanCategory = new BeanCategory();
        DaoGame daoGame = new DaoGame();

        switch(action){
            case "create":

                Part part = request.getPart("image");
                InputStream image = part.getInputStream();
                beanCategory.setIdCategory(Integer.parseInt(request.getParameter("idCategory")));

                beanGame.setIdGame(Integer.parseInt("idGame"));
                beanGame.setNameGame(request.getParameter("name"));
                beanGame.setDatePremiere(request.getParameter("date"));
                beanGame.setCategory_idCategory(beanCategory);

                boolean flag = daoGame.create(beanGame, image);
                if(flag){
                    map.put("message", "Se han registrado correctamente");
                }else{
                    map.put("message", "Se han registrado correctamente");
                }
                write(response, map);
                break;
            case "update":
                beanCategory.setIdCategory(Integer.parseInt(request.getParameter("idCategory")));
                beanGame.setIdGame(Integer.parseInt("idGame"));
                beanGame.setNameGame(request.getParameter("name"));
                beanGame.setDatePremiere(request.getParameter("date"));
                beanGame.setCategory_idCategory(beanCategory);

                boolean flag2 = daoGame.update(beanGame);
                if(flag2){
                    CONSOLE.error("Se registro correctamente");
                }else{
                    CONSOLE.error("No se registro correctamente");
                }
                break;
            case  "delete":
                if (new DaoGame().delete(Integer.parseInt(request.getParameter("idGame"))));{
                //false
                }           {
                //false
                }
            break;
            default:
                request.getRequestDispatcher("/").forward(request, response);
                break;

        }
        response.sendRedirect(request.getContextPath()+"/readGame");
    }
    private void  write(HttpServletResponse response, Map<String, Object> map) throws IOException{
        response.setContentType("aplication/json");
        response.getWriter().write(new Gson().toJson(map));
    }
}
