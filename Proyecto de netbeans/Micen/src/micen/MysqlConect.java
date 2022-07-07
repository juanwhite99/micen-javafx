package micen;
import java.sql.*;
import javax.swing.JOptionPane;

public class MysqlConect {
    public int log_auth(String user, String pass)
    {
        UserLog ul = new UserLog();
        Connection con=null;
        ResultSet rs = null;
        Statement cmd = null;
        String u,p;
        try {
            String servidor = "jdbc:mysql://localhost/micen_test";                 // Aqui pones el nombre de la ase de datos en lugar de "micen_test"
            String usuarioDB="root";                                              // Aqui pones el ususario que tengas en la BD
            String passwordDB="/C0n3j1t0.";                                       // Aqui poones la contraseña de tu BD
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection(servidor,usuarioDB,passwordDB);
            cmd = con.createStatement();
            rs = cmd.executeQuery("SELECT log_user,log_pass FROM user_accs");     // Sentencia de SQL que se ejecutara
            while (rs.next()) 
            {
                u = rs.getString ("log_user");
                p = rs.getString ("log_pass");
                if (u.equals(user)== true &&p.equals(pass) == true )
                {
                    ul.server_error = 9;
                }
            }/*
            if (ul.server_error != 9)
            {
                ul.get_access(2);
            }*/
        }
        catch(ClassNotFoundException ex)
        {
            con=null;
        }
        catch(SQLException ex)
        {
            con=null;
        }
        catch(Exception ex)
        {
            con=null;
        }
        finally
        {
            
        }
        return ul.server_error;
    }
    
    public static Connection GetConnection()
    {
        Connection conexion=null;
        UserLog ul = new UserLog();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://localhost/micen_test";
            String usuarioDB="root";
            String passwordDB="/C0n3j1t0.";
            conexion= DriverManager.getConnection(servidor,usuarioDB,passwordDB);
        }
        catch(ClassNotFoundException ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error1 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            ul.server_error = 2;
            conexion=null;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error2 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion=null;
            ul.server_error = 2;
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error3 en la Conexión con la BD "+ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion=null;
            ul.server_error = 2;
        }
        finally
        {
            return conexion;
        }
    }
}
