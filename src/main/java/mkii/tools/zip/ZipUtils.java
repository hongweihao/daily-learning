package mkii.tools.zip;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtils {
    public static void aa(String[] args) throws IOException {
        String fileZip = "D:\\Test\\Desktop.zip";
        File destDir = new File("D:\\Test\\");
        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            File newFile = newFile(destDir, zipEntry);
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }

    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }

    public static void main(String[] args) throws IOException, ZipException {
        String fileZip = "D:\\Test\\Desktop.zip";
        String destDir = "D:\\Test";

        ZipFile zipFile = new ZipFile(fileZip);
        zipFile.setFileNameCharset("gbk");

        if (zipFile.isEncrypted()){
            System.out.println("加密压缩包");
        }

        zipFile.extractAll(destDir);
    }



}
