package adtrepaso.services;

import java.sql.Date;

public interface Iapi {

    String pedidosPorCliente(String identificacion);

    String pedidosPorFecha(Date fecha);

    String pedidosPorPrecio(double precioMinimo, double precioMaximo);

}
