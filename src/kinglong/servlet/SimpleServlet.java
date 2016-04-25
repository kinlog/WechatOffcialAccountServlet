package kinglong.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wechat.service.CoreService;
import com.wechat.utils.SignCheck;

@WebServlet
public class SimpleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
	// check signature
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String signature = req.getParameter("signature");  
        // 时间戳  
        String timestamp = req.getParameter("timestamp");  
        // 随机数  
        String nonce = req.getParameter("nonce");  
        // 随机字符串  
        String echostr = req.getParameter("echostr");  
  
        PrintWriter pw = resp.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
        if (SignCheck.checkSignature(signature, timestamp, nonce)) {
        	pw.write(echostr);
        } else {
        	pw.write("");
        }
        pw.flush();
        pw.close();
		
		// req.setCharacterEncoding("utf-8");
		// resp.setCharacterEncoding("utf-8");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 修改中文乱码问题
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		// 调用核心业务类接收消息、处理消息  
        String respMessage = CoreService.processRequest(req);
        
        // 写回消息
		PrintWriter pw = resp.getWriter();
		pw.write(respMessage);
		pw.flush();
		pw.close();
	}
}
