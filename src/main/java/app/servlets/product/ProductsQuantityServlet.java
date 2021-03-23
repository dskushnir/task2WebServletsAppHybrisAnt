package app.servlets.product;

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


public class ProductsQuantityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        try {
            Connection con = MySQLConnection.getMySQLConnection();
            PreparedStatement ps1 = con.prepareStatement("select products.name, SUM(order_items.quantity) as quantity_of_product from products inner join order_items on products.id=order_items.product_id group by products.name order by order_items.quantity desc;");
            ResultSet rs1 = ps1.executeQuery();
            out.println("List Quantity of Ordered Products<br>");
            out.println("<html><table><tr>");
            out.println("<td><b>Name of Product</b></td>");
            out.println("<td><b>Quantity of Products</b></td>");

            out.println("</tr>");
            while (rs1.next()) {

                String name = rs1.getString(1);
                int quantity = rs1.getInt(2);
                out.println("<tr>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + quantity + "</td>");

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
