package mx.edu.utez.model.game;
import mx.edu.utez.model.category.BeanCategory;
import mx.edu.utez.service.ConnectionMySQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class DaoGame{
    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;
    final private Logger CONSOLE= LoggerFactory.getLogger(DaoGame.class);

    public List<BeanGame> findAll(){
        List<BeanGame> listGame = new ArrayList<>();
        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_findAll}");
            rs = cstm.executeQuery();

            while(rs.next()){
                BeanCategory beanCategory = new BeanCategory();
                BeanGame beanGame = new BeanGame();

                beanGame.setIdGame(rs.getInt("idGame"));
                beanGame.setNameGame(rs.getString("nameGame"));
                beanGame.setDatePremiere(rs.getString("DatePremiere"));
                beanGame.setStatus(rs.getInt("status"));
                beanGame.setImgGame(Base64.getEncoder().encodeToString(rs.getBytes("imgGame")));

                beanGame.setCategory_idCategory(beanCategory);
                listGame.add(beanGame);
            }
        }catch (SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return listGame;
    }

    public BeanGame findById(long id){
        BeanGame beanGame =null;
        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("SELECT * FROM game AS G INNER JOIN category AS C ON G.idGame = C.idCategory WHERE G.idGame = ?");
            cstm.setLong(1, id);
            rs = cstm.executeQuery();

            if(rs.next()){
                BeanCategory beanCategory = new BeanCategory();
                beanGame = new BeanGame();

                beanGame.setIdGame(rs.getInt("idGame"));
                beanGame.setNameGame(rs.getString("nameGame"));
                beanGame.setDatePremiere(rs.getString("DatePremiere"));
                beanGame.setStatus(rs.getInt("status"));
                beanGame.setImgGame(Base64.getEncoder().encodeToString(rs.getBytes("imgGame")));

                beanGame.setCategory_idCategory(beanCategory);beanGame.setIdGame(rs.getInt("idGame"));
                beanGame.setNameGame(rs.getString("nameGame"));
                beanGame.setDatePremiere(rs.getString("DatePremiere"));
                beanGame.setStatus(rs.getInt("status"));
                beanGame.setImgGame(Base64.getEncoder().encodeToString(rs.getBytes("imgGame")));

                beanGame.setCategory_idCategory(beanCategory);
            }
        }catch (SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return beanGame;
    }

    public boolean create(BeanGame beanGame, InputStream image){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("INSERT INTO game(nameGame, image, datePremiere, status) VALUES (?,?,?,?,?)");
            cstm.setString(1, beanGame.getNameGame());
            cstm.setBlob(2, image);
            cstm.setString(3, beanGame.getDatePremiere());
            cstm.setInt(4, beanGame.getStatus());
            cstm.execute();
            flag = true;
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }

    public boolean update(BeanGame game){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("UPDATE game SET nameGame=?, datePremiere=?, status=? WHERE idGame = ?");
            cstm.setString(1, game.getNameGame());
            cstm.setString(2, game.getDatePremiere());
            cstm.setInt(3, game.getStatus());
            cstm.execute();

            flag = cstm.execute();
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }

    public boolean delete(long idGame){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("DELETE * FROM game WHERE idGame = ?");
            cstm.setLong(1, idGame);

            flag = cstm.execute();
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }




}

