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
		//1.������������
		Class.forName("com.mysql.jdbc.Driver");
		//2.������ݿ�����
		Connection conn=DriverManager.getConnection(URL, USER, PASSWORD);
		//3.ͨ�����ݿ�����Ӳ������ݿ⣬ʵ����ɾ�Ĳ飨ʹ��Statement�ࣩ
		PreparedStatement prepareStatement = null;
		String base = Util.getClassPath();
		//ԭ��λ��
		BufferedReader verbBr = Util.getBufferedReaderByPath(base + "lexicon/data/verbToExplain_total.txt");
		String line = "����\t����";
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
		
		//�ر���Դ
		prepareStatement.close();
		conn.close();
		verbBr.close();		
	}

}
