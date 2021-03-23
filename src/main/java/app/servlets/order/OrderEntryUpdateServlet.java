package app.servlets.order;

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


public class OrderEntryUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/order_entry/orderEntryUpdate.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        Connection con;
        int order_id = Integer.parseInt(req.getParameter("order_id"));
        int product_id = Integer.parseInt(req.getParameter("product_id"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        try {
            con = MySQLConnection.getMySQLConnection();
            Statement statement = con.createStatement();
            int result = statement.executeUpdate("update order_items set quantity ='" + quantity + "' where order_id='" + order_id + "'and product_id='" + product_id + "'");
            out.println(result + " row successfully updated your data");
            out.flush();
            out.close();
            MySQLConnection.disconnect(con);
        } catch (ClassNotFoundException | SQLException e) {
            out.print(e.getMessage());
            e.printStackTrace();
        }
    }
}
