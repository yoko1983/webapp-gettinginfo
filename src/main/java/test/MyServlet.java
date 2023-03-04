package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/myservlet")
public class MyServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/html; charset=utf-8");
		try (PrintWriter out = res.getWriter()) {
			out.println("<html><body>");
			out.println("サーブレット: Hello Servlet World2!<br>");
			out.println(getServletContext().getServerInfo());
			out.println("インスタンス名:"+System.getProperty("com.sun.aas.instanceName"));
			out.println("VM変数 myVMParam:"+System.getProperty("myVMParam"));
			out.println("クラスパス:"+System.getProperty("java.class.path"));
			out.println("クラスパス:"+Thread.currentThread().getContextClassLoader().getResource("").getPath());
			ServletConfig config = getServletConfig();
			ServletContext context = config.getServletContext();
			out.println("CONTEXT変数 myParam:"+context.getInitParameter("myParam"));
			out.println("仮想サーバ名:"+context.getVirtualServerName());
			try {
				Context ctx = new InitialContext();
				String appName = (String) ctx.lookup("java:app/AppName");
				out.println("AP名:"+appName);
			} catch (Exception e) {
		        out.println("lookup error:" + e);
			}

			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			URL[] urls = ((URLClassLoader) cl).getURLs();
			out.print("クラスローダ:");
			for (URL url : urls) {
				out.print(url.getFile()+", ");
			}
			out.println();
	        //入カストリーム
	        try {
	            InputStream input = MyServlet.class.getClassLoader().getResourceAsStream(
	                    "classpath.properties");
//
//		        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(
//			            "classpath.properties");

		        // プロパティファイルとして読み込み
		        Properties proper = new Properties();

		        proper.load(input);
		        // 入力ストリームを閉じる
		        input.close();

		        //読み込んだ内容を表示
		        out.println("クラスローダから取得したプロパティ contentの値:" + proper.getProperty("content"));
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
		        out.println("class load error:" + e);
			}


			out.println("</body></html>");

		}


	}
}