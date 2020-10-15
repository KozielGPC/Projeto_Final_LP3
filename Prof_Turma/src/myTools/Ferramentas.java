package myTools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author radames
 */
public class Ferramentas {

    public String doubleFormatado(double x) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
        return decimalFormat.format(x);
    }

    public List<String> abrirArquivo(String caminho) {
        List<String> texto = new ArrayList<String>();
        try {
            //OpenFile
            FileReader arquivo = new FileReader(caminho);
            BufferedReader conteudoDoArquivo = new BufferedReader(arquivo);
            String linha = conteudoDoArquivo.readLine();
            while (linha != null) {
                texto.add(linha);
                linha = conteudoDoArquivo.readLine();
            }
            conteudoDoArquivo.close();
        } catch (Exception e) {//Catch exception if any
            texto = null;
            System.err.println("Erro: " + e.getMessage());
        }
        return texto;
    }

    public int salvarArquivo(String caminho, List<String> texto) {
        try {
            // Create file 
            FileWriter arquivo = new FileWriter(caminho);
            BufferedWriter conteudoDoArquivo = new BufferedWriter(arquivo);
            for (int i = 0; i < texto.size(); i++) {
                conteudoDoArquivo.write(texto.get(i) + System.getProperty("line.separator")); // 
            }
            conteudoDoArquivo.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
            return 1; //houve erro
        }
        return 0;
    }
    
        public int LimparArquivo(String caminho, String texto) {
        try {
            // Create file 
            FileWriter arquivo = new FileWriter(caminho);
            BufferedWriter conteudoDoArquivo = new BufferedWriter(arquivo);
            conteudoDoArquivo.write(texto); // 
            
            conteudoDoArquivo.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
            return 1; //houve erro
        }
        return 0;
    }

}
