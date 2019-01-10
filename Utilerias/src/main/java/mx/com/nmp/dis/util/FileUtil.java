package mx.com.nmp.dis.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * Utilería para manejo de archivos
 * 
 * @author jgregorio
 * @date 2018-12-28
 *
 */
@Component
public class FileUtil {
    /**
     * Objeto para implementar las bit&aacute;coras
     */
    private static final Logger LOGGER    = LoggerFactory.getLogger(FileUtil.class);
    /**
     * Constante para indicar como inica generalmente una ruta de un archivo
     */
    public static final String  INIT_FILE = "file:";

    /**
     * Renombra un archivo
     * 
     * @param oldFile Nombre actual del archivo
     * @param newFile Nuevo nombre
     * @return TRUE si la operaci&oacute;n se realiz&oacute; sin errores FALSE si no se pudo realizar la acci&oacute;n
     */
    public Boolean executeRenameFile(final String oldFile, final String newFile) {
        Boolean exito = Boolean.FALSE;
        try {

            File fileOld = new File(oldFile);
            File fileNew = new File(newFile);

            if (fileOld.renameTo(fileNew)) {
                exito = Boolean.TRUE;
            }
        } catch (Exception e) {
            LOGGER.error("Error al renombrar el archivo {}", oldFile);
        }
        return exito;
    }

    /**
     * Agrega una nueva linea a un archivo, si el archivo no existe lo crea
     * 
     * @param file Archivo a modificar
     * @param line Linea que se agregar&aacute; al final
     * @return TRUE si la operaci&oacute;n se realiz&oacute; sin errores FALSE si no se pudo realizar la acci&oacute;n
     */
    public Boolean executeAppendToFile(final String file, final String linea) {
        Boolean     exito = Boolean.FALSE;
        PrintWriter out   = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            out.println(linea);
            exito = Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.error("No se pudo escribir en el archivo {}", e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return exito;
    }

    /**
     * Escribe un objeto en un archivo, si el archivo no existe lo crea
     * 
     * @param file   Archivo a crear
     * @param object Objeto para escribir
     * @return TRUE si la operaci&oacute;n se realiz&oacute; sin errores FALSE si no se pudo realizar la acci&oacute;n
     */
    public Boolean executeWriteObjectToFile(final String filePath, Object object) {

        try {
            FileOutputStream   fileOut   = new FileOutputStream(filePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(object);
            objectOut.close();

            LOGGER.info("Persistencia del objeto en archivo satisfactoria");
            return Boolean.TRUE;
        } catch (Exception ex) {
            LOGGER.error("No se pudo escribir en el archivo {}", ex.getMessage());
            return Boolean.FALSE;
        }
    }

    public Object executeReadObjectFromFile(final String filePath) {
        Object result;

        try {
            FileInputStream   file = new FileInputStream(filePath);
            ObjectInputStream in   = new ObjectInputStream(file);

            result = in.readObject();

            in.close();
            file.close();
        } catch (IOException ex) {
            LOGGER.error("No se pudo leer los datos del archivo {}", ex.getMessage());
            result = null;
        } catch (ClassNotFoundException ex) {
            LOGGER.error("No se pudo convertir el contenido del archivo en el archivo {}", ex.getMessage());
            result = null;
        }
        return result;
    }

    /**
     * Crea un nuevo archivo en la ruta indicada
     * 
     * @param file Ruta del archivo a crear
     * @return TRUE si la operaci&oacute;n se realiz&oacute; sin errores FALSE si no se pudo realizar la acci&oacute;n
     */
    public Boolean executeCreateFile(final String file) {
        Boolean resultado = Boolean.FALSE;
        try {

            File newFile = new File(file);

            if (newFile.createNewFile()) {
                resultado = Boolean.TRUE;
            }

        } catch (IOException e) {
            LOGGER.error("Error al crear un archivo {}", e.getMessage());
        }
        return resultado;
    }

    /**
     * Verifica la existencia de un archivo
     * 
     * @param file ruta del archivo a verificar
     * @return TRUE si la operaci&oacute;n se realiz&oacute; sin errores FALSE si no se pudo realizar la acci&oacute;n
     */
    public Boolean executeExistFile(final String file) {
        Boolean resultado = Boolean.FALSE;
        try {
            File fileExist = new File(file);
            if (fileExist.exists() && !fileExist.isDirectory()) {
                resultado = Boolean.TRUE;
            }
        } catch (Exception e) {
            LOGGER.error("Error al crear un archivo {}", e.getMessage());
        }
        return resultado;
    }

    /**
     * Elimina un archivo en la ruta indicada
     * 
     * @param file Ruta del archivo a crear
     * @return TRUE si la operaci&oacute;n se realiz&oacute; sin errores FALSE si no se pudo realizar la acci&oacute;n
     */
    public Boolean executeRemoveFile(String file) {
        Boolean resultado = Boolean.FALSE;
        try {
            File archivo = new File(file);

            if (archivo.delete()) {
                resultado = Boolean.TRUE;
            }
        } catch (Exception e) {
            LOGGER.error("No se pudo eliminar el archivo {}", e.getMessage());
            resultado = null;
        }
        return resultado;
    }

    /**
     * Obtiene el nombre de un archivo dentro de un ruta completa
     * 
     * @param file Ruta completa del archivo
     * @return Nombre del archivo
     */
    public String executeGetNameResource(String file) {
        int indice = file.lastIndexOf("/");
        return file.substring(indice + 1);
    }

    public String executeReadFileInOneLine(String filePath) {

        StringBuilder texto = null;
        try {
            File file = new File(filePath);

            BufferedReader br = new BufferedReader(new FileReader(file));

            texto = new StringBuilder();
            String st;
            while ((st = br.readLine()) != null) {
                texto.append(st);
            }

        } catch (Exception e) {
            LOGGER.error("No se pudo leer el archivo {}", e.getMessage());
            texto = null;
        }
        
        return texto.toString();
}

}
