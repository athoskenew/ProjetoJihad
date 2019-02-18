package util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class FormataData {

    public String dataAtualFormatada(Date data) {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = null;
        dataFormatada = format.format(data);
        return dataFormatada;
    }

    public String dataMensalParcelaFormatada(Date data, int numeroParcela) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date dataVenda;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + numeroParcela);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        dataVenda = calendar.getTime();
        String dataFormatada = null;
        dataFormatada = formato.format(dataVenda);
        return dataFormatada;
    }

    public String dataVencimentoFormatada(String data) {
        String[] dataFormatada = data.split(" ");
        return dataFormatada[0];
    }
    public static void main(String[] args) {
        String a = "12/12/1222 ff:ff:ff";
        FormataData f = new FormataData();
        System.out.println(f.dataVencimentoFormatada(a));
    }
}
