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


public class DeleteProductByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/product/deleteProductById.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Connection con;

        String id = req.getParameter("id");
        int i = Integer.parseInt(id);

        try {
            con = MySQLConnection.getMySQLConnection();
            Statement statement = con.createStatement();
            int result = statement.executeUpdate("delete from products where id=" + i);
            if (result > 0) {
                out.println(result + " row successfully deleted your data");
            } else {
                out.println("Not found product id=" + i);
            }
            out.flush();
            out.close();
            MySQLConnection.disconnect(con);
        } catch (ClassNotFoundException | SQLException e) {
            out.print(e.getMessage());
            e.printStackTrace();

        }
    }
}
