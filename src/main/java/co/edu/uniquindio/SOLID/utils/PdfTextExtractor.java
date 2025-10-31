package co.edu.uniquindio.SOLID.utils;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PdfTextExtractor {

    public static void main(String[] args) {
        String defaultInput = "C:\\Users\\Usuario\\Downloads\\Parcial2N.pdf";
        String defaultOutput = "src/main/resources/Parcial2N.txt";

        String inputPath = args != null && args.length > 0 && args[0] != null && !args[0].isBlank()
                ? args[0]
                : defaultInput;
        String outputPath = args != null && args.length > 1 && args[1] != null && !args[1].isBlank()
                ? args[1]
                : defaultOutput;

        System.out.println("Extrayendo texto desde: " + inputPath);
        System.out.println("Guardando texto en: " + outputPath);

        try {
            extractText(inputPath, outputPath);
            System.out.println("Extracción completada correctamente.");
        } catch (IOException e) {
            System.err.println("Error extrayendo texto del PDF: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void extractText(String pdfPath, String txtPath) throws IOException {
        File pdfFile = new File(pdfPath);
        if (!pdfFile.exists()) {
            throw new IOException("No existe el archivo PDF: " + pdfFile.getAbsolutePath());
        }

        try (PDDocument document = Loader.loadPDF(pdfFile)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            Path outPath = Paths.get(txtPath).toAbsolutePath();
            Files.createDirectories(outPath.getParent());
            try (FileOutputStream fos = new FileOutputStream(outPath.toFile())) {
                fos.write(text.getBytes(StandardCharsets.UTF_8));
            }
        }
    }
}
