package ejercicio_cinco_junio;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AmazonScraper {
    public static void main(String[] args) {
        String url = "https://www.amazon.com/s?k=juegos"; // URL de la búsqueda en Amazon

        try {
            Document doc = Jsoup.connect(url).get(); // Obtener el documento HTML de la página de resultados

            Elements products = doc.select(".s-result-item"); // Seleccionar los elementos que representan los productos

            BufferedWriter writer = new BufferedWriter(new FileWriter("resultados.csv")); // Crear un escritor de archivos

            writer.write("Nombre, Precio"); // Escribir los encabezados de las columnas
            writer.newLine();

            for (Element product : products) {
                String title = product.select(".a-text-normal").text(); // Obtener el título del producto
                String price = product.select(".a-offscreen").text(); // Obtener el precio del producto

                // Escribir los datos en el archivo CSV
                writer.write(title + "," + price);
                writer.newLine();
            }

            writer.close(); // Cerrar el escritor de archivos

            System.out.println("La búsqueda se ha completado correctamente. Los resultados se han guardado en 'resultados.csv'.");
        } catch (IOException e) {
            System.out.println("Se produjo un error al realizar la búsqueda en Amazon: " + e.getMessage());
        }
    }
}

