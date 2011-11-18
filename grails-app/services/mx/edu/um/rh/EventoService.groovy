package mx.edu.um.rh

import java.math.*
import java.text.*
import org.apache.poi.hssf.usermodel.*
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import groovy.sql.*

class EventoService {

    def dataSource

    def subeEmpleados(archivo) {
    
        def claves = []
        
        POIFSFileSystem fs = new POIFSFileSystem(new java.io.ByteArrayInputStream(archivo.bytes))
        HSSFWorkbook workbook = new HSSFWorkbook(fs)

        def numeroDePaginas = workbook.getNumberOfSheets()

        for(int idx = 0; idx < numeroDePaginas; idx++) {
            log.debug "############ Pagina $idx"
            HSSFSheet sheet = workbook.getSheetAt(idx)
            def rows = sheet.getPhysicalNumberOfRows()
            log.debug "rows > " + rows
            for (int r = 0; r < rows; r++) {
                HSSFRow row = sheet.getRow(r)
                if (row) {
                    def clave = row.getCell(0).toString().split("\\.")
                    log.debug "clave > " + clave[0]
                    if(clave) claves << clave[0]
                }
            }
        }
        return claves
    }
}

