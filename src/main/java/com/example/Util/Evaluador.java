package com.example.Util;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

public class Evaluador {

    public static String medirPesoObjeto(Object o) throws Exception{

        try {

            String mensajeCalculo = "";

            //System.out.println("\n" + ClassLayout.parseInstance(p1).toPrintable());

            /*System.out.println("\n=== Layout Interno ===");
            // Esto muestra la estructura de la clase y su peso base.
            System.out.println(ClassLayout.parseInstance(p1).toPrintable());

            System.out.println("\n=== Layout con Referencias ===");
            // Te dice el tamaño total en memoria (el objeto + todos los referenciados).
            System.out.println(GraphLayout.parseInstance(p1).toPrintable());

            System.out.println("\n=== Tamanio total en memoria ===");
            System.out.println(GraphLayout.parseInstance(p1).totalSize() + " bytes");*/

            mensajeCalculo += "\n|====== Layout Interno ======|\n" + ClassLayout.parseInstance(o).toPrintable();

            mensajeCalculo += "\n|====== Layout con Referencias ======|\n" + GraphLayout.parseInstance(o).toPrintable();

            mensajeCalculo += "\n|====== Tamanio total en memoria ======|\n" + GraphLayout.parseInstance(o).totalSize() + " bytes";

            return  mensajeCalculo;

        } catch (Exception e) {
            throw new Exception(e);
        }

    }

}
