import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class CharsetApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputText = scanner.nextLine();
        String outputText;
        Charset charset = Charset.forName("windows-1251");
        Charset charset2 = Charset.forName("koi8-r");
        //Charset charset2 = StandardCharsets.UTF_8;
        String inputFile = "inputFile.txt";
        String outputFile = "outputFile.txt";
        char[] buf = new char[100];
        int sz = 0;

        //Запись текста
        try (OutputStream os = new FileOutputStream(new File(inputFile))) {
            os.write(inputText.getBytes(charset));
            System.out.printf("Текст записан в '%s' файл в кодировке '%s' \n", inputFile, charset);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //чтение файла inputText.txt
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(inputFile), charset);
             OutputStream os2 = new FileOutputStream(new File(outputFile))
        ) {
            //считанный текст из inputFile
            while ((sz = isr.read(buf)) != -1) {
                System.out.println(Arrays.copyOf(buf, sz));

                //запись в outputFile
                outputText = String.valueOf(Arrays.copyOf(buf, sz)); //преобразует массив char в стоку (обрезает буффер по размеру)
                os2.write(outputText.getBytes(charset2));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
