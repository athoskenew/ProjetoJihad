package teste;

import java.time.Instant;
import java.util.Date;
import util.FormataData;

public class Teste {

    public static void main(String[] args) {
        String dataAtual = new FormataData().dataAtualFormatada(Date.from(Instant.now()));
        String dataVencimento = "07/02/2019 23:59:59";
        String situacao = new String();
        System.out.println(dataAtual);
        String[] vetorDataAtual = dataAtual.split("/");
        String[] vetorDataAnoAtual = vetorDataAtual[2].split(" ");

        int diaAtual = Integer.parseInt(vetorDataAtual[0]);
        int mesAtual = Integer.parseInt(vetorDataAtual[1]);
        int anoAtual = Integer.parseInt(vetorDataAnoAtual[0]);


        System.out.println(dataVencimento);
        String[] vetorDataVencimento = dataVencimento.split("/");
        String[] vetorDataAnoVencimento = vetorDataVencimento[2].split(" ");

        int diaVencimento = Integer.parseInt(vetorDataVencimento[0]);
        int mesVencimento = Integer.parseInt(vetorDataVencimento[1]);
        int anoVencimento = Integer.parseInt(vetorDataAnoVencimento[0]);

        if (anoAtual == anoVencimento && mesAtual <= mesVencimento && diaAtual <= diaVencimento) {
            situacao = "Em dia";
        }else if(anoAtual < anoVencimento){
            situacao = "Em dia";
        }else{
            situacao = "Atrasado";
        }
       
        System.out.println(situacao);
    }

}
