package com.example.Util;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.software.os.OperatingSystem;

public class PerformanceMonitor {

    private static final Logger logger = LogManager.getLogger( "performance" );

    private String NombreProceso;
    private String MensajeLog;

    private long TiempoInicio; // Tiempo de inicio del monitoreo
    private long TiempoFinalizado; // Tiempo de finalización del monitoreo

    private double ConsumoCpuInicio; // Consumo de CPU al inicio del monitoreo
    private double ConsumoCpuFinal; // Consumo de CPU al final del monitoreo

    private long UsoMemoriaInicial; // Uso de memoria de la JVM al inicio del monitoreo
    private long UsoMemoriaFinal; // Uso de memoria de la JVM al final del monitoreo

    // Metricas OSHI
    private SystemInfo InformacionSistema; // Información del sistema usando OSHI
    private CentralProcessor Procesador; // Información del procesador
    private GlobalMemory MemoriaFisica; // Información de la memoria física
    private OperatingSystem Os; // Información del sistema operativo
    private long[] TicksIniciales; // Array de ticks iniciales del procesador

    public PerformanceMonitor( String nombreProceso ){

        NombreProceso = nombreProceso;
        MensajeLog = "";

        // Inicializar OSHI
        InformacionSistema = new SystemInfo();
        Procesador = InformacionSistema.getHardware().getProcessor();
        MemoriaFisica = InformacionSistema.getHardware().getMemory();
        Os = InformacionSistema.getOperatingSystem();

    }

    public void inicio(){

        // Registrar el tiempo de inicio del monitoreo
        TiempoInicio = System.currentTimeMillis();
        // Calcular el uso de memoria inicial de la JVM
        UsoMemoriaInicial = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        // Obtener los ticks iniciales del procesador
        TicksIniciales = Procesador.getSystemCpuLoadTicks();
        // Calcular el consumo de CPU inicial (promedio del sistema)
        ConsumoCpuInicio = Procesador.getSystemCpuLoadBetweenTicks(TicksIniciales)  * 100;

        MensajeLog += "\n === PROCESO [ " + NombreProceso + " ] === ";
        MensajeLog += "\n - Uso Memoria Inicio : " + Math.round(bytesToMegabytes(UsoMemoriaInicial)) + " MB";
        MensajeLog += "\n - Consumo CPU Inicio : " + String.format( "%.2f", ConsumoCpuInicio ) + " %";

    }

    public void finalizado(){

        Runtime runtime = Runtime.getRuntime();

        long diferenciaTiempo = 0;
        long diferenciaMemoria = 0;
        double diferenciaCpu = 0.0;

        long memoriaFisicaUsada = 0; // Memoria física usada por el sistema
        long tiempoCpuProceso = 0; // Tiempo de CPU usado por el proceso actual
        int numeroHilos = 0; // Número de hilos activos del proceso actual

        // Registrar el tiempo de finalización del monitoreo
        TiempoFinalizado = System.currentTimeMillis();
        // Calcular el uso de memoria final de la JVM
        UsoMemoriaFinal = runtime.totalMemory() - runtime.freeMemory();
        // Calcular el consumo de CPU final (promedio del sistema)
        ConsumoCpuFinal = Procesador.getSystemCpuLoadBetweenTicks(TicksIniciales) * 100;

        // Calcular las diferencias entre las métricas iniciales y finales
        diferenciaTiempo = TiempoFinalizado - TiempoInicio;
        diferenciaMemoria = UsoMemoriaFinal - UsoMemoriaInicial;
        diferenciaCpu = ConsumoCpuFinal - ConsumoCpuInicio;

        MensajeLog += "\n - Uso Memoria Final : " + Math.round(bytesToMegabytes(UsoMemoriaFinal)) + " MB";
        MensajeLog += "\n - Consumo CPU Final : " + String.format( "%.2f", ConsumoCpuFinal ) + " %";
        MensajeLog += "\n - Incremento Memoria : " + Math.round(bytesToMegabytes(diferenciaMemoria)) + " MB";
        MensajeLog += "\n - Incremento CPU : " + String.format( "%.2f", diferenciaCpu ) + " %";
        MensajeLog += "\n - Tiempo Ejecucion : " + diferenciaTiempo + " ms";
        MensajeLog += "\n - Memoria Disponible JVM : " + Math.round(bytesToMegabytes(runtime.freeMemory())) + " MB";
        MensajeLog += "\n =============================== \n";


        // Métricas adicionales
        memoriaFisicaUsada = MemoriaFisica.getTotal() - MemoriaFisica.getAvailable(); // Calcular memoria física usada
        tiempoCpuProceso = Os.getProcess(Os.getProcessId()).getKernelTime() + Os.getProcess(Os.getProcessId()).getUserTime(); // Calcular tiempo de CPU usado por el proceso
        numeroHilos = Os.getProcess(Os.getProcessId()).getThreadCount(); // Obtener el número de hilos activos

        // Agregar métricas adicionales al log
        MensajeLog += "\n - Memoria Física Total: " + Math.round(bytesToMegabytes(MemoriaFisica.getTotal())) + " MB";
        MensajeLog += "\n - Memoria Física Usada: " + Math.round(bytesToMegabytes(memoriaFisicaUsada)) + " MB";
        MensajeLog += "\n - Número de Hilos Activos: " + numeroHilos;
        MensajeLog += "\n - Tiempo de CPU Usado por el Proceso: " + tiempoCpuProceso + " ms \n\n";

        logger.info( MensajeLog );

    }

    private double bytesToMegabytes(long bytes) { return bytes / (1024.0 * 1024.0); }

}
