import java.sql.*;
import java.time.LocalDate;

public class MySQLConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/bdtiendita";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }


    public void insertar(String codigoProducto, String nombreProducto, Float precioUnitario, int cantidadProducto, LocalDate fechaVencimiento) {
        String query = "INSERT INTO producto (codigoProducto, nombreProducto, precioUnitario, cantidadProducto, fechaVencimiento) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = connect();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, codigoProducto);
            ps.setString(2, nombreProducto);
            ps.setFloat(3, precioUnitario);
            ps.setInt(4, cantidadProducto);
            ps.setDate(5, Date.valueOf(fechaVencimiento));
            ps.executeUpdate();
            System.out.println("Registro insertado correctamente.");
        } catch (Exception e) {
            throw new RuntimeException("Error al insertar producto: " + e.getMessage());
        }
    }

    public void actualizar(String codigoProducto, String nombreProducto, Float precioUnitario, int cantidadProducto, LocalDate fechaVencimiento) {
        String query = "UPDATE producto SET nombreProducto = ?, precioUnitario = ?, cantidadProducto = ?, fechaVencimiento = ? WHERE codigoProducto = ?";
        try (Connection connection = connect();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, nombreProducto);
            ps.setFloat(2, precioUnitario);
            ps.setInt(3, cantidadProducto);
            ps.setDate(4, Date.valueOf(fechaVencimiento));
            ps.setString(5, codigoProducto);
            ps.executeUpdate();
            System.out.println("Registro actualizado correctamente.");
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar producto: " + e.getMessage());
        }
    }


    public void eliminar(String codigoProducto) {
        String query = "DELETE FROM producto WHERE codigoProducto = ?";
        try (Connection connection = connect();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, codigoProducto);
            ps.executeUpdate();
            System.out.println("Registro eliminado correctamente.");
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar producto: " + e.getMessage());
        }
    }

    public void consultarPorCodigo(String codigoProducto) {
        String query = "SELECT * FROM producto WHERE codigoProducto = ?";
        try {
            Connection connection = connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, codigoProducto);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                System.out.println(" | CodigoProducto: " + rs.getString("codigoProducto") + " | NombreProducto " + rs.getString("nombreProducto") + " | Precio Unitario " + rs.getDouble("precioUnitario") + " | Cantidad Producto: " + rs.getInt("cantidadProducto") + " | Fecha Vencimiento " + rs.getDate("fechaVencimiento"));
            }else{
                System.out.println("la cagamos xd");
            }

        }catch (Exception e) {
            throw new RuntimeException("Error al consultar producto: " + e.getMessage());
        }
    }

    public void consultar() {
        String query = "SELECT * FROM producto";
        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                System.out.println(" | CodigoProducto: " + rs.getString("codigoProducto") + " | NombreProducto " + rs.getString("nombreProducto") + " | Precio Unitario " + rs.getDouble("precioUnitario") + " | Cantidad Producto: " + rs.getInt("cantidadProducto") + " | Fecha Vencimiento " + rs.getDate("fechaVencimiento"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
