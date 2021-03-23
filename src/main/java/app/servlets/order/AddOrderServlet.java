package app.servlets.order;

import app.connection.MySQLConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class AddOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/order/addOrder.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Connection con1;
        Connection con2;
        int id = Integer.parseInt(req.getParameter("id"));
        String status = req.getParameter("status");
        int idP = Integer.parseInt(req.getParameter("product_id"));
        int q = Integer.parseInt(req.getParameter("quantity"));
        String create_at = LocalDateTime.now(Clock.systemUTC()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        try {
            con1 = MySQLConnection.getMySQLConnection();
            Statement statement = con1.createStatement();
            int result = statement.executeUpdate("insert into orders (id,create_at,status) values('" + id + "','" + create_at + "','" + status + "')");
            out.println(result + " row successfully inserted your order");
            MySQLConnection.disconnect(con1);
            con2 = MySQLConnection.getMySQLConnection();
            Statement statement2 = con2.createStatement();
            int result2 = statement2.executeUpdate("insert into order_items (order_id,product_id,quantity) values('" + id + "','" + idP + "','" + q + "')");
            out.println(result2 + " row successfully inserted your order_item");
            out.flush();
            out.close();
            MySQLConnection.disconnect(con2);

        } catch (ClassNotFoundException | SQLException e) {
            out.print(e.getMessage());
            e.printStackTrace();
        }
    }
}
