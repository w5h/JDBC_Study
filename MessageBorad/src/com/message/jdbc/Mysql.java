package com.message.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mysql {

    private Connection connection = null;
public  Mysql()
{

    /*����JdBC����
     * 
     */
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("load Driver successfully");
    } catch (Exception e) {
        System.out.println("load  Driver error");
        e.printStackTrace();
    }




    /*�������ݿ�
     * 
     */
    try {
       connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web01","root","wu5HAO");
        //jdbc:mysql://localhost:�˿ں�/���ݿ�����","�û���","����"
        System.out.println("connect sql successfully");
    } catch (Exception e) {
        System.out.println("connect sql erro");
        e.printStackTrace();
    }



    /*����û��˺���������
     * 
     */

}


/*�������ݿ��Ҫ�ǵùر�
 * 
 */
private void CloseSqlConnect(Connection con ){
    if (con != null)
        try{
            con.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
}

private ResultSet execSQL(String sql,Object... args) throws SQLException{

    //����PreparedStatement����

    PreparedStatement pStmt = connection.prepareStatement(sql);

    //ΪpStmt��������SQL����ֵ

    for(int i = 0; i < args.length; i++){

        pStmt.setObject(i+1, args[i]);

    }

    //ִ��SQL���

    pStmt.execute();

    //���ؽ����,���ִ�е�SQL��䲻���ؽ�������򷵻�null

    return pStmt.getResultSet();

}

public String checkUser(String username,String password){

    boolean has_username = false;

    boolean password_correct = false;

    ResultSet rs = null;

    try {

        rs = execSQL("select * from user");

    } catch (SQLException e) {

        System.err.println("��ѯ���ݿ����");

        e.printStackTrace();

        return null;

    }

    try {

        while(rs.next()){

            String temp_username = rs.getString("name").trim();

            String temp_password = rs.getString("password").trim();

            if(username.equals(temp_username)){

                has_username = true;

                if(password.equals(temp_password)){

                    password_correct = true;

                    return "hasUserNameAndPasswordCorrect";

                }

                return "hasUserNameButPasswordInCorrect";

            }

        }

    } catch (SQLException e) {

        System.err.println("����ResultSet����");

        e.printStackTrace();

    }

    return "hasNoUserName";

}
}