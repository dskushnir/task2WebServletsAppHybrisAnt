package app.servlets.product;

import app.connection.MySQLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FindAllProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        try {
            Connection con = MySQLConnection.getMySQLConnection();
            PreparedStatement ps1 = con.prepareStatement("select * from products");
            ResultSet rs1 = ps1.executeQuery();
            out.println("List Products<br>");
            out.println("<html><table><tr>");
            out.println("<td><b>Id</b></td>");
            out.println("<td><b>Name</b></td>");
            out.println("<td><b>Price</b></td>");
            out.println("<td><b>Status</b></td>");
            out.println("<td><b>Creation Date of Product</b></td>");
            out.println("</tr>");
            while (rs1.next()) {
                int id = rs1.getInt(1);
                String name = rs1.getString(2);
                int price = rs1.getInt(3);
                String status = rs1.getString(4);
                String created_at = rs1.getObject(5, LocalDateTime.class).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + price + "</td>");
                out.println("<td>" + status + "</td>");
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











