package com.onboarding.simulator.service;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImageFactory {
    private static final Logger log = LoggerFactory.getLogger(ImageFactory.class);

    private static final String DATA_IMAGE_PREFIX = "data:image/png;base64,";

    @Value("${simulator.user.avatar}")
    private  String imagemFotoFace;

    @Value("${simulator.user.cnh.frente}")
    private  String imagemDocumentoFrente;


    public String gerarImagemFace()  {
        return DATA_IMAGE_PREFIX+ gearImagem(this.imagemFotoFace) ;
    }    

    public String gerarImagemDocumentoFrente()  {
        return DATA_IMAGE_PREFIX + gearImagem(this.imagemDocumentoFrente) ;
    }    

    private String gearImagem(String imagemBase)  {
        byte[] imageBytes = Base64.getDecoder().decode(imagemBase);
        BufferedImage inputImage;
        try {
            inputImage = ImageIO.read(new java.io.ByteArrayInputStream(imageBytes));
        } catch (IOException e) {
            log.error(imagemBase + " - Erro ao ler a imagem: " + e.getMessage());
            return null;
        }

        // Gerar uma cor pastel aleatória
        Color pastelColor = generateRandomPastelColor();

        // Criar um novo BufferedImage para aplicar a camada de cor pastel
        BufferedImage outputImage = new BufferedImage(
                inputImage.getWidth(),
                inputImage.getHeight(),
                BufferedImage.TYPE_INT_ARGB
        );

        // Obter o gráfico da nova imagem
        Graphics2D g2d = outputImage.createGraphics();

        // Desenhar a imagem original
        g2d.drawImage(inputImage, 0, 0, null);

        // Definir a cor pastel com transparência
        g2d.setColor(new Color(pastelColor.getRed(), pastelColor.getGreen(), pastelColor.getBlue(), 128)); // 50% de opacidade
        g2d.fillRect(0, 0, inputImage.getWidth(), inputImage.getHeight());

        // Liberar recursos do gráfico
        g2d.dispose();

        // Converter a nova imagem para Base64
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(outputImage, "png", outputStream);
        } catch (IOException e) {
            log.error(imagemBase + " - Erro ao escrever a imagem: " + e.getMessage());
            return null;
        }
        byte[] modifiedImageBytes = outputStream.toByteArray();
        return Base64.getEncoder().encodeToString(modifiedImageBytes);
    }

    // Método para gerar uma cor pastel aleatória
    private static Color generateRandomPastelColor() {
        Random random = new Random();
        int red = (random.nextInt(128) + 127); // Valor entre 127 e 255
        int green = (random.nextInt(128) + 127);
        int blue = (random.nextInt(128) + 127);
        return new Color(red, green, blue);
    }

}