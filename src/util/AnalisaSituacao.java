
package util;

import java.time.Instant;
import java.util.Date;

public class AnalisaSituacao {
    
    public String analisaSituacao(String dataVencimento){
        
        String dataAtual = new FormataData().dataAtualFormatada(Date.from(Instant.now()));
        String situacao = new String();
        String[] vetorDataAtual = dataAtual.split("/");
        String[] vetorDataAnoAtual = vetorDataAtual[2].split(" ");

        int diaAtual = Integer.parseInt(vetorDataAtual[0]);
        int mesAtual = Integer.parseInt(vetorDataAtual[1]);
        int anoAtual = Integer.parseInt(vetorDataAnoAtual[0]);

        String[] vetorDataVencimento = dataVencimento.split("/");
        String[] vetorDataAnoVencimento = vetorDataVencimento[2].split(" ");

        int diaVencimento = Integer.parseInt(vetorDataVencimento[0]);
        int mesVencimento = Integer.parseInt(vetorDataVencimento[1]);
        int anoVencimento = Integer.parseInt(vetorDataAnoVencimento[0]);

        if (anoAtual == anoVencimento && mesAtual == mesVencimento && diaAtual <= diaVencimento) {
            situacao = "Em dia";
        }else if(anoAtual < anoVencimento){
            situacao = "Em dia";
        }else if(anoAtual == anoVencimento && mesAtual < mesVencimento){
            situacao = "Em dia";
        }else{
            situacao = "Atrasado";
        }
        return situacao;
    }
    
    public static void main(String[] args) {
        AnalisaSituacao a = new AnalisaSituacao();
        System.out.println("eeebeeee "+a.analisaSituacao("01/03/2019 23:59:59"));
    }
}
