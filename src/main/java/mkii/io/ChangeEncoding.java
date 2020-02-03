package mkii.io;

import org.apache.commons.io.input.BOMInputStream;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

public class ChangeEncoding {
    public static void main(String[] args) throws Exception {

        String inputString = readFileWithoutBom("D:\\work\\Kaiser_Shipment_Milestone_Update_Report_CSV20200117010005933_MB202001170108320001.csv");

        String res = new String(inputString.getBytes(), StandardCharsets.ISO_8859_1);

        //LocalFileUtil.writeToFileWithEncoding("C:\\Users\\HONGKA\\Downloads\\kaiser.csv", res, "ISO-8859-1");

        //File file = new File("C:\\Users\\HONGKA\\Downloads\\k.csv");
        File file = new File("D:\\work\\l.csv");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        bufferedWriter.write(res);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    /**
     * @param source      full source file name
     * @param dest        full dest file name
     * @param fromCharSet source file encoding
     * @param toCharSet   dest file encoding
     * @throws Exception
     */
    public static void changeEncoding(String source, String dest, String fromCharSet, String toCharSet) throws Exception {
        String content = readFileWithEncoding(source, fromCharSet);
        saveFileWithEncoding(dest, content, toCharSet);
    }

    public static String readFileWithEncoding(String source, String encoding) throws IOException {
        File file = new File(source);
        if (!Charset.isSupported(encoding)) {
            throw new UnsupportedCharsetException(encoding);
        }

        InputStream inputStream = null;
        InputStreamReader reader = null;
        String str;
        try {
            inputStream = new FileInputStream(file);
            reader = new InputStreamReader(inputStream, encoding);
            char[] chs = new char[(int) file.length()];
            reader.read(chs);
            str = new String(chs).trim();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return str;
    }

    public static void saveFileWithEncoding(String dest, String content, String encoding) throws IOException {
        File file = new File(dest);

        if (!file.exists()) {
            file.createNewFile();
        }
        if (!Charset.isSupported(encoding)) {
            throw new UnsupportedCharsetException(encoding);
        }

        OutputStream outputStream = null;
        OutputStreamWriter outWrite = null;
        try {
            outputStream = new FileOutputStream(file);
            outWrite = new OutputStreamWriter(outputStream, encoding);
            outWrite.write(content);
        } finally {
            assert outWrite != null;
            outWrite.close();
        }
    }

    // read file content. depends common-io jar
    public static String readFileWithoutBom(String source) throws IOException {
        File file = new File(source);

        InputStream inputStream = null;
        InputStreamReader reader = null;
        String str;
        try {
            inputStream = new FileInputStream(file);
            // 使用commons-io去除bom头
            BOMInputStream bomInputStream = new BOMInputStream(inputStream);
            reader = new InputStreamReader(bomInputStream);

            char[] chs = new char[(int) file.length()];
            reader.read(chs);
            str = new String(chs).trim();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return str;
    }

}
