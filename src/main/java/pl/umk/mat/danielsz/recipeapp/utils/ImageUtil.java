package pl.umk.mat.danielsz.recipeapp.utils;

import org.springframework.web.multipart.MultipartFile;
import pl.umk.mat.danielsz.recipeapp.exceptions.FileEncodingException;

import java.io.IOException;
import java.util.Base64;

public class ImageUtil {

    public String encodeFileToBase64(MultipartFile image) {
        byte[] imageByteArr = new byte[0];

        try {
            imageByteArr = image.getBytes();
        } catch (IOException e) {
            throw new FileEncodingException("Processing image failed.");
        }

        return Base64.getEncoder().encodeToString(imageByteArr);
    }
}
