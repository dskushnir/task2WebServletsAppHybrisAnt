package app.servlets.order;

import app.connection.MySQLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FindAllOrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        try {
            Connection con = MySQLConnection.getMySQLConnection();
            PreparedStatement ps1 = con.prepareStatement("SELECT orders.id,(order_items.quantity*products.price) as products_total_price,products.name,order_items.quantity,orders.create_at FROM orders INNER JOIN order_items ON (orders.id=order_items.order_id) INNER JOIN products ON (products.id=order_items.product_id) ORDER BY orders.id;");
            ResultSet rs1 = ps1.executeQuery();
            out.println("List Orders<br>");
            out.println("<html><table><tr>");
            out.println("<td><b>Order ID</b></td>");
            out.println("<td><b>Products Total Price</b></td>");
            out.println("<td><b>Name of Product</b></td>");
            out.println("<td><b>Quantity of Product</b></td>");
            out.println("<td><b>Creation Date of Order</b></td>");
            out.println("</tr>");
            while (rs1.next()) {
                int id = rs1.getInt(1);
                int products_total_price = rs1.getInt(2);
                String name = rs1.getString(3);
                int quantity = rs1.getInt(4);
                String created_at = rs1.getString(5);
                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + products_total_price + "</td>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + quantity + "</td>");
                out.println("<td>" + created_at + "</td>");
                out.println("</tr>");
            }
            out.println("</table><br>");
            out.flush();
            out.close();
            MySQLConnection.disconnect(con);
        } catch (SQLException | ClassNotFoundException e) {
            out.print(e.getMessage());
            e.printStackTrace();

        }
    }

}
