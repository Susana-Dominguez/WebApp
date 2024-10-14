package com.example.webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/send")
public class App extends HttpServlet {
    private MessageSender messageSender = new MessageSender();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message = request.getParameter("message");
        if (message != null) {
            messageSender.sendMessage(message);
            response.getWriter().println("Message Sent: " + message);
        } else {
            response.getWriter().println("Please provide a message in the URL parameter: ?message=your_message");
        }
    }
}

