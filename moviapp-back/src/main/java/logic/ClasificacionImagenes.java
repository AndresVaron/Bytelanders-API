package logic;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions.Builder;

import exceptions.BusinessLogicException;
import persistence.ClientePersistence;

public class ClasificacionImagenes {

	@Inject
	DireccionErradaLogic dirLog;
	
	@Inject
	ClientePersistence clientePersistence;
	
    /**
     * Metodo principal que integra todas las soluciones
     * @throws BusinessLogicException 
     */
    public String identificacionPredio(String direccion, String ciudad, String depto) throws IOException, BusinessLogicException {

        //TODO Con la direccion obtener la latitud y la longitud
    	
    	Coords coordenadas = dirLog.darCoordenadasDireccion(direccion, ciudad, depto);
        	
    	

 /*       if(direccionesRepetidas){
			return "Apartamento";		}
		else if(true){
			return palabrasClave(direccion);
		}
		else{
			return tipoPredio(lat,lon);
		}
        return null;*/

    }

    //Metodo 1 mirar si hay direcciones repetidas en la base de datos
    //TODO VERIFICAR EN LA BASE DE DATOS, SI HAY REPETIDOS ES TRUE Y QUIERE DECIR QUE SE TIENE UN APTO
    public boolean direccionesRepetidas() {
        //poner en la clase cliente el atributo direccion 
    	
    	clientePersistence.findByDireccion()
    	
        return false;
    }

    //Metodo 2 mirar palabras claves en la direccion
    private String palabrasClave(String direccion) {
        String direccionComparar = direccion.toUpperCase();
        String[] palabrasApartamento = {"APT", "APTO", "INTERIOR", "PISO", "TOR", "TORR", "TORRE"};
        String[] palabrasCasa = {"CS", "CAS", "CASA", "ETAPA", "LT", "LOTE"};

        String ans = null;

        for (int i = 0; i < palabrasApartamento.length; i++) {
            if (direccion.contains(palabrasCasa[i])) {
                ans = "Conjunto";
                break;
            } else if (direccion.contains(palabrasApartamento[i])) {
                ans = "Edificio";
                break;
            }
        }
        return ans;
    }

    //Metodo 3 usar watson para la clasificacion de imagenes segun la latitud y longitud del predio
    @SuppressWarnings("static-access")
    public String tipoPredio(double longitud, double latitud) throws IOException {

        URL url = new URL("https://maps.googleapis.com/maps/api/streetview?size=400x400&location=" + longitud + "," + latitud + "&fov=90&heading=235&pitch=10&key=AIzaSyCyhpCQfv5n_EeBBFTBxZMxSD633ul9I2o");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        con.setInstanceFollowRedirects(true);

        BufferedImage image = ImageIO.read(con.getInputStream());
        //JFrame frame = new JFrame();
        //JLabel label = new JLabel(new ImageIcon(image));
        //frame.getContentPane().add(label, BorderLayout.CENTER);
        //frame.pack();
        //frame.setVisible(true);
        String path = "./data/imagen4.jpeg";
        ImageIO.write(image, "jpeg", new File(path));
        con.disconnect();

        //Paso 2: con ese archivo de imagen utilizo el modelo de watson para identificar el tipo de predio
        IamOptions options = new IamOptions.Builder().apiKey("_0wueJL_fkmsl4h60P1Swha8gduliZVpZ5ROIM1zSqkO").build();

        VisualRecognition service = new VisualRecognition("2018-03-19", options);

        InputStream imagesStream = new FileInputStream("./data/imagen4.jpeg");
        ClassifyOptions classifyOptions = ((Builder) new ClassifyOptions.Builder())
                .imagesFile(imagesStream)
                .imagesFilename("imagen4.jpeg")
                .threshold((float) 0.6)
                .classifierIds(Arrays.asList("DefaultCustomModel_2139877187"))
                .build();
        ClassifiedImages result = service.classify(classifyOptions).execute();
        //System.out.println(result.toString());
        String respAnterior = result.getImages().get(0).getClassifiers().get(0).toString();
        //System.out.println("Este es el resultado antes de dividir el contenido" + respAnterior);
        String[] ans = result.getImages().get(0).getClassifiers().get(0).getClasses().get(0).toString().split(",");
        String[] info = ans[0].split(":");
        //System.out.println("Este es el primer elemento de la division " + info[1]);

        String tipoFinal = info[1].substring(2, info[1].length() - 1);
        //System.out.println("Este es el elemento final " + tipoFinal);

        String respuesta = "";
        if (tipoFinal.equals("Casas")) {
            respuesta = "Casa";
        } else if (tipoFinal.equals("Apartamentos")) {
            respuesta = "Apartamento";
        }
        return respuesta;
    }

    /*public static void main(String[] args) throws IOException {

        //4.6613926,-74.0507888
        //4.6618163,-74.0509704
        //40.720032,-73.988354
        //4.8672231,-74.0416982
        //4.86391,-74.0594804
        //4.864098,-74.0593876
        //4.8641722,-74.0593486
        //4.8644235,-74.0592279
        String res = ClasificacionImagenes.tipoPredio(4.8644235, -74.0592279);
        System.out.println("Este es el tipo del predio " + res);

        //String ans = ClasificacionImagenes.palabrasClaves("MANZANA I LOTE 3 ETAPA 1 SN  ");
        //System.out.println("Esta es el tipo del predio " + ans);
    }*/

}
