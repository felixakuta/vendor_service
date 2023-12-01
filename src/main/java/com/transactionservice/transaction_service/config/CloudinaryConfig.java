package com.transactionservice.transaction_service.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

public class CloudinaryConfig {
//    @Value("${spring.cloudinary.api_secret}")
    private final   String CLOUDINARY_SECRET="Rqn_ruEgMuc0rMjN562UsT2zElo";
//    @Value("${spring.cloudinary.cloud_name}")
    private final String CLOUDINARY_USERNAME="dsajgjipb";
//    @Value("${spring.cloudinary.api_key}")
    private final  String CLOUDINARY_KEY="966992583741261";

private Map<String,String> hash(){
    System.out.println(CLOUDINARY_KEY);
    Map<String,String> stringStringMap = new HashMap<>();
    stringStringMap.put("cloud_name", CLOUDINARY_USERNAME);
            stringStringMap.put("api_key", CLOUDINARY_KEY);
            stringStringMap.put("api_secret", CLOUDINARY_SECRET);
    return stringStringMap;
}

    public String upload(MultipartFile file,Long id) throws IOException {
        Map<String,String> config =hash();
        Cloudinary cloudinary = new Cloudinary(config);

        cloudinary.uploader().upload(file.getBytes(),ObjectUtils.asMap("public_id",UUID.randomUUID().toString()));
        return cloudinary.url().transformation(new Transformation().width(200).height(200).crop("file")).generate(UUID.randomUUID().toString());
    }
}
