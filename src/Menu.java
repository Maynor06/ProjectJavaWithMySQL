import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {

    public static Scanner sc = new Scanner(System.in);
    private static MySQLConnector connector = new MySQLConnector();

    public void insertProduct(){
        System.out.println("Ingrese el codigo del producto: ");
        String codigoProducto = sc.nextLine();
        System.out.println("Ingrese el nombre del producto: ");
        String nombreProducto = sc.nextLine();
        System.out.println("Ingrese el precio unitario: ");
        Float precioUnitario = Float.valueOf(sc.nextLine());
        System.out.println("Ingrese el cantidad de producto: ");
        int cantidadProducto = Integer.parseInt(sc.nextLine());
        System.out.println("Ingrese la fecha de vencimiento del producto: ");
        String dateVencimiento = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaVencimiento = LocalDate.parse(dateVencimiento, formatter);

        connector.insertar(codigoProducto, nombreProducto, precioUnitario, cantidadProducto, fechaVencimiento);
    }

    public void listProduct(){
        connector.consultar();
    }

    public void searchProduct(){
        System.out.println("Ingrese el codigo del producto: ");
        String codigoProducto = sc.nextLine();
        connector.consultarPorCodigo(codigoProducto);
    }

    public void editProduct(){
        System.out.println("Ingrese el codigo del producto que deseas modificar: ");
        String codigoProducto = sc.nextLine();
        System.out.println("Ingrese el nuevo nombre del producto: ");
        String nombreProducto = sc.nextLine();
        System.out.println("Ingrese el nuevo precio unitario: ");
        Float precioUnitario = Float.valueOf(sc.nextLine());
        System.out.println("Ingrese la cantidad nueva de productos: ");
        int cantidadProducto = Integer.parseInt(sc.nextLine());
        System.out.println("Ingrese la nueva fecha de vencimiento del producto: ");
        String dateVencimiento = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaVencimiento = LocalDate.parse(dateVencimiento, formatter);

        connector.actualizar(codigoProducto, nombreProducto, precioUnitario, cantidadProducto, fechaVencimiento);
    }

    public void deleteProduct(){
        System.out.println("Ingrese el codigo del producto: ");
        String codigoProducto = sc.nextLine();
        connector.eliminar(codigoProducto);

    }

    public Menu(){

        int opcion = 0;
        do{
            System.out.println("---------------------------> Menu principal <---------------------------");
            System.out.println(" ");
            System.out.println("1. Ingresar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar producto");
            System.out.println("4. editar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("6. Salir");

            System.out.println(" ");
            System.out.println("Ingresa una opcion: ");
            opcion = Integer.parseInt(sc.nextLine());
            System.out.println(" ");
            switch(opcion){
                case 1:
                    insertProduct();
                    break;
                    case 2:
                        listProduct();
                        break;
                        case 3:
                            searchProduct();
                            break;
                            case 4:
                                editProduct();
                                break;
                                case 5:
                                    deleteProduct();
                                    break;
                                    case 6:
                                        System.out.println("Gracias por utilzar el programa :D");
                                        break;
                                        default:
                                            System.out.println("Opcion no valida");
                                            break;
            }

            if (opcion != 6) {
                System.out.println("Presiona Enter para continuar...");
                new Scanner(System.in).nextLine(); // Esperar a que el usuario presione Enter
            }

        }while (opcion != 6);

    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // Ejecutar el comando cls en Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Ejecutar el comando clear en Linux o macOS
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("No se pudo limpiar la pantalla");
        }
    }


}
