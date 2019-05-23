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

    /*加载JdBC驱动
     * 
     */
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("load Driver successfully");
    } catch (Exception e) {
        System.out.println("load  Driver error");
        e.printStackTrace();
    }




    /*连接数据库
     * 
     */
    try {
       connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web01","root","wu5HAO");
        //jdbc:mysql://localhost:端口号/数据库名称","用户名","密码"
        System.out.println("connect sql successfully");
    } catch (Exception e) {
        System.out.println("connect sql erro");
        e.printStackTrace();
    }



    /*检查用户账号密码正否
     * 
     */

}


/*连接数据库后要记得关闭
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

    //建立PreparedStatement对象

    PreparedStatement pStmt = connection.prepareStatement(sql);

    //为pStmt对象设置SQL参数值

    for(int i = 0; i < args.length; i++){

        pStmt.setObject(i+1, args[i]);

    }

    //执行SQL语句

    pStmt.execute();

    //返回结果集,如果执行的SQL语句不返回结果集，则返回null

    return pStmt.getResultSet();

}

public String checkUser(String username,String password){

    boolean has_username = false;

    boolean password_correct = false;

    ResultSet rs = null;

    try {

        rs = execSQL("select * from user");

    } catch (SQLException e) {

        System.err.println("查询数据库出错");

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

        System.err.println("操作ResultSet出错");

        e.printStackTrace();

    }

    return "hasNoUserName";

}
}