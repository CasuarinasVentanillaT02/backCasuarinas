package com.systems.service.impl;

import com.systems.repository.MantLectRepository;
import com.systems.service.ReportesService;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class ReportesServiceImpl implements ReportesService {

    private MantLectRepository mantLectRepository;

    @Autowired
    public void setMantLectRepository(
            MantLectRepository mantLectRepository
    ) {
        this.mantLectRepository = mantLectRepository;
    }

    @Override
    public byte[] reporteMantLect01(Integer idTorre, Integer idPeriodo,Connection conexion) throws Exception {
        InputStream reportStream = this.getClass().getResourceAsStream("/reportesPdf/PdfTorrePeriodo.jasper");

        List<Object[]> cabeceraResult = mantLectRepository.fRepMantLectCabFoot(idTorre, idPeriodo);
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("pIdTorre", idTorre);
        parametros.put("pIdPeriodo", idPeriodo);
        parametros.put("pDeTorre", cabeceraResult.get(0)[0]);
        parametros.put("pDePeriodo", cabeceraResult.get(0)[1]);
        parametros.put("pDeLecturaAnt", cabeceraResult.get(0)[2]);
        parametros.put("pDeLecturaAct", cabeceraResult.get(0)[3]);
        parametros.put("pImServicioAgua", (BigDecimal) cabeceraResult.get(0)[4]);
        parametros.put("pImMantenimiento", (BigDecimal)cabeceraResult.get(0)[5]);
        parametros.put("pNuM3Recibo", (BigDecimal)cabeceraResult.get(0)[6]);
        parametros.put("pDeBorrador", cabeceraResult.get(0)[7]);
        parametros.put("pNuCantDepas", BigDecimal.valueOf((Long) cabeceraResult.get(0)[8]));
        parametros.put("pNuM3Consumo", (BigDecimal)cabeceraResult.get(0)[9]);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, parametros, conexion);
        /*new JREmptyDataSource()*/

        return JasperExportManager.exportReportToPdf(jasperPrint);

        /*try {
            final File file = ResourceUtils.getFile("classpath:reportesPdf/TorrePeriodoMant.jasper");
            final JasperReport report = (JasperReport) JRLoader.loadObject(file);
            final HashMap<String, Object> parameters = new HashMap<>();

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

            byte[] reporte = JasperExportManager.exportReportToPdf(jasperPrint);
            return reporte;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReportesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;*/
    }

}
