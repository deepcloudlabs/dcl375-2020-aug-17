package com.example.lottery.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.lottery.model.LotteryViewModel;
import com.example.lottery.service.LotteryService;
//   http://localhost:7001/lottery/draw
//                           /\    /\
// context-root =============|     |
//                                 | 
@WebServlet(urlPatterns =       "/draw")
public class LotteryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
    private LotteryService lotteryService;
	@Inject
    private LotteryViewModel lotteryViewModel;
    
    public LotteryController() {
        super();
    }

    // first time user access the application
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("lottery.jsp")
		       .forward(request, response);
	}

	// user clicks "draw" button
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var numbers = lotteryService.draw();  // delegate the request to service
		lotteryViewModel.setNumbers(numbers); // update/create model
		request.getRequestDispatcher("lottery.jsp") // dispatch the request to 
	       .forward(request, response);		 // the view component (lottery.jsp)
	}

}
