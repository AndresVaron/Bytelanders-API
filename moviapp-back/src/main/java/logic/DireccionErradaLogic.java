/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import entities.ClienteEntity;
import exceptions.BusinessLogicException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

import persistence.ClientePersistence;

/**
 *
 * @author ByteLanders
 */
@Stateless
public class DireccionErradaLogic {

    @Inject
    ClientePersistence clientePersistence;

    @Inject
    BusquedaPersistence busquedaPersistence;

    private final Logger LOGGER = Logger.getLogger(GeoActualizacionLogic.class.getName());

    public boolean predioEnRangoCaja(String addrrPredio, String addrrCaja, String ciudad, String depto) throws BusinessLogicException {

        Coords boxCoords = addressCoords(formatAddress(addrrCaja), formatDept(depto, ciudad), formatCity(ciudad));
        Coords instalCoords = addressCoords(formatAddress(addrrPredio), formatDept(depto, ciudad), formatCity(ciudad));

        if (boxCoords == null || instalCoords == null) {
            return false;
        }

        return distance(boxCoords.getLat(), instalCoords.getLat(), boxCoords.getLng(), instalCoords.getLng(), 0, 0) >= 150.0;

    }

    public boolean predioEnRangoCaja(String addrrPredio, Coords boxCoords, String ciudad, String depto) throws BusinessLogicException {

        Coords instalCoords = addressCoords(formatAddress(addrrPredio), formatDept(depto, ciudad), formatCity(ciudad));

        if (instalCoords == null) {
            return false;
        }

        return distance(boxCoords.getLat(), instalCoords.getLat(), boxCoords.getLng(), instalCoords.getLng(), 0, 0) >= 150.0;

    }

    private String formatDept(String dept, String city) {
        return (city.equals("Bogota")) ? "" : ",+" + dept;
    }

    private String formatCity(String city) {
        return ",+" + city;
    }

    private String formatAddress(String oAddress) throws BusinessLogicException {

        String[] partes = oAddress.split(" ");

        String newS = "";
        if (partes[0].equals("Calle")) {
            newS += "Cl.";
        } else if (partes[0].equals("Carrera")) {
            newS += "Cra.";
        } else if (partes[0].equals("Diagonal")) {
            newS += "Dg.";
        } else if (partes[0].equals("Transversal")) {
            newS += "Tv.";
        } else {
            throw new BusinessLogicException("El formato de dirección no se reconoció");
        }

        String[] endNums = partes[2].split("-");
        String endNum = endNums[0] + endNums[1];

        String mainNum = partes[1].split("#")[0];

        newS += ("+" + mainNum + "+" + endNum);

        return newS;
    }

    public Coords addressCoords(String address, String dept, String city) {

        HttpURLConnection con = null;

        Coords coords = new Coords();

        try {
            URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + city + dept + "&key=AIzaSyCyhpCQfv5n_EeBBFTBxZMxSD633ul9I2o");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setInstanceFollowRedirects(true);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            boolean expectingCoords = false;
            boolean latDone = false;
            boolean lngDone = false;

            while ((inputLine = in.readLine()) != null) {
                if (inputLine.contains("location")) {
                    expectingCoords = true;
                }
                if (expectingCoords && inputLine.contains("lat")) {
                    latDone = true;
                    double lat = Double.parseDouble(inputLine.substring(0, inputLine.length() - 1).split(":")[1]);
                    coords.setLat(lat);
                }
                if (expectingCoords && inputLine.contains("lng")) {
                    lngDone = true;
                    double lng = Double.parseDouble(inputLine.substring(0, inputLine.length() - 1).split(":")[1]);
                    coords.setLng(lng);
                }
                if (latDone && lngDone) {
                    break;
                }

            }

        } catch (IOException ioe) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return coords;
    }

    /**
     * Calcula distancia entre dos coordenadas. Tomado de
     * https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude-what-am-i-doi
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     *
     * @returns Distance in Meters
     */
    private double distance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    /*
    @Inject
    private ClientePersistence clientePersistence;

    @Inject
    private BusquedaPersistence busquedaPersistence;

    public BusquedaEntity calcularDireccion(String direccion) {
        BusquedaEntity busqueda = new BusquedaEntity();
        busqueda = busquedaPersistence.create(busqueda);
        LOGGER.log(Level.INFO, "Saliendo del proceso de actualizar calcular Direccion");
        return busqueda;
    }

    public List<BusquedaEntity> getBusquedas() {
        LOGGER.info("Inicia proceso de consultar todas las busquedas");
        List<BusquedaEntity> busquedas = busquedaPersistence.findAll();
        LOGGER.info("Termina proceso de consultar todas las busquedas");
        return busquedas;
    }*/
    public void asignarErrados() throws BusinessLogicException {
        List<ClienteEntity> clientes = clientePersistence.findAll();
        for (Iterator<ClienteEntity> iterator = clientes.iterator(); iterator.hasNext();) {
            ClienteEntity next = iterator.next();

            //Si la dirección ya contiene las coordenadas, aproveche.
            if (next.getDireccion().contains("lat:") && next.getDireccion().contains("lon:")) {

                String[] s = next.getDireccion().split(" ");

                next.setLatitud(s[next.getDireccion().length() - 2]);
                next.setLongitud(s[next.getDireccion().length() - 1]);
                //ACTUALIZAR PREDIO
                clientePersistence.update(next);
                continue;
            }

            boolean enrango = false;

            if (next.getLongitud() != null && next.getLatitud() != null) {
                Coords coord = new Coords(Double.parseDouble(next.getLatitud()),
                        Double.parseDouble(next.getLongitud()));

                enrango = predioEnRangoCaja(next.getDireccion(), coord, next.getDepartamento(),
                        next.getLocalidad());
            } else {

                enrango = predioEnRangoCaja(next.getDireccion(), next.getIpModem(),
                        next.getLocalidad(), next.getDepartamento());
            }

            if (!enrango) {
                next.setErrada(true);

                //TODO COMPRAR Y ACTUALIZAR
                //LUEGO SE CREA ENTIDAD DE BUSQUEDA
            } else {
                Coords coords = addressCoords(next.getDireccion(), next.getDepartamento(), next.getLocalidad());
                next.setLongitud("" + coords.getLng());
                next.setLatitud("" + coords.getLat());
                next.setErrada(false);

                //TODO, debería determinar tipo de predio.
            }
            //update
            clientePersistence.update(next);

        }
    }
}
