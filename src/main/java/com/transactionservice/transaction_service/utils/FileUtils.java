package com.transactionservice.transaction_service.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.transactionservice.transaction_service.config.CloudinaryConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


public class FileUtils {
    @Value("${spring.cloudinary.api_secret}")
    private  String CLOUDINARY_SECRET;
    @Value("${spring.cloudinary.cloud_name}")
    private  String CLOUDINARY_USERNAME;
    @Value("${spring.cloudinary.api_key}")
    private  String CLOUDINARY_KEY;
    public  String fileConversion(MultipartFile file,Long id) throws IOException {
     Cloudinary cloudinary= new Cloudinary(ObjectUtils.asMap(
             "cloud_name", CLOUDINARY_USERNAME,
             "api_key", CLOUDINARY_KEY,
             "api_secret", CLOUDINARY_SECRET));
        cloudinary.uploader().upload(file.getBytes(),ObjectUtils.asMap("picture","image_id"+id));
        return cloudinary.url().transformation(new Transformation().width(200).height(200).crop("file")).generate("file_id");
    }
}
