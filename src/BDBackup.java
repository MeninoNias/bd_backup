import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BDBackup {

    // Constantes da classe
    private static String VERSION = "4.0.3";
    private static String SEPARATOR = File.separator;
    private static String POST_PATH =
            "C:" + SEPARATOR +
                    "Program Files" + SEPARATOR +
                    "PostgreSQL" + SEPARATOR +
                    "12" + SEPARATOR +
                    "bin" + SEPARATOR;


    private static String PRESENTATION =
            "==========================================================\n" +
                    "  Backup do banco de dados MySQL - Versao " + VERSION + "\n" +
                    "  Autor: Marcelo Carvalho Pinto da Cunha\n\n" +
                    "  Desenvolvido em 07/09/2009\n\n" +
                    "  MarcWare Software, 2009-2012\n" +
                    "==========================================================\n\n";

    // Lista dos bancos de dados a serem "backupeados"; se desejar adicionar mais,
    // basta colocar o nome separado por espaços dos outros nomes

    private static String DATABASES ="sertao_protocolo";
    private List<String> dbList = new ArrayList<String>();

    public BDBackup() {

        String command = POST_PATH + "pg_dump.exe";
        String[] databases = DATABASES.split(" ");

        Collections.addAll(dbList, databases);

        // Mostra apresentação
        System.out.println(PRESENTATION);
        System.out.println("Iniciando backups...\n\n");


        // Contador
        int i = 1;

        // Tempo
        long time1, time2, time;


        // Início
        time1 = System.currentTimeMillis();

        String path = "C:"+SEPARATOR+"Users"+SEPARATOR+"anani"+SEPARATOR+"Desktop"+SEPARATOR+"codes"+SEPARATOR+"bd_backup"+SEPARATOR+ "sertao_protocolo" + ".backup";


        for (String dbName : dbList) {
            ProcessBuilder pb = new ProcessBuilder(
                    command,
                    "-h",
                    "localhost",
                    "-U",
                    "postgres",
                    "-Ft",
                    "-p",
                    "5432",
                    "-f",
                    path,
                    dbName);


            try {
                System.out.println("Backup do banco de dados (" + i + "): " + dbName + " ...");
                pb.start();

            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
        }
        // Fim
        time2 = System.currentTimeMillis();

        // Tempo total da operação
        time = time2 - time1;

        // Avisa do sucesso
        System.out.println("\nBackups realizados com sucesso.\n\n");
        System.out.println("Tempo total de processamento: " + time + " ms\n");
        System.out.println("Finalizando...");


        try {
            // Paralisa por 2 segundos
            Thread.sleep(2000);
        } catch (Exception ignored) {
        }


        // Termina o aplicativo

        System.exit(0);

    }

}
