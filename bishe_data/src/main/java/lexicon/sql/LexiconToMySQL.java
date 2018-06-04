package lexicon.sql;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import util.Util;

public class LexiconToMySQL {

	public static void main(String[] args) throws Exception {
		String URL="jdbc:mysql://127.0.0.1:3306/bishe_data?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT";
		String USER="root";
		String PASSWORD="1q2w3e4r5t@";
		//1.加载驱动程序
		Class.forName("com.mysql.jdbc.Driver");
		//2.获得数据库链接
		Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
		//3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
		PreparedStatement prepareStatement = null;
		String base = Util.getClassPath();
		//原词位置
		BufferedReader verbBr = Util.getBufferedReaderByPath(base + "lexicon/data/verbToExplain_total.txt");
		String line = "测试\t测试";
		while((line = verbBr.readLine()) != null){
			String[] split = line.split("\t");
			String insert = "insert into lexicon (lexicon,isFunction,explained,isSet) values(?,?,?,?)";
			prepareStatement = conn.prepareStatement(insert);
			prepareStatement.setString(1, split[0]);
			prepareStatement.setInt(2, 1);
			prepareStatement.setString(3, split[1]);
			prepareStatement.setInt(4, 0);
			prepareStatement.executeUpdate();
		}
		
		//关闭资源
		prepareStatement.close();
		conn.close();
		verbBr.close();		
	}

}
