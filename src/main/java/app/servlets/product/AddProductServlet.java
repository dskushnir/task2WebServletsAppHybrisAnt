package app.servlets.product;

import app.connection.MySQLConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class AddProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/product/addProduct.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Connection con;
        String name = req.getParameter("name");
        int price = Integer.parseInt(req.getParameter("price"));
        String status = req.getParameter("status");
        try {
            con = MySQLConnection.getMySQLConnection();
            Statement statement = con.createStatement();
            int result = statement.executeUpdate("insert into products (name,price,status,created_at) values('" + name + "','" + price + "','" + status + "',localtime())");
            out.println(result + " row successfully inserted your data");
            out.flush();
            out.close();
            MySQLConnection.disconnect(con);
        } catch (ClassNotFoundException | SQLException e) {
            out.print(e.getMessage());
            e.printStackTrace();
        }
    }
}
