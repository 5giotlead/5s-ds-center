package com.fgiotlead.ds.center.util;

import com.fgiotlead.ds.center.model.entity.SignageFileEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Slf4j
public class FileUtils {

    @Value("${rsocket.file.buffer.size}")
    private int bufferSize;
    private final static String ROOT_PATH = "resource/";
    private final static String BACKUP_PATH = "backup/";

    public static SignageFileEntity upload(MultipartFile file, String access) throws NoSuchAlgorithmException {
        String originalName = file.getOriginalFilename();
        if (originalName != null) {
            String fileName = originalName.substring(0, originalName.lastIndexOf('.'));
            String suffixName = originalName.substring(originalName.lastIndexOf('.') + 1).toLowerCase();
            String id = UUID.randomUUID().toString();
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(ROOT_PATH + access + "/" + id + "." + suffixName);
                Files.createDirectories(path.getParent());
                Files.write(path, bytes);
                return SignageFileEntity
                        .builder()
                        .id(UUID.fromString(id))
                        .originalName(fileName)
                        .mimeType(suffixName)
                        .access(access)
                        .hash(hash(file.getBytes()))
                        .build();
            } catch (IOException e) {
                log.warn(e.getMessage());
            }
        }
        return null;
    }

    public static void delete(SignageFileEntity file) throws IOException {
        String filename = file.getAccess() + "/" + file.getId() + "." + file.getMimeType();
        Path path = Paths.get(ROOT_PATH + filename);
        backup(path, filename);
        Files.delete(path);
    }


    public static void restore(SignageFileEntity file) throws IOException {
        String filename = file.getAccess() + "/" + file.getId() + "." + file.getMimeType();
        Path backupPath = Paths.get(BACKUP_PATH + filename);
        Path resourcePath = Paths.get(ROOT_PATH + filename);
        Files.createDirectories(resourcePath.getParent());
        Files.copy(backupPath, resourcePath);
    }

    private static void backup(Path path, String filename) throws IOException {
        Path backupPath = Paths.get(BACKUP_PATH + filename);
        Files.createDirectories(backupPath.getParent());
        Files.copy(path, backupPath);
    }


    public static Flux<ByteBuffer> getFileStream(SignageFileEntity signageFile) {
        String filePath = signageFile.getAccess();
        String fileName = signageFile.getId() + "." + signageFile.getMimeType();
        Path path = Paths.get(ROOT_PATH + filePath + "/" + fileName);
        return Flux.create(fluxSink -> {
            try (ReadableByteChannel rbc = Files.newByteChannel(path)) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(8192);
                while (rbc.read(byteBuffer) != -1) {
                    byteBuffer.flip();
                    fluxSink.next(byteBuffer);
                    byteBuffer = ByteBuffer.allocate(8192);
//                    ByteBuffer newBuffer = ByteBuffer.wrap(byteBuffer.array().clone());
//                    newBuffer.limit(byteBuffer.limit());
//                    fluxSink.next(newBuffer);
//                    byteBuffer.clear();
                }
                fluxSink.complete();
            } catch (IOException e) {
                fluxSink.error(e);
                log.warn(e.getMessage());
            }
        });
    }

    private static String hash(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
        byte[] hashBytes = messageDigest.digest(bytes);
        return new String(Hex.encode(hashBytes));
    }
}
